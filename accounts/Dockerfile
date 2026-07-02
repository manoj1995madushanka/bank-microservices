FROM amazoncorretto:21-alpine
LABEL authors="manumax95"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# this will execute java -jar accounts-0.0.1-SNAPSHOT.jar when the container starts
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]