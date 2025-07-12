#!/bin/bash

# Check if a session ID was provided
if [ -z "$1" ]
then
  echo "Please provide a session ID."
  exit 1
fi

SESSION_ID=$1

PROJECT_ARN=arn:aws:devicefarm:us-west-2:834570351938:testgrid-project:0135dfb9-37bf-4552-8346-9a3ac843a8cd

# Get the ARN of the session
SESSION_ARN=$(aws devicefarm get-test-grid-session --project-arn $PROJECT_ARN --session-id $SESSION_ID --query 'testGridSession.arn' --output text)


# Get the URL of the video
VIDEO_URL=$(aws devicefarm list-test-grid-session-artifacts --session-arn $SESSION_ARN --query 'artifacts[-1:].url' --output text)

# Download the video
if [ "$VIDEO_URL" != "None" ]
then
  # Create the videos directory if it doesn't exist
  mkdir -p videos

  # Use the session ID as the video name
  VIDEO_NAME=$SESSION_ID

  # Download the video to the videos directory
  curl -o videos/$VIDEO_NAME.mp4 $VIDEO_URL
else
  echo "No video found for this session."
fi