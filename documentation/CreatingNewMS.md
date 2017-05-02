# Overview
This document explains how to create a new microservice and it's various components. A maven archetype has been created to assist in creating a new service implementation.  The archetype will create a new maven project that has the following:

## Perquisite
You have to have built the microservice framework. See ![README](../README.md)

You need to install the tanuki software service wrapper delta pack into your local Maven repository.

    $ mkdir -p ~/.m2/repository/tanukisoft/wrapper-delta-pack/3.5.25
    $ cd ~/.m2/repository/tanukisoft/wrapper-delta-pack/3.5.25 
    $ curl -XGET https://wrapper.tanukisoftware.com/download/3.5.25/wrapper-delta-pack-3.5.25.tar.gz > wrapper-delta-pack-3.5.25.tar.gz

## Getting started.
- Open a terminal.
- Go to a location where you would like your ervice to be created at.
- Create the microservice

    $ mvn archetype:generate -DarchetypeGroupId=com.deciphernow -DarchetypeArtifactId=gm-fabric-archetype -DarchetypeVersion=0.1.3

Maven will prompt you for the following information:

| Identifier | Description |
| -----------| ------------|
| groupId | groupId will identify your project uniquely across all projects, so we need to enforce a naming schema. It has to follow the package name rules, which means that it has to be at least a domain name you control, and you can create as many subgroups as you want. <br /><br />Please see the link below for more information:<br />http://maven.apache.org/guides/mini/guide-naming-conventions.html |
| artifactId | artifactId is the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols. If it's a third party jar, you have to take the name of the jar as it's distributed.<br /><br />Please see the link below for more information: <br /> http://maven.apache.org/guides/mini/guide-naming-conventions.html |
| version | version is defaulted and cannot be changed. 0.1.0-SNAPSHOT. |
| package | A package is a group of related types providing access protection and name space management. Note that types refers to classes, interfaces, enumerations, and annotation types. Enumerations and annotation types are special kinds of classes and interfaces, respectively, so types are often referred to in this lesson simply as classes and interfaces.<br /><br />Please see the link below for more information:<br />http://docs.oracle.com/javase/tutorial/java/package/packages.html |
| appName | The appName is the programmatic name for your new service.  The archetype uses this value to generate the class names as defined below. |

Please use the following pattern when coming up with your appName value:

    - Is a single word
    - Is a noun in UpperCamelCase, with the first letter of every word capitalized.
        - Example: MyFirstMicroservice 

For this example, the table below identifies the data entered.

| Question/Statement | Data entered | Description |
|--------------------|--------------|-------------|
| Define value for property 'groupId': : | com.somepackage | Name of group. |
| Define value for property 'artifactId': : | myfirstmicroservice | Name of artifact. |
| Define value for property 'package':  com.somepackage : | | If keeping the package name the same just hit return. |
| Define value for property 'appName': : | MyFirstMicroservice | Entrant point of the microserice, aka the main. |
|  Y: : | Y | Accept all the values you entered |

  
## Project structure
The following module and directory structure is generated.

    - my-first-microservice
        - business
            - pom.xml
            - src/main
                - java/com.somepackage
                - scala/com.somepackage
                    - MyFirstMicroserviceManager.scala
        - client
            - pom.xml
            - src/main
                - java/com.somepackage
                - scala/com.somepackage.client/thrift/
                    - MyFirstMicroserviceClientFactory.scala
        - model
            - pom.xml
            - src/main/thrift/
                - MyFirstMicroservice.thrift
        - server
            - pom.xml
            - src/main
                - assembly
                    - bundle-app.xml
                - java/com.somepackage.thrift/
                    - MyFirstMicroserviceThriftService.java
                - package/etc/
                    - logback.xml
                    - parameters.config
                - resources
                - scala/com.somepackage
                    - MyFirstMicroservice.scala
                    - rest/
                        - MyFirstMicroserviceRestController.java
                - scripts
        - pom.xml

- Within myfirstmicroservice build the project

        $ mvn clean package
     
- To build the thrift sources then
     
        $ mvn clean generate-sources package
        
