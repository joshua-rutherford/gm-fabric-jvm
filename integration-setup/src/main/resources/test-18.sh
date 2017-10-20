#!/bin/bash -


(

# ################################################################################################ #
#
# Description:
#
# hostname : 
#
# Announce
#  - admin  : 33333
#  - http   : 5556
#  - https  : 10001
#  - thrift : 9090
#
# Bind 
#  - admin  : :33333 
#  - http   : :10000
#  - https  : :10001
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
export NUMBER=18
export ANSWER_REPORT="${DEFAULT_DIR}"/report-"${NUMBER}".txt
export ERROR_REPORT="${DEFAULT_DIR}"/error-report-"${NUMBER}".txt
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" 

# ################################################################################################ #
#
# Set up ENV vars here.
#
# ################################################################################################ #
#
export ANNOUNCE_HOSTNAME=WOMBAT
export BIND_ADMIN_PORT=33333
scala -classpath ${INTEGRATION_CLASSPATH}:${INTEGRATION_JAR}:. \
    -Dcom.deciphernow.announcement.config.service.forward.httpPort=:5556 \
    -Dcom.deciphernow.server.config.os.env.adminPort=BIND_ADMIN_PORT \
    -Dcom.deciphernow.server.config.rest.httpPort=10000 \
    -Dcom.deciphernow.server.config.rest.httpsPort=10001  \
    -Dcom.deciphernow.server.config.admin.port=10002 \
    -Dcom.deciphernow.server.config.ipAddress.enableIpAddressResolution=true \
    -Dcom.deciphernow.announcement.config.os.env.hostname=ANNOUNCE_HOSTNAME \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}

)
