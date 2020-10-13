## Running the sample code

1. Make sure you have compiled the project

    ```
    mvn compile 
    ```

2. Start a node:

    ```
    mvn exec:exec -DAPP_CONFIG=local1.conf
    ```

3. Check for service readiness

    ```
    curl http://localhost:9301/ready
    ```

4. Try it with [grpcurl](https://github.com/fullstorydev/grpcurl):

    ```
    grpcurl -d '{"cartId":"cart1", "items":[{"itemId":"socks", "quantity":3}, {"itemId":"t-shirt", "quantity":2}]}' -plaintext 127.0.0.1:8301 shoppingorder.ShoppingOrderService.Order
    ```
