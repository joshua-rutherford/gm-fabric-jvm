# Overview
This document describes how to configure the microservice to announce and or bind to different ports, change the hostname etc.

# Announcing and Binding
The attributes for the microservice have been enhanced to allow for separate configuration for announcing and binding ports. As well, one can also define environment variables that will override a configuration.

## Bind attributes

### Bind through configuration.
The following attributes are the default announce and bind attributes of a microservice.

Configure the admin port. The default value is __:9990__.

    -com.deciphernow.server.config.admin.port=:10000
    
Configure the http port. The default value is __:8888__.

    -com.deciphernow.server.config.rest.httpPort=:20000
    
Configure the https port. The default value is __:8999__.

    -com.deciphernow.server.config.rest.httpsPort=:20001
    
Configure the thrift port. The default value is __:9090__.

    -com.deciphernow.server.config.thrift.port=:30000
    
### Bind through environment variables.
The following attributes allow for a defined environment variable to be the assigned as the __bind__  and __announce__ port.
  
Point to the environment variable defining the hostname. The default value is __None__. This overrides __-com.deciphernow.server.config.ipAddress.enableIpAddressResolution__ only if ENV is defined with a value.

    -com.deciphernow.server.config.os.env.hostname
    
Point to the environment variable defining the admin port. The default value is __None__. This overrides __-com.deciphernow.server.config.admin.port__ only if ENV is defined with a value.

    -com.deciphernow.server.config.os.env.adminPort
     
Point to the environment variable defining the http port. The default value is __None__. This overrides __-com.deciphernow.server.config.rest.httpPort__ only if ENV is defined with a value.

    -com.deciphernow.server.config.os.env.httpPort
    
Point to the environment variable defining the https port. The default value is __None__. This overrides __-com.deciphernow.server.config.rest.httpsPort__ only if ENV is defined with a value.

    -com.deciphernow.server.config.os.env.httpsPort
    
Point to the environment variable defining the thrift port. The default value is __None__. This overrides __-com.deciphernow.server.config.thrift.port__ only if ENV is defined with a value.

    -com.deciphernow.server.config.os.env.thriftPort 


## Announce attributes
If any of the announcement attributes are defined then they override any __BIND__ attributes with respect to announcement.

### Announce through configuration
The following attributes define the announcement point of the microservice. If set, they will override all __BIND__ attributes with respect to announcement.


Point to the environment variable defining the hostname. The default value is __None__. This overrides __-com.deciphernow.server.config.ipAddress.enableIpAddressResolution__.

    -com.deciphernow.announcement.config.service.forward.hostname

Point to the environment variable defining the admin port. The default value is __None__. 
    
    -com.deciphernow.announcement.config.service.forward.adminPort

Point to the environment variable defining the http port. The default value is __None__. 
    
    -com.deciphernow.announcement.config.service.forward.httpPort

Point to the environment variable defining the https port. The default value is __None__. 
    
    -com.deciphernow.announcement.config.service.forward.httpsPort

Point to the environment variable defining the thrift port. The default value is __None__.
    
    -com.deciphernow.announcement.config.service.forward.thriftPort

### Announce with environment variables
The following attributes point to environment variables that contain the information. If set, they will override __all__ attributes with respect to announcement.
    
Point to the environment variable defining the hostname. The default value is __None__. This overrides __-com.deciphernow.server.config.ipAddress.enableIpAddressResolution__ only if ENV is defined with a value.

    -com.deciphernow.announcement.config.os.env.hostname
    
Point to the environment variable defining the admin port. The default value is __None__. This overrides __-com.deciphernow.announcement.config.service.forward.adminPort__ only if ENV is defined with a value.

    -com.deciphernow.announcement.config.os.env.adminPort
     
Point to the environment variable defining the http port. The default value is __None__. This overrides __-com.deciphernow.announcement.config.service.forward.httpPort__ only if ENV is defined with a value.

    -com.deciphernow.announcement.config.os.env.httpPort
    
Point to the environment variable defining the https port. The default value is __None__. This overrides __-com.deciphernow.announcement.config.service.forward.httpsPort__ only if ENV is defined with a value.

    -com.deciphernow.announcement.config.os.env.httpsPort
    
Point to the environment variable defining the thrift port. The default value is __None__. This overrides __-com.deciphernow.announcement.config.service.forward.thriftPort__ only if ENV is defined with a value.

    -com.deciphernow.announcement.config.os.env.thriftPort     
