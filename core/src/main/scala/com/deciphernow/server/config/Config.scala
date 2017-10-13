package com.deciphernow.server.config


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

import com.twitter.app.GlobalFlag



/**
  *
  */
package resources {
  object decryptClass extends GlobalFlag[String]("com.deciphernow.server.support.NoDecryptor","Class to decrypt data")
}


/**
  * Environment variables that override the configuration in parameters.config.
  *
  */
package os.env {
  import com.deciphernow.server.Implicits.stringOptionFlaggable
  object hostname extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the 'hostname' or 'ipAddress'")
  object adminPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the admin port.")
  object httpPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the http port.")
  object httpsPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the https port.")
  object thriftPort extends GlobalFlag[Option[String]](None, "Environment variable name that will contain the thrift port.")
}

/**
  * Define the RESTful ports.
  */
package rest {

  object httpPort  extends GlobalFlag[String](":8888", "HTTP Port")
  object httpsPort extends GlobalFlag[String](":8999", "HTTPS Port")

}

/**
  * Define the Admin port. It does not follow Twitter in that the real AdminHttp port is an
  * integer.
  *
  */
package admin {

  object port extends GlobalFlag[String](":9990", "Admin Port")

}

/**
  * Define the Thrift port.
  */
package thrift {

  // shouldn't this be: GlobalFlag[String]?
  //object port extends GlobalFlag(":9090", "Thrift Port")
  object port extends GlobalFlag[String](":9090", "Thrift Port")
}

/**
  * Configure the stats filter level of time.
  */
package stats {
  object time extends GlobalFlag[String]("MILLISECONDS", "Set time to milliseconds.")
}

/**
  * Define Zookeeper Information.
  */
package zk {

  object announcementPoint extends GlobalFlag[String]("", "Service ZK Announcement Point")
  object zookeeperConnection extends GlobalFlag[String]("", "Zookeeper Connection String")

}

/**
  * Define TLS information for configuring 2-Way SSL.
  */
package tls {

  import com.deciphernow.server.Implicits.fileOptionFlaggable
  import java.io.File

  object keyStore extends GlobalFlag[Option[File]](None, "SSL Key Store File")
  object keyStorePass extends GlobalFlag[String]("", "SSL Key Store Password")
  object trustStore extends GlobalFlag[Option[File]](None, "SSL Trust Store File")
  object trustStorePass extends GlobalFlag[String]("", "SSL Trust Store password")

}

/**
  * Configure the number of workers & separate thread pool.
  */
package client {

  import com.twitter.jvm.numProcs

  object numWorkers extends GlobalFlag[Int]((numProcs() * 2).ceil.toInt, "Size of netty3 client worker pool")
  object useSeparatePool extends GlobalFlag[Boolean](true, "Whether to use a separate thread pool for clients")
}

/**
  * Tell the framework to register IP addresses to Zookeeper instead of it's hostname.
  */
package ipAddress {
  object enableIpAddressResolution extends GlobalFlag[Boolean](false, "The announce IP address instead of hostname. Default is false")
  object useNetworkInterfaceName extends GlobalFlag[String]("", "The network interface to use to find the ip address. Defaults to ''")
}

package staticRoutes {
  object ignore extends GlobalFlag[String]("/ping", "This is a comma separated list of URI that the system will automatically register with a StatsReceiver so don't register these.")
}

package filter {
  object blockHttp extends GlobalFlag[Boolean](false,"The AclRestFilter blocks HTTP requests. Default is to allow HTTP requests.")
}