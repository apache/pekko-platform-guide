# Apache Pekko Platform Guide

## Review and feedback

This is the repository for the Apache Pekko Platform Guide and we would love to hear your feedback.

Feel free to give your feedback in the form of issues/PRs.

## Status

The code in this repo has been changed to use Pekko libs instead of Akka/Alpakka. The docs themselves still need to be updated. The [Akka Guide](https://developer.lightbend.com/docs/akka-guide/) should still be a reasonably good intro still.

## Contributing

Contributions are *very* welcome!

If you see an issue that you'd like to see fixed, the best way to make it happen is to help out by submitting a pull request implementing it.

Refer to the [CONTRIBUTING.md](https://github.com/apache/pekko-platform-guide/blob/main/CONTRIBUTING.md) file for more details about the workflow,
and general hints on how to prepare your pull request. You can also ask for clarifications or guidance in GitHub issues directly.

## License

Apache Pekko is Open Source and available under the Apache 2 License.

## Code formatting

The 14 Scala sbt examples use Apache Pekko's Scalafmt configuration, adapted to
their Scala 2 language mode. From an example directory, use `sbt applyCodeStyle`
to format sources and build files, and `sbt checkCodeStyle` to check them.
Managed/generated sources are not formatting inputs. Java examples use Maven
and retain their existing build tooling; no Java formatter is added to Scala-only
sbt examples.

The sbt builds use sbt 1.13.1 because sbt-scalafmt 2.6.2 requires sbt 1.12.9
or later. This tooling update does not upgrade the examples' runtime libraries.

Scala 2 examples retain the Scala 2 dialect and disable Scala 3 syntax rewrites;
formatting does not change their compiler language mode.
