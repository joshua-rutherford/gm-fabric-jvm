package com.deciphernow.integration

import java.io.File

import com.deciphernow.server.GMFNetworkConfigurationResolver

object IntegrationTests extends App {

  def t0 = {
    val pb = new ProcessBuilder()
    pb.redirectOutput(new File("/tmp/t0-log.txt"))
    pb.redirectError(new File("/tmp/t0-error-log.txt"))
    pb.command("pwd").start()

  }

  def t1 = {
    println("t1 ... fired off....")
    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.httpPort","ANNOUNCE_HTTP_PORT");

    System.getProperties.setProperty("com.deciphernow.server.config.os.env.adminPort","BIND_ADMIN_PORT")
    System.getProperties.setProperty("com.deciphernow.server.config.os.env.httpPort","BIND_HTTP_PORT")

    //System.getenv().putIfAbsent("ANNOUNCE_ADMIN_PORT",":7777")
    //    System.getenv().put("ANNOUNCE_ADMIN_PORT",":7777")
    //System.getProperties.setProperty("ANNOUNCE_ADMIN_PORT",":00000");

    //println("System::getProperties " + System.getProperties)
    //println("System::env           " + sys.env)
    GMFNetworkConfigurationResolver.resolveConfiguration
    //println(System.getProperties.getProperty("com.deciphernow.announcement.config.os.env.adminPort"))
    println("Announce adminPort = " + GMFNetworkConfigurationResolver.getAnnounceAdminPort)
    println("Announce httpPort  = " + GMFNetworkConfigurationResolver.getAnnounceHttpPort)
    println("Bind adminPort     = " + GMFNetworkConfigurationResolver.getBindAdminPort)
    println("Bind httpPort      = " + GMFNetworkConfigurationResolver.getBindHttpPort)

    val finished = new ProcessBuilder("touch","/tmp/integration-finished.txt")
    finished.start()
  }

  def t2 = {
    val pb = new ProcessBuilder()
    var en = pb.environment()
    en.put("ANNOUNCE_ADMIN_PORT",":1010")

    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.httpPort","ANNOUNCE_HTTP_PORT");

    pb.redirectOutput(new File("/tmp/t2-log.txt"))
    pb.redirectError( new File("/tmp/t2-error-log.txt"))
    pb.command("scala -classpath . com.deciphernow.integration.IT001").start()
//    pb.command("cd integration/src/main/scala","pwd").start()
    //import util.List
    //val commandSet = java.util.ArrayList[String] {"a"};

    //pb.command(commandSet).start()
  }

  override def main(args: Array[String]): Unit = {
    println("Starting .... ")
    //t0
    t1

    //t2
    //GMFNetworkConfigurationResolver.resolveConfiguration
    println("Finished .... ")
  }
}
