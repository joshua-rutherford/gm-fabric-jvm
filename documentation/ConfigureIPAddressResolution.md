# Overview
When there is no DNS the ip address of the node has to be registered to Zookeeper. There are two properties to configure in `parameters.conf` that will discover the IP address of the node.

## Use IP address

To use the IP address over the hostname of the server add the following property and set to true.

    -com.deciphernow.server.config.ipAddress.enableIpAddressResolution=true
    
## Network interface
By assigning the name of the network interface the ip address will be used. If no network interface name assigned it will attempt to auto discover the correct network interface to leverage the IP address from. This configuration may be superseded by [other configuration attributes](AnnounceAndBind.md)

### Auto discover
Leave unassigned. It will attempt to pick the correct interface that is not the loopback interface.

    -com.deciphernow.server.config.ipAddress.useNetworkInterfaceName=
    
### Explicit network interface
Assign the name of ethernet to use.
    
    -com.deciphernow.server.config.ipAddress.useNetworkInterfaceName=eth0
    
    

