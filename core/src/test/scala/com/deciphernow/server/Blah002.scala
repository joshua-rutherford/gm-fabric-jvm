//package com.deciphernow.server
//
//import java.io.File
//import java.util
//import java.util.List
//
//object Blah002 extends App {
//
//  override def main(args: Array[String]): Unit = {
//
//    val pb = new ProcessBuilder()
//    var en = pb.environment()
//    en.put("ANNOUNCE_ADMIN_PORT",":1010")
//
//    System.getProperties.setProperty("com.deciphernow.server.config.rest.httpPort",":65000")
//    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.adminPort","ANNOUNCE_ADMIN_PORT");
//    System.getProperties.setProperty("com.deciphernow.announcement.config.os.env.httpPort","ANNOUNCE_HTTP_PORT");
//
//    pb.redirectOutput(new File("/tmp/log.txt"))
//    pb.redirectError( new File("/tmp/error-log.txt"))
//    pb.command("scala").start()
//    //import util.List
//    //val commandSet = java.util.ArrayList[String] {"a"};
//
//    //pb.command(commandSet).start()
//  }
//}
///*
// ProcessBuilder pb =
//   new ProcessBuilder("myCommand", "myArg1", "myArg2");
// Map<String, String> env = pb.environment();
// env.put("VAR1", "myValue");
// env.remove("OTHERVAR");
// env.put("VAR2", env.get("VAR1") + "suffix");
// pb.directory(new File("myDir"));
// File log = new File("log");
// pb.redirectErrorStream(true);
// pb.redirectOutput(Redirect.appendTo(log));
// Process p = pb.start();
// assert pb.redirectInput() == Redirect.PIPE;
// assert pb.redirectOutput().file() == log;
// assert p.getInputStream().read() == -1;
// */