To run the project:
mvn clean install
mvn spring-boot:run

To test:
 ->Sending new transactions to the api ( should replace YY with epoch milis timestamp, and XX with the amount value):
    curl -v -H "Content-Type:application/json" -X POST http://localhost:8080/transactions -d '{"amount":XX,"timestamp":YY}'
 -> To get the statistics:
    curl -X GET http://localhost:8080/statistics
