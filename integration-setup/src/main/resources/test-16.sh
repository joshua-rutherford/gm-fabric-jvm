#!/bin/bash -


(

# ################################################################################################ #
#
# Description:
#
# hostname :
#
# Announce
#  - admin  : 9990
#  - http   : 8888
#  - https  : 8999
#  - thrift : 9090
#
# Bind 
#  - admin  : :9990
#  - http   : :8888
#  - https  : :8999
#  - thrift : :9090
#
# ################################################################################################ #
#

# ################################################################################################ #
#
# Configure base environment variables.
#
# ################################################################################################ #
#
source configure.sh
export NUMBER=16
export ANSWER_REPORT="${DEFAULT_DIR}"/report-"${NUMBER}".txt
export ERROR_REPORT="${DEFAULT_DIR}"/error-report-"${NUMBER}".txt
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" 

# ################################################################################################ #
#
# Set up ENV vars here.
#
# ################################################################################################ #
#
export ANNOUNCE_ADMIN_PORT=25000
scala -classpath ${INTEGRATION_CLASSPATH}:${INTEGRATION_JAR}:. \
    -Dcom.deciphernow.announcement.config.os.env.adminPort=ANNOUNCE_ADMIN_PORT \
    -Dcom.deciphernow.announcement.config.service.forward.hostname=Pickles \
    -Dcom.deciphernow.announcement.config.service.forward.adminPort=:5555 \
    -Dcom.deciphernow.announcement.config.service.forward.httpPort=:5556 \
    -Dcom.deciphernow.server.config.rest.httpPort=10000 \
    -Dcom.deciphernow.server.config.rest.httpsPort=10001  \
    -Dcom.deciphernow.server.config.admin.port=10002 \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}

)
