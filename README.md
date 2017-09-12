# Code used for the talk at Microsoft Experience 2017

This is the code of the talk I gave at Microsoft Experience 2017 with [Sebastien Pertus](https://twitter.com/sebastienpertus).

## Demo 01

In this demo we have a simple REST API (developed with [Spring Boot](https://projects.spring.io/spring-boot/)) that returns a random ISBN number, and a simple Angular web interface. Both are deployed on Microsoft Azure.

```
├── number-api : REST API returning a random ISBN number
│   ├── pom.xml
│   └── src
├── number-web : Angular Web interface interacting with the Number API
│   └── src
└── pom.xml
```

### Number API

* `mvn clean install` creates the executable Spring Boot war file (`target/number-api-1.0.jar`)
* Execute the service with `java -jar target/number-api-1.0.jar`
* HTTP GET on [http://localhost:8080/api/isbn]()
* Swagger contract on [http://localhost:8080/v2/api-docs]()
* Swagger UI on [http://localhost:8080/swagger-ui.html#/isbn-resource]()

### Number Web

Angular Web app invoking Number API

## Demo 02

In this demo, we keep the Number API and we add another REST API that inserts Books into a database. 

```
├── book-api : REST API inserting a book into a database (needs the Number API)
│   ├── pom.xml
│   └── src
├── number-API : REST API returning a random ISBN number
│   ├── pom.xml
│   └── src
├── book-web : Angular Web interface interacting with the Book
│   └── src
└── pom.xml
```

### Registry

Now that we are living in a Microservices world, we need a registry. We will use the JHipster registry.

* Execute the registry with Docker
* `docker-compose -f book-api/src/main/docker/jhipster-registry.yml up`
* Check the admin console at http://localhost:8761

### Console

The microservices send the logs to the JHipster Console. This way all the metrics are aggregated in a single console.

* Execute the console with Docker
* `docker-compose -f book-api/src/main/docker/jhipster-console.yml up`
* Check the admin console at http://localhost:8761


### Number API
