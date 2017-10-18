package com.deciphernow.integration

import java.io.{File, PrintStream, PrintWriter}

import com.deciphernow.server.GMFNetworkConfigurationResolver


object IT001 extends App {

  override def main(args: Array[String]): Unit = {

    val f = new File(args(0))

    GMFNetworkConfigurationResolver.resolveConfiguration

    val pw = new PrintWriter(f)

    System.setErr(new PrintStream(new File("/tmp/super-error-wombat.txt")))

    //  ::: Announce ports :::
    pw.println("Announce HOSTNAME    = " + GMFNetworkConfigurationResolver.getAnnounceHostname)
    pw.println("Announce adminPort   = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
    pw.println("Announce httpPort    = " + GMFNetworkConfigurationResolver.getAnnounceHttpPort)
    pw.println("Announce httpsPort   = " + GMFNetworkConfigurationResolver.getAnnounceHttpsPort)
    pw.println("Announce thriftPort  = " + GMFNetworkConfigurationResolver.getAnnounceThriftPort)

    //  ::: Bind ports :::
    pw.println("Bind adminPort       = " + GMFNetworkConfigurationResolver.getBindAdminPort)
    pw.println("Bind httpPort        = " + GMFNetworkConfigurationResolver.getBindHttpPort)
    pw.println("Bind httpsPort       = " + GMFNetworkConfigurationResolver.getBindHttpsPort)
    pw.println("Bind thriftPort      = " + GMFNetworkConfigurationResolver.getBindThriftPort)

    pw.flush()
    pw.close()

    val finished = new File("/tmp/integration-finished.txt")
    finished.createNewFile()


  }

}
