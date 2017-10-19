#!/bin/bash -


(

# ################################################################################################ #
#
# Description:
#
# hostname : Pickles
#
# Announce
#  - admin  : 5555
#  - http   : 5556
#  - https  : 5557
#  - thrift : 5558
#
# Bind 
#  - admin  : :10002
#  - http   : :10000
#  - https  : :10001
#  - thrift : :10002
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
export NUMBER=13
export ANSWER_REPORT="${DEFAULT_DIR}"/report-"${NUMBER}".txt
export ERROR_REPORT="${DEFAULT_DIR}"/error-report-"${NUMBER}".txt
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" 

# ################################################################################################ #
#
# Set up ENV vars here.
#
# ################################################################################################ #
#
scala -classpath ${INTEGRATION_CLASSPATH}:${INTEGRATION_JAR}:. \
    -Dcom.deciphernow.announcement.config.service.forward.hostname=Pickles \
    -Dcom.deciphernow.announcement.config.service.forward.adminPort=:5555 \
    -Dcom.deciphernow.announcement.config.service.forward.httpPort=:5556 \
    -Dcom.deciphernow.announcement.config.service.forward.httpsPort=:5557 \
    -Dcom.deciphernow.announcement.config.service.forward.thriftPort=:5558 \
    -Dcom.deciphernow.server.config.rest.httpPort=10000 \
    -Dcom.deciphernow.server.config.rest.httpsPort=10001  \
    -Dcom.deciphernow.server.config.admin.port=10002 \
    -Dcom.deciphernow.server.config.thrift.port=10003  \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}

)
