# Overview
This document is a live example of roughly what you will see when you generate your first microservice and execute it.

# Creating the microservice.

## Generation command

    $ mvn archetype:generate -DarchetypeGroupId=com.deciphernow -DarchetypeArtifactId=gm-fabric-archetype -DarchetypeVersion=0.1.3
    
### Output generated

    [INFO] Scanning for projects...
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building Maven Stub Project (No POM) 1
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] >>> maven-archetype-plugin:3.0.0:generate (default-cli) > generate-sources @ standalone-pom >>>
    [INFO] 
    [INFO] <<< maven-archetype-plugin:3.0.0:generate (default-cli) < generate-sources @ standalone-pom <<<
    [INFO] 
    [INFO] --- maven-archetype-plugin:3.0.0:generate (default-cli) @ standalone-pom ---
    [INFO] Generating project in Interactive mode
    [WARNING] Archetype not found in any catalog. Falling back to central repository (http://repo.maven.apache.org/maven2).
    [WARNING] Use -DarchetypeRepository=<your repository> if archetype's repository is elsewhere.
    Downloading: http://repo.maven.apache.org/maven2/com/deciphernow/gm-fabric-archetype/0.1.3/maven-metadata.xml
    
    Downloading: http://repo.maven.apache.org/maven2/com/deciphernow/gm-fabric/0.1.3/maven-metadata.xml
    
    Define value for property 'groupId': com.somepackage
    Define value for property 'artifactId': myfirstmicroservice
    [INFO] Using property: version = 0.1.0-SNAPSHOT
    Define value for property 'package' com.somepackage: : 
    Define value for property 'appName': MyFirstMicroservice
    [INFO] Using property: serviceFrameworkVersion = 0.1.3
    Confirm properties configuration:
    groupId: com.somepackage
    artifactId: myfirstmicroservice
    version: 0.1.0-SNAPSHOT
    package: com.somepackage
    appName: MyFirstMicroservice
    serviceFrameworkVersion: 0.1.3
     Y: : Y
    [INFO] ----------------------------------------------------------------------------
    [INFO] Using following parameters for creating project from Archetype: gm-fabric-archetype:0.1.3
    [INFO] ----------------------------------------------------------------------------
    [INFO] Parameter: groupId, Value: com.somepackage
    [INFO] Parameter: artifactId, Value: myfirstmicroservice
    [INFO] Parameter: version, Value: 0.1.0-SNAPSHOT
    [INFO] Parameter: package, Value: com.somepackage
    [INFO] Parameter: packageInPathFormat, Value: com/somepackage
    [INFO] Parameter: appName, Value: MyFirstMicroservice
    [INFO] Parameter: serviceFrameworkVersion, Value: 0.1.3
    [INFO] Parameter: package, Value: com.somepackage
    [INFO] Parameter: version, Value: 0.1.0-SNAPSHOT
    [INFO] Parameter: groupId, Value: com.somepackage
    [INFO] Parameter: artifactId, Value: myfirstmicroservice
    [INFO] Parent element not overwritten in /home/user/dev/workspaces/ms/myfirstmicroservice/business/pom.xml
    [INFO] Parent element not overwritten in /home/user/dev/workspaces/ms/myfirstmicroservice/client/pom.xml
    [INFO] Parent element not overwritten in /home/user/dev/workspaces/ms/myfirstmicroservice/model/pom.xml
    [INFO] Parent element not overwritten in /home/user/dev/workspaces/ms/myfirstmicroservice/server/pom.xml
    [INFO] Project created from Archetype in dir: /home/user/dev/workspaces/ms/myfirstmicroservice
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 17.938 s
    [INFO] Finished at: 2017-02-23T11:06:45-05:00
    [INFO] Final Memory: 16M/189M
    [INFO] ------------------------------------------------------------------------
    
## Compiling MyFirstMicroservice
    
    $ cd myfirstmicroservice
    $ mvn clean generate-sources package
    
### Compiled output

    [INFO] Scanning for projects...
    [WARNING] 
    [WARNING] Some problems were encountered while building the effective model for com.somepackage:myfirstmicroservice-business:jar:0.1.0-SNAPSHOT
    [WARNING] 'build.plugins.plugin.version' for net.alchim31.maven:scala-maven-plugin is missing. @ com.somepackage:myfirstmicroservice-business:[unknown-version], /home/user/dev/workspaces/ms/myfirstmicroservice/business/pom.xml, line 28, column 21
    [WARNING] 
    [WARNING] Some problems were encountered while building the effective model for com.somepackage:myfirstmicroservice-client:jar:0.1.0-SNAPSHOT
    [WARNING] 'build.plugins.plugin.version' for net.alchim31.maven:scala-maven-plugin is missing. @ com.somepackage:myfirstmicroservice-client:[unknown-version], /home/user/dev/workspaces/ms/myfirstmicroservice/client/pom.xml, line 32, column 21
    [WARNING] The expression ${version} is deprecated. Please use ${project.version} instead.
    [WARNING] 
    [WARNING] Some problems were encountered while building the effective model for com.somepackage:myfirstmicroservice-server:jar:0.1.0-SNAPSHOT
    [WARNING] 'build.plugins.plugin.version' for net.alchim31.maven:scala-maven-plugin is missing. @ com.somepackage:myfirstmicroservice-server:[unknown-version], /home/user/dev/workspaces/ms/myfirstmicroservice/server/pom.xml, line 147, column 21
    [WARNING] The expression ${artifactId} is deprecated. Please use ${project.artifactId} instead.
    [WARNING] The expression ${version} is deprecated. Please use ${project.version} instead.
    [WARNING] The expression ${version} is deprecated. Please use ${project.version} instead.
    [WARNING] 
    [WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
    [WARNING] 
    [WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
    [WARNING] 
    [INFO] ------------------------------------------------------------------------
    [INFO] Reactor Build Order:
    [INFO] 
    [INFO] myfirstmicroservice
    [INFO] myfirstmicroservice-business
    [INFO] myfirstmicroservice-model
    [INFO] myfirstmicroservice-client
    [INFO] myfirstmicroservice-server
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building myfirstmicroservice 0.1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ myfirstmicroservice ---
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building myfirstmicroservice-business 0.1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ myfirstmicroservice-business ---
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ myfirstmicroservice-business ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/main/resources
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:add-source (scala-compile-first) @ myfirstmicroservice-business ---
    [INFO] Add Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/main/scala
    [INFO] Add Test Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/test/scala
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:compile (scala-compile-first) @ myfirstmicroservice-business ---
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/main/java:-1: info: compiling
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/main/scala:-1: info: compiling
    [INFO] Compiling 1 source files to /home/user/dev/workspaces/ms/myfirstmicroservice/business/target/classes at 1487866017753
    [INFO] prepare-compile in 0 s
    [INFO] compile in 1 s
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ myfirstmicroservice-business ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ myfirstmicroservice-business ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/business/src/test/resources
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:testCompile (scala-test-compile) @ myfirstmicroservice-business ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ myfirstmicroservice-business ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ myfirstmicroservice-business ---
    [INFO] No tests to run.
    [INFO] 
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ myfirstmicroservice-business ---
    [INFO] Building jar: /home/user/dev/workspaces/ms/myfirstmicroservice/business/target/myfirstmicroservice-business-0.1.0-SNAPSHOT.jar
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building myfirstmicroservice-model 0.1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ myfirstmicroservice-model ---
    [INFO] 
    [INFO] --- scrooge-maven-plugin:4.14.0:compile (thrift-sources) @ myfirstmicroservice-model ---
    [INFO] finding thrift files in dependencies
    [INFO] finding thrift files in referenced (reactor) projects
    [INFO] compiling thrift files [/home/user/dev/workspaces/ms/myfirstmicroservice/model/src/main/thrift/MyFirstMicroservice.thrift] with Scrooge
    [INFO] 
    [INFO] --- scrooge-maven-plugin:4.14.0:compile (thrift-sources) @ myfirstmicroservice-model ---
    [INFO] finding thrift files in dependencies
    [INFO] finding thrift files in referenced (reactor) projects
    [INFO] Generated thrift files up to date, skipping compile.
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ myfirstmicroservice-model ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/model/src/main/resources
    [INFO] Copying 1 resource
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/generated-resources
    [INFO] Copying 1 resource
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/generated-resources
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ myfirstmicroservice-model ---
    [INFO] Changes detected - recompiling the module!
    [INFO] Compiling 1 source file to /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/classes
    [WARNING] /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/generated-sources/thrift/scrooge/com/somepackage/thrift/MyFirstMicroservice.java: /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/generated-sources/thrift/scrooge/com/somepackage/thrift/MyFirstMicroservice.java uses unchecked or unsafe operations.
    [WARNING] /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/generated-sources/thrift/scrooge/com/somepackage/thrift/MyFirstMicroservice.java: Recompile with -Xlint:unchecked for details.
    [INFO] 
    [INFO] --- scrooge-maven-plugin:4.14.0:testCompile (thrift-test-sources) @ myfirstmicroservice-model ---
    [INFO] finding thrift files in dependencies
    [INFO] finding thrift files in referenced (reactor) projects
    [INFO] No thrift files to compile.
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ myfirstmicroservice-model ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/model/src/test/resources
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ myfirstmicroservice-model ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ myfirstmicroservice-model ---
    [INFO] No tests to run.
    [INFO] 
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ myfirstmicroservice-model ---
    [INFO] Building jar: /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/myfirstmicroservice-model-0.1.0-SNAPSHOT.jar
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building myfirstmicroservice-client 0.1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ myfirstmicroservice-client ---
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ myfirstmicroservice-client ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/main/resources
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:add-source (scala-compile-first) @ myfirstmicroservice-client ---
    [INFO] Add Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/main/scala
    [INFO] Add Test Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/test/scala
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:compile (scala-compile-first) @ myfirstmicroservice-client ---
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/main/java:-1: info: compiling
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/main/scala:-1: info: compiling
    [INFO] Compiling 1 source files to /home/user/dev/workspaces/ms/myfirstmicroservice/client/target/classes at 1487866022716
    [INFO] prepare-compile in 0 s
    [INFO] compile in 1 s
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ myfirstmicroservice-client ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ myfirstmicroservice-client ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/client/src/test/resources
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:testCompile (scala-test-compile) @ myfirstmicroservice-client ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ myfirstmicroservice-client ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO] 
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ myfirstmicroservice-client ---
    [INFO] No tests to run.
    [INFO] 
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ myfirstmicroservice-client ---
    [INFO] Building jar: /home/user/dev/workspaces/ms/myfirstmicroservice/client/target/myfirstmicroservice-client-0.1.0-SNAPSHOT.jar
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building myfirstmicroservice-server 0.1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [WARNING] The POM for tanukisoft:wrapper-delta-pack:tar.gz:3.5.25 is missing, no dependency information available
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ myfirstmicroservice-server ---
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-pre-install) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-post-install) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-pre-remove) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-post-remove) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-pre-install) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-post-install) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-pre-remove) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:copy-resources (create-post-remove) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 1 resource
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 0 resource
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:add-source (scala-compile-first) @ myfirstmicroservice-server ---
    [INFO] Add Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/server/src/main/scala
    [INFO] Add Test Source directory: /home/user/dev/workspaces/ms/myfirstmicroservice/server/src/test/scala
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:compile (scala-compile-first) @ myfirstmicroservice-server ---
    [WARNING] Missing POM for tanukisoft:wrapper-delta-pack:tar.gz:3.5.25
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/server/src/main/java:-1: info: compiling
    [INFO] /home/user/dev/workspaces/ms/myfirstmicroservice/server/src/main/scala:-1: info: compiling
    [INFO] Compiling 3 source files to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/classes at 1487866024923
    [INFO] prepare-compile in 0 s
    [INFO] compile in 2 s
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ myfirstmicroservice-server ---
    [INFO] Changes detected - recompiling the module!
    [INFO] Compiling 1 source file to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/classes
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ myfirstmicroservice-server ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /home/user/dev/workspaces/ms/myfirstmicroservice/server/src/test/resources
    [INFO] 
    [INFO] --- scala-maven-plugin:3.2.2:testCompile (scala-test-compile) @ myfirstmicroservice-server ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ myfirstmicroservice-server ---
    [INFO] Nothing to compile - all classes are up to date
    [INFO] 
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ myfirstmicroservice-server ---
    [INFO] No tests to run.
    [INFO] 
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ myfirstmicroservice-server ---
    [INFO] Building jar: /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT.jar
    [INFO] 
    [INFO] --- maven-dependency-plugin:2.8:unpack (default) @ myfirstmicroservice-server ---
    [INFO] Configured Artifact: tanukisoft:wrapper-delta-pack:?:tar.gz
    [INFO] Unpacking /home/user/.m2/repository/tanukisoft/wrapper-delta-pack/3.5.25/wrapper-delta-pack-3.5.25.tar.gz to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target with includes "" and excludes ""
    [INFO] Expanding: /home/user/.m2/repository/tanukisoft/wrapper-delta-pack/3.5.25/wrapper-delta-pack-3.5.25.tar.gz into /home/user/dev/workspaces/ms/myfirstmicroservice/server/target
    [INFO] 
    [INFO] --- appassembler-maven-plugin:1.9:generate-daemons (generate-jsw-scripts) @ myfirstmicroservice-server ---
    [INFO] Installing artifact /home/user/.m2/repository/com/deciphernow/gm-fabric-core/0.1.3/gm-fabric-core-0.1.3.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/gm-fabric-core-0.1.3.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-logging_2.11/6.41.0/util-logging_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-logging_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra-thrift_2.11/2.8.0/finatra-thrift_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-thrift_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-thrift_2.11/2.8.0/inject-thrift_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-thrift_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-thriftmux_2.11/6.42.0/finagle-thriftmux_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-thriftmux_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/yaml/snakeyaml/1.12/snakeyaml-1.12.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/snakeyaml-1.12.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/typesafe/scala-logging/scala-logging-api_2.11/2.1.2/scala-logging-api_2.11-2.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-logging-api_2.11-2.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/typesafe/scala-logging/scala-logging-slf4j_2.11/2.1.2/scala-logging-slf4j_2.11-2.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-logging-slf4j_2.11-2.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/slf4j/jul-to-slf4j/1.7.9/jul-to-slf4j-1.7.9.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jul-to-slf4j-1.7.9.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/apache/httpcomponents/httpclient/4.3.5/httpclient-4.3.5.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/httpclient-4.3.5.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-logging-1.1.3.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-codec/commons-codec/1.6/commons-codec-1.6.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-codec-1.6.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/httpcore-4.3.2.jar
    [INFO] Installing artifact /home/user/dev/workspaces/ms/myfirstmicroservice/model/target/myfirstmicroservice-model-0.1.0-SNAPSHOT.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/myfirstmicroservice-model-0.1.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/scrooge-core_2.11/4.14.0/scrooge-core_2.11-4.14.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scrooge-core_2.11-4.14.0.jar
    [INFO] Installing artifact /home/user/dev/workspaces/ms/myfirstmicroservice/business/target/myfirstmicroservice-business-0.1.0-SNAPSHOT.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/myfirstmicroservice-business-0.1.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-core_2.11/6.42.0/finagle-core_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-core_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-toggle_2.11/6.42.0/finagle-toggle_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-toggle_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-app_2.11/6.41.0/util-app_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-app_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-cache_2.11/6.41.0/util-cache_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-cache_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-codec_2.11/6.41.0/util-codec_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-codec_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-hashing_2.11/6.41.0/util-hashing_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-hashing_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-jvm_2.11/6.41.0/util-jvm_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-jvm_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-lint_2.11/6.41.0/util-lint_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-lint_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-registry_2.11/6.41.0/util-registry_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-registry_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-security_2.11/6.41.0/util-security_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-security_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-stats_2.11/6.41.0/util-stats_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-stats_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/github/ben-manes/caffeine/caffeine/2.3.4/caffeine-2.3.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/caffeine-2.3.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jsr305-2.0.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty/3.10.1.Final/netty-3.10.1.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-3.10.1.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-stats_2.11/6.42.0/finagle-stats_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-stats_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-http_2.11/6.42.0/finagle-http_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-http_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-base-http_2.11/6.42.0/finagle-base-http_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-base-http_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-netty4-http_2.11/6.42.0/finagle-netty4-http_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-netty4-http_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-collection_2.11/6.41.0/util-collection_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-collection_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-lang-2.6.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/metrics/0.0.38/metrics-0.0.38.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/metrics-0.0.38.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/base/0.0.115/base-0.0.115.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/base-0.0.115.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/collections/0.0.110/collections-0.0.110.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/collections-0.0.110.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/quantity/0.0.99/quantity-0.0.99.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/quantity-0.0.99.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/stats-util/0.0.59/stats-util-0.0.59.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/stats-util-0.0.59.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/util-executor-service-shutdown/0.0.67/util-executor-service-shutdown-0.0.67.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-executor-service-shutdown-0.0.67.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/util-system-mocks/0.0.104/util-system-mocks-0.0.104.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-system-mocks-0.0.104.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-events_2.11/6.41.0/util-events_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-events_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.8.4/jackson-core-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-core-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.8.4/jackson-databind-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-databind-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/module/jackson-module-scala_2.11/2.8.4/jackson-module-scala_2.11-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-module-scala_2.11-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/module/jackson-module-paranamer/2.8.4/jackson-module-paranamer-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-module-paranamer-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/paranamer-2.8.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/guava/guava/16.0.1/guava-16.0.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/guava-16.0.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-core_2.11/6.41.0/util-core_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-core_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-function_2.11/6.41.0/util-function_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-function_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/scala-reflect/2.11.8/scala-reflect-2.11.8.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-reflect-2.11.8.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/modules/scala-parser-combinators_2.11/1.0.4/scala-parser-combinators_2.11-1.0.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-parser-combinators_2.11-1.0.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-thrift_2.11/6.42.0/finagle-thrift_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-thrift_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-netty4_2.11/6.42.0/finagle-netty4_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-netty4_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-codec-http/4.1.8.Final/netty-codec-http-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-codec-http-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-codec/4.1.8.Final/netty-codec-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-codec-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-handler/4.1.8.Final/netty-handler-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-handler-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-buffer/4.1.8.Final/netty-buffer-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-buffer-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-common/4.1.8.Final/netty-common-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-common-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-transport/4.1.8.Final/netty-transport-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-transport-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-resolver/4.1.8.Final/netty-resolver-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-resolver-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-handler-proxy/4.1.8.Final/netty-handler-proxy-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-handler-proxy-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/io/netty/netty-codec-socks/4.1.8.Final/netty-codec-socks-4.1.8.Final.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/netty-codec-socks-4.1.8.Final.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/libthrift/0.5.0-7/libthrift-0.5.0-7.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/libthrift-0.5.0-7.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-serversets_2.11/6.42.0/finagle-serversets_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-serversets_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-zk-common_2.11/6.41.0/util-zk-common_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-zk-common_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/util-zk_2.11/6.41.0/util-zk_2.11-6.41.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-zk_2.11-6.41.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/zookeeper/client/0.0.80/client-0.0.80.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/client-0.0.80.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/net-util/0.0.102/net-util-0.0.102.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/net-util-0.0.102.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/zookeeper/group/0.0.91/group-0.0.91.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/group-0.0.91.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/apache/zookeeper/zookeeper/3.5.0-alpha/zookeeper-3.5.0-alpha.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/zookeeper-3.5.0-alpha.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/slf4j/slf4j-log4j12/1.7.5/slf4j-log4j12-1.7.5.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/slf4j-log4j12-1.7.5.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-cli/commons-cli/1.2/commons-cli-1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-cli-1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/log4j/log4j/1.2.15/log4j-1.2.15.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/log4j-1.2.15.jar
    [INFO] Installing artifact /home/user/.m2/repository/javax/mail/mail/1.4/mail-1.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/mail-1.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/javax/activation/activation/1.1/activation-1.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/activation-1.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/net/java/dev/javacc/javacc/5.0/javacc-5.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/javacc-5.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/io-json/0.0.54/io-json-0.0.54.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/io-json-0.0.54.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/io/0.0.68/io-0.0.68.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/io-0.0.68.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/code/gson/gson/2.3.1/gson-2.3.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/gson-2.3.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/zookeeper/server-set/1.0.1.2/server-set-1.0.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/server-set-1.0.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/args/0.2.41/args-0.2.41.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/args-0.2.41.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/io-thrift/0.0.67/io-thrift-0.0.67.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/io-thrift-0.0.67.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/dynamic-host-set/0.0.56/dynamic-host-set-0.0.56.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/dynamic-host-set-0.0.56.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/util/0.0.1.2/util-0.0.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-0.0.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/jdk-logging/0.0.82/jdk-logging-0.0.82.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jdk-logging-0.0.82.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/stats/0.0.115/stats-0.0.115.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/stats-0.0.115.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/stat/0.0.74/stat-0.0.74.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/stat-0.0.74.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/stats-registry/0.0.1/stats-registry-0.0.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/stats-registry-0.0.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/application-action/0.0.90/application-action-0.0.90.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/application-action-0.0.90.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/util-sampler/0.0.78/util-sampler-0.0.78.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/util-sampler-0.0.78.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/stats-provider/0.0.93/stats-provider-0.0.93.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/stats-provider-0.0.93.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/args-core/0.1.37/args-core-0.1.37.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/args-core-0.1.37.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/common/service-thrift/1.0.55/service-thrift-1.0.55.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/service-thrift-1.0.55.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/slf4j/slf4j-api/1.7.9/slf4j-api-1.7.9.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/slf4j-api-1.7.9.jar
    [INFO] Installing artifact /home/user/.m2/repository/ch/qos/logback/logback-core/1.1.2/logback-core-1.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/logback-core-1.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/ch/qos/logback/logback-classic/1.1.2/logback-classic-1.1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/logback-classic-1.1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/scala-library/2.11.8/scala-library-2.11.8.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-library-2.11.8.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/scala-compiler/2.11.8/scala-compiler-2.11.8.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-compiler-2.11.8.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/modules/scala-xml_2.11/1.0.4/scala-xml_2.11-1.0.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-xml_2.11-1.0.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/twitter-server_2.11/1.27.0/twitter-server_2.11-1.27.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/twitter-server_2.11-1.27.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-zipkin-core_2.11/6.42.0/finagle-zipkin-core_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-zipkin-core_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra-http_2.11/2.8.0/finatra-http_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-http_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra-jackson_2.11/2.8.0/finatra-jackson_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-jackson_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-joda/2.8.4/jackson-datatype-joda-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-datatype-joda-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/scala-lang/scalap/2.11.8/scalap-2.11.8.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scalap-2.11.8.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra/finatra-scalap-compiler-deps_2.11/2.0.0/finatra-scalap-compiler-deps_2.11-2.0.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-scalap-compiler-deps_2.11-2.0.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra-slf4j_2.11/2.8.0/finatra-slf4j_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-slf4j_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finatra-utils_2.11/2.8.0/finatra-utils_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finatra-utils_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/github/spullara/mustache/java/compiler/0.8.18/compiler-0.8.18.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/compiler-0.8.18.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/bijection-util_2.11/0.9.4/bijection-util_2.11-0.9.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/bijection-util_2.11-0.9.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/bijection-core_2.11/0.9.4/bijection-core_2.11-0.9.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/bijection-core_2.11-0.9.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-exp_2.11/6.42.0/finagle-exp_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-exp_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-fileupload/commons-fileupload/1.3.1/commons-fileupload-1.3.1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-fileupload-1.3.1.jar
    [INFO] Installing artifact /home/user/.m2/repository/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/servlet-api-2.5.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/inject/extensions/guice-testlib/4.0/guice-testlib-4.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/guice-testlib-4.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/inject/guice/4.0/guice-4.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/guice-4.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/aopalliance-1.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-core_2.11/2.8.0/inject-core_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-core_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.8.4/jackson-annotations-2.8.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jackson-annotations-2.8.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/inject/extensions/guice-assistedinject/4.0/guice-assistedinject-4.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/guice-assistedinject-4.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/google/inject/extensions/guice-multibindings/4.0/guice-multibindings-4.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/guice-multibindings-4.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/commons-io/commons-io/2.4/commons-io-2.4.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/commons-io-2.4.jar
    [INFO] Installing artifact /home/user/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/javax.inject-1.jar
    [INFO] Installing artifact /home/user/.m2/repository/joda-time/joda-time/2.5/joda-time-2.5.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/joda-time-2.5.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/github/nscala-time/nscala-time_2.11/1.6.0/nscala-time_2.11-1.6.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/nscala-time_2.11-1.6.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/net/codingwell/scala-guice_2.11/4.1.0/scala-guice_2.11-4.1.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/scala-guice_2.11-4.1.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/clapper/grizzled-slf4j_2.11/1.3.0/grizzled-slf4j_2.11-1.3.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/grizzled-slf4j_2.11-1.3.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/joda/joda-convert/1.2/joda-convert-1.2.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/joda-convert-1.2.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-modules_2.11/2.8.0/inject-modules_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-modules_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-app_2.11/2.8.0/inject-app_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-app_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-server_2.11/2.8.0/inject-server_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-server_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-slf4j_2.11/2.8.0/inject-slf4j_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-slf4j_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/slf4j/jcl-over-slf4j/1.7.21/jcl-over-slf4j-1.7.21.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/jcl-over-slf4j-1.7.21.jar
    [INFO] Installing artifact /home/user/.m2/repository/org/slf4j/log4j-over-slf4j/1.7.21/log4j-over-slf4j-1.7.21.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/log4j-over-slf4j-1.7.21.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/inject-utils_2.11/2.8.0/inject-utils_2.11-2.8.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/inject-utils_2.11-2.8.0.jar
    [INFO] Installing artifact /home/user/.m2/repository/com/twitter/finagle-mux_2.11/6.42.0/finagle-mux_2.11-6.42.0.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/finagle-mux_2.11-6.42.0.jar
    [INFO] Installing artifact /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT.jar to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/appassembler/jsw/myfirstmicroservice-server/lib/myfirstmicroservice-server-0.1.0.jar
    [INFO] 
    [INFO] --- maven-assembly-plugin:2.5.2:single (default) @ myfirstmicroservice-server ---
    [INFO] Building tar: /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app.tgz
    [INFO] Copying files to /home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app
    [INFO] ------------------------------------------------------------------------
    [INFO] Reactor Summary:
    [INFO] 
    [INFO] myfirstmicroservice ................................ SUCCESS [  0.095 s]
    [INFO] myfirstmicroservice-business ....................... SUCCESS [  2.410 s]
    [INFO] myfirstmicroservice-model .......................... SUCCESS [  1.639 s]
    [INFO] myfirstmicroservice-client ......................... SUCCESS [  3.389 s]
    [INFO] myfirstmicroservice-server ......................... SUCCESS [  6.126 s]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 13.806 s
    [INFO] Finished at: 2017-02-23T11:07:10-05:00
    [INFO] Final Memory: 50M/387M
    [INFO] ------------------------------------------------------------------------ 
    
