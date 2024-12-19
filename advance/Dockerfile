# Use the specified Maven image
FROM maven:3.9.2-eclipse-temurin-20

# download and install allure and python pip
RUN apt-get update && apt-get install -y default-jre-headless python3-pip
RUN wget https://github.com/allure-framework/allure2/releases/download/2.29.0/allure_2.29.0-1_all.deb
RUN dpkg -i allure_2.29.0-1_all.deb

# install awscli
RUN pip install awscli

# Set the working directory in the container
WORKDIR /app

# Copy the local source code to the Docker container
COPY . .

RUN ["mkdir", "/app/test-output"]

# Run dummy command to install the dependencies
RUN ["mvn", "clean", "verify", "test", "-Dcucumber.options=-t '@Dummy'"]

# Run cleanup command
RUN ["rm", "-rf", "/app/test-output"]

RUN ["mkdir", "/app/test-output"]

RUN ["chmod", "+x", "runTests.sh"]
