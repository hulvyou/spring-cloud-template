FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /data
ADD spring-cloud-user-server.jar app.jar
ADD run.sh
RUN sh -c 'touch /app.jar'
RUN sh -c 'touch /run.sh'
ENTRYPOINT ["/bin/sh" ,"/run.sh"]