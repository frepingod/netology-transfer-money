FROM openjdk:18-jdk-alpine3.13

EXPOSE 5500

ADD target/netology-transfer-money-0.0.1-SNAPSHOT.jar transfer.jar

ENTRYPOINT ["java", "-jar", "transfer.jar"]