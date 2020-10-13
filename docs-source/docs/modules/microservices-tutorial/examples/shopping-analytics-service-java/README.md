## Running the sample code

1. Make sure you have compiled the project

    ```
    mvn compile 
    ```

2. Start a node:

    ```
    mvn exec:exec -DAPP_CONFIG=local1.conf
    ```
   
3. (Optional) Start another node with different ports:

    ```
    mvn exec:exec -DAPP_CONFIG=local2.conf
    ```

4. Check the readiness of the nodes

    ```
    curl http://localhost:9201/ready
    curl http://localhost:9202/ready
    ```
