#!/bin/bash

if [ ! -n "$SERVER_PORT" ]; then
    JAVA_OPTS=" -server -Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:SurvivorRatio=2 -XX:+UseParallelGC -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8 -Dsun.jnu.encoding=UTF8 -Duser.timezone=GMT+08 -Djava.util.Arrays.useLegacyMergeSort=true"
else
    JAVA_OPTS=" -server -Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m -XX:SurvivorRatio=2 -XX:+UseParallelGC -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8 -Dsun.jnu.encoding=UTF8 -Duser.timezone=GMT+08 -Djava.util.Arrays.useLegacyMergeSort=true -Dserver.port=$SERVER_PORT"
fi

exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar