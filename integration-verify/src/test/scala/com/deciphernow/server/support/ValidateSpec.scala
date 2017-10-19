package com.deciphernow.server.support

import java.io.File

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ValidateSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  // What if the last test finishes before a prior test?
  override def beforeEach(): Unit = {
    val f = new File("/tmp/integration-finished.txt")
    val MAX_COUNT = 10//120
    var count = 0
    var haveFile = false
    while (!haveFile) {
      try {
        println("Waiting for the tests to finish. Going to sleep [" + count + "] of [" + MAX_COUNT + "]")
        Thread.sleep(1000)
      }
      catch {
        case e : Exception => {
          e.printStackTrace()
        }
      }
      count = count + 1
      haveFile = if (f.exists() || count >= MAX_COUNT ) true else false
    }
    if (count >= MAX_COUNT) {
      println("It appears that some of the tests never finished. Failing the suite.")
      fail()
    }
  }

  "this " should " be able to get to the data " in {
    val f = new File("/tmp/report-1.txt")
      if (f.exists()) {
        println("WOMBAT :: found the file ....")
        assert(true)
      }
      else {
        println("WOMBAT :: did not NOT find the file ...")
        fail()
      }
    //assert(false)
  }

}
