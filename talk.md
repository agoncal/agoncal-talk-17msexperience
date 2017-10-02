# Talk naration

* Increase fonts in console, IDE

# Demo 01 : Microservice Number API 

## Number API with Spring Boot

* Go to [http://start.spring.io]()
* Group Id : `org.msexpe`
* Artifact Id : `numberapi`
* Add Web dependency
* Download and Unzip to `$CODE_HOME/MSExp`
* Open project in Intellij Idea
* Create package `rest`
* Create class `IsbnResource`

```
@RestController
@RequestMapping("/api")
public class IsbnResource {
    
    @GetMapping("/isbn")
    public String generateIsbnNumber() {
        return "ISBN-" + Math.random();
    }
}
```

* Go to [http://localhost:8080/api/isbn]()

## Add Swagger

* Add Swagger dependencies in `pom.xml`
        <!-- Swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>

* Add `@EnableSwagger2` tp `NumberapiApplication`
* Go to [http://localhost:8080/v2/api-docs]()

# Demo 02 : Angular app for Number API 


# Demo 03 : Deploy in Azure 


# Demo 04 : JHipster

## Clean directory `$CODE_HOME/MSExp`

* Close Intellij IDEA
* Remove old numberApi and copy new one 
* `rm -rf *` (`rm -rf .*` if needed for hidden files)
* `cp -rf ../Agoncal/agoncal-talk-17msexperience/demo03/number-api/ ./number-api`
* Open the new number-api project in Intellij

## Create the Microservice Book API with JHipster

* `$CODE_HOME/MSExp/mkdir book-api`
* `cd book-api`
* `jhipster` / application name `bookapi` / port `8082` / package `org.msexpe.bookapi` / db `MongoDB`
* Open project in Intellij IDEA

### Add the Book entity

* `jhipster entity book`
* title (String) / price (Integer) / isbn (String) 

## Create the Microservice Gateway

* `cd ..`
* `mkdir book-web`
* `cd book-web`
* `jhipster` / application name `bookweb` / port `8080` / package `org.msexpe.bookweb` / db `PostgreSQL`

### Add the Book entity

* `jhipster entity book`
* From microservice
* root directory: `../book-api`

### Show code

* `BookResource`

## Run Docker components MongoDB / Postgres / Registry 

* `$CODE_HOME/MSExp`
* MongoDB for Book API
  * Show `book-api/src/main/docker/mongodb.yml`
  * `docker-compose -f book-api/src/main/docker/mongodb.yml up -d`
* Postgres for Gateway API
  * Show `book-web/src/main/docker/mongodb.yml`
  * `docker-compose -f book-api/src/main/docker/mongodb.yml up -d`
* Registry
  * Show `book-web/src/main/docker/jhipster-registry.yml`
  * `docker-compose -f src/main/docker/jhipster-registry.yml up`

## Run Number API / Book API / Gateway

* Run the 3 projects in Intellij IDEA
* Go to [http://localhost:8080/]()
* Show Chrome console / Network tab / Clear
* Create a book

## Generate Client API using Swagger

* Show swagger contract at [http://localhost:8081/v2/api-docs]()
* `cd book-api`
* `yo jhipster-swagger-cli`
* Show class `ApiApiClient` change `@FeignClient` to `@FeignClient(value = "numberapi")`
* In `BookResource`

```
    @Autowired
    private ApiApiClient numberApi;
```

* Add lines

```
    ResponseEntity<String> response = numberApi.generateIsbnNumberUsingGET();
    String isbn = response.getBody();
    book.setIsbn(isbn);
    Book result = bookRepository.save(book);
```

* Redeploy

## Generate Kubernetes

* `mkdir deploy && cd deploy`
* `jhipster kubernetes`
