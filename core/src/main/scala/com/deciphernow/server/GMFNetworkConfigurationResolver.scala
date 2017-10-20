package com.deciphernow.server

import java.net.{Inet4Address, InetAddress, NetworkInterface}

import com.deciphernow.server.{config => serverConfig}
import com.deciphernow.announcement.{config => announcementConfig}
import com.twitter.logging.Logger

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

/**
  *
  */
object GMFNetworkConfigurationResolver {

  val log = Logger.get(getClass)

  var announceHostname   : String = _

  var announceAdminPort  : String = _
  var announceHttpPort   : String = _
  var announceHttpsPort  : String = _
  var announceThriftPort : String = _

  var bindAdminPort  : String = _
  var bindHttpPort   : String = _
  var bindHttpsPort  : String = _
  var bindThriftPort : String = _

  def getBindAdminPort = bindAdminPort
  def getBindHttpPort = bindHttpPort
  def getBindHttpsPort = bindHttpsPort
  def getBindThriftPort = bindThriftPort

  def getAnnounceHostname = announceHostname
  def getAnnounceAdminPort = announceAdminPort
  def getAnnounceHttpPort  = announceHttpPort
  def getAnnounceHttpsPort = announceHttpsPort
  def getAnnounceThriftPort = announceThriftPort

  /**
    *
    */
  def resolveConfiguration = {

    resolveAnnounceHostname

    resolveAnnounceAdminPort
    resolveAnnounceHttpPort
    resolveAnnounceHttpsPort
    resolveAnnounceThriftPort

    resolveBindAdminPort
    resolveBindHttpPort
    resolveBindHttpsPort
    resolveBindThriftPort

  }

  /**
    * FIXME: still not resolving hostname accuratly.
    */
  protected [this] def resolveAnnounceHostname : Unit = {
    identifyHostOrIP
    announceHostname = pickValue(
      List(envValue(announcementConfig.os.env.hostname.apply),
           configValue(announcementConfig.service.forward.hostname.apply),
           envValue(serverConfig.os.env.hostname.apply),
           announceThis)
    )
  }

  /**
    *
    */
  protected [this] def resolveBindAdminPort : Unit = {
    bindAdminPort = addTwitterNuance(
      pickValue(List(
        envValue(serverConfig.os.env.adminPort.apply),serverConfig.admin.port.apply
      ))
    )
  }

  /**
    *
    */
  protected [this] def resolveBindHttpPort : Unit = {
    bindHttpPort = addTwitterNuance(
      pickValue(List(
        envValue(serverConfig.os.env.httpPort.apply),serverConfig.rest.httpPort.apply
      ))
    )
  }

  /**
    *
    */
  protected [this] def resolveBindHttpsPort : Unit = {
    bindHttpsPort = addTwitterNuance(
      pickValue(List(
        envValue(serverConfig.os.env.httpsPort.apply),serverConfig.rest.httpsPort.apply
      ))
    )
  }

  /**
    *
    */
  protected [this] def resolveBindThriftPort : Unit = {
    bindThriftPort = addTwitterNuance(
      pickValue(List(
        envValue(serverConfig.os.env.thriftPort.apply),serverConfig.thrift.port.apply
      ))
    )
  }

  /**
    *
    */
  protected [this] def resolveAnnounceAdminPort : Unit = {
    announceAdminPort = filterTwitterNuance(pickValue(List(envValue(announcementConfig.os.env.adminPort.apply),
      configValue(announcementConfig.service.forward.adminPort.apply),
      envValue(serverConfig.os.env.adminPort.apply),
      serverConfig.admin.port.apply)))
  }

  /**
    *
    */
  protected [this] def resolveAnnounceHttpPort : Unit = {
    announceHttpPort = filterTwitterNuance(pickValue(List(envValue(announcementConfig.os.env.httpPort.apply),
      configValue(announcementConfig.service.forward.httpPort.apply),
      envValue(serverConfig.os.env.httpPort.apply),
      serverConfig.rest.httpPort.apply)))
  }

