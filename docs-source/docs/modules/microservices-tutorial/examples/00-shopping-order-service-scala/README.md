## Running the sample code

1. Start a first node:

    ```shell
    sbt -Dconfig.resource=local1.conf run
    ```

2. (Optional) Start another node with different ports:

    ```shell
    sbt -Dconfig.resource=local2.conf run
    ```

3. Check for service readiness

    ```shell
    curl http://localhost:9301/ready
    ```

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
