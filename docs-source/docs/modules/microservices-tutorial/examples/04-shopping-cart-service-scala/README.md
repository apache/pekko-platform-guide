## Running the sample code

1. Start a local Cassandra server on default port 9042. The included `docker-compose.yml` starts everything required for running locally.

    ```
    docker-compose up -d
    
    docker exec -i shopping-cart-service_cassandra_1 cqlsh -t < ddl-scripts/create_user_tables.cql
    ```

2. Start a first node:

    ```
    sbt -Dconfig.resource=local1.conf run
    ```

3. Try it with [grpcurl](https://github.com/fullstorydev/grpcurl):

    ```
    # add item to cart
    grpcurl -d '{"cartId":"cart1", "itemId":"hoodie", "quantity":5}' -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.AddItem
   
    # get popularity
    grpcurl -d '{"itemId":"hoodie"}' -plaintext 127.0.0.1:8101 shoppingcart.ShoppingCartService.GetItemPopularity
    ```
