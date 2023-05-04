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
import org.apache.pekko._

lazy val docs = (project in file(".")).
  settings(
      name := s"incubator-pekko-platform").
  settings(Paradox.settings).
  enablePlugins(
    ParadoxPlugin,
    PekkoParadoxPlugin,
    ParadoxBrowse
    )
