
# CallCenter API Rest

## Overview
API Rest for basic call center.

## Requirements
- Java
- Maven
- Docker
- RabbitMQ
- Postgres

## Developing application locally
Create and run Pgsql Db and Rabbit Queue:
``` 
$ docker-compose up
```
run server by console with
```
$ mvn spring-boot:run
```

## Test
1 Create and run psql database and rabbit queue:
``` 
$ docker-compose up
```
2 Test your changes
```
$ mvn test
```

## Example
Make a call with the follow command:
```
curl -X POST -H "Content-Type: application/json" 'localhost:8080/callcenter' | jq .
```