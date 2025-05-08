FROM openjdk:23
LABEL authors="suare"
WORKDIR /app
COPY target/OvaService-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "OvaService-0.0.1-SNAPSHOT.jar"]