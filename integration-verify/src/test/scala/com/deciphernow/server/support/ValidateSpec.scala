package com.deciphernow.server.support

import java.io.File
import scala.io.Source
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

/*
    Copyright 2017 Decipher Technology Studios LLC

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

/**
  *
  */
class ValidateSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

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
    "::: /tmp/report-18.txt:::Announce HOSTNAME=[WOMBAT]Announce adminPort=[33333]Announce httpPort=[5556]Announce httpsPort=[10001]Announce thriftPort=[9090]Bind adminPort=[:33333]Bind httpPort=[:10000]Bind httpsPort=[:10001]Bind thriftPort=[:9090]",
    "::: /tmp/report-19.txt:::Announce HOSTNAME=[Not.Pickles]Announce adminPort=[44444]Announce httpPort=[44445]Announce httpsPort=[8999]Announce thriftPort=[9090]Bind adminPort=[:44446]Bind httpPort=[:44447]Bind httpsPort=[:8999]Bind thriftPort=[:9090]"
  )

  val replacementHostname="Announce HOSTNAME=[]"

  val dropHostname : List[Int] = List(1,2,3,4,5,6,17)
  val verifyIpAddressHostname : List[Int] = List(17)

  "For all test script reports " should " assert accordingly" in {

    val MAX_COUNT = 100

    // Update the 'configure.sh' to return the correct dir if changed here.
    val DEFAULT_DIR = "/tmp"

    // Not a very accurate representation of IPv4 address.
    val ipPattern = "(((2[0-5][0-5])|(1[0-9][0-9])|([1-9][0-9])|([0-9]))\\.|((2[0-5][0-5])|(1[0-9][0-9])|([1-9][0-9])|([0-9])))".r

    // Store each test result that failed. Will be dumped in the end. This way can identify all that failed at once.
    var failed : List[String] = List()

    for ( id <- 1 to MAX_COUNT ) {
      val reportFilename = DEFAULT_DIR + "/report-" + id + ".txt"
      val reportFile = new File(reportFilename)
      var reportLines : Array[String] = null
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

        // Leave - Used this to acquire answers to new tests.
        // println("ONE-LINE => " + answer)

        validateSeperatly match {
          case Some(v) => {
            val start = v.indexOf("[")
            val end = v.indexOf("]")
            val inspectThis = v.substring(start+1,end)
            val matches = ipPattern.findAllIn(inspectThis)
            var isAddressGood = false
            var isIpAddress = false
            verifyIpAddressHostname.foreach { ipIdx =>
              isIpAddress = (ipIdx == id)
              isAddressGood = (matches.length == 4)
            }
            if (!isAddressGood && isIpAddress) {
              failed = ("For test ["+id+"] an ipAddress should have been returned and it was not. Value = [" + inspectThis + "]") :: failed
            }
            else if (isAddressGood && !isIpAddress) {
              failed = ("For test [" + id + "] expected a Hostname and suspect received an ipAddress. Value = [" + inspectThis + "]") :: failed
            }
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
        }
      }
    }

    // Dump all the tests that failed && exit.
    if (failed.length>0) {
      fail( failed.reverse.foreach( v => println(v)).toString )
    }

    // w00t everything passed.
    assert(true)
  }

}
