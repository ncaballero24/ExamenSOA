FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn -q -DskipTests package
EXPOSE 8080
CMD ["java","-jar","/app/target/examen-replica-0.0.1-SNAPSHOT.jar"]
