FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java $JAVA_OPTS -Djdbc.driver=org.postgresql.Driver -Djdbc.url=jdbc:postgresql://arts-db.postgres.database.azure.com:5432/arts -Djdbc.username=ndph@arts-db -Djdbc.password=England99! -Dserver.port=8081","-jar","/app.jar"]
EXPOSE 8080:80
LABEL "ndph-arts.address-service.image.source"="https://github.com/NDPH-ARTS/address-service"
