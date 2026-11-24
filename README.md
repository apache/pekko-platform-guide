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

## Organize Scala imports

Each sbt example containing Scala sources includes Scalafix. Run
`sbt sortImports` from that example directory to organize main and test imports
and remove unused imports. Each example carries its own configuration so it
continues to work when copied outside this repository.

This uses Scalafix 0.14.10 and the OrganizeImports grouping from
[apache/pekko#3342](https://github.com/apache/pekko/pull/3342). Relative imports
remain unexpanded and adjacent to their root import.
