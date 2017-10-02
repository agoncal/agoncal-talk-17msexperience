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

* Remove old numberApi and copy new one 
* `rm -rf *` (`rm -rf .*` if needed for hidden files)
* `cp -rf ../Agoncal/agoncal-talk-17msexperience/demo03/number-api/ ./number-api`

## Book API with JHipster

* `$CODE_HOME/MSExp/mkdir book-api`
* `cd book-api`


## Generate Client API using Swagger

* `yo jhipster-swagger-cli`


## Generate Kubernetes

* `mkdir deploy && cd deploy`
* `jhipster kubernetes`
