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

    useIpAddressResolution = configuration.ipAddress.enableIpAddressResolution.get.fold(false)(_ => true)
    networkInterfaceName = configuration.ipAddress.useNetworkInterfaceName.get.fold("")(definedInterfaceName => definedInterfaceName)
    haveNetworkInterfaceName = (networkInterfaceName.trim.length > 0)

    identifyHostOrIP

    sys.addShutdownHook(close(Time.fromSeconds(2)))

    thrift match {
      case Some(_) =>
        log.ifDebug("creating thrift server.")
        thriftServer = Option(new GMFabricThriftServer(thrift.get.filters, thrift.get.service))
        thriftServer.foreach(_ => {
          thriftServer.get.main(Array())
          announcer(configuration.thrift.port(),"thrift")
        })
      case _ => log.info("No thrift server defined.")
    }

    rest match {
      case Some(_) =>
        log.ifDebug("creating restful server.")
        restServer = Option(new GMFabricRestServer(rest.get.filters, rest.get.controllers))
        announcer(restServer.get.getAdminPort,"admin")
        announcer(restServer.get.getHttpPort,"http")
        announcer(restServer.get.getHttpsPort,"https")
        restServer.get.main(Array())
      case _ => log.error("No rest server defined. All services will shutdown.")
    }

  }

  var useIpAddressResolution : Boolean = false
  var networkInterfaceName : String = ""
  var haveNetworkInterfaceName : Boolean = false
  private[this] var announcements = List.empty[Future[Announcement]]

  // Hostname or IPAddress to be used to for endpoints.
  var announceThis : String = ""

  /**
    * Registers endpoint with ZK.
    * @param port
    * @param scheme
    */
  def announcer(port : Option[String], scheme: String) : Unit = {
    announcer(port.get,scheme)
  }

  /**
    * Registers endpoint with ZK.
    * @param port
    * @param scheme
    */
  def announcer(port : String, scheme: String) : Unit = {
    val tmp = if (port.startsWith(":")) {
      port.substring(1)
    }
    else {
      port
    }
    if (tmp.trim.length>0) {
      try {
        announcer(tmp.toInt,scheme)
      }
      catch { case e : Exception => log.ifInfo(e) }
    }
  }

  /**
    * Registers endpoint with ZK.
    * @param port
    * @param scheme
    */
  def announcer(port : Int, scheme: String) : Unit = {
    val announcementPoint = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
    val ann = Announcer.announce(new InetSocketAddress(announceThis, port), announcementPoint)
    announcements ::= ann
  }


  /**
    * Looks up a specific interface to be used for registration to ZK.
    * @param networkInterface
    */
  def getNetworkInfo(networkInterface: Option[NetworkInterface]) : Unit = {
    networkInterface match {
      case Some(v) => findNetworkInfo(v)
      case None => log.ifInfo("NetworkInterface [" + networkInterfaceName + "] is NULL. Shutting down!")
        System.exit(-1)
    }
  }

  /**
    * Iterates through the interfaces for a network looking for a valid interface to register with ZK.
    * @param networkInterface
    */
  def findNetworkInfo(networkInterface: NetworkInterface) : Unit = {

    if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
      val addresses = networkInterface.getInetAddresses

      while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
        val anAddress = addresses.nextElement

        if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
          announceThis = if (useIpAddressResolution) { convertIpAddress(anAddress.getAddress) }
          else { InetAddress.getLocalHost.getHostName}
        }
      }
    }
  }

  /**
    * Register either the Hostname or IP Address for service endpoints to ZK.
    */
  def identifyHostOrIP : Unit = {
    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
      if (haveNetworkInterfaceName) {
        getNetworkInfo(Option(NetworkInterface.getByName(networkInterfaceName)))
      }
      else {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces
        while (networkInterfaces.hasMoreElements && !(announceThis.trim.length > 0)) {
          findNetworkInfo(networkInterfaces.nextElement)
        }
      }
    }
    else {
      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
    }
  }

  /**
    * Convert the byte array representation of the IPv4 address to a string.
    *
    * @param rawBytes
    * @return
    */
  def convertIpAddress(rawBytes: Array[Byte]) : String = rawBytes.map(n => n & 0xFF).mkString(".")

}
