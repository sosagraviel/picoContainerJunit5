Automation runner
========
# Pre-Requisites
- Docker

# Installing the tool
Run `sh install.sh` in your console. Make sure to have the docker desktop running before using it.

# Using the tool
Run `sh runAutomation.sh <env> <testFilter>`. The command accepts two parameters:
- `<env>`: The environment that you want to point the automation execution
- `<testFilter>`: The cucumber options to filter which tests do you want to run. For example `"-t '@Login'"`

Examples:
- Running login tests on demo environment: `sh runAutomation.sh DEMO "-t '@Login'"`