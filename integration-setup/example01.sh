#!/bin/bash -


(
#cd target
source dependencies.sh
echo ${INTEGRATION_CLASSPATH}
scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-0.1.5-SNAPSHOT.jar:. com.deciphernow.integration.IT001

)
