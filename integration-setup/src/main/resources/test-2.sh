#!/bin/bash -


(

# ################################################################################################ #
#
# Description:
#
# hostname :
#
# Announce
#  - admin  : 44444
#  - http   : 44445
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
export NUMBER=2
export ANSWER_REPORT="${DEFAULT_DIR}"/report-"${NUMBER}".txt
export ERROR_REPORT="${DEFAULT_DIR}"/error-report-"${NUMBER}".txt
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" 

# ################################################################################################ #
#
# Set up ENV vars here.
#
# ################################################################################################ #
#
export ANNOUNCE_ADMIN_PORT=44444
export ANNOUNCE_HTTP_PORT=44445
scala -classpath ${INTEGRATION_CLASSPATH}:${INTEGRATION_JAR}:. \
    -Dcom.deciphernow.announcement.config.os.env.adminPort=ANNOUNCE_ADMIN_PORT \
    -Dcom.deciphernow.announcement.config.os.env.httpPort=ANNOUNCE_HTTP_PORT \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}
)
