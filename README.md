# Pivot reports app

### Description
Application intended to provide pivot reports according to user's selected parameters.
It also provides opportunity to generate HTML representation file of report. 

### Technologies

The following technologies are used:

Spring Boot, Thymeleaf, Bootstrap, Spring boot web, Spring Data(with H2 in memory db), 

### Installation
You should have Java 11 and maven installed on your machine to run this app.

Steps:

Install:

`mvn clean install`

Run app:

`mvn spring-boot:run`

Go to app page:

`http://localhost:8080/`

Go to on memory H2 DB console(type `jdbc:h2:mem:testdb` at JDBC URL to connect to app db):

`http://localhost:8080/h2-console/`

**Test app:**

At application startup test data is generated in H2 db by 'TestService' class.

To generate report and see it in application page, select all 4 parameters and tap on **'Generate report'** button.

To extract HTML report press on **'Generate html report'** button(report will be generated in _../pivotal_reports folder_, which is at same level with project)

**NOTE**

3rd point is done via GUI interface(the spring application need to be started to connect to db, to have initialized beans...).  

To test app via mysql db(not in memory) you can open appropriate properties in application.yml file and in pom.xml
