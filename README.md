# Notification Service
This is a readme instructions on how to use and setup the Notifications service.
## Description
This is a Notification service that used in-memory messaging queue that uses Publisher-subscriber pattern.
## Getting Started
### Dependencies
This projects requires the following:
* Postgresql sql is installed on the machine
  * follow the setup below for Postgres
* JDK is installed on the machine
* maven is setup on the machine

### Postgres Setup
* Create a new Database on Postgres
* configure the following env to connect to postgres
  * spring.datasource.password
  * spring.datasource.url=jdbc:postgresql://\<url>:\<port>/\<database>
  * spring.datasource.username
* The application will now auto create the notifications table
### Rules setup
* update the rules-config.json on resources/ folder
### Executing the program
``mvn spring-boot:run``