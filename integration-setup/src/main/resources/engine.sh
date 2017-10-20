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

source configure.sh
BASE_SCRIPT_NAME=test
let COUNT=0
let MAX_COUNT=100
for ((;COUNT<MAX_COUNT;++COUNT))
do
  FILE=$BASE_SCRIPT_NAME-$COUNT.sh
  if [ -e $FILE ] 
  then
    echo "*************************************"
    echo "Running: $FILE"
    echo "*************************************"
    source $FILE
  fi
done

