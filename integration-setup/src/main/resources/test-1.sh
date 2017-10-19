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
export NUMBER=1
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
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}
)
