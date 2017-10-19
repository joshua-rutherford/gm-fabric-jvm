#!/bin/bash -

# ################################################################################################ #
# Clean up.
# ################################################################################################ #
unset DEFAULT_DIR TERMINATION_FILE INTEGRATION_JAR INTEGRATION_CLASSPATH
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
export DEFAULT_DIR=/tmp
export TERMINATION_FILE="${DEFAULT_DIR}/integration-finished.txt"
export INTEGRATION_JAR=`ls gm-fabric-*.jar | grep -iv javadoc`
export INTEGRATION_CLASSPATH=`cat ${TEMP_FILE} | tr -d '\n' | tr -d ' '`

#export INTEGRATION_CLASSPATH=lib/activation-1.1.jar:lib/aopalliance-1.0.jar:lib/application-action-0.0.90.jar:lib/args-0.2.41.jar:lib/args-core-0.1.37.jar:lib/base-0.0.115.jar:lib/bijection-core_2.11-0.9.4.jar:lib/bijection-util_2.11-0.9.4.jar:lib/caffeine-2.3.4.jar:lib/client-0.0.80.jar:lib/collections-0.0.110.jar:lib/commons-cli-1.2.jar:lib/commons-codec-1.6.jar:lib/commons-fileupload-1.3.1.jar:lib/commons-io-2.4.jar:lib/commons-lang-2.6.jar:lib/commons-logging-1.1.3.jar:lib/compiler-0.8.18.jar:lib/dynamic-host-set-0.0.56.jar:lib/finagle-base-http_2.11-6.42.0.jar:lib/finagle-core_2.11-6.42.0.jar:lib/finagle-exp_2.11-6.42.0.jar:lib/finagle-http_2.11-6.42.0.jar:lib/finagle-mux_2.11-6.42.0.jar:lib/finagle-netty4_2.11-6.42.0.jar:lib/finagle-netty4-http_2.11-6.42.0.jar:lib/finagle-serversets_2.11-6.42.0.jar:lib/finagle-stats_2.11-6.42.0.jar:lib/finagle-thrift_2.11-6.42.0.jar:lib/finagle-thriftmux_2.11-6.42.0.jar:lib/finagle-toggle_2.11-6.42.0.jar:lib/finagle-zipkin-core_2.11-6.42.0.jar:lib/finatra-http_2.11-2.8.0.jar:lib/finatra-jackson_2.11-2.8.0.jar:lib/finatra-scalap-compiler-deps_2.11-2.0.0.jar:lib/finatra-slf4j_2.11-2.8.0.jar:lib/finatra-thrift_2.11-2.8.0.jar:lib/finatra-utils_2.11-2.8.0.jar:lib/gm-fabric-core-0.1.5-SNAPSHOT.jar:lib/grizzled-slf4j_2.11-1.3.0.jar:lib/group-0.0.91.jar:lib/gson-2.3.1.jar:lib/guava-16.0.1.jar:lib/guice-4.0.jar:lib/guice-assistedinject-4.0.jar:lib/guice-multibindings-4.0.jar:lib/hamcrest-core-1.1.jar:lib/httpclient-4.3.5.jar:lib/httpcore-4.3.2.jar:lib/inject-app_2.11-2.8.0.jar:lib/inject-core_2.11-2.8.0.jar:lib/inject-modules_2.11-2.8.0.jar:lib/inject-server_2.11-2.8.0.jar:lib/inject-slf4j_2.11-2.8.0.jar:lib/inject-thrift_2.11-2.8.0.jar:lib/inject-utils_2.11-2.8.0.jar:lib/io-0.0.68.jar:lib/io-json-0.0.54.jar:lib/io-thrift-0.0.67.jar:lib/jackson-annotations-2.8.0.jar:lib/jackson-core-2.8.4.jar:lib/jackson-databind-2.8.4.jar:lib/jackson-datatype-joda-2.8.4.jar:lib/jackson-module-paranamer-2.8.4.jar:lib/jackson-module-scala_2.11-2.8.4.jar:lib/javacc-5.0.jar:lib/javax.inject-1.jar:lib/jcl-over-slf4j-1.7.21.jar:lib/jdk-logging-0.0.82.jar:lib/jms-1.1.jar:lib/jmxri-1.2.1.jar:lib/jmxtools-1.2.1.jar:lib/joda-convert-1.2.jar:lib/joda-time-2.5.jar:lib/jsr305-2.0.1.jar:lib/jul-to-slf4j-1.7.9.jar:lib/junit-4.10.jar:lib/libthrift-0.5.0-7.jar:lib/log4j-1.2.15.jar:lib/log4j-over-slf4j-1.7.21.jar:lib/logback-classic-1.1.2.jar:lib/logback-core-1.1.2.jar:lib/mail-1.4.jar:lib/metrics-0.0.38.jar:lib/netty-3.10.1.Final.jar:lib/netty-buffer-4.1.8.Final.jar:lib/netty-codec-4.1.8.Final.jar:lib/netty-codec-http-4.1.8.Final.jar:lib/netty-codec-socks-4.1.8.Final.jar:lib/netty-common-4.1.8.Final.jar:lib/netty-handler-4.1.8.Final.jar:lib/netty-handler-proxy-4.1.8.Final.jar:lib/netty-resolver-4.1.8.Final.jar:lib/netty-transport-4.1.8.Final.jar:lib/net-util-0.0.102.jar:lib/nscala-time_2.11-1.6.0.jar:lib/paranamer-2.8.jar:lib/quantity-0.0.99.jar:lib/scala-compiler-2.11.8.jar:lib/scala-guice_2.11-4.1.0.jar:lib/scala-library-2.11.8.jar:lib/scala-logging-api_2.11-2.1.2.jar:lib/scala-logging-slf4j_2.11-2.1.2.jar:lib/scalap-2.11.8.jar:lib/scala-parser-combinators_2.11-1.0.4.jar:lib/scala-reflect-2.11.8.jar:lib/scala-xml_2.11-1.0.4.jar:lib/scrooge-core_2.11-4.14.0.jar:lib/server-set-1.0.111.jar:lib/service-thrift-1.0.55.jar:lib/servlet-api-2.5.jar:lib/slf4j-api-1.7.9.jar:lib/slf4j-log4j12-1.7.5.jar:lib/snakeyaml-1.12.jar:lib/stat-0.0.74.jar:lib/stats-0.0.115.jar:lib/stats-provider-0.0.93.jar:lib/stats-registry-0.0.1.jar:lib/stats-util-0.0.59.jar:lib/twitter-server_2.11-1.27.0.jar:lib/util-0.0.121.jar:lib/util-app_2.11-6.41.0.jar:lib/util-cache_2.11-6.41.0.jar:lib/util-codec_2.11-6.41.0.jar:lib/util-collection_2.11-6.41.0.jar:lib/util-core_2.11-6.41.0.jar:lib/util-events_2.11-6.41.0.jar:lib/util-executor-service-shutdown-0.0.67.jar:lib/util-function_2.11-6.41.0.jar:lib/util-hashing_2.11-6.41.0.jar:lib/util-jvm_2.11-6.41.0.jar:lib/util-lint_2.11-6.41.0.jar:lib/util-logging_2.11-6.41.0.jar:lib/util-registry_2.11-6.41.0.jar:lib/util-sampler-0.0.78.jar:lib/util-security_2.11-6.41.0.jar:lib/util-stats_2.11-6.41.0.jar:lib/util-system-mocks-0.0.104.jar:lib/util-zk_2.11-6.41.0.jar:lib/util-zk-common_2.11-6.41.0.jar:lib/zookeeper-3.5.0-alpha.jar
