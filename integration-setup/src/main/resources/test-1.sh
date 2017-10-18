#!/bin/bash -


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
    ${ERROR_REPORT} \
    blah

)
