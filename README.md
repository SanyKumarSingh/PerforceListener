# Perforce Listener Plugin

Listener plugin for Perforce Methodics (IPLM)

To execute this SpringBootApplication run PerforceListenerApplication.java main class, it will automatically start the application on embedded Apache Tomcat and InMemory H2 database. 
Deployable after mvn clean install could be found at -C:\Users\a5143522\.m2\repository\com\renesas\PerforceListener\0.0.1-SNAPSHOT\PerforceListener-0.0.1-SNAPSHOT.pom

H2 Database Console - http://localhost:8080/h2-console/

Postman(Chrome Plugin) to test below REST API's - https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop/related?hl=en 

GET Request -
Fetch All LogEvents - http://localhost:8080/api/v1/viewLogEvents

POST Request -
Add Log Events - http://localhost:8080/api/v1/addLogEvents


MicroSoft Archieve for Redis - https://github.com/microsoftarchive/redis/releases