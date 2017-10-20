#!/bin/bash -

# ################################################################################################ #
#
#    Copyright 2017 Decipher Technology Studios LLC
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#
# ################################################################################################ #

(

# ################################################################################################ #
#
# Description:
#
# hostname : Should be an IPv4 address.
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
export NUMBER=17
export ANSWER_REPORT="${DEFAULT_DIR}"/report-"${NUMBER}".txt
export ERROR_REPORT="${DEFAULT_DIR}"/error-report-"${NUMBER}".txt
rm -rf "${ANSWER_REPORT}" "${ERROR_REPORT}" 

# ################################################################################################ #
#
# Set up ENV vars here.
#
# ################################################################################################ #
#
export BIND_ADMIN_PORT=33333
scala -classpath ${INTEGRATION_CLASSPATH}:${INTEGRATION_JAR}:. \
    -Dcom.deciphernow.announcement.config.service.forward.httpPort=:5556 \
    -Dcom.deciphernow.server.config.os.env.adminPort=BIND_ADMIN_PORT \
    -Dcom.deciphernow.server.config.rest.httpPort=10000 \
    -Dcom.deciphernow.server.config.rest.httpsPort=10001  \
    -Dcom.deciphernow.server.config.admin.port=10002 \
    -Dcom.deciphernow.server.config.ipAddress.enableIpAddressResolution=true \
    com.deciphernow.integration.TestEngine \
    ${ANSWER_REPORT} \
    ${ERROR_REPORT} \
    ${DEFAULT_DIR}

)
