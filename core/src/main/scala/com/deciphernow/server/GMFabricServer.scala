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
import java.util
import java.util.Enumeration

import com.deciphernow.server.rest.GMFabricRestServer
import com.deciphernow.server.support.SupportUtils
import com.deciphernow.server.thrift.GMFabricThriftServer
import com.twitter.app.App
import com.twitter.finagle._
import com.twitter.logging.Logger
import com.twitter.util.{Future, Time}
import com.deciphernow.server.{config => configuration}
import com.twitter.finagle.util.InetSocketAddressUtil

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
    //haveNetworkInterfaceName = if (networkInterfaceName.trim.length > 0) { return true; } else { return false; }


//    haveNetworkInterfaceName = if (networkInterfaceName.trim.length > 0) {
//      true
//    }
//    else {
//      false
//    }
    haveNetworkInterfaceName = (networkInterfaceName.trim.length > 0)


    identifyHostOrIP

    sys.addShutdownHook(close(Time.fromSeconds(2)))

    thrift match {
      case Some(_) =>
        log.ifDebug("creating thrift server.")
        thriftServer = Option(new GMFabricThriftServer(thrift.get.filters, thrift.get.service))
        thriftServer.foreach(_ => {
          thriftServer.get.main(Array())
          log.ifInfo(s"thrift server started on port ${configuration.thrift.port()}")
          //announce(thriftServer.get.getServer,"thrift")
          //myAnnouncer("thrift")

          // only do if zk configured
          announcer(configuration.thrift.port(),"thrift")
        })
      case _ => log.info("No thrift server defined.")
    }



    println("ZOMBIE :: thrift server bound to: " + InetSocketAddressUtil.toPublic(thriftServer.get.getServer.get.boundAddress))

    rest match {
      case Some(_) =>
        log.ifDebug("creating restful server.")
        restServer = Option(new GMFabricRestServer(rest.get.filters, rest.get.controllers))

        println("ZOMBIE :: Admin server bound to: " + InetSocketAddressUtil.toPublic(restServer.get.adminBoundAddress))

        println("ZOMBIE :: hostname                                         = " + restServer.get.adminBoundAddress.getHostName)
        println("ZOMBIE :: direct InetAddress.localhost request             = " + InetAddress.getLocalHost)
        println("ZOMBIE :: direct InetAddress.localhost.hostname request    = " + InetAddress.getLocalHost.getHostName)
        println("ZOMBIE :: direct InetAddress.localhost.hostaddress request = " + InetAddress.getLocalHost.getHostAddress)
        println("ZOMBIE :: port for admin                                   = " + restServer.get.defaultHttpPort)
        println("ZOMBIE :: port for http                                    = " + restServer.get.defaultFinatraHttpPort)
        println("ZOMBIE :: port for https                                   = " + restServer.get.defaultHttpsPort)

// only do if zk configured.
        announcer(restServer.get.getAdminPort,"admin")
        announcer(restServer.get.getHttpPort,"http")
        announcer(restServer.get.getHttpsPort,"https") // todo: what happens if does not exist?

        restServer.get.main(Array())
//        restServer.foreach( _ => {
////          myAnnouncer("http")
////          myAnnouncer("https")
////          myAnnouncer("admin")
//          if (configuration.zk.announcementPoint.equals("") || configuration.zk.zookeeperConnection.equals("")) {
//            restServer.get.main(Array())
//          }
//          else {
//            restServer.get.main(
//              Array(configureAnnouncement("-http.announce", "http"),
//                configureAnnouncement("-https.announce", "https"),
//                configureAnnouncement("-admin.announce", "admin"))
//            )
//          }
//        }
//        )
      case _ => log.error("No rest server defined. All services will shutdown.")
    }

  }

  var useIpAddressResolution : Boolean = false
  var networkInterfaceName : String = ""
  var haveNetworkInterfaceName : Boolean = false
  private[this] var announcements = List.empty[Future[Announcement]]

  var announceThis : String = ""

  def announcer(port : Option[String], scheme: String) : Unit = {
    println("ZOMBIE : announcer(port : Option[String], scheme: String) : " + port + " // " + scheme)
    announcer(port.get,scheme)
  }

  def announcer(port : String, scheme: String) : Unit = {
    // todo: convert 'port' to int.
    //announcer(portConverted,scheme)
    println("ZOMBIE : announcer(port : String, scheme: String) : " + port + " // " + scheme)
    val tmp = if (port.startsWith(":")) {
      port.substring(1)
    }
    else {
      port
    }
    if (tmp.trim.length>0) {
      try {

        announcer(tmp.toInt,scheme)
        //        if (configuration.ipAddress.enableIpAddressResolution.get.get) {
        //          announcerIpAddress(tmp.toInt,scheme)
        //        }
        //        else {
        //          announcerHostname(tmp.toInt,scheme)
        //        }
      }
      catch { case _ : Exception => println("ZOMBIE :: I HAVE AN EXCEPTION!!!!") }
    }
  }
  def announcer(port : Int, scheme: String) : Unit = {
    println("ZOMBIE :: announceThis " + announceThis + ":" + port + " for service-type :" + scheme )
    val announcementPoint = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
    val ann = Announcer.announce(new InetSocketAddress(announceThis, port), announcementPoint)
    announcements ::= ann
    //ann // does this actuall announce????
  }


  def getNetworkInfo(networkInterface: Option[NetworkInterface]) = {
    networkInterface match {
      case Some(v) => findNetworkInfo(v)
      case None => println("ZOMBIE .... networkInterface is NULL == capture failure.")
    }
  }

  def findNetworkInfo(networkInterface: NetworkInterface) = {

    println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
    println("ZOMBIE ni.name              : " + networkInterface.getName)
    //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
    println("ZOMBIE ni                   : " + networkInterface)
    println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
    println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
    println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
    println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)

    if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
      val addresses = networkInterface.getInetAddresses
      //var breakOutOfLoop = breakOut

      while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
        val anAddress = addresses.nextElement

        if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
          println("ZOMBIE :: anAddress = " + anAddress)
          println("ZOMBIE :: useIpAddressResolution => " + useIpAddressResolution)
          announceThis = if (useIpAddressResolution) {
            println("ZOMBIE :: useIpAddressResolution - convertIpAddress")
            convertIpAddress(anAddress.getAddress)
          }
          else {
            println("ZOMBIE :: useIpAddressResolution - anAddress.getHostName")
            //anAddress.getHostName
            InetAddress.getLocalHost.getHostName
          }
          println("ZOMBIE :: announceThis => ",announceThis)
          //breakOut = (announceThis.trim.length > 0)
        }
      }
    }
  }
  def identifyHostOrIP : Unit = {
    //var finished = false;


    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
      println("ZOMBIE networkInterfaceName          = " + networkInterfaceName)
      println("ZOMBIE do I use networkInterfaceName = " + haveNetworkInterfaceName)
      if (haveNetworkInterfaceName) {
        getNetworkInfo(Option(NetworkInterface.getByName(networkInterfaceName)))
      }
      else {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces

        //Enumeration[NetworkInterface] tmp = new Enumeration[NetworkInterface] {(NetworkInterface.getByName(networkInterfaceName))}

        while (networkInterfaces.hasMoreElements && !(announceThis.trim.length > 0)) {

          //val networkInterface = networkInterfaces.nextElement

          //        println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
          //        println("ZOMBIE ni.name              : " + networkInterface.getName)
          //        //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
          //        println("ZOMBIE ni                   : " + networkInterface)
          //        println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
          //        println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
          //        println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
          //        println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)

          findNetworkInfo(networkInterfaces.nextElement)
          //        if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
          //          val addresses = networkInterface.getInetAddresses
          //          //var breakOutOfLoop = breakOut
          //
          //          while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
          //            val anAddress = addresses.nextElement
          //
          //            if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
          //              println("ZOMBIE :: anAddress = " + anAddress)
          //              println("ZOMBIE :: useIpAddressResolution => " + useIpAddressResolution)
          //              announceThis = if (useIpAddressResolution) {
          //                println("ZOMBIE :: useIpAddressResolution - convertIpAddress")
          //                convertIpAddress(anAddress.getAddress)
          //              }
          //              else {
          //                println("ZOMBIE :: useIpAddressResolution - anAddress.getHostName")
          //                //anAddress.getHostName
          //                InetAddress.getLocalHost.getHostName
          //              }
          //              println("ZOMBIE :: announceThis => ",announceThis)
          //              //breakOut = (announceThis.trim.length > 0)
          //            }
          //          }
          //        }
          //breakOut = (announceThis.trim.length > 0)
        }
      }
      //var breakOut = false
//      val networkInterfaces = NetworkInterface.getNetworkInterfaces
//
//      //Enumeration[NetworkInterface] tmp = new Enumeration[NetworkInterface] {(NetworkInterface.getByName(networkInterfaceName))}
//
//      while (networkInterfaces.hasMoreElements && !(announceThis.trim.length > 0)) {
//
//        //val networkInterface = networkInterfaces.nextElement
//
////        println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
////        println("ZOMBIE ni.name              : " + networkInterface.getName)
////        //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
////        println("ZOMBIE ni                   : " + networkInterface)
////        println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
////        println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
////        println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
////        println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)
//
//        findNetworkInfo(networkInterfaces.nextElement)
////        if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
////          val addresses = networkInterface.getInetAddresses
////          //var breakOutOfLoop = breakOut
////
////          while (addresses.hasMoreElements && !(announceThis.trim.length > 0)) {
////            val anAddress = addresses.nextElement
////
////            if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
////              println("ZOMBIE :: anAddress = " + anAddress)
////              println("ZOMBIE :: useIpAddressResolution => " + useIpAddressResolution)
////              announceThis = if (useIpAddressResolution) {
////                println("ZOMBIE :: useIpAddressResolution - convertIpAddress")
////                convertIpAddress(anAddress.getAddress)
////              }
////              else {
////                println("ZOMBIE :: useIpAddressResolution - anAddress.getHostName")
////                //anAddress.getHostName
////                InetAddress.getLocalHost.getHostName
////              }
////              println("ZOMBIE :: announceThis => ",announceThis)
////              //breakOut = (announceThis.trim.length > 0)
////            }
////          }
////        }
//        //breakOut = (announceThis.trim.length > 0)
//      }

    }
    else {
      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
    }
  }

  def identifyHostOrIP_XXX : Unit = {
    // println("ZOMBIE : announcer(port : Int, scheme: String) : " + port + " // " + scheme)

    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
      val networkInterfaces = NetworkInterface.getNetworkInterfaces
      println("ZOMBIE networkInterfaceName          = " + networkInterfaceName)
      println("ZOMBIE do I use networkInterfaceName = " + haveNetworkInterfaceName)
      //Enumeration[NetworkInterface] tmp = new Enumeration[NetworkInterface] {(NetworkInterface.getByName(networkInterfaceName))}
      var breakOut = false

      while (networkInterfaces.hasMoreElements && !breakOut) {

        val networkInterface = networkInterfaces.nextElement

        println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
        println("ZOMBIE ni.name              : " + networkInterface.getName)
        //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
        println("ZOMBIE ni                   : " + networkInterface)
        println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
        println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
        println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
        println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)

        if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
          val addresses = networkInterface.getInetAddresses
          while (addresses.hasMoreElements) {
            val anAddress = addresses.nextElement

            if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
              println("ZOMBIE :: anAddress = " + anAddress)
              println("ZOMBIE :: useIpAddressResolution => " + useIpAddressResolution)
              announceThis = if (useIpAddressResolution) {
                println("ZOMBIE :: useIpAddressResolution - convertIpAddress")
                convertIpAddress(anAddress.getAddress)
              }
              else {
                println("ZOMBIE :: useIpAddressResolution - anAddress.getHostName")
                //anAddress.getHostName
                InetAddress.getLocalHost.getHostName
              }
              println("ZOMBIE :: announceThis => ",announceThis)
//              val newSocket = new InetSocketAddress(tmp, port)
//              println("ZOMBIE :: bld.address :: FINALLY!! -> " + newSocket)
//              val announcementPoint = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
//              val ann = Announcer.announce(newSocket, announcementPoint)
//              announcements ::= ann
//              ann
            }
          }
        }
        breakOut = (announceThis.trim.length > 0)
      }

    }
    else {
      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
    }
  }

  def announcerXXX(port : Option[String], scheme: String) : Unit = {
    println("ZOMBIE : announcer(port : Option[String], scheme: String) : " + port + " // " + scheme)
    announcerXXX(port.get,scheme)
  }

  def announcerXXX(port : String, scheme: String) : Unit = {
    // todo: convert 'port' to int.
    //announcer(portConverted,scheme)
    println("ZOMBIE : announcer(port : String, scheme: String) : " + port + " // " + scheme)
    val tmp = if (port.startsWith(":")) {
      port.substring(1)
    }
    else {
      port
    }
    if (tmp.trim.length>0) {
      try {
        announcerXXX(tmp.toInt,scheme)
//        if (configuration.ipAddress.enableIpAddressResolution.get.get) {
//          announcerIpAddress(tmp.toInt,scheme)
//        }
//        else {
//          announcerHostname(tmp.toInt,scheme)
//        }
      }
      catch { case _ : Exception => println("ZOMBIE :: I HAVE AN EXCEPTION!!!!") }
    }
  }

