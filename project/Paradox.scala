/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, derived from Akka.
 */

/*
 * Copyright (C) 2019-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko

import com.lightbend.paradox.sbt.ParadoxPlugin
import com.lightbend.paradox.sbt.ParadoxPlugin.autoImport._
import com.lightbend.paradox.apidoc.ApidocPlugin
import com.lightbend.sbt.publishrsync.PublishRsyncPlugin.autoImport._
import org.apache.pekko.PekkoParadoxPlugin.autoImport._
import sbt.Keys._
import sbt._

import scala.concurrent.duration._

object Paradox {
  val pekkoBaseURL = "https://pekko.apache.org"
  val propertiesSettings = Seq(
    Compile / paradoxProperties ++= Map(
      // Add settings here to be included in all documentation
    ))

  val rootsSettings = Seq(
    paradoxRoots := List(
      "index.html"))

  val themeSettings = Seq(
    // allow access to snapshots for pekko-sbt-paradox
    resolvers += "Apache Nexus Snapshots".at("https://repository.apache.org/content/repositories/snapshots/"),
    pekkoParadoxGithub := Some("https://github.com/apache/incubator-pekko-platform-guide"))

  // FIXME https://github.com/lightbend/paradox/issues/350
  // Exclusions from direct compilation for includes dirs/files not belonging in a TOC
  val includesSettings = Seq(
    (Compile / paradoxMarkdownToHtml / excludeFilter) := (Compile / paradoxMarkdownToHtml / excludeFilter).value ||
    ParadoxPlugin.InDirectoryFilter((Compile / paradox / sourceDirectory).value / "includes"),
    // Links are interpreted relative to the page the snippet is included in,
    // instead of relative to the place where the snippet is declared.
    (Compile / paradoxMarkdownToHtml / excludeFilter) := (Compile / paradoxMarkdownToHtml / excludeFilter).value ||
    ParadoxPlugin.InDirectoryFilter((Compile / paradox / sourceDirectory).value / "includes.html"))

  val groupsSettings = Seq(Compile / paradoxGroups := Map("Language" -> Seq("Scala", "Java")))

  val parsingSettings = Seq(Compile / paradoxParsingTimeout := 5.seconds)

  val settings =
    propertiesSettings ++
    rootsSettings ++
    includesSettings ++
    groupsSettings ++
    parsingSettings ++
    themeSettings ++
    Seq(
      Compile / paradox / name := "Pekko Platform Guide",
      resolvers += Resolver.jcenterRepo,
      ApidocPlugin.autoImport.apidocRootPackage := "org.apache.pekko"
    )
}
