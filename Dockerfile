FROM openjdk:8-jdk-alpine
MAINTAINER marcosebsantos@gmail.com
VOLUME /tmp
EXPOSE 8080
ADD target/api-discount-0.0.1-SNAPSHOT.jar logs.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/logs.jar"]