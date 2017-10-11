FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD spring-cloud-config-server.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=" -server -Xms128m -Xmx128m -XX:MaxMetaspaceSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8 -Dsun.jnu.encoding=UTF8 -Duser.timezone=GMT+08 -Djava.util.Arrays.useLegacyMergeSort=true"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]