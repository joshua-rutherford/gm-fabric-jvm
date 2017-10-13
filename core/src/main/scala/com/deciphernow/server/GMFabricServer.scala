package com.deciphernow.server


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

import java.net._

import com.deciphernow.server.rest.GMFabricRestServer
import com.deciphernow.server.thrift.GMFabricThriftServer
import com.twitter.app.App
import com.twitter.finagle._
import com.twitter.logging.Logger
import com.twitter.util.{Future, Time}
import com.deciphernow.server.{config => configuration}

/**
  * Created by ghershfield on 4/19/16.
  */
abstract class GMFabricServer extends App {

  val log = Logger.get(getClass)
  var restServer : Option[GMFabricRestServer] = None
  var thriftServer : Option[GMFabricThriftServer] = None

  def rest(): Option[RestServer]
  def thrift(): Option[ThriftServer]

  onExit {
    log.ifWarning("Shutting server down")
    if (restServer!=None) { restServer map (_.close()) }
    if (thriftServer!=None) { thriftServer map (_.close()) }
  }


  /**
    *
    */
  final def main(): Unit = {

    log.ifDebug("Main starting up ... ")

    GMFNetworkConfigurationResolver.resolveConfiguration
    //GMFNetworkConfiguration.identifyHostOrIP

    sys.addShutdownHook(close(Time.fromSeconds(2)))

    thrift match {
      case Some(_) =>
        log.ifDebug("creating thrift server.")
        thriftServer = Option(new GMFabricThriftServer(thrift.get.filters, thrift.get.service))
        thriftServer.foreach(_ => {
          thriftServer.get.main(Array())
          GMFAnnouncer.announce(GMFNetworkConfigurationResolver.getAnnounceThriftPort,"thrift")
        })
      case _ => log.info("No thrift server defined.")
    }

    rest match {
      case Some(_) =>
        log.ifDebug("creating restful server.")
        restServer = Option(new GMFabricRestServer(rest.get.filters, rest.get.controllers))
        GMFAnnouncer.announce(GMFNetworkConfigurationResolver.getAnnounceAdminPort,"admin")
        GMFAnnouncer.announce(GMFNetworkConfigurationResolver.getAnnounceHttpPort,"http")
        GMFAnnouncer.announce(GMFNetworkConfigurationResolver.getAnnounceHttpsPort,"https")
        restServer.get.main(Array())
      case _ => log.error("No rest server defined. All services will shutdown.")
    }

  }

//  private[this] var announcements = List.empty[Future[Announcement]]
//
//  // todo: move our 'announcer' code out into it's own 'object'
//
//  /**
//    * Registers endpoint with ZK.
//    * @param port
//    * @param scheme
//    */
//  def announcer(port : Option[String], scheme: String) : Unit = announcer(port.get,scheme)
//
//
//  /**
//    * Registers endpoint with ZK.
//    * @param port
//    * @param scheme
//    */
//  def announcer(port : String, scheme: String) : Unit = {
//    val tmp = if (port.startsWith(":")) {
//      port.substring(1)
//    }
//    else {
//      port
//    }
//    if (tmp.trim.length>0) {
//      try {
//        announcer(tmp.toInt,scheme)
//      }
//      catch { case e : Exception => log.ifInfo(e) }
//    }
//  }
//
//  /**
//    * Registers endpoint with ZK.
//    * @param port
//    * @param scheme
//    */
//  def announcer(port : Int, scheme: String) : Unit = {
//    val announcementPoint = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
//    val ann = Announcer.announce(new InetSocketAddress(GMFNetworkConfigurationResolver.getAnnounceHostname, port), announcementPoint)
//    announcements ::= ann
//  }

}
