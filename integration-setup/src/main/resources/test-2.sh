#!/bin/bash -


(

# ################################################################################################ #
#
# Description:
#
# Set env for announcement of the 'admin' && 'http' port. Assign values to said env.
# All bind ports will drop back to default.
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
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" "${TERMINATION_FILE}"

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

sleep 15
)
