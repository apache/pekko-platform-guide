#!/usr/bin/env bash

declare -r tutorial_root="docs-source/docs/modules/microservices-tutorial/examples"

find ${tutorial_root} -name .scalafmt.conf |
while read result
do
	pushd $(dirname $result)
	sbt scalafmtAll
	popd
done

find ${tutorial_root} -name pom.xml |
while read result
do
	pushd $(dirname $result)
	mvn spotless:apply
	popd
done
