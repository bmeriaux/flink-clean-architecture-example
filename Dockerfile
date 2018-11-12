
FROM openjdk:8u171-jre-slim

ARG JAR_VERSION="1.0.0-SNAPSHOT"

EXPOSE ${PORT:-8080}

COPY application/build/libs/application-${JAR_VERSION}.jar /app.jar
COPY job/build/libs/job-${JAR_VERSION}-all.jar /job/job.jar

CMD ["java","-Djava.security.egd=file:/dev/urandom","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-jar","app.jar"]