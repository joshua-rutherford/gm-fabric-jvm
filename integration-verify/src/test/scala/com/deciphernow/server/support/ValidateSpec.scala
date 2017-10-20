package com.deciphernow.server.support

import java.io.File
import scala.io.Source
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ValidateSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  // fixme: the HOSTNAME once fixed will have incorrect HOSTNAME etc on every computer thus failing.

  val expectedAnswers : List[String] = List(
    "::: /tmp/report-1.txt:::Announce HOSTNAME=[]Announce adminPort=[9990]Announce httpPort=[8888]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:9990]Bind httpPort=[:8888]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-2.txt:::Announce HOSTNAME=[]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:9990]Bind httpPort=[:8888]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-3.txt:::Announce HOSTNAME=[]Announce adminPort=[9990]Announce httpPort=[8888]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:9990]Bind httpPort=[:8888]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-4.txt:::Announce HOSTNAME=[]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-5.txt:::Announce HOSTNAME=[]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-6.txt:::Announce HOSTNAME=[]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-7.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-8.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-9.txt:::Announce HOSTNAME=[Not.Pickles]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-10.txt:::Announce HOSTNAME=[Not.Pickles]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-11.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[5555]Announce httpPort=[5556]Announce httpsPort=[5557]Announce thriftPort=[5558]Bind adminPort=[:9990]Bind httpPort=[:8888]Bind httpsPort=[:8999]Bind thriftPort=[:9090]",
    "::: /tmp/report-12.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[5555]Announce httpPort=[5556]Announce httpsPort=[5557]Announce thriftPort=[5558]Bind adminPort=[:10002]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:10003]",
    "::: /tmp/report-13.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[5555]Announce httpPort=[5556]Announce httpsPort=[5557]Announce thriftPort=[5558]Bind adminPort=[:10002]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:10003]",
    "::: /tmp/report-14.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[5555]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:10002]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]",
    "::: /tmp/report-15.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[5555]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:10002]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]",
    "::: /tmp/report-16.txt:::Announce HOSTNAME=[Pickles]Announce adminPort=[25000]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:10002]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]",
    "::: /tmp/report-17.txt:::Announce HOSTNAME=[]Announce adminPort=[33333]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:33333]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]",
    "::: /tmp/report-18.txt:::Announce HOSTNAME=[WOMBAT]Announce adminPort=[33333]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:33333]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]"
  )

  val replacementHostname="Announce HOSTNAME=[]"

  val dropHostname : List[Int] = List(1,2,3,4,5,6,17)
  val verifyIpAddressHostname : List[Int] = List(17)
  // fixme : provide a list of HOSTNAME filters per 'id' ... for example 'Pickles' && 'Not.Pickles' or explicit assignment are good.

  "For all test script reports " should " assert accordingly" in {

    val MAX_COUNT = 100
    val DEFAULT_DIR = "/tmp"

    var answers : List[String] = List()
    var failed : List[String] = List()

    for ( id <- 1 to MAX_COUNT ) {
      val reportFilename = DEFAULT_DIR + "/report-" + id + ".txt"
      val errorReportFilename = DEFAULT_DIR + "/error-report-" + id + ".txt"
      val reportFile = new File(reportFilename)
      val errorReportFile = new File(errorReportFilename)
      var reportLines : Array[String] = null
      var errorLines : List[String] = null
      var validateSeperatly : Option[String] = None
      val builder = new StringBuilder
      if (reportFile.exists()) {
        reportLines = Source.fromFile(reportFilename).getLines().toArray
        dropHostname.foreach { value => if (id == value) {
            validateSeperatly = Option(reportLines(1))
            reportLines(1) = replacementHostname
          }
        }
        reportLines.toList.foreach{ line => builder.append(line) }
        val answer = builder.toString()
//        println("ONE-LINE => " + answer)

        //println("validateSeperatly => " + validateSeperatly.startsWith("["))
        validateSeperatly match {
          case Some(v) => {
            println("validateSeperatly => " + v)
            val start = v.indexOf("[")
            val end = v.indexOf("]")
            println("interestedIn      => " + v.substring(start+1,end))
          }
          case None => None
        }
        if (id <= expectedAnswers.length) {
          try {
            assertResult(expectedAnswers((id-1)))(answer)
          }
          catch {
            case e : Exception => {
              failed = ("Expected [" + expectedAnswers((id-1)) + "] got [" + answer + "]") :: failed
            }
          }

//          if (expectedAnswers((id-1)).equalsIgnoreCase(answer)) {
//            assert(true)
//          }
//          else {
//
//            fail("Expected [" + expectedAnswers((id-1)) + "] got [" + answer + "]")
//          }
        }
      }
//      if (errorReportFile.exists()) {
//        errorLines = Source.fromFile(errorReportFilename).getLines().toList
////        println("Filename : " + errorReportFilename)
////        println("Report   : " + errorLines)
//        // todo: force a failure ... do error check first.
//      }
    }
    if (failed.length>0) {
      fail( failed.reverse.foreach( v => println(v)).toString )
    }
  }

}
