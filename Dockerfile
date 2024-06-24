FROM openjdk:11-jre-slim
COPY target/simple-java-maven-app.jar /app/simple-java-maven-app.jar
ENTRYPOINT ["java", "-jar", "/app/simple-java-maven-app.jar"]

