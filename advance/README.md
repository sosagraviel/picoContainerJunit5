# WorkShop Example

This project was born with the idea to show how to handle postman in a RESTFul API.

## Pre-condition

### What do you need?

1- Docker desktop https://docs.docker.com/desktop/ \
2- IntelliJ IDEA https://www.jetbrains.com/idea/download/#section=mac \
3- Java https://www.java.com/en/download/help/download_options.html \
4- Maven https://www.baeldung.com/install-maven-on-windows-linux-mac

## How does it run?

### Launch a docker compose with mongodb

First off you need to run a docker compose.
In the root of the project run the next command from a terminal:

    docker-compose up 

### Run your Spring Boot application

    mvn spring-boot:run

### Verify your application

You can run a curl with event-stream to verify is it working well.

    curl --location --request GET 'http://localhost:8080/cars'

### Verify your swagger

You can verify into the following url.

    http://localhost:8080/swagger-ui/index.html
