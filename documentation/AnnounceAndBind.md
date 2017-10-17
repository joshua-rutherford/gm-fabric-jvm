# Overview
This document describes how to configure the microservice to announce and or bind to different ports.

# Announcing and Binding
The attributes for the microservice have been enhanced to allow for separate configuration for announcing and binding ports. As well, one can also define environment variables that will override a configuration. This is the default configuration.

### Section 1

    :9990
    -com.deciphernow.server.config.admin.port=:10000
    
    :8888
    -com.deciphernow.server.config.rest.httpPort=:20000
    
    :8999
    -com.deciphernow.server.config.rest.httpsPort=:20001
    
    :9090
    -com.deciphernow.server.config.thrift.port=:30000
    
### Section 2
The following attributes allow for a defined environment variable to be the assigned __bind__ port.
  
  None
  -com.deciphernow.server.config.os.env.hostname 
  None
  -com.deciphernow.server.config.os.env.adminPort 
  None
  -com.deciphernow.server.config.os.env.httpPort 
  None
  -com.deciphernow.server.config.os.env.httpsPort 
  None
  -com.deciphernow.server.config.os.env.thriftPort 


### Section 3

all default to None
-com.deciphernow.announcement.config.os.env.hostname
-com.deciphernow.announcement.config.os.env.adminPort
-com.deciphernow.announcement.config.os.env.httpPort
-com.deciphernow.announcement.config.os.env.httpsPort
-com.deciphernow.announcement.config.os.env.thriftPort

### Section 4
The following attributes define the announcement point of the microservice. They override __Section 2 and Section 1__ with respect to announcement. At minimum __adminPort__ and __httpPort__ must be defined for these to take effect.

None
-com.deciphernow.announcement.config.service.forward.hostname
-com.deciphernow.announcement.config.service.forward.adminPort
-com.deciphernow.announcement.config.service.forward.httpPort
-com.deciphernow.announcement.config.service.forward.httpsPort
-com.deciphernow.announcement.config.service.forward.thriftPort

## Order of precedence

# Example configurations

## Example 1

## Example 2

## Example 3