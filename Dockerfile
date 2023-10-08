FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/*.jar bios.jar

CMD ["java", "-jar", "bios.jar"]