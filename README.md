# Project to book movie tickets

# Documentation:
1. Architectural Decision Records [ADR](docs/ADR's)
2. High level design view [High level design](docs/High Level design)
3. Low level design view [High level design](docs/Low%20Level%20Design)

> Note: Class diagram is not updated. Might have some outdated information.

##  Functional aspects covered

- Theatre service
  Description: created to render theatre and linked shows.


### APIS:
1. getShowsByTheatre
2. createShows
3. deleteShow
4. updateShow

visit  [ShowsController](services/theatre/theatre-service/src/main/java/com/bookmy/theatres/controller/ShowsController.java)

## Non functional features
- API first approach with OpenAPI 3.0 visit  [openapi specifications](services/theatre/theatre-spec/src/main/resources/openapi.yaml)
- JPA auditing with AuditAware 
- Central Logging configuration
- Global exception handler
- Logging filter to log request and responses
- Env based configuration enabled
- common configuration in separate file
- API specification in specification project separately to handle clients,api generation[specifications](services/theatre/theatre-spec)
- Flyway enabled to support database migrations
- shared libraries are separate to enable common code to be shared accross services. visit  [shared libraries](shared)
- Bill of materials for service and spec [bom](shared/bom)
- ArchUnit support to ensure developers follow good code practices [archunit](shared/archunit-tests)
- Common error library to support all common errors [common error library](shared/error-lib)
- Added few unit test cases.

## Microservices envisaged
- Theatre service
- Booking service
- Payment integration service
- User service



### Postman collection
Postman collection can be found at [Postman collection](services/theatre/theatre-service/postman)

### How to run
1. create database in postgres instance with name `customerdb`.
2. Update [application-local](services/theatre/theatre-service/src/main/resources/app-config/application-local.yml) yaml file to point to your database configuration.
  ```properties
  datasource:
   url: jdbc:postgresql://localhost:5432/customerdb
   username: postgres
   password: postgres
   ```
3. do `mvn clean install` in the root project level.
4. Change to main application dir
`cd bookmy/services/theatre/theatre-service/src/main/java`
5. Run `mvn spring-boot:run`
