FROM eclipse-temurin:17-jre
ARG JAR_FILE=build/libs/*.jar
COPY init-table.sql /docker-entrypoint-initdb.d/
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
