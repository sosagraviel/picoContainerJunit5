#!/bin/bash

# Check if Python3 is installed
if ! command -v python3 &> /dev/null
then
  echo "Python3 could not be found. Please install it first."
  exit
fi

# Navigate to the directory containing the Allure report
cd test-output/allure-report

# Define the starting port number
port=8001

# Iterate until an unused port is found
while lsof -Pi :$port -sTCP:LISTEN -t >/dev/null ; do
    port=$((port+1))
done

# Serve the directory at http://localhost:$port/
python3 -m http.server $port &
# Save the PID of the server process
server_pid=$!

# Define a function to kill the server process
kill_server() {
  kill -15 "$server_pid"
}

# Set the function to be called when the script receives SIGINT
trap kill_server SIGINT

sleep 1

# Open the served directory in the default web browser
open http://localhost:$port

# Wait for the server process to finish
wait "$server_pid"