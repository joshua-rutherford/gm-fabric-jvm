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

  var haveAnnouncementPoints = false;
  var haveBindPoints = false;
  var hostname   : String = _

  var bindAdminPort  : String = _
  var bindHttpPort   : String = _
  var bindHttpsPort  : String = _
  var bindThriftPort : String = _

  var announceHostname   : String = _

  var announceAdminPort  : String = _
  var announceHttpPort   : String = _
  var announceHttpsPort  : String = _
  var announceThriftPort : String = _

  def getHostname = hostname

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
    * Figure out which set of configuration parameters are to be used to announce the service endpoints &&
    * which configuration parameters are used to bind.
    */
  def resolveConfiguration = {
    resolveAnnouncementEnvironmentVars
    resolveAnnouncementConfiguration
    resolveBindEnvironmentVars
    resolveBindConfiguration
  }

  /**
    *
    */
  protected [this] def resolveBindConfiguration = {

    // if none are configured then bind && with these values.
    if (!haveBindPoints) {

      identifyHostOrIP

      bindHttpPort   = addTwitterNuance(serverConfig.rest.httpPort.apply)
      bindHttpsPort  = addTwitterNuance(serverConfig.rest.httpsPort.apply)
      bindAdminPort  = addTwitterNuance(serverConfig.admin.port.apply)
      bindThriftPort = addTwitterNuance(serverConfig.thrift.port.apply)

      if (!haveAnnouncementPoints) {
        announceHostname   = announceThis
        announceAdminPort  = filterTwitterNuance(bindAdminPort)
        announceHttpPort   = filterTwitterNuance(bindHttpPort)
        announceHttpsPort  = filterTwitterNuance(bindHttpsPort)
        announceThriftPort = filterTwitterNuance(bindThriftPort)
      }
    }
  }

  /**
    *
    */
  protected [this] def resolveBindEnvironmentVars = {

    // if announcement NOT configured && have ENV here ... then announcement + binding are equal to these values.
    val hostnameValue = envValue(serverConfig.os.env.hostname.apply)

    // todo: how do we handle if ENV set && useIpAddress is set?
    identifyHostOrIP

    val adminValue    = envValue(serverConfig.os.env.adminPort.apply)
    val httpValue     = envValue(serverConfig.os.env.httpPort.apply)
    val httpsValue    = envValue(serverConfig.os.env.httpsPort.apply)
    val thriftValue   = envValue(serverConfig.os.env.thriftPort.apply)

    haveBindPoints = ((adminValue.trim.length > 0 ) && (httpValue.trim.length > 0))

    if (haveBindPoints) {

      hostname       = hostnameValue
      bindAdminPort  = addTwitterNuance(adminValue)
      bindHttpPort   = addTwitterNuance(httpValue)
      bindHttpsPort  = addTwitterNuance(httpsValue)
      bindThriftPort = addTwitterNuance(thriftValue)

      if (!haveAnnouncementPoints) {

        announceHostname   = announceThis
        announceAdminPort  = filterTwitterNuance(adminValue)
        announceHttpPort   = filterTwitterNuance(httpValue)
        announceHttpsPort  = filterTwitterNuance(httpsValue)
        announceThriftPort = filterTwitterNuance(thriftValue)
      }
    }

  }

  /**
    *
    */
  protected [this] def resolveAnnouncementConfiguration = {
    if (!haveAnnouncementPoints) {

      val hostnameValue = configValue(announcementConfig.service.forward.hostname.apply)
      val adminValue    = configValue(announcementConfig.service.forward.adminPort.apply)
      val httpValue     = configValue(announcementConfig.service.forward.httpPort.apply)
      val httpsValue    = configValue(announcementConfig.service.forward.httpsPort.apply)
      val thriftValue   = configValue(announcementConfig.service.forward.thriftPort.apply)

      haveAnnouncementPoints = ((adminValue.trim.length > 0) && (httpValue.trim.length > 0))

      if (haveAnnouncementPoints) {
        announceHostname   = hostnameValue
        announceAdminPort  = filterTwitterNuance(adminValue)
        announceHttpPort   = filterTwitterNuance(httpValue)
        announceHttpsPort  = filterTwitterNuance(httpsValue)
        announceThriftPort = filterTwitterNuance(thriftValue)
      }
    }
  }

  /**
    *
    */
  protected [this] def resolveAnnouncementEnvironmentVars = {

    val hostnameValue = envValue(announcementConfig.os.env.hostname.apply)
    val adminValue    = envValue(announcementConfig.os.env.adminPort.apply)
    val httpValue     = envValue(announcementConfig.os.env.httpPort.apply)
    val httpsValue    = envValue(announcementConfig.os.env.httpsPort.apply)
    val thriftValue   = envValue(announcementConfig.os.env.thriftPort.apply)

    haveAnnouncementPoints = ((adminValue.trim.length > 0 ) && (httpValue.trim.length > 0))

    if (haveAnnouncementPoints) {
      announceHostname   = hostnameValue
      announceAdminPort  = filterTwitterNuance(adminValue)
      announceHttpPort   = filterTwitterNuance(httpValue)
      announceHttpsPort  = filterTwitterNuance(httpsValue)
      announceThriftPort = filterTwitterNuance(thriftValue)
    }
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
    * For ports to be bound, twitter expects the port values to start with ':'. Pre-append the port with ':'
    * if it is missing.
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
          else { InetAddress.getLocalHost.getHostName}
        }
      }
    }
  }

  /**
    * Register either the Hostname or IP Address for service endpoints to ZK.
    */
  def identifyHostOrIP : Unit = {
    if (!serverConfig.zk.zookeeperConnection().isEmpty && !serverConfig.zk.announcementPoint().isEmpty) {
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
