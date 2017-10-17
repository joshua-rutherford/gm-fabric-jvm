# Overview
This document describes how to configure the microservice to announce and or bind to different ports.

# Announcing and Binding
The attributes for the microservice have been enhanced to allow for separate configuration for announcing and binding ports. As well, one can also define environment variables that will override a configuration.

## Bind attributes

### Bind through configuration.
The following attributes are the default announce and bind attributes of a microservice.  If no __Announce attributes__ defined, then these attribute are used in announcement and binding. If these attributes are __NOT__ defined, the microservice will announce and bind to the __default__ values. One should always prefix the port with a colon.

Configure the admin port. The default value is __:9990__

    -com.deciphernow.server.config.admin.port=:10000
    
Configure the http port. The default value is __:8888__

    -com.deciphernow.server.config.rest.httpPort=:20000
    
Configure the https port. The default value is __:8999__

    -com.deciphernow.server.config.rest.httpsPort=:20001
    
Configure the thrift port. The default value is __:9090__

    -com.deciphernow.server.config.thrift.port=:30000
    
### Bind through environment variables.
The following attributes allow for a defined environment variable to be the assigned as the __bind__  and __announce__ port. If no __Announce attributes__ defined, then these attribute are used in announcement and binding. At minimum the __adminPort__ and __httpPort__ must be defined for the bind environment variables to be used. The reason is there will always be an __admin__ and __http__ port but not necessarily a __thrift__ or __https__ port.
  
Point to the environment variable defining the hostname. The default value is __None__

    -com.deciphernow.server.config.os.env.hostname
    
Point to the environment variable defining the admin port. The default value is __None__

    -com.deciphernow.server.config.os.env.adminPort
     
Point to the environment variable defining the http port. The default value is __None__

    -com.deciphernow.server.config.os.env.httpPort
    
Point to the environment variable defining the https port. The default value is __None__

    -com.deciphernow.server.config.os.env.httpsPort
    
Point to the environment variable defining the thrift port. The default value is __None__

    -com.deciphernow.server.config.os.env.thriftPort 


## Announce attributes
If the following 

### Announce with environment variables
The following attributes point to environment variables that contain the information. If the environment variable is __NOT__ defined or has an empty value the 

None
-com.deciphernow.announcement.config.os.env.hostname
-com.deciphernow.announcement.config.os.env.adminPort
-com.deciphernow.announcement.config.os.env.httpPort
-com.deciphernow.announcement.config.os.env.httpsPort
-com.deciphernow.announcement.config.os.env.thriftPort

### Announce through configuration
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