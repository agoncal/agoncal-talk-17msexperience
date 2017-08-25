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
