###### Perforce Listener Plugin ##########
Listener plugin for Perforce Methodics (IPLM)

Event Listener in Java Code here - https://github.com/SanyKumarSingh/PerforceListener
git clone https://github.com/SanyKumarSingh/PerforceListener.git

To execute this SpringBootApplication run Application.java main class, it will automatically start the application on embedded Apache Tomcat and InMemory H2 database. 

Application class is the main entry point of this SpringBoot Application. 
Execute the main method to run the application on in-memory Tomcat Server and H2 database.

Dev H2 Database Console - http://localhost:8080/h2-console/

GET Request -
Fetch All LogEvents - http://localhost:8080/api/v1/viewLogEvents

POST Request -
Add Log Events - http://localhost:8080/api/v1/addLogEvents

Postman(Chrome Plugin) to test below REST API's - https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop/related?hl=en 


###########  Code Details ###########

RedisConfig.Java Class sets up the  message listener container and the message listener adapter.

redisMessageListenerContainer() method creates a RedisMessageListenerContainer bean and configures it to use the Redis connection factory obtained from the RedisTemplate. It adds the messageListenerAdapter as the message listener for a specific channel.

To listen to Single or List of channel using Spring Boot and Spring Data Redis use ChannelTopic class, and to listen to multiple Redis channels use the PatternTopic class, using a pattern example "*".

redisConnectionFactory() method configure the host and port details of the Single Instance Redis Configuration. If the Redis Server runs locally then this method is not required. The RedisConnectionFactory bean is created using the LettuceConnectionFactory class, which is one of the Redis connection factory implementations provided by Spring Data Redis. The RedisStandaloneConfiguration provided by Spring Data Redis is used to configure the Redis host and port details.

messageListenerAdapter() method creates a MessageListenerAdapter bean that implements the MessageListener interface.  It defines the logic to process the received Redis messages.



PerforceListenerApplication.java Class implements message listener container and the message listener adapter.

onMessage() checks if pattern message and message is a Json then it will store the message attributes to database.



DataMapperUtils.java is common utils class to convert DTO to Entity and JSON to DTO. 

EventController.java class is the controller for Restful web services.

LogEventServiceImpl.java class has the backed logic of fetching data, its processing and insertion to database via repository.

LogEventRepository.java is the Interface that extends JpaRepository and is the repository layer of the program to interact with the database. 

data.sql has the DDL statement and sample DML statement.

application.properties is the configuration file.


########## Deployment on Windows Server #########

Build the Spring Boot application: Use Maven as build tool to build this Spring Boot application. This will create an executable JAR file.
C:\Users\a5143522\eclipse-workspace\PerforceListener>mvn clean install

Deployable could be found at - C:\Users\a5143522\.m2\repository\com\renesas\PerforceListener\0.0.1-SNAPSHOT\PerforceListener-0.0.1-SNAPSHOT.jar

2. Copy the application files to the server: Copy the generated JAR file to the Windows server where you want to deploy the application. Place it in any directory of  choice.

3. Install Java: Ensure that Java is installed on the Windows server. 

4. Start the application: Open a command prompt or PowerShell window on the Windows server. Navigate to the directory where the application file (generated JAR) is copied.

5. Run the application: Execute the following command to start the Spring Boot application with the in-memory Tomcat server

C:\Users\a5143522\RenesasCodebase>java -jar PerforceListener-0.0.1-SNAPSHOT.jar


