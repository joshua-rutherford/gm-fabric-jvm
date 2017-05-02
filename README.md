# PROLOG

This product is a microservice framework for the JVM and is intended to be built upon and extended. We have made an initial release of the source code, and continue to work actively to make it better. We hope to build an active open source community that drives the future of this framework, both by providing feedback and by actively contributing to the source code.

# gm-fabric-jvm ( gmf )
---

This project is built on top of Twitters Finagle and Finatra libraries. The core functionality resides in finagle-core and finatra-http and has been abstracted from the developer.

This project has two modules core and archetype. The core module has the underlying business logic that abstracts the Thrift and RESTful capabilities from the developer. The archetype module is used to build out a microservice.

A microservice ( MS ) is composed of four modules defined in the table below.
	
| Module | Description |
| -------| ----------- |
| business | Shared business logic either written in scala or java. |
| client | Thrift client and test coverage. |
| model | Thrift model. |
| server | REST and Thrift endpoints. |

## Latest release
[gm-fabric-jvm-0.1.3](https://github.com/DecipherNow/gm-fabric-jvm/releases/tag/gm-fabric-jvm-0.1.3)

## Prerequisite
You will need to install maven 3.3.x.

## How to do stuff
The following section is made up of multiple sections describing how to code, configure and various other subject matter with respect to dealing with a microservice.

## Coding
The various aspects of coding a microservice.

<!-- https://github.com/DecipherNow -->

- [How to create my microservice.](documentation/CreatingNewMS.md)
- [How to add logging to my microservice.](documentation/Logging.md)
- [Expanding thrift capabilities.](documentation/Thrift.md)
- [How to write clients.](documentation/Clients.md)
- [How to decrypt encrypted string resources.](documentation/ResourceDecrypter.md)
- [Adding API documentation to your microservice.](documentation/APIDocumentation.md)
- [How to build an RPM.](documentation/RPM.md)
- [How to upgrade the microservice framework with new Twitter libraries.](documentation/UpgradingFramework.md)

## Configuration
The various aspects of configuring a microservice.

- [Available parameters to configure for the microservice.](documentation/Parameters.md)
- [How to register the microservice for auto-discovery.](documentation/ZookeeperAutoDiscovery.md)
- [How to register IP addresses for auto discovery.](documentation/ConfigureIPAddressResolution.md)
- [How to enable 2-Way SSL.](documentation/TwoWaySSL.md)
- [How to decrypt encrypted string resources.](documentation/ResourceDecrypter.md)
- [How to configure the microservice framework on windows.](documentation/MicrosoftWindowsConfiguration.md)

## Filters
How to configure filters and what they provide for your microservice.

- [How to enforce security on RESTful interfaces (AclRestFilter).](documentation/AclRestFilter.md)
- [How to capture metrics for dynamic URI's (GenericUriStatsFilter) ](documentation/GenericUriStatsFilter.md)
- [How to control impersonation access to the microservice (WhitelistClientFilter).](documentation/WhitelistClientFilter.md)


## Administration
How to administer your microservice.

- [How to administer the microservice.](documentation/Admin.md)

## Known issues
- [Known issues.](documentation/Issues.md)

