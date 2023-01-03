FROM openjdk:17-oracle
WORKDIR /app
ADD target/eCommerce-0.0.1-SNAPSHOT.jar eCommerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","eCommerce-0.0.1-SNAPSHOT.jar"]