# Libralink Agent

[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/3mRSbP89jqQQqkK78hQhCE/LEaH5aYP5LmW33fLeNb1JH/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/3mRSbP89jqQQqkK78hQhCE/LEaH5aYP5LmW33fLeNb1JH/tree/main)

## Run Postgres

```
docker-compose -f docker-compose.yml up -d postgres
```

## Data Migration
```
cd ./libralink-agent/migration
mvn clean install liquibase:update -N -DabsolutePath=`pwd`/src/main/resources
```

## Swagger
```
http://localhost:8080/swagger-ui.html
```
