FROM openjdk:14-jdk-alpine
MAINTAINER aMoL
ADD ./target/rest-api-0.0.1-SNAPSHOT.jar /opt/app/rest-api-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/rest-api-0.0.1-SNAPSHOT.jar"]
