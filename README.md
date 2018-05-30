# PROLOG

This product is a microservice framework for the JVM and is intended to be built upon and extended. We have made an initial release of the source code, and continue to work actively to make it better. We hope to build an active open source community that drives the future of this framework, both by providing feedback and by actively contributing to the source code.

# gm-fabric-jvm ( gmf )
---

This project is built on top of Twitter's [Finagle](https://github.com/twitter/finagle) and [Finatra](https://github.com/twitter/finatra) libraries. The core functionality resides in finagle-core and finatra-http and has been abstracted from the developer.

This project has two modules core and archetype. The core module has the underlying business logic that abstracts the Thrift and RESTful capabilities from the developer. The archetype module is used to build out a microservice.

A microservice ( MS ) is composed of four modules defined in the table below.
	
| Module | Description |
| -------| ----------- |
| business | Shared business logic either written in scala or java. |
| client | Thrift client and test coverage. |
| config | Parameters to be leveraged across all modules. |
| model | Thrift model. |
| server | REST and Thrift endpoints. |

## Latest release
- [gm-fabric-jvm-0.2.5](https://github.com/DecipherNow/gm-fabric-jvm/releases/tag/gm-fabric-jvm-0.2.5)
- [Release Notes:](documentation/ReleaseNotes.md)

## Generic Prerequisite
You will need to install:

- Maven 3.3.x.
- Java 8
- Scala 2.11.8
- rpm

## How to do stuff
The following section is made up of multiple sections describing how to code, configure and various other subject matter with respect to dealing with a microservice.

## Coding
How to code a microservice.

<!-- https://github.com/DecipherNow -->

- [How to create a microservice.](documentation/CreatingNewMS.md)
- [How to add logging to a microservice.](documentation/Logging.md)
- [Expanding thrift capabilities.](documentation/Thrift.md)
- [How to write clients.](documentation/Clients.md)
- [Adding API documentation to the microservice.](documentation/APIDocumentation.md)
- [How to build an RPM.](documentation/RPM.md)
- [Upgrading a microservices built on an older version of gm-fabric-jvm](documentation/UpgradingOlderMicroservice.md)

## Version Control System

- [You should probably ignore these things.](documentation/VCS.md)

## Configuration
Configuring a microservice.

- [Available parameters to configure for the microservice.](documentation/Parameters.md)
- [Change announcement and bind ports.](documentation/AnnounceAndBind.md)
- [How to register the microservice for auto-discovery.](documentation/ZookeeperAutoDiscovery.md)
- [How to register IP addresses for auto discovery.](documentation/ConfigureIPAddressResolution.md)
- [How to enable 2-Way SSL.](documentation/TwoWaySSL.md)
- [How to decrypt encrypted string resources.](documentation/ResourceDecrypter.md)
- [How to configure the microservice framework on windows.](documentation/MicrosoftWindowsConfiguration.md)

#### Adding parameters
Adding your own parameters and changing default behavior provided by the framework.

- [Coding your own parameters.](documentation/Config.md)
- [Provided parameters to change default behavior.](documentation/Parameters.md)

#### Configuring metric plugins

- [AWS: Capture metrics to be sent to AWS.](documentation/Cloudwatch-plugin.md)

## Filters
How to configure filters and what they provide for your microservice.

- [How to enforce security on RESTful interfaces (AclRestFilter).](documentation/AclRestFilter.md)
- [How to capture metrics for dynamic URI's (GenericUriStatsFilter) ](documentation/GenericUriStatsFilter.md)
- [How to control impersonation access to the microservice (WhitelistClientFilter).](documentation/WhitelistClientFilter.md)

## TLS Utilities.

- [How to use TLS utilities.](documentation/TLSUtils.md)

## Administration
How to administer your microservice.

- [How to administer the microservice.](documentation/Admin.md)

## Other

- [How to upgrade the microservice framework with new Twitter libraries.](documentation/UpgradingFramework.md)

## Known issues

- [Known issues.](documentation/Issues.md)

## License
- [](LICENSE.txt)