//  def announcerHostname(port: Int, scheme: String) : Unit = {
//    println("ZOMBIE : announcerHostname(port : Int, scheme: String) : " + port + " // " + scheme)
//    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
//
//    }
//    else {
//      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
//    }
//
//  }



  def announcerXXX(port : Int, scheme: String) : Unit = {

    println("ZOMBIE : announcer(port : Int, scheme: String) : " + port + " // " + scheme)

    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
      val networkInterfaces = NetworkInterface.getNetworkInterfaces
      println("ZOMBIE networkInterfaceName          = " + networkInterfaceName)
      println("ZOMBIE do I use networkInterfaceName = " + haveNetworkInterfaceName)
      //Enumeration[NetworkInterface] tmp = new Enumeration[NetworkInterface] {(NetworkInterface.getByName(networkInterfaceName))}
      while (networkInterfaces.hasMoreElements) {
        val networkInterface = networkInterfaces.nextElement

        println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
        println("ZOMBIE ni.name              : " + networkInterface.getName)
        //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
        println("ZOMBIE ni                   : " + networkInterface)
        println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
        println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
        println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
        println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)

        if (networkInterface.isUp && !networkInterface.isLoopback && !networkInterface.isVirtual) {
          val addresses = networkInterface.getInetAddresses
          while (addresses.hasMoreElements) {
            val anAddress = addresses.nextElement

            if (anAddress.isInstanceOf[Inet4Address] && !anAddress.isLoopbackAddress) {
              println("ZOMBIE :: anAddress = " + anAddress)
              println("ZOMBIE :: useIpAddressResolution => " + useIpAddressResolution)
              val tmp = if (useIpAddressResolution) {
                println("ZOMBIE :: useIpAddressResolution - convertIpAddress")
                convertIpAddress(anAddress.getAddress)
              }
              else {
                println("ZOMBIE :: useIpAddressResolution - anAddress.getHostName")
                //anAddress.getHostName
                InetAddress.getLocalHost.getHostName
              }
              println("ZOMBIE :: tmp => ",tmp)
              val newSocket = new InetSocketAddress(tmp, port)
              println("ZOMBIE :: bld.address :: FINALLY!! -> " + newSocket)
              val announcementPoint = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
              val ann = Announcer.announce(newSocket, announcementPoint)
              announcements ::= ann
              ann
            }
          }
        }
      }
    }
    else {
      log.ifInfo("Zookeeper properties not configured. Nothing to announce.")
    }

  }

  def _myAnnouncerX(scheme : String) : Unit = {
    val address = InetAddress.getLocalHost
    println("ZOMBIE address-canonical-name : " + address.getCanonicalHostName)
    println("ZOMBIE address-host-name      : " + address.getHostName)
    println("ZOMBIE address-host-address   : " + address.getHostAddress)
    println("ZOMBIE address-address        : " + address.getAddress)

    println("ZOMBIE create a new SocketAddress ... " + InetSocketAddressUtil.toPublic(new InetSocketAddress(address.getHostName,2222)))
    val nis : util.Enumeration[NetworkInterface] = NetworkInterface.getNetworkInterfaces
    while (nis.hasMoreElements) {
      val networkInterface = nis.nextElement()
      println("ZOMBIE ni.displayName       : " + networkInterface.getDisplayName)
      println("ZOMBIE ni.name              : " + networkInterface.getName)
      //println("ZOMBIE ni.hardwareAddress   : " + convertIpAddress(networkInterface.getHardwareAddress)) // MAC address
      println("ZOMBIE ni                   : " + networkInterface)
      println("ZOMBIE ni.isLoopback        : " + networkInterface.isLoopback)
      println("ZOMBIE ni.isPointToPoint    : " + networkInterface.isPointToPoint)
      println("ZOMBIE ni.isUp              : " + networkInterface.isUp)
      println("ZOMBIE ni.isVirtual         : " + networkInterface.isVirtual)

      val theAddresses = networkInterface.getInetAddresses
      while (theAddresses.hasMoreElements) {
        val tAddr = theAddresses.nextElement();
        if (tAddr.isInstanceOf[Inet4Address] && !tAddr.isLoopbackAddress) {
          println("ZOMBIE :: tAddr:: FINALLY!! -> " + Some(new InetSocketAddress(convertIpAddress(tAddr.getAddress), 10)))
        }
      }
      //println("ZOMBIE ni.parent.displayName = : " + networkInterface.getParent.getDisplayName)
    }
    val endpointAddress = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
    //val socketAddress = getLocalAddressByName
    println("ZOMBIE zk endpointAddress     : " + endpointAddress)
    //NetworkInterface.getByName().
    val interface = NetworkInterface.getByName("enp109s0f1")
    val addresses = interface.getInetAddresses
    while (addresses.hasMoreElements) {
      var addr = addresses.nextElement()
      println("ZOMBIE :: addr.getAddress : " + convertIpAddress(addr.getAddress))
      if (addr.isInstanceOf[Inet4Address] && !addr.isLoopbackAddress) {
        println("ZOMBIE :: addr -> " + Some(new InetSocketAddress(convertIpAddress(addr.getAddress), 1010)))
      }
    }

    //InetAddress.
  }

  /**
    *
    * @param server
    * @param scheme
    */
  private def announce(server: Option[ListeningServer], scheme: String): Unit = {
    // Make the service known to the world
    server foreach  { server =>
      if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
        log.ifInfo(s"Announcing ${scheme} to Zookeeper at ${configuration.zk.announcementPoint()}")
        val addr = s"zk!${configuration.zk.zookeeperConnection()}!${configuration.zk.announcementPoint()}/${scheme}!0"
        if (useIpAddressResolution) {
          val public = if (networkInterfaceName.length>0) {
            getLocalAddressByName(server.boundAddress,networkInterfaceName)
          }
          else {
            locateAddress(server.boundAddress)
          }
          val ann = Announcer.announce(public.asInstanceOf[InetSocketAddress], addr)
          announcements ::= ann
          ann
        }
        else {
          server.announce(addr)
        }
      }
      else {
        log.ifWarning(s"Zookeeper announcement point not configured!  Not announcing ${scheme} services to Zookeeper!")
      }
    }
  }

  /**
    *
    * @param twitterAttribute
    * @param scheme
    * @return
    */
  private def configureAnnouncement(twitterAttribute: String, scheme: String) : String = {
    var announcement = ""
    if (!configuration.zk.zookeeperConnection().isEmpty && !configuration.zk.announcementPoint().isEmpty) {
      announcement = twitterAttribute + "=zk!" + configuration.zk.zookeeperConnection.get.get +
              "!" + configuration.zk.announcementPoint.get.get + "/" + scheme + "!0"
    }
    else {
      log.ifWarning(s"Zookeeper announcement point not configured!  Not announcing ${scheme} services to Zookeeper!")
    }
    log.ifDebug(announcement)
    announcement
  }

  /**
    * Convert the byte array representation of the IPv4 address to a string.
    *
    * @param rawBytes
    * @return
    */
  def convertIpAddress(rawBytes: Array[Byte]) : String = {
    rawBytes.map(n => n & 0xFF).mkString(".")
  }

  /**
    *
    * @param bound
    * @param interfaceName
    * @return
    */
  def getLocalAddressByName(bound: SocketAddress, interfaceName: String) : SocketAddress = {
    val port = bound.asInstanceOf[InetSocketAddress].getPort
    var addressOfInterest = None: Option[SocketAddress]
    try {
      val interface = NetworkInterface.getByName(interfaceName)
      val addresses = interface.getInetAddresses
      while (addresses.hasMoreElements) {
        var addr = addresses.nextElement()
        if (addr.isInstanceOf[Inet4Address] && !addr.isLoopbackAddress) {
          addressOfInterest = Some(new InetSocketAddress(convertIpAddress(addr.getAddress), port))
        }
      }
    }
    catch {
      case _ : Exception => None
    }
    addressOfInterest match {
      case Some(_) => addressOfInterest.get
      case None => new InetSocketAddress(InetAddress.getLoopbackAddress,port)
    }
  }

  /**
    * This traverses all available network interfaces and identifies an IPv4 interface that
    * is NOT a loopback and returns the IP Address of it.
    *
    * @param bound
    * @return
    */
  def locateAddress(bound: SocketAddress) : SocketAddress = {
    val port = bound.asInstanceOf[InetSocketAddress].getPort
    val interfaces = NetworkInterface.getNetworkInterfaces
    var addressOfInterest = None: Option[SocketAddress]
    while (interfaces.hasMoreElements) {
      var addresses = interfaces.nextElement().getInetAddresses
      while (addresses.hasMoreElements) {
        var addr = addresses.nextElement()
        if (addr.isInstanceOf[Inet4Address] && !addr.isLoopbackAddress) {
          addressOfInterest = Some(new InetSocketAddress(convertIpAddress(addr.getAddress), port))
        }
      }
    }
    addressOfInterest match {
      case Some(_) => addressOfInterest.get
      case None => new InetSocketAddress(InetAddress.getLoopbackAddress,port)
    }
  }

}