## Executing MyFirstMicroservice
   
    $ server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app/myfirstmicroservice-server-0.1.0-SNAPSHOT/bin/myfirstmicroservice-server console
    
### Execution output

    Running myfirstmicroservice-server...
    wrapper  | --> Wrapper Started as Console
    wrapper  | Java Service Wrapper Community Edition 64-bit 3.5.25
    wrapper  |   Copyright (C) 1999-2014 Tanuki Software, Ltd. All Rights Reserved.
    wrapper  |     http://wrapper.tanukisoftware.com
    wrapper  | 
    wrapper  | Launching a JVM...
    jvm 1    | WrapperManager: Initializing...
    jvm 1    | Feb 23, 2017 11:07:24 AM com.twitter.app.App$$anonfun$register$1 apply
    jvm 1    | WARNING: Multiple com.twitter.app.App main methods called. com.somepackage.MyFirstMicroservice, then com.deciphernow.server.thrift.GMFabricThriftServer
    jvm 1    | SLF4J: Class path contains multiple SLF4J bindings.
    jvm 1    | SLF4J: Found binding in [jar:file:/home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app/myfirstmicroservice-server-0.1.0-SNAPSHOT/lib/logback-classic-1.1.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    jvm 1    | SLF4J: Found binding in [jar:file:/home/user/dev/workspaces/ms/myfirstmicroservice/server/target/myfirstmicroservice-server-0.1.0-SNAPSHOT-app/myfirstmicroservice-server-0.1.0-SNAPSHOT/lib/slf4j-log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    jvm 1    | SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
    jvm 1    | SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
    jvm 1    | 11:07:24.639 [WrapperSimpleAppMain] INFO  c.d.s.thrift.GMFabricThriftServer - Process started
    jvm 1    | Feb 23, 2017 11:07:25 AM com.twitter.finagle.Init$$anonfun$4 apply$mcV$sp
    jvm 1    | INFO: Finagle version 6.42.0 (rev=f48520b6809792d8cb87c5d81a13075fd01c051d) built at 20170203-165908
    jvm 1    | Feb 23, 2017 11:07:25 AM com.deciphernow.server.GMFabricServer$$anonfun$main$8 apply
    jvm 1    | INFO: thrift server started on port :9090
    jvm 1    | Feb 23, 2017 11:07:25 AM com.deciphernow.server.GMFabricServer$$anonfun$com$deciphernow$server$GMFabricServer$$announce$1 apply
    jvm 1    | WARNING: Zookeeper announcement point not configured!  Not announcing thrift::non-ssl services to Zookeeper!
    jvm 1    | Feb 23, 2017 11:07:25 AM com.twitter.finagle.http.HttpMuxer$$anonfun$4 apply
    jvm 1    | INFO: HttpMuxer[/admin/metrics.json] = com.twitter.finagle.stats.MetricsExporter(<function1>)
    jvm 1    | Feb 23, 2017 11:07:25 AM com.twitter.finagle.http.HttpMuxer$$anonfun$4 apply
    jvm 1    | INFO: HttpMuxer[/admin/per_host_metrics.json] = com.twitter.finagle.stats.HostMetricsExporter(<function1>)
    jvm 1    | 11:07:25.675 [WrapperSimpleAppMain] INFO  c.t.i.logging.Slf4jBridgeUtility$ - org.slf4j.bridge.SLF4JBridgeHandler installed.
    jvm 1    | 11:07:25.683 [WrapperSimpleAppMain] WARN  com.somepackage.MyFirstMicroservice - Zookeeper announcement point not configured!  Not announcing http services to Zookeeper!
    jvm 1    | 11:07:25.683 [WrapperSimpleAppMain] WARN  com.somepackage.MyFirstMicroservice - Zookeeper announcement point not configured!  Not announcing https services to Zookeeper!
    jvm 1    | 11:07:25.683 [WrapperSimpleAppMain] WARN  com.somepackage.MyFirstMicroservice - Zookeeper announcement point not configured!  Not announcing admin services to Zookeeper!
    jvm 1    | 11:07:25.683 [WrapperSimpleAppMain] WARN  com.twitter.app.App$ - Multiple com.twitter.app.App main methods called. com.deciphernow.server.thrift.GMFabricThriftServer, then com.deciphernow.server.rest.GMFabricRestServer
    jvm 1    | 11:07:25.683 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Process started
    jvm 1    | 11:07:25.733 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer -  => com.twitter.server.handler.AdminRedirectHandler
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin => com.twitter.server.handler.SummaryHandler
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/server_info => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/contention => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/lint => com.twitter.server.handler.LintHandler
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/lint.json => com.twitter.server.handler.LintHandler
    jvm 1    | 11:07:25.734 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/failedlint => com.twitter.server.handler.FailedLintRuleHandler
    jvm 1    | 11:07:25.735 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/threads => com.twitter.server.handler.ThreadsHandler
    jvm 1    | 11:07:25.735 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/threads.json => com.twitter.server.handler.ThreadsHandler
    jvm 1    | 11:07:25.735 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/announcer => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:25.735 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/dtab => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:25.735 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/heap => com.twitter.server.handler.HeapResourceHandler
    jvm 1    | 11:07:25.736 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/profile => com.twitter.server.handler.ProfileResourceHandler
    jvm 1    | 11:07:25.736 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/contention => com.twitter.server.handler.ProfileResourceHandler
    jvm 1    | 11:07:25.742 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/ping => com.twitter.server.handler.ReplyHandler
    jvm 1    | 11:07:25.742 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/shutdown => com.twitter.server.handler.ShutdownHandler
    jvm 1    | 11:07:25.742 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/tracing => com.twitter.server.handler.TracingHandler
    jvm 1    | 11:07:25.742 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/events => com.twitter.server.handler.EventsHandler
    jvm 1    | 11:07:25.743 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/events/record/ => com.twitter.server.handler.EventRecordingHandler
    jvm 1    | 11:07:25.743 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/logging => com.twitter.server.handler.LoggingHandler
    jvm 1    | 11:07:25.743 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/metrics => com.twitter.server.handler.MetricQueryHandler
    jvm 1    | 11:07:25.743 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/clients/ => com.twitter.server.handler.ClientRegistryHandler
    jvm 1    | 11:07:25.743 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/servers/ => com.twitter.server.handler.ServerRegistryHandler
    jvm 1    | 11:07:25.744 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/files/ => com.twitter.server.handler.ResourceHandler
    jvm 1    | 11:07:25.745 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/registry.json => com.twitter.server.handler.RegistryHandler
    jvm 1    | 11:07:25.745 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/toggles => com.twitter.server.handler.ToggleHandler
    jvm 1    | 11:07:25.745 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/toggles/ => com.twitter.server.handler.ToggleHandler
    jvm 1    | 11:07:25.745 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /favicon.ico => com.twitter.server.handler.ResourceHandler
    jvm 1    | 11:07:25.745 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/histograms => com.twitter.server.handler.HistogramQueryHandler
    jvm 1    | 11:07:25.746 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/histograms.json => com.twitter.server.handler.HistogramQueryHandler
    jvm 1    | 11:07:25.748 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Serving admin http on 0.0.0.0/0.0.0.0:9990
    jvm 1    | 11:07:25.783 [WrapperSimpleAppMain] DEBUG i.n.u.i.l.InternalLoggerFactory - Using SLF4J as the default logging framework
    jvm 1    | 11:07:25.784 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - -Dio.netty.noUnsafe: false
    jvm 1    | 11:07:25.787 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - java.nio.Buffer.address: available
    jvm 1    | 11:07:25.787 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - sun.misc.Unsafe.theUnsafe: available
    jvm 1    | 11:07:25.787 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - sun.misc.Unsafe.copyMemory: available
    jvm 1    | 11:07:25.788 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - direct buffer constructor: available
    jvm 1    | 11:07:25.788 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - java.nio.Bits.unaligned: available, true
    jvm 1    | 11:07:25.789 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent0 - java.nio.DirectByteBuffer.<init>(long, int): available
    jvm 1    | 11:07:25.789 [WrapperSimpleAppMain] DEBUG io.netty.util.internal.Cleaner0 - java.nio.ByteBuffer.cleaner(): available
    jvm 1    | 11:07:25.790 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - Java version: 8
    jvm 1    | 11:07:25.790 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - sun.misc.Unsafe: available
    jvm 1    | 11:07:25.790 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - -Dio.netty.noJavassist: false
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - Javassist: unavailable
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - You don't have Javassist in your class path or you don't have enough permission to load dynamically generated classes.  Please check the configuration for better performance.
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - -Dio.netty.tmpdir: /tmp (java.io.tmpdir)
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - -Dio.netty.bitMode: 64 (sun.arch.data.model)
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - -Dio.netty.noPreferDirect: false
    jvm 1    | 11:07:25.791 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - io.netty.maxDirectMemory: 921174016 bytes
    jvm 1    | 11:07:25.816 [WrapperSimpleAppMain] DEBUG i.n.c.MultithreadEventLoopGroup - -Dio.netty.eventLoopThreads: 4
    jvm 1    | 11:07:25.830 [WrapperSimpleAppMain] DEBUG io.netty.channel.nio.NioEventLoop - -Dio.netty.noKeySetOptimization: false
    jvm 1    | 11:07:25.830 [WrapperSimpleAppMain] DEBUG io.netty.channel.nio.NioEventLoop - -Dio.netty.selectorAutoRebuildThreshold: 512
    jvm 1    | 11:07:25.832 [WrapperSimpleAppMain] DEBUG i.n.util.internal.PlatformDependent - org.jctools-core.MpscChunkedArrayQueue: available
    jvm 1    | 11:07:25.861 [WrapperSimpleAppMain] DEBUG io.netty.channel.DefaultChannelId - -Dio.netty.processId: 4738 (auto-detected)
    jvm 1    | 11:07:25.863 [WrapperSimpleAppMain] DEBUG io.netty.util.NetUtil - -Djava.net.preferIPv4Stack: false
    jvm 1    | 11:07:25.863 [WrapperSimpleAppMain] DEBUG io.netty.util.NetUtil - -Djava.net.preferIPv6Addresses: false
    jvm 1    | 11:07:25.865 [WrapperSimpleAppMain] DEBUG io.netty.util.NetUtil - Loopback interface: lo (lo, 0:0:0:0:0:0:0:1%lo)
    jvm 1    | 11:07:25.866 [WrapperSimpleAppMain] DEBUG io.netty.util.NetUtil - /proc/sys/net/core/somaxconn: 128
    jvm 1    | 11:07:25.867 [WrapperSimpleAppMain] DEBUG io.netty.channel.DefaultChannelId - -Dio.netty.machineId: 08:00:27:ff:fe:9a:68:53 (auto-detected)
    jvm 1    | 11:07:25.870 [WrapperSimpleAppMain] DEBUG i.n.util.internal.ThreadLocalRandom - -Dio.netty.initialSeedUniquifier: 0xe3f1377c0a1fd0ce
    jvm 1    | 11:07:25.880 [WrapperSimpleAppMain] DEBUG io.netty.util.ResourceLeakDetector - -Dio.netty.leakDetection.level: simple
    jvm 1    | 11:07:25.880 [WrapperSimpleAppMain] DEBUG io.netty.util.ResourceLeakDetector - -Dio.netty.leakDetection.maxRecords: 4
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.numHeapArenas: 4
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.numDirectArenas: 4
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.pageSize: 8192
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.maxOrder: 4
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.chunkSize: 131072
    jvm 1    | 11:07:25.890 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.tinyCacheSize: 512
    jvm 1    | 11:07:25.891 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.smallCacheSize: 256
    jvm 1    | 11:07:25.891 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.normalCacheSize: 64
    jvm 1    | 11:07:25.892 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.maxCachedBufferCapacity: 32768
    jvm 1    | 11:07:25.892 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.cacheTrimInterval: 8192
    jvm 1    | 11:07:25.892 [WrapperSimpleAppMain] DEBUG i.n.buffer.PooledByteBufAllocator - -Dio.netty.allocator.useCacheForAllThreads: true
    jvm 1    | 11:07:25.899 [WrapperSimpleAppMain] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.allocator.type: pooled
    jvm 1    | 11:07:25.899 [WrapperSimpleAppMain] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.threadLocalDirectBufferSize: 65536
    jvm 1    | 11:07:25.899 [WrapperSimpleAppMain] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.maxThreadLocalCharBufferSize: 16384
    jvm 1    | 11:07:25.900 [WrapperSimpleAppMain] WARN  io.netty.bootstrap.ServerBootstrap - Unknown channel option 'SO_LINGER' for channel '[id: 0x5b263209]'
    jvm 1    | 11:07:26.171 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: shutdown.time = 1.minutes
    jvm 1    | 11:07:26.172 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.level = INFO
    jvm 1    | 11:07:26.172 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: local.doc.root = 
    jvm 1    | 11:07:26.172 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: https.port = 
    jvm 1    | 11:07:26.172 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: mustache.templates.dir = templates
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.async = true
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: cert.path = 
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: http.name = http
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: maxRequestSize = 5242880.bytes
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.rollPolicy = Never
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: help = false
    jvm 1    | 11:07:26.173 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.output = /dev/stderr
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: http.port = :8888
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.async.maxsize = 4096
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: https.name = https
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: http.response.charset.enabled = true
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.rotateCount = -1
    jvm 1    | 11:07:26.174 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: doc.root = 
    jvm 1    | 11:07:26.175 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.async.inferClassNames = false
    jvm 1    | 11:07:26.175 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: key.path = 
    jvm 1    | 11:07:26.175 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: log.append = true
    jvm 1    | 11:07:26.175 [WrapperSimpleAppMain] DEBUG c.t.inject.app.internal.FlagsModule - Binding flag: admin.port = 0.0.0.0/0.0.0.0:9990
    jvm 1    | 11:07:26.340 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Resolving Finagle clients before warmup
    jvm 1    | 11:07:26.343 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Done resolving clients: [].
    jvm 1    | 11:07:26.344 [WrapperSimpleAppMain] INFO  c.t.s.internal.FinagleBuildRevision$ - Resolved Finagle build revision: (rev=f48520b6809792d8cb87c5d81a13075fd01c051d)
    jvm 1    | 11:07:26.487 [WrapperSimpleAppMain] DEBUG c.t.f.h.modules.MessageBodyModule$ - Configuring MessageBodyManager
    jvm 1    | 11:07:26.492 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Warming up.
    jvm 1    | 11:07:26.571 [WrapperSimpleAppMain] INFO  c.t.finatra.http.routing.HttpRouter - Adding routes
    jvm 1    | GET     /ping
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer -  => com.twitter.server.handler.AdminRedirectHandler
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin => com.twitter.server.handler.SummaryHandler
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/server_info => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/contention => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/lint => com.twitter.server.handler.LintHandler
    jvm 1    | 11:07:26.574 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/lint.json => com.twitter.server.handler.LintHandler
    jvm 1    | 11:07:26.575 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/failedlint => com.twitter.server.handler.FailedLintRuleHandler
    jvm 1    | 11:07:26.575 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/threads => com.twitter.server.handler.ThreadsHandler
    jvm 1    | 11:07:26.575 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/threads.json => com.twitter.server.handler.ThreadsHandler
    jvm 1    | 11:07:26.575 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/announcer => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:26.576 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/dtab => com.twitter.finagle.Filter$$anon$1
    jvm 1    | 11:07:26.577 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/heap => com.twitter.server.handler.HeapResourceHandler
    jvm 1    | 11:07:26.577 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/profile => com.twitter.server.handler.ProfileResourceHandler
    jvm 1    | 11:07:26.577 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/pprof/contention => com.twitter.server.handler.ProfileResourceHandler
    jvm 1    | 11:07:26.577 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/ping => com.twitter.server.handler.ReplyHandler
    jvm 1    | 11:07:26.577 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/shutdown => com.twitter.server.handler.ShutdownHandler
    jvm 1    | 11:07:26.580 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/tracing => com.twitter.server.handler.TracingHandler
    jvm 1    | 11:07:26.580 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/events => com.twitter.server.handler.EventsHandler
    jvm 1    | 11:07:26.580 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/events/record/ => com.twitter.server.handler.EventRecordingHandler
    jvm 1    | 11:07:26.580 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/logging => com.twitter.server.handler.LoggingHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/metrics => com.twitter.server.handler.MetricQueryHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/clients/ => com.twitter.server.handler.ClientRegistryHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/servers/ => com.twitter.server.handler.ServerRegistryHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/files/ => com.twitter.server.handler.ResourceHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/registry.json => com.twitter.server.handler.RegistryHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/toggles => com.twitter.server.handler.ToggleHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/toggles/ => com.twitter.server.handler.ToggleHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /favicon.ico => com.twitter.server.handler.ResourceHandler
    jvm 1    | 11:07:26.581 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/histograms => com.twitter.server.handler.HistogramQueryHandler
    jvm 1    | 11:07:26.582 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - /admin/histograms.json => com.twitter.server.handler.HistogramQueryHandler
    jvm 1    | 11:07:26.607 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - http server started on port: 8888
    jvm 1    | 11:07:26.608 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Enabling health endpoint on port 9990
    jvm 1    | 11:07:26.608 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - com.deciphernow.server.rest.GMFabricRestServer started.
    jvm 1    | 11:07:26.609 [WrapperSimpleAppMain] INFO  c.d.server.rest.GMFabricRestServer - Startup complete, server ready.


