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
#  - http   : 5557
#  - https  : 5556
#  - thrift : 5558
#
# Bind 
#  - admin  : :5555
#  - http   : :5557
#  - https  : :5556
#  - thrift : :5558
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
export NUMBER=11
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
    -Dcom.deciphernow.announcement.config.service.forward.adminPort=5555 \
    -Dcom.deciphernow.announcement.config.service.forward.httpPort=5556 \
    -Dcom.deciphernow.announcement.config.service.forward.httpsPort=5557 \
    -Dcom.deciphernow.announcement.config.service.forward.thriftPort=5558 \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}

)
