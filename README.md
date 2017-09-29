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

## Demo 01 .Net Core

To be able to create the new project, you have to install :
* Visual Studio 2017 Preview 4.
* .Net Core 2.0

Then, Crate a new project :
* `Angular 4` ASP.NET Core Web application
* Choose `ASP.NET CORE 2.0` to be able to see Angular Template
* Run it with `F5` to see if everything is working as expected.


### Number API

* `mvn clean install` creates the executable Spring Boot war file (`target/number-api-1.0.jar`)
* Execute the service with `java -jar target/number-api-1.0.jar`
* HTTP GET on [http://localhost:8080/api/isbn]()
* Swagger contract on [http://localhost:8080/v2/api-docs]()
* Swagger UI on [http://localhost:8080/swagger-ui.html#/isbn-resource]()

### Number Web

Angular Web app invoking Number API using `Swagger`

**1) Creating a Web App on Azure for hosting a Java Web API**

Full tutorial on this page : [https://docs.microsoft.com/fr-fr/azure/app-service/app-service-deploy-spring-boot-web-app-on-azure](https://docs.microsoft.com/fr-fr/azure/app-service/app-service-deploy-spring-boot-web-app-on-azure)


* Execute the `mvn clean install` to generate the .jar
* Login to [http://portal.azure.com](http://portal.azure.com)
* Create a new `Web App` , something like `msexp17`
* Configure for Java in `Application Settings` with **Java 8** and **Tomcat latest**
* Create the `web.config` file replacing with your `xxx.jar`  :

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <system.webServer>
    <handlers>
      <add name="httpPlatformHandler" path="*" verb="*" modules="httpPlatformHandler" resourceType="Unspecified" />
    </handlers>
    <httpPlatform processPath="%JAVA_HOME%\bin\java.exe"
        arguments="-Djava.net.preferIPv4Stack=true -Dserver.port=%HTTP_PLATFORM_PORT% -jar &quot;%HOME%\site\wwwroot\gs-spring-boot-0.1.0.jar&quot;">
    </httpPlatform>
  </system.webServer>
</configuration>
```

* Upload with FTP on `site/wwwroot`
* Restart web site from **Azure** portal
* HTTP GET on [http://msexp17.azurewebsites.net/api/isbn]()
* Swagger contract on [http://msexp17.azurewebsites.net/v2/api-docs]()
* Swagger UI on [http://msexp17.azurewebsites.net/swagger-ui.html#/isbn-resource]()


**2) Create an Angular 4 client proxy to reach the Web API and create an Angular 4 UI, using Swagger generation**

* Go to to [https://editor.swagger.io/]() to generate the client code  
* Get the swagger contract from [http://msexp17.azurewebsites.net:8080/v2/api-docs]()  
* Generate the **Angular 4** files and save in `/ClientApp/app/isbn`  
* Remove unnecessary files (like `.gitignore` and push bat)  
* Add a new component called **isbn** in `ClientApp/app/Components` (without css)
* Editing module `app.module.shared.ts` :  

```
import * as isbn from './isbn/index'; 
import {IsbnComponent } from './components/isbn/isbn.component';

```
```
declarations: [
        IsbnComponent
    ],
```

```
 RouterModule.forRoot([
            { path: 'isbn', component: IsbnComponent },
        ])
```

```
 providers: [
        isbn.IsbnresourceApi,
        { provide: isbn.BASE_PATH, useValue: 'http://msexp17.azurewebsites.net' }

    ]
```

* Editing the typescript component **isbn** in `/ClientApp/app/Component/isbn/isbn.component.ts` :

```
import { IsbnresourceApi } from "../../isbn/index";
```

```
    isbn: string = "";

    /** isbn ctor */
    constructor(private api: IsbnresourceApi) {
    }

    /** Called by Angular after isbn component initialized */
    ngOnInit(): void {
        this.api.generateIsbnNumberUsingGETWithHttpInfo()
            .subscribe(r => {
                if (r.status === 200)
                    this.isbn = r.text();
            });

    }
```

* Editing the html component **isbn** in `/ClientApp/app/Component/isbn/isbn.component.html` :

```
<span>{{isbn}}</span>
```

* Browse [http://localhost:5272/isbn]() to see the CORS error

**3) Enabling CORS on Azure to be able to call Web API not on same domain**

During demo, you will have a CORS error.  
Go to **Azure** portal and enable **CORS** in the **Cors menu**.  
Add `*`


## Demo 02

This demo is deprecated has it uses Postgres instead of MongoDB

## Demo 03

In this demo, we keep the Number API and we add another REST API that inserts Books into a database. 

```
├── book-api : REST API inserting a book into a MongoDB database (needs the Number API)
│   ├── pom.xml
│   └── src
├── number-API : REST API returning a random ISBN number (no database)
│   ├── pom.xml
│   └── src
├── book-web : Angular gateway interacting with the Book API (Postgres is needed to store users and roles)
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

* Public API (no authorization)
* HTTP GET on [http://localhost:8081/api/isbn]()
* Swagger contract on [http://localhost:8081/v2/api-docs]()

### Book API

* Needs MongoDB `docker-compose -f book-api/src/main/docker/mongodb.yml up`
* Public API (no authorization because changed `MicroserviceSecurityConfiguration`)
* HTTP GET on [http://localhost:8082/api/books]()
* Swagger contract on [http://localhost:8082/v2/api-docs]()

### Book Web

* Needs Postgres `docker-compose -f book-web/src/main/docker/postgresql.yml up `
* [http://localhost:8080]()
