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
