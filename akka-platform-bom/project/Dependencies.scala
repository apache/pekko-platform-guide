object Dependencies {
  object Versions {
    // FIXME: do we support 2.11 BOM's? some projects don't publish it anymore

    val AkkaCloudPlatform = "1.1.10"

    val Akka = "2.6.13"
    val AkkaHttp = "10.2.4"
    val AkkaManagement = "1.0.10"
    val AkkaProjections = "1.1.0"
    val AkkaPersistenceCassandra = "1.0.5"
    val AkkaPersistenceJdbc = "5.0.0"
    val AkkaPersistenceCouchbase = "1.0"
    val AkkaEnhancements = "1.1.13"
    val Alpakka = "3.0.0"
    val AlpakkaKafka = "2.1.0"
  }

  import Versions._

  /**
   * Based on list of supported components
   * https://developer.lightbend.com/docs/introduction/getting-help/build-dependencies.html
   */

  val akkaCoreBom = "com.typesafe.akka" %% "akka-bom" % Akka
  val akkaHttpBom = "com.typesafe.akka" %% "akka-http-bom" % AkkaHttp

  val akkaManagement = Seq(
    "com.lightbend.akka.management" %% "akka-management" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-cluster-http" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-loglevels-logback" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-discovery" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-discovery-dns" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-lease-kubernetes" % AkkaManagement
  )

  val akkaProjections = Seq(
    "com.lightbend.akka" %% "akka-projection-core" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-eventsourced" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-kafka" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-cassandra" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-jdbc" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-testkit" % AkkaProjections
  )

  val akkaPersistencePlugins = Seq(
    "com.typesafe.akka" %% "akka-persistence-cassandra" % AkkaPersistenceCassandra,
    "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % AkkaPersistenceCassandra,
    "com.typesafe.akka" %% "akka-persistence-jdbc" % AkkaPersistenceCassandra,
    "com.lightbend.akka" %% "akka-persistence-couchbase" % AkkaPersistenceCouchbase
  )

  val akkaPersistenceEnhancements = Seq(
    "com.lightbend.akka" %% "akka-gdpr" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-gdpr-jackson" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-gdpr-playjson" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-persistence-update" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-persistence-update-cassandra" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-persistence-update-jdbc" % AkkaEnhancements
    "com.lightbend.akka" %% "akka-persistence-multi-dc" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-persistence-multi-dc-testkit" % AkkaEnhancements
  )

  val akkaResilienceEnhancements = Seq(
    "com.lightbend.akka" %% "akka-split-brain-resolver" % AkkaEnhancements,
    // FIXME this is also published in the akka management org
    //"com.lightbend.akka" %% "akka-lease-kubernetes" % AkkaResilienceEnhancements
    "com.lightbend.akka" %% "akka-diagnostics" % AkkaEnhancements,
    "com.lightbend.akka" %% "akka-fast-failover" % AkkaEnhancements
  )

// FIXME: wait for 3.0.0 (Alpakka), 2.1.0 (Alpakka Kafka), for Akka 2.6?
//  val alpakka = Seq(
//    "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % Alpakka,
//    "com.lightbend.akka" %% "akka-stream-alpakka-couchbase" % Alpakka,
//    "com.lightbend.akka" %% "akka-stream-alpakka-csv" % Alpakka,
//    "com.typesafe.akka" %% "akka-stream-kafka" % AlpakkaKafka
//  )
}
