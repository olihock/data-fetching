Docker PostgreSQL

Data Location

* Default: ```/var/lib/postgresql```

Start PostgreSQL Container
```
docker run -d \
-p 5432:5432 \
--name postgres \
-e POSTGRES_PASSWORD=pimaldaumen \
-v ~/ws/postgres-data:/var/lib/postgresql/data \
postgres
```

postgres
oliwertzu
pimaldaumen

-e PGDATA=/var/lib/postgresql/data \

Stop and Remove Container
```
docker rm -f postgres
```

Build a layered JAR (default layers)
```
./mvnw spring-boot:build-image
./mvnw -DskipTests spring-boot:build-image
```

Display layers in JAR
```
java -Djarmode=layertools -jar target/data-fetching-0.0.1-SNAPSHOT.jar list
```

Build docker application
```
docker-compose build
```


SDS011 Air Quality/Dust Sensor 
- (PM1, PM2.5, PM10)
- (früher PPD42NS)

BME280 Temperature Sensor