- To build [RPM](RPM.md)        
    
- Start the microservice
    - Traverse into the microservice:
    
            $ cd myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app/myfirstmicroservice-server-0.1.0-SNAPSHOT/bin
        
    - Start the service in foreground
    
            $ myfirstmicroservice console
            
    - Start the service in background

            $ myfirstmicroservice start
        
- Stopping the microservice
    - If in foreground, just __crtl-c__
    - If in background:
        
            $ myfirstmicroservice stop
            

## Service Ports
The following ports are the default ports:

| Service | Port | Description |
|---------|------|-------------|
| admin | 9990 | Is always HTTP. |
| http | 8888 | |
| https | 8999 | Only active when configured for [Two-Way SSL](TwoWaySSL.md) |
| thrift | 9090 | Port is always the same for either non-SSL or SSL |

## Administration
To access the [administration](Admin.md) and see active services http://localhost:9990/admin

## Testing my microservice
To see your microservice running without SSL.
From the command line:

    cd myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app/myfirstmicroservice-server-0.1.0-SNAPSHOT/bin
    ./myfirst-microserviceserver console
       
In your browser hit the service admin endpoint:
       
    localhost:9990/admin
       
In your browser hit the ping / pong endpoint:

    localhost:8888/ping


## Full example 
![MyFirstMicroservice full example](ExampleGeneratedExec.md)

### Out of the box, the generated microservice does not have SSL configured. 
This makes for easy testing that the base structure was generated correctly.

    package com.somepackage
    
    import java.io.File
    
    import com.somepackage.rest.MyFirstMicroserviceRestController
    import com.somepackage.thrift.MyFirstMicroserviceThriftService
    import com.deciphernow.server.{GMFabricServer, RestServer, ThriftServer}
    import com.deciphernow.server.Implicits._
    import com.deciphernow.server.filters.GenericUriStatsFilter
    
    import scala.concurrent.duration.Duration
    
    object MyFirstMicroservice extends GMFabricServer {
    
      val myFirstMicroserviceManager = new MyFirstMicroserviceManager
    
      // When using impersonating security filters, we need an access manager
      //var accessManager: FileWhitelistImpersonationAccessManager = _
    
      // The access manager will require a whitelist file.  This is one way to use configuration for the file path
      //val whitelistFile = flag[File]("acl.whitelist.file", "ACL whitelist file for user impersonation")
    
      // If we want to create the access manager, do it in the premain block like this.
      // Note we need to do it outside the class body (in premain) because flag parsing occurrs later
      premain {
        //accessManager = new FileWhitelistImpersonationAccessManager(
        //   whitelistFile(), Duration(1, "minute")
        //)
      }
    
      /*
        Assign None to server if no server is going to be defined.
        def thrift = None
       */
      def thrift = Some(new ThriftServer(
        Nil,
        new MyFirstMicroserviceThriftService(myFirstMicroserviceManager)
      ))
    
      /*
        A rest server is always required since the admin server is
        instantiated here.
     */
      def rest = Some(new RestServer(
        Nil,
        Seq(new MyFirstMicroserviceRestController(myFirstMicroserviceManager))
      ))
    
    }


## ThriftServer:
You pass in a sequence of filters and only one service.

    case class ThriftServer(filters: Seq[SimpleFilter[Array[Byte], Array[Byte]]], service: AnyRef)
    
## RestServer:
You pass in a sequence of filters and a sequence of controllers. Though you can pass in more than one controller, it is customary for all RESTful endpoints are
defined in just one Controller.   

    case class RestServer(filters: Seq[Filter[FinagleRequest, FinagleResponse,FinagleRequest, FinagleResponse]],controllers: Seq[Controller])

To see examples of implementation see __Microservice without SSL__ and __Microservice with SSL__.

# How to configure security
See the following pages

![To enable Two-way SSL](TwoWaySSL.md)

![To enable WhitelistClientFilter](WhitelistClientFilter.md)

![To enforce RESTful access ( AclRestFilter )](AclRestFilter.md)



        

    

    
