FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080:80
LABEL "ndph-arts.address-service.image.source"="https://github.com/NDPH-ARTS/address-service"
