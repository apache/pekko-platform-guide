#!/bin/sh

set -e

NEW_VERSION=$1

echo "Updating references to Apache Pekko HTTP to version $NEW_VERSION"

find -name *.sbt | xargs sed -E -i "s/PekkoHttpVersion = \"[^\"]+\"/PekkoHttpVersion = \"$NEW_VERSION\"/g"
git add **/*.sbt
git commit -m "Update to Apache Pekko HTTP $NEW_VERSION"
echo "Commit was created"