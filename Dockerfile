FROM openjdk:13-jdk-alpine
VOLUME /tmp
COPY target/*.jar cloud-gateway.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/cloud-gateway.jar"]