FROM openjdk:8-jdk-alpine

# Copy local code to the container image.
WORKDIR /app

ADD target/beanapi-thirdparty-0.0.1.jar .

EXPOSE 9100

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","/app/target/beanapi-thirdparty-0.0.1.jar","--spring.profiles.active=prod"]