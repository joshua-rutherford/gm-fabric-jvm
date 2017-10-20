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

# ################################################################################################ #
# Clean up.
# ################################################################################################ #
unset DEFAULT_DIR INTEGRATION_JAR INTEGRATION_CLASSPATH
TEMP_FILE=/tmp/temp-libs-gmf.txt
rm -rf ${TEMP_FILE}

# ################################################################################################ #
# Build the classpath on the fly.
# ################################################################################################ #
FILES=`ls lib`

for FILE in ${FILES}
do
   echo ":lib/${FILE}" >> ${TEMP_FILE}
done

# ################################################################################################ #
# Export the variables.
# ################################################################################################ #

#
# If you change this DIR then make sure to update the 'integration-verify'/ValidateSpec to look for
# the same dir. Otherwise all tests will fail.
#
export DEFAULT_DIR=/tmp
export INTEGRATION_JAR=`ls gm-fabric-*.jar | grep -iv javadoc`
export INTEGRATION_CLASSPATH=`cat ${TEMP_FILE} | tr -d '\n' | tr -d ' '`
