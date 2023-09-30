FROM openjdk:8-jdk-alpine

WORKDIR /app

ADD target/beanapi-gateway-0.0.1.jar .

EXPOSE 8090

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","/app/target/beanapi-gateway-0.0.1.jar","--spring.profiles.active=prod"]