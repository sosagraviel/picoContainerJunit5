#!/bin/bash

# Check if AWS CLI is installed
if ! command -v aws &> /dev/null
then
    echo "AWS CLI could not be found, please install it first."
    exit 1
fi

# Source the secrets-QA.env file
source secrets-QA.env

# Check if the AWS keys are set
if [ -z "$AWS_ACCESS_KEY_ID" ] || [ -z "$AWS_SECRET_ACCESS_KEY" ]; then
  echo "AWS keys are not set in secrets-QA.env"
  exit 1
fi

# Configure AWS CLI
aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
aws configure set default.region us-west-2
aws configure set default.output json

echo "AWS CLI configured successfully"