# Overview
The following parameters can be configured in the `etc/parameters.config` file.

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
    

### Bind and Announce
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
 
# Ignore creating StatsReceiver for static routes.
The framework auto-magically understands how to create the StatsReceiver for the static route. No need to create it.
   
    -com.deciphernow.server.config.staticRoutes.ignore=/ping