  /**
    *
    */
  protected [this] def resolveAnnounceHttpsPort : Unit = {
    announceHttpsPort = filterTwitterNuance(pickValue(List(envValue(announcementConfig.os.env.httpsPort.apply),
      configValue(announcementConfig.service.forward.httpsPort.apply),
      envValue(serverConfig.os.env.httpsPort.apply),
      serverConfig.rest.httpsPort.apply)))
  }

  /**
    *
    */
  protected [this] def resolveAnnounceThriftPort : Unit = {
    announceThriftPort = filterTwitterNuance(pickValue(List(envValue(announcementConfig.os.env.thriftPort.apply),
      configValue(announcementConfig.service.forward.thriftPort.apply),
      envValue(serverConfig.os.env.thriftPort.apply),
      serverConfig.thrift.port.apply)))
  }

  /**
    *
    * @param values
    * @return
    */
  protected [this] def pickValue(values: List[String]) : String = {
    var retValue : String = ""
    values.foreach { v =>
      if ((retValue.trim.length == 0 ) && (v.trim.length > 0)) {
        retValue = v
      }
    }
    retValue
  }

  /**
    * Retrieve the value from the environment variable. Return empty String if None.
    * @param env
    * @return
    */
  protected [this] def envValue(env: Option[String]) = env.flatMap{value => sys.env.get(value)}.getOrElse("")

  /**
    * Retrieve the value from the Option. Return empty String if it is None.
    * @param config
    * @return
    */
  protected [this] def configValue(config: Option[String]) = {
    config match {
      case Some(v) => v
      case None => ""
    }
  }

  /**
    * For ports to be be bound, twitter expects the port values to start with ':'. Remove said value if going
    * to announce the port.
    *
    * @param value
    * @return
    */
  protected [this] def filterTwitterNuance(value: String) = {
    val v = if (value.trim.length>0) {
      value.startsWith(":") match {
        case true => value.substring(1)
        case false => value
      }
    }
    else {
      value
    }
    v
  }

  /**
    * For ports to be bound, twitter expects the port values to start with ':'. Pre-append the port with ':' if it is missing.
    *
    * @param value
    * @return
    */
  protected [this] def addTwitterNuance(value: String) = {
    val v = if (value.trim.length>0) {
      value.startsWith(":") match {
        case true => value
        case false => ":" + value
      }
    }
    else {
      value
    }
    v
  }

  var useIpAddressResolution : Boolean = false
  var networkInterfaceName : String = ""
  var haveNetworkInterfaceName : Boolean = false
  var announceThis : String = ""

  useIpAddressResolution = serverConfig.ipAddress.enableIpAddressResolution.get.fold(false)(_ => true)
  networkInterfaceName = serverConfig.ipAddress.useNetworkInterfaceName.get.fold("")(definedInterfaceName => definedInterfaceName)
  haveNetworkInterfaceName = (networkInterfaceName.trim.length > 0)

  /**
    * Looks up a specific interface to be used for registration to ZK.
    * @param networkInterface
    */
  protected [this] def getNetworkInfo(networkInterface: Option[NetworkInterface]) : Unit = {
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
  protected [this] def findNetworkInfo(networkInterface: NetworkInterface) : Unit = {

    if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
      val addresses = networkInterface.getInetAddresses

      while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
        val anAddress = addresses.nextElement

        if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
          announceThis = if (useIpAddressResolution) { convertIpAddress(anAddress.getAddress) }
          else { InetAddress.getLocalHost.getHostName }
        }
      }
    }
  }

  /**
    * Register either the Hostname or IP Address for service endpoints to ZK.
    */
  def identifyHostOrIP : Unit = {
//    if (!serverConfig.zk.zookeeperConnection().isEmpty && !serverConfig.zk.announcementPoint().isEmpty) {
      if (haveNetworkInterfaceName) {
        getNetworkInfo(Option(NetworkInterface.getByName(networkInterfaceName)))
      }
      else {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces
        while (networkInterfaces.hasMoreElements && !(announceThis.trim.length > 0)) {
          findNetworkInfo(networkInterfaces.nextElement)
        }
      }
//    }
//    else {
//      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
//    }
  }

  /**
    * Convert the byte array representation of the IPv4 address to a string.
    *
    * @param rawBytes
    * @return
    */
  def convertIpAddress(rawBytes: Array[Byte]) : String = rawBytes.map(n => n & 0xFF).mkString(".")

}
