#!/bin/sh

set -e

NEW_VERSION=$1

echo "Updating references to Akka HTTP to version $NEW_VERSION"

find -name *.sbt | xargs sed -E -i "s/AkkaHttpVersion = \"[^\"]+\"/AkkaHttpVersion = \"$NEW_VERSION\"/g"
git add **/*.sbt
git commit -m "Update to Akka HTTP $NEW_VERSION"
echo "Commit was created"