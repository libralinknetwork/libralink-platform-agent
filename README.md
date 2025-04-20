# Libralink Agent

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
