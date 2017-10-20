package com.deciphernow.integration

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

import java.io.{File, PrintStream}

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
    //val errorFile = new File(args(1))
    
    System.setOut(new PrintStream(answerFile))
    //System.setErr(new PrintStream(errorFile))

    GMFNetworkConfigurationResolver.resolveConfiguration

    //  ::: Header for the report file :::
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

    //    System.err.flush()
    //    System.err.close()
    System.out.flush()
    System.out.close()

  }
}
