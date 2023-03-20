## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.7.5

## Read-Only Files:
- src/test/*

## Requirements:
G
Response Code: 400

Response Body:
```json
{
    "message": "Buzz Exception has been thrown",
    "errorReason" : "Bad Request"
}
```


## Commands
- run: 
```bash
mvn clean package; java -jar target/RestControllerAdvice-1.0.jar
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```
