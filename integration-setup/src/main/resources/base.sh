#!/bin/bash -


(

echo `pwd`
#cd target
source dependencies.sh

#
# The required jar does not get built before this script is executed thus go to sleep.
#sleep 20
#scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-0.1.5-SNAPSHOT.jar:. com.deciphernow.integration.IT001
#scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-0.1.5-SNAPSHOT.jar:. com.deciphernow.integration.IntegrationTests
export ANNOUNCE_ADMIN_PORT=:45000
export ANNOUNCE_HTTP_PORT=:45001
scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-setup-0.1.5-SNAPSHOT.jar:. -Dcom.deciphernow.announcement.config.os.env.adminPort=ANNOUNCE_ADMIN_PORT -Dcom.deciphernow.announcement.config.os.env.httpPort=ANNOUNCE_HTTP_PORT com.deciphernow.integration.IT001 /tmp/001.txt


)

(

source dependencies.sh
scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-setup-0.1.5-SNAPSHOT.jar:. \
    -Dcom.deciphernow.announcement.config.os.env.adminPort=ANNOUNCE_ADMIN_PORT \
    -Dcom.deciphernow.announcement.config.os.env.httpPort=ANNOUNCE_HTTP_PORT \
    com.deciphernow.integration.TestEngine \
    /tmp/report-1.txt \
    /tmp/error-1.txt

)

(

export DIR=/tmp
export ANSWER_REPORT="${DIR}"/report-1.txt
export ERROR_REPORT="${DIR}"/error-report-1.txt
source dependencies.sh
scala -classpath ${INTEGRATION_CLASSPATH}:gm-fabric-core-integration-setup-0.1.5-SNAPSHOT.jar:. \
    -Dcom.deciphernow.announcement.config.os.env.adminPort=ANNOUNCE_ADMIN_PORT \
    -Dcom.deciphernow.announcement.config.os.env.httpPort=ANNOUNCE_HTTP_PORT \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT}

)
