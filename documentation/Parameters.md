# Overview
The following parameters can be configured in the `etc/parameters.config` file. To create your own attributes and adding them to __parameters.config__ ![see](Config.md).

## White-list Enforcement File
This points to the file that identifies the services that are allowed to access and perform impersonation.

    -acl.whitelist.file=etc/whitelist.txt
    
## Increase the Size of Netty client worker pool
    
    -com.deciphernow.server.config.client.numWorkers=16
    
## Turn on use separate thread pool for clients
The system defaults to true.
    
    -com.deciphernow.server.config.client.useSeparatePool=true
    
## Keystore
If passwords are encrypted, See __Enable a Decryptor__
    
    -com.deciphernow.server.config.tls.keyStore=etc/keystore.jks
    -com.deciphernow.server.config.tls.keyStorePass=password
    
## Truststore
If passwords are encrypted, See __Enable a Decryptor__

    -com.deciphernow.server.config.tls.trustStore=etc/truststore.jks
    -com.deciphernow.server.config.tls.trustStorePass=password
    
## Enable a Decryptor
If not using any encrypted passwords for keystore/truststore then this attribute is not required. If passwords
are encrypted through your implementation then enable as shown.

    -com.deciphernow.server.config.resources.decryptClass=com.blah.my.own.Decryptor
    

    #-com.deciphernow.server.config.flags.allowUndefinedFlags=false
    #-com.deciphernow.server.config.flags.failFastOnFlagsNotParsed=false

## Changing RESTful server names

    -com.deciphernow.server.config.rest.httpServerName=http
    -com.deciphernow.server.config.rest.httpsServerName=https

## Disable the Admin HTTP Server

    -com.deciphernow.server.config.admin.disableAdminHttpServer=false
        
## AclRestFilter block HTTP Requests

    -com.deciphernow.server.config.filter.blockHttp=false
        
## Processing flags

Allow for flags to be defined in **paramters.conf** but not defined in the code and not fail.

    -com.deciphernow.server.config.flags.allowUndefinedFlags=false
    
Have the service shutdown if there is an issue when flags are not parsed.
    
    -com.deciphernow.server.config.flags.failFastOnFlagsNotParsed=false
        
## Changing the Maximum Request Size
The default value is **25** Megabytes. It is a long value always represented as **Megabytes**. It must be less than **2**GB. You don't have to specify the unit of measure.

    -com.deciphernow.server.config.rest.maxRequestSize=25

## Bind and Announce
To configure different ports to __BIND__ to or __ANNOUNCE__ different ports [see](AnnounceAndBind.md).
    
## Set the Thrift method level statistics unit of measure

The following units of measure are available: `NANOSECONDS`, `MICROSECONDS`, `MILLISECONDS`, `SECONDS`, `MINUTES`, `HOURS`, `DAYS`.
Unfortunately the unit of measure is not displayed in /admin/metrics.json and thus there can be a mixture of Units Of Measure which could lead to confusion.

    -com.deciphernow.server.config.stats.time=MILLISECONDS

## Announce the Thrift and RESTful endpoints to Zookeeper
    
    -com.deciphernow.server.config.zk.announcementPoint=/example/service/mywombat/1.0
    -com.deciphernow.server.config.zk.zookeeperConnection=localhost:2181
    
## Enable IP address resolution instead of DNS
If `enableIpAddressResolution` is true and no ethernet interface name assigned to `userNetworkInterfaceName` it will iterate over all interface names looking for a valid IPv4 that is NOT a Loopback.

    -com.deciphernow.server.config.ipAddress.enableIpAddressResolution=false
    -com.deciphernow.server.config.ipAddress.useNetworkInterfaceName=eth0
 
## Ignore creating StatsReceiver for static routes.
The framework auto-magically understands how to create the StatsReceiver for the static route. No need to create it.
   
    -com.deciphernow.server.config.staticRoutes.ignore=/ping

## Assigning ENVIRONMENT variables
For all attributes that are of type __String__ an ENVIRONMENT variable may be defined as in __Example 1__.

Example 1

    -com.deciphernow.server.config.zk.announcementPoint=ENV_ZK_ANNOUNCE
    
To retrieve the value assigned to the __zk.announcementPoint__ you would do the following ([based having followed](CreatingNewMS.md)):


    package com.acme
    
    import com.deciphernow.server.ConfigUtilities
    
    /**
     *
     */
    class MyFirstMicroserviceManager {
    
      /**
        * Keep this. Use to see if the service is accepting connections.
        * @return
        */
      def getPong = {
        val ap = ConfigUtilities.selectValue(com.deciphernow.server.config.zk.announcementPoint.apply))
        "pong\n"
      }
    
    }