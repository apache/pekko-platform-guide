ThisBuild / name := "telemetry-sample"
ThisBuild / organization := "com.lightbend"
ThisBuild / organizationHomepage := Some(url("https://akka.io"))
ThisBuild / licenses := Seq(
  ("CC0", url("https://creativecommons.org/publicdomain/zero/1.0")))

ThisBuild / scalaVersion := "2.13.5"

Compile / scalacOptions ++= Seq(
  "-target:11",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint")

val AkkaVersion = "2.6.13"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion)

enablePlugins(Cinnamon)

enablePlugins(JavaAppPackaging, DockerPlugin)
dockerBaseImage := "docker.io/library/adoptopenjdk:11-jre-hotspot"
dockerUsername := sys.props.get("docker.username")
dockerRepository := sys.props.get("docker.registry")
ThisBuild / dynverSeparator := "-"

run / cinnamon := true
test / cinnamon := true

// tag::telemetry-dependencies[]
libraryDependencies ++= Seq(
  // Use Coda Hale Metrics
  Cinnamon.library.cinnamonCHMetrics,
  // Use Akka instrumentation
  Cinnamon.library.cinnamonAkka,
  Cinnamon.library.cinnamonAkkaTyped,
  Cinnamon.library.cinnamonAkkaPersistence,
  Cinnamon.library.cinnamonAkkaStream,
  // Use Akka HTTP instrumentation
  Cinnamon.library.cinnamonAkkaHttp,
  // Use Akka Projection Instrumentation
  Cinnamon.library.cinnamonAkkaProjection)
// end::telemetry-dependencies[]

// tag::telemetry-prometheus-dependencies[]
libraryDependencies ++= Seq(
  Cinnamon.library.cinnamonPrometheus,
  Cinnamon.library.cinnamonPrometheusHttpServer)
// end::telemetry-prometheus-dependencies[]
