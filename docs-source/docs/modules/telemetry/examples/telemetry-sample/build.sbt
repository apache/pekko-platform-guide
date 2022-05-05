ThisBuild / name := "telemetry-sample"
ThisBuild / organization := "com.lightbend"
ThisBuild / organizationHomepage := Some(url("https://akka.io"))
ThisBuild / licenses := Seq(
  ("CC0", url("https://creativecommons.org/publicdomain/zero/1.0")))

// tag::telemetry-build-properties[]
ThisBuild / scalaVersion := "2.13.5"
// end::telemetry-build-properties[]

Compile / scalacOptions ++= Seq(
  "-target:11",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint")

// tag::telemetry-build-properties[]
val AkkaVersion = "2.6.19"
// end::telemetry-build-properties[]

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion)

// tag::telemetry-sbt-plugin[]
enablePlugins(Cinnamon)
// end::telemetry-sbt-plugin[]

// tag::telemetry-javaagent-docker[]
enablePlugins(JavaAppPackaging, DockerPlugin)
dockerBaseImage := "docker.io/library/adoptopenjdk:11-jre-hotspot"
dockerUsername := sys.props.get("docker.username")
dockerRepository := sys.props.get("docker.registry")
// end::telemetry-javaagent-docker[]

ThisBuild / dynverSeparator := "-"

// tag::telemetry-javaagent-run[]
run / cinnamon := true
// end::telemetry-javaagent-run[]

// tag::telemetry-javaagent-test[]
test / cinnamon := true
// end::telemetry-javaagent-test[]

// tag::telemetry-dependencies[]
libraryDependencies ++= Seq(
  // Use Coda Hale Metrics
  Cinnamon.library.cinnamonCHMetrics,
  // Use Akka instrumentation
  Cinnamon.library.cinnamonAkka,
  // Use Akka Persistence instrumentation
  Cinnamon.library.cinnamonAkkaPersistence,
  // Use Akka Projection instrumentation
  Cinnamon.library.cinnamonAkkaProjection,
  // Use Akka HTTP instrumentation
  Cinnamon.library.cinnamonAkkaHttp,
  // Use Akka gRPC instrumentation
  Cinnamon.library.cinnamonAkkaGrpc)
// end::telemetry-dependencies[]

// tag::telemetry-prometheus-dependencies[]
libraryDependencies ++= Seq(
  Cinnamon.library.cinnamonPrometheus,
  Cinnamon.library.cinnamonPrometheusHttpServer)
// end::telemetry-prometheus-dependencies[]

// tag::telemetry-opentracing-dependencies[]
libraryDependencies ++= Seq(
  Cinnamon.library.cinnamonOpenTracing,
  Cinnamon.library.cinnamonOpenTracingZipkin)
// end::telemetry-opentracing-dependencies[]
