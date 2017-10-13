package com.deciphernow.server


import java.net.{Inet4Address, InetAddress, NetworkInterface}

import com.deciphernow.server.{config => serverConfig}
import com.deciphernow.announcement.{config => announceConfig}
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
object GMFNetworkConfiguration {

  val log = Logger.get(getClass)

//  var useIpAddressResolution : Boolean = false
//  var networkInterfaceName : String = ""
//  var haveNetworkInterfaceName : Boolean = false
//  var announceThis : String = ""
//
//  useIpAddressResolution = serverConfig.ipAddress.enableIpAddressResolution.get.fold(false)(_ => true)
//  networkInterfaceName = serverConfig.ipAddress.useNetworkInterfaceName.get.fold("")(definedInterfaceName => definedInterfaceName)
//  haveNetworkInterfaceName = (networkInterfaceName.trim.length > 0)
//
//  def announce = announceThis
//
//  /**
//    * Looks up a specific interface to be used for registration to ZK.
//    * @param networkInterface
//    */
//  protected [this] def getNetworkInfo(networkInterface: Option[NetworkInterface]) : Unit = {
//    networkInterface match {
//      case Some(v) => findNetworkInfo(v)
//      case None => log.ifInfo("NetworkInterface [" + networkInterfaceName + "] is NULL. Shutting down!")
//        System.exit(-1)
//    }
//  }
//
//  /**
//    * Iterates through the interfaces for a network looking for a valid interface to register with ZK.
//    * @param networkInterface
//    */
//  protected [this] def findNetworkInfo(networkInterface: NetworkInterface) : Unit = {
//
//    if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
//      val addresses = networkInterface.getInetAddresses
//
//      while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
//        val anAddress = addresses.nextElement
//
//        if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
//          announceThis = if (useIpAddressResolution) { convertIpAddress(anAddress.getAddress) }
//          else { InetAddress.getLocalHost.getHostName}
//        }
//      }
//    }
//  }
//
//  /**
//    * Register either the Hostname or IP Address for service endpoints to ZK.
//    */
//  def identifyHostOrIP : Unit = {
//    if (!serverConfig.zk.zookeeperConnection().isEmpty && !serverConfig.zk.announcementPoint().isEmpty) {
//      if (haveNetworkInterfaceName) {
//        getNetworkInfo(Option(NetworkInterface.getByName(networkInterfaceName)))
//      }
//      else {
//        val networkInterfaces = NetworkInterface.getNetworkInterfaces
//        while (networkInterfaces.hasMoreElements && !(announceThis.trim.length > 0)) {
//          findNetworkInfo(networkInterfaces.nextElement)
//        }
//      }
//    }
//    else {
//      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
//    }
//  }
//
//  /**
//    * Convert the byte array representation of the IPv4 address to a string.
//    *
//    * @param rawBytes
//    * @return
//    */
//  def convertIpAddress(rawBytes: Array[Byte]) : String = rawBytes.map(n => n & 0xFF).mkString(".")

}

//object GMFNetworkConfigurationResolver {
//
//
//}
