package com.deciphernow.server.support

import java.io.File

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ValidateSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

    override def beforeEach(): Unit = {
      val f = new File("/tmp/integration-finished.txt")

      var count = 0
      var haveFile = false
      while (!haveFile) {
        println("WOMBAT .... integration-finished .... not [" + count + "]")
        try {
          Thread.sleep(1000)
        }
        catch {
          case e : Exception => {
            e.printStackTrace()
          }
        }
        count = count + 1
        haveFile = if (f.exists() || count > 120 ) true else false
      }
      if (count > 120) fail()
    }
//  override def beforeEach(): Unit = {
//
//  }

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
