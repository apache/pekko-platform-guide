#!/usr/bin/env bash

set -x
PROJECT_ROOT=$PWD
DIR=$1

cd ${DIR}

if [ -f docker-compose.yml ]; then
  docker-compose up -d
fi

# Do not display transfer progress when downloading or uploading.
# This helps to clean up the build output.
mvn --no-transfer-progress test spotless:check

THE_EXIT_CODE=$?

if [ -f docker-compose.yml ]; then
  docker-compose stop
fi

cd ${PROJECT_ROOT}

exit $THE_EXIT_CODE
