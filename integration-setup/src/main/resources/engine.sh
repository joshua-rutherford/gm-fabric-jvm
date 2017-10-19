#!/bin/bash -

source configure.sh
BASE_SCRIPT_NAME=test
let COUNT=0
let MAX_COUNT=100
for ((;COUNT<MAX_COUNT;++COUNT))
do
  FILE=$BASE_SCRIPT_NAME-$COUNT.sh
  if [ -e $FILE ] 
  then
    source $FILE
  fi
done

#touch $TERMINATION_FILE
