# Overview

This document defines the required modifications one has to make to microservices that where build upon an older version of the gm-fabric-jvm.

## Upgrading to gm-fabric-jvm-0.2.5

If a microservice was built on gm-fabric-jvm-0.2.2 or less, then you will also have to add the following artifact to the <dependencyManagement> section of the root pom.xml for it to compile.


----
    <dependency>
        <groupId>com.deciphernow</groupId>
        <artifactId>gm-fabric-jvm-config</artifactId>
        <version>${version.gm-fabric}</version>
    </dependency>

----
- [See coding your own parameters.](Config.md)
