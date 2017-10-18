package com.deciphernow.server.support

import java.io.File

import org.scalatest.{BeforeAndAfterEach, FlatSpec, FunSuite, Matchers}

class FailSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  // This fails :: The sleep breaks it.
//  override def afterEach(): Unit = {
//    val f = new File("/tmp/integration-finished.txt")
//
//    var count = 0
//    var haveFile = false
//    while (!haveFile) {
//      println("WOMBAT .... integration-finished .... not [" + count + "]")
//      try {
//        Thread.sleep(1000)
//      }
//      catch {
//        case e : Exception => {
//          e.printStackTrace()
//        }
//      }
//      count = count + 1
//      haveFile = if (f.exists() || count > 300000 ) true else false
//    }
//  }

  "force abject fail " should " stop everything " in {
//    val p1 = new ProcessBuilder();
//
//    p1.redirectError(new File("/tmp/abc-error-setup.txt"))
//    p1.redirectOutput(new File("/tmp/abc-output-setup.txt"))
//    p1.command("chmod","0700","target/example01.sh").start()
//    p1.command("chmod","0700","target/dependencies.sh").start()
//
//    val p2 = new ProcessBuilder("exec","target/example01.sh")
//    //p2.redirectError(new File("/tmp/abc-error-exec.txt"))
//    p2.redirectOutput(new File("/tmp/abc-output-exec.txt"))
//    p2.start()

    assert(true)
  }
}
