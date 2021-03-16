import Dependencies._

lazy val `akka-platform-bom` = Project(id = "akka-platform-bom", base = file("."))
  .enablePlugins(BillOfMaterialsPlugin)
  .settings(
    name := "akka-platform-bom",
    version := AkkaCloudPlatformVersion,
    bomIncludeModules := Seq(akkaCoreBom, akkaHttpBom) ++ akkaManagement,
    description := s"${description.value} (depending on Scala ${CrossVersion.binaryScalaVersion(scalaVersion.value)})"
  )
