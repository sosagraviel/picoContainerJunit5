# Project Name

## Running Tests

To run tests with specific tags, use the following Maven commands.

### Run Regression Tests

First, run the maven command to get the dependencies needed for the tests.

```sh
mvn clean install
```

```sh
mvn test -Dcucumber.filter.tags="@Regression"
```

### Run Tests with Environment Variables

To run tests with environment variables, use the following commands. Make sure to set the necessary environment
variables before executing the tests.

#### Run Login Tests

```sh
ENVIRONMENTS=QA PASSWORD=Carolina123 PASSWORD_SUPERADMIN=Carolina123 PLATFORM_GOAL_EXECUTION_TEST=LOCAL mvn test -Dcucumber.filter.tags="@Logins"
```

#### Run Login Tests Excluding Specific Tag

```sh
ENVIRONMENTS=QA PASSWORD=Carolina123. PASSWORD_SUPERADMIN=Carolina123. PLATFORM_GOAL_EXECUTION_TEST=LOCAL mvn test -Dcucumber.filter.tags="@Logins and not @RunThisTag"
```

## Generating Allure Reports

After running the tests, you can generate and serve the Allure reports using the following Maven commands.


# IMPORTANT TO MAKE THE PROJECT WORK

## ChainTest Configuration

### Prerequisites
Before running tests with ChainTest reporting, you need to start the ChainTest service using Docker.

### Start ChainTest Service
```sh
docker compose -f docker-compose-h2.yml up
```
This will start the ChainTest container on port 84.

### View ChainTest Reports
Once the service is running, you can access the reports at:
```
http://localhost:84
```

### Stop ChainTest Service
```sh
docker compose -f docker-compose-h2.yml down
```

### ChainTest Configuration File
The project uses ChainTest for additional reporting. The configuration is located in:
```
src/test/resources/chaintest.properties
```

**Important Configuration:**
```properties
chaintest.generator.chainlp.host.url=http://localhost:84/
```

### Common ChainTest Errors and Solutions

#### Error 1: Connection Refused
```
java.net.ConnectException: null
Caused by: java.nio.channels.ClosedChannelException: null
```

**Cause:** ChainTest service is not running or wrong port configuration.

**Solution:**
1. Start the ChainTest service:
   ```sh
   docker compose -f docker-compose-h2.yml up
   ```
2. Verify the service is running:
   ```sh
   docker ps
   ```
3. Check if the service is accessible:
   ```sh
   curl -I http://localhost:84
   ```

#### Error 2: Wrong Port Configuration
If you see connection errors but the service is running, check the port configuration in `src/test/resources/chaintest.properties`:

**Current Configuration:**
```properties
chaintest.generator.chainlp.host.url=http://localhost:84/
```

**Alternative Ports:**
- For MySQL setup: `http://localhost:80/` (uses `chainlp/docker/docker-compose-mysql.yml`)
- For PostgreSQL setup: `http://localhost:80/` (uses `chainlp/docker/docker-compose-postgres.yml`)

#### Error 3: NullPointerException in ChainTest Plugin
```
java.lang.NullPointerException: Cannot invoke "java.net.http.HttpResponse.statusCode()" because "response" is null
```

**Cause:** ChainTest plugin is enabled but service is not accessible.

**Solution:**
1. Ensure ChainTest service is running
2. Check port configuration
3. Verify network connectivity

#### Disabling ChainTest (Temporary Solution)
If you need to run tests without ChainTest, you can temporarily disable it by removing the ChainTest plugin from the test runner:

In `src/test/java/workshopcucumberadvance/Test/StepsDef/Runner/RunCucumberTest.java`, comment out the ChainTest plugin:

```java
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, "
    + "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, "
    + "html:target/cucumber-reports/report.html, "
    + "json:target/cucumber-reports/cucumber.json"
    // + ", com.aventstack.chaintest.plugins.ChainTestCucumberListener:target/chaintest/chaintest-report.json"
)
```

### Generate Allure Report

```sh
mvn allure:report
```

### Serve Allure Report

```sh
mvn allure:serve
```

## Configuration Files

### `junit-platform.properties`

The `junit-platform.properties` file should contain the following configuration:

```properties
cucumber.plugin=pretty,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm
cucumber.execution.parallel.enabled=false
cucumber.filter.tags=@Logins
```

This configuration specifies the `pretty` reporter for JUnit Platform, the `AllureCucumber7Jvm` plugin for Allure,
and disables parallel execution,
these configurations don't be necessary if you have this in the RunCucumberTest.java class already specified

### `allure.properties`

The `allure.properties` file can be used to configure Allure-specific properties if needed. Here is an example
configuration:

```properties
# Example Allure properties configuration
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://my.issue.tracker/issues/%s
allure.link.tms.pattern=https://my.tms.tracker/tests/%s
```

## Parallel Execution Configuration

To disable parallel execution, you can use the following configuration parameter in your test runner or configuration
file:

@ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "false");

Ensure that this is set appropriately in your test runner class or in the properties file.

## Environment Variables

The following environment variables are used in the commands:

- `ENVIRONMENTS`: Specifies the environment (e.g., QA, DEV, PROD).
- `PASSWORD`: The password for the user.
- `PASSWORD_SUPERADMIN`: The password for the super admin.
- `PLATFORM_GOAL_EXECUTION_TEST`: The platform goal execution test (e.g., LOCAL, REMOTE).

Make sure to replace the placeholder values with the actual values as per your environment.

## Example Usage

Here is an example of how you would use the commands:

1. Run regression tests:
   ```sh
   mvn test -Dcucumber.filter.tags="@Regression"
   ```

2. Generate an Allure report: this going to generate a report with the name `index.html` in the `target/site/`
   directory.
   ```sh
   mvn allure:report
   ```

3. Serve an Allure report: this will open the report in the browser. and Press <Ctrl+C> to exit the server.
   ```sh
   mvn allure:serve
   ```

4. Run login tests with environment variables:
   ```sh
   ENVIRONMENTS=QA PASSWORD=Carolina123 PASSWORD_SUPERADMIN=Carolina123 PLATFORM_GOAL_EXECUTION_TEST=LOCAL mvn test -Dcucumber.filter.tags="@Logins"
   ```

5. Run login tests excluding specific tag:
   ```sh
   ENVIRONMENTS=QA PASSWORD=Carolina123 PASSWORD_SUPERADMIN=Carolina123 PLATFORM_GOAL_EXECUTION_TEST=LOCAL mvn test -Dcucumber.filter.tags="@Logins and not @RunThisTag"
   ```

By following the steps and commands outlined in this `README.md`, you can effectively run your tests and generate
detailed reports with Allure.
