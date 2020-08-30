# socialnetwork

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.socialnetwork.SocialnetworkApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Endpoints
* GET /api/post/{postId}
* GET /api/posts

## Possible enhancements
* Improve the unit tests to cover more scenarios. It's only a high level at the moment.
* Create a cache layer in the integration api call layer.
* In the get all comments method, we could use a HashMap to load all the comments. It would perform better than the ArrayList in the search by id.
* Decouple the code a bit more. Program to interface could be used
* Have a more detailed error messages.
* Add some specific validations.
* Add swagger api documentation.
* Use HATEOS for Hypermedia api.
* Authentication/Security.
