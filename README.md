# Introduction
This is a task project for building microservice based on the following requiements:

  Customer has the following attributes
  Name
  Date of birth
  Address
  List of accounts
  
  Account has the following attributes
  Start date
  Balance
  Transactions
  Product type / current and saving
  
  As well as CRUD applications on the above domains there should be three core operations
  Create customer
  Create account and add to customer
  Get account balance
  
  Use a public GitHub project
  
     -      Clean code
     -      SOLID principles
     -      12 Factor app
     -      Deployable as a Kubernetes deployment and external services.
     -      Readme in the project to document your assumptions
     -      Implement standard health check and readiness endpoint
     -      Store data to a mongo database (optional)


# Getting Started

The customer service has been developed using Spring Boot using the following technical components
  - Web
  - Spring data for Mongondb
  - Lombok
  

### Build and running the tests via Docker container
Steps to build and run the test via the docker compose:
  - package the microservice app: mvn clean -Dmaven.test.skip=true package
  - create the docker containers for mongodb and customer-servivce
    * docker-compose build
  - deploy the app with mongodb
    * docker-compose up
  - shut down the docker compose
    * ctrl + c (will stop both containers)
  
### Deploy with Kubernetes
The following guides illustrate how to use some features concretely:


