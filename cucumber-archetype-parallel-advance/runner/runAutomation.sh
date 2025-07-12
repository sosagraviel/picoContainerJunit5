#!/bin/bash

# Check if the correct number of arguments were provided
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 <environment> <test_filters>"
  exit 1
fi

# Assign arguments to variables
ENVIRONMENT=$1
TEST_FILTERS=$2

# Determine the secrets file based on the environment
SECRETS_FILE="secrets-${ENVIRONMENT}.env"

# Check if the secrets file exists
if [ ! -f "$SECRETS_FILE" ]; then
  echo "Secrets file ${SECRETS_FILE} not found"
  exit 1
fi

rm -rf test-output

mkdir -p test-output

# Run the Docker container
docker run --user root --env-file "$SECRETS_FILE" -e TEST_FILTERS="$TEST_FILTERS" -e ENVIRONMENTS="$ENVIRONMENT" -e PLATFORM_GOAL_EXECUTION_TEST="AWS" -v "$(pwd)/test-output:/app/test-output" -ti --entrypoint "/app/runTests.sh" e2e