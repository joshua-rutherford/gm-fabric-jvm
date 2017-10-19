package com.deciphernow.server.support

import java.io.File
import scala.io.Source
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ValidateSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  // What if the last test finishes before a prior test?
//  override def beforeEach(): Unit = {
//    val f = new File("/tmp/integration-finished.txt")
//    val MAX_COUNT = 10//120
//    var count = 0
//    var haveFile = false
//    while (!haveFile) {
//      try {
//        println("Waiting for the tests to finish. Going to sleep [" + count + "] of [" + MAX_COUNT + "]")
//        Thread.sleep(1000)
//      }
//      catch {
//        case e : Exception => {
//          e.printStackTrace()
//        }
//      }
//      count = count + 1
//      haveFile = if (f.exists() || count >= MAX_COUNT ) true else false
//    }
//    if (count >= MAX_COUNT) {
//      println("It appears that some of the tests never finished. Failing the suite.")
//      fail()
//    }
//  }

  "retrieve all reports " should " assert accordingly" in {

    val MAX_COUNT = 100
    val DEFAULT_DIR = "/tmp"

    for ( id <- 1 to MAX_COUNT ) {
      val reportFilename = DEFAULT_DIR + "/report-" + id + ".txt"
      val errorReportFilename = DEFAULT_DIR + "/error-report-" + id + ".txt"
      val reportFile = new File(reportFilename)
      val errorReportFile = new File(errorReportFilename)
      var reportLines : List[String] = null
      var errorLines : List[String] = null
      if (reportFile.exists()) {
        reportLines = Source.fromFile(reportFilename).getLines().toList
        println("Filename : " + reportFilename)
        println("Report   : " + reportLines)
      }
      if (errorReportFile.exists()) {
        errorLines = Source.fromFile(errorReportFilename).getLines().toList
        println("Filename : " + errorReportFilename)
        println("Report   : " + errorLines)
      }

    }

//    val f = new File("/tmp/report-1.txt")
//      if (f.exists()) {
//        println("WOMBAT :: found the file ....")
//        assert(true)
//      }
//      else {
//        println("WOMBAT :: did not NOT find the file ...")
//        fail()
//      }
    //assert(false)
  }

}
