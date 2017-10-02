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

# Demo 02 : Create a Web app, hosting the Java Web API

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


# Demo 03 : Angular app for Number API 

## 1- Create a new ASP.NET CORE 2.0 Angular 4 project

To be able to create the new project, you have to install :
* Visual Studio 2017 Preview 4.
* .Net Core 2.0

Then, Crate a new project :
* `Angular 4` ASP.NET Core Web application
* Choose `ASP.NET CORE 2.0` to be able to see Angular Template
* Run it with `F5` to see if everything is working as expected.

## 2- Create an Angular 4 client proxy to reach the Web API and create an Angular 4 UI, using Swagger generation**

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

## 3- Enabling CORS on Azure to be able to call Web API not on same domain**

During demo, you will have a CORS error.  
Go to **Azure** portal and enable **CORS** in the **Cors menu**.  
Add `*`

## 4- TO KEEP TIME : Create the kubernetes cluster on azure

To save time, prefer launch those instructions now:

```
az group create --name rgk8msexp --location westeurope
az acs create --orchestrator-type kubernetes --resource-group rgk8msexp --name k8msexp --generate-ssh-keys

```

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

# Demo 05 : JHipster dans Azure

* From `/demo03` folder, open a terminal command an execute :

```
// Create a new temp directory
mkdir kube
cd kube

// launch jhipster kubernetes command
jhipster kubernetes
```

Follow instructions and dont forget to point to `agoncal` hub.docker.com 

Open a command to `Azure CLI`:

```
// Get credentials and point to Kubernetes cluster
az acs kubernetes get-credentials --resource-group=rgk8msexp --name=k8msexp

// Get informations 
kubectl get nodes
kubectl get service
kubectl get pods
kubectl get rs
```

Execute commands from the Jhistper Kubernetes readme text file :

```
kubectl apply -f registry

kubectl apply -f bookapi
kubectl apply -f bookweb
kubectl apply -f numberapi
```

Watch evolutions on pods :

```
// watcher on pods / svc status
kubectl get pods --watch
kubectl get svc --watch

// Get deployment
kubectl get deployment

// OPTIONAL : Scale on 3 replicats
kubectl scale deployment bookweb --replicas 3

// OPTIONAL : launch console admin
az acs kubernetes browse -g rgk8msexp -n k8msexp

// OPTIONAL : Port forwarding to see jhipster registry 
kubectl port-forward [podname] 8761:8761
```

