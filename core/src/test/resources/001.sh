#!/bin/bash -

(
echo `pwd`
echo "The shellscript 001: "
export ANNOUNCE_ADMIN_PORT=:9898
# cd ../../../target/test-classes
cd core/target
echo `pwd`
scala -classpath gm-fabric-core-0.1.5-SNAPSHOT.jar:test-classes com.deciphernow.server.Blah001
)

