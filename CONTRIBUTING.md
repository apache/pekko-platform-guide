# Welcome! Thank you for contributing to Apache Pekko!

We follow the standard GitHub [fork & pull](https://help.github.com/articles/using-pull-requests/#fork--pull) approach to pull requests. Just fork the official repo, develop in a branch, and submit a PR!

You're always welcome to submit your PR straight away and start the discussion (without reading the rest of this wonderful doc, or the README.md). The goal of these notes is to make your experience contributing to Apache Pekko as smooth and pleasant as possible. We're happy to guide you through the process once you've submitted your PR.

# The Apache Pekko Community

If you have questions about the contribution process or discuss specific issues, please interact with the community using the following resources.

- [GitHub discussions](https://github.com/apache/incubator-pekko-platform-guide/discussions): for questions and general discussion.
- [Pekko user mailing list](https://lists.apache.org/list.html?user@pekko.apache.org): for Pekko usage discussions.
- [Pekko dev mailing list](https://lists.apache.org/list.html?dev@pekko.apache.org): for Pekko development discussions.
- [GitHub issues](https://github.com/apache/incubator-pekko-platform-guide/issues): for bug reports and feature requests. Please search the existing issues before creating new ones. If you are unsure whether you have found a bug, consider asking in GitHub discussions or the mailing list first.

# Antora-based Apache Pekko Documentation

This folder contains the sources for parts of the [Apache Pekko Platform Guide web site](https://pekko.apache.org/docs/pekko-platform-guide/current).

This folder is structured as follows:
- The root directory contains the `Makefile` for the documentation generation process.
- The structured documentation is located under `docs-source/`.

Contributions to the documentation are welcome and encouraged.
If you are unfamiliar with the project or with asciidoc, please read the contribution guidelines below.

## Antora extension

Find some hints about the Lightbend Antora theme at https://lightbend.github.io/antora-supplemental-ui-lightbend-theme/build/site/index.html

The theme sources are in a Lightbend-private project at https://github.com/lightbend/antora-supplemental-ui-lightbend-theme

## Contributing to the Apache Pekko Documentation

Detailed information about working with the documentation is provided in the [docs-source](docs-source/README.adoc) folder.

## Building the Documentation

This part of the Apache Pekko documentation is built using [Antora](https://docs.antora.org/antora/2.3/), from asciidoc sources.
The building process is managed by `make` using the [makefile](./Makefile) script.


To build the documentation, use `make` with the following commands:

* `make local-preview`
    Generates the documentation and serves it at `localhost:8000`.

* `make html-author-mode` 

    Generates the documentation, in 'author' mode, to display review comments and TODOs. The result is available at `target/staging/index.html`.

* `make all` (default) 

    Generates the complete documentation bundle.

* `make html`

    Generates the html documentation and homepage. 

* `make check-links`

    Checks that the external links point to a reachable URL.

* `make list-todos`

    List all the TODOs, review comments, unresolve references, etc. from the documentation.

# Synchronize code for different parts of the tutorial

Each part of the tutorial has full source code in directories in `docs-source/docs/modules/microservices-tutorial/examples/`.

Many files are identical and maintained with `scripts/copy-identical-files.sh`, which copies the files.

The `00` directory is the "empty" project corresponds to the generated code from a future seed template. 

It's often easiest to make changes to the example in the full example (last part of the tutorial) and then
propagate those changes backwards with `scripts/copy-identical-files.sh` and manual edits of non-identical
files. IntelliJ has a good diff tool for propagating changes between files and folders.

PR validation in Travis will run the `scripts/copy-identical-files.sh` and fail the build if expected identical
files are different.
