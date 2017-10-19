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
    *             1 -> error report.
    */
  override def main(args: Array[String]): Unit = {

    val answerFile = new File(args(0))
    val errorFile = new File(args(1))
    
    System.setOut(new PrintStream(answerFile))
    System.setErr(new PrintStream(errorFile))

    GMFNetworkConfigurationResolver.resolveConfiguration

    //  ::: Id the report file :::
    println("::: " + args(0) + ":::")

    //  ::: Announce hostname :::
    println("Announce HOSTNAME=[" + GMFNetworkConfigurationResolver.getAnnounceHostname + "]")

    //  ::: Announce ports :::
    println("Announce adminPort=[" + GMFNetworkConfigurationResolver.getAnnounceAdminPort + "]")
    println("Announce httpPort=[" + GMFNetworkConfigurationResolver.getAnnounceHttpPort + "]")
    println("Announce httpsPort=[" + GMFNetworkConfigurationResolver.getAnnounceHttpsPort + "]")
    println("Announce thriftPort=[" + GMFNetworkConfigurationResolver.getAnnounceThriftPort + "]")

    //  ::: Bind ports :::
    println("Bind adminPort=[" + GMFNetworkConfigurationResolver.getBindAdminPort + "]")
    println("Bind httpPort=[" + GMFNetworkConfigurationResolver.getBindHttpPort + "]")
    println("Bind httpsPort=[" + GMFNetworkConfigurationResolver.getBindHttpsPort + "]")
    println("Bind thriftPort=[" + GMFNetworkConfigurationResolver.getBindThriftPort + "]")

    System.err.flush()
    System.err.close()
    System.out.flush()
    System.out.close()

  }
}
