## Running the sample code

1. Start a local Cassandra server on default port 9042 and Kafka server on default port 9092. The included `docker-compose.yml` starts everything required for running locally.

    ```
    docker-compose up -d
    ```

2. Start a first node:

    ```
    sbt -Dconfig.resource=local1.conf run
    ```

3. Start `shopping-analytics-service`

4. Try it with [grpcurl](https://github.com/fullstorydev/grpcurl):

    ```
    # add item to cart
    grpcurl -d '{"cartId":"cart1", "itemId":"pencil", "quantity":1}' -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem
    ```
    
    Look at the log output in the terminal of the `shopping-analytics-service`.
