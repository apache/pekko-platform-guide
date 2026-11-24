## Running the sample code

1. Start a first node:

    ```
    sbt -Dconfig.resource=local1.conf run
    ```

2. Start `shopping-cart-service` and add item to cart

3. Notice the log output in the terminal of the `shopping-analytics-service`

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
