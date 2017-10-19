package com.deciphernow.integration

import java.io.{File, PrintStream, PrintWriter}

import com.deciphernow.server.GMFNetworkConfigurationResolver

/**
  *
  */
object TestEngine extends App {

  /**
    *
    * @param args
    *             0 -> test report.
    *             1 -> errors
    */
  override def main(args: Array[String]): Unit = {

    val answerFile = new File(args(0))
    val errorFile = new File(args(1))

//    if (args.length == 4 && args(3).equals("done")) {
//      val finished = new File(args(2) + "/integration-finished.txt")
//      finished.createNewFile()
//    }

    System.setOut(new PrintStream(answerFile))
    System.setErr(new PrintStream(errorFile))

    GMFNetworkConfigurationResolver.resolveConfiguration

    //  ::: Announce hostname :::
    print("Announce HOSTNAME=[" + GMFNetworkConfigurationResolver.getAnnounceHostname + "]")

    //  ::: Announce ports :::
    print("Announce adminPort=[" + GMFNetworkConfigurationResolver.getAnnounceAdminPort + "]")
    print("Announce httpPort=[" + GMFNetworkConfigurationResolver.getAnnounceHttpPort + "]")
    print("Announce httpsPort=[" + GMFNetworkConfigurationResolver.getAnnounceHttpsPort + "]")
    print("Announce thriftPort=[" + GMFNetworkConfigurationResolver.getAnnounceThriftPort + "]")

    //  ::: Bind ports :::
    print("Bind adminPort=[" + GMFNetworkConfigurationResolver.getBindAdminPort + "]")
    print("Bind httpPort=[" + GMFNetworkConfigurationResolver.getBindHttpPort + "]")
    print("Bind httpsPort=[" + GMFNetworkConfigurationResolver.getBindHttpsPort + "]")
    print("Bind thriftPort=" + GMFNetworkConfigurationResolver.getBindThriftPort + "]")

    System.err.flush()
    System.err.close()
    System.out.flush()
    System.out.close()

  }
}
