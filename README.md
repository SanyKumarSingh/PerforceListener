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

6. Access the application: http://localhost:8080/api/v1/viewLogEvents


########## To kill the Process on Windows Server ##########

Find the process ID (PID): Open the Task Manager on the Windows server and go to the "Processes" tab. Look for the Java process associated with your Spring Boot application. Note down the process ID (PID) of the Java process.

Open the Command Prompt on the Windows server.

Stop the application: In the Command Prompt, execute the following command to stop the Spring Boot application:
taskkill /F /PID <pid>

Replace <pid> with the process ID (PID)

C:\Users\a5143522>taskkill /F /PID 44112
SUCCESS: The process with PID 44112 has been terminated.

4. Verify the application is stopped: Check the Task Manager again to ensure that the Java process associated with your Spring Boot application is no longer running. Alternatively, try accessing the application's URL in a web browser to confirm that it is no longer accessible.



########## Deployment to Linux Server ##########

1. Build the Spring Boot application: Use Maven as build tool to build this Spring Boot application. This will create an executable JAR file.
C:\Users\a5143522\eclipse-workspace\PerforceListener>mvn clean install

Deployable could be found at - C:\Users\a5143522\.m2\repository\com\renesas\PerforceListener\0.0.1-SNAPSHOT\PerforceListener-0.0.1-SNAPSHOT.jar

2. Copy the application files to the server: Copy the generated JAR file to the Linux server where you want to deploy the application. Place it in any directory of  choice.

scp <local_file_path> <username>@<server_ip>:<destination_directory>

scp C:\Users\a5143522\RenesasCodebase\PerforceListener-0.0.1-SNAPSHOT.jar ssingh@slsrvcomp-01.diasemi.com:/users/ssingh/perforce

3. Install Java: Ensure that Java is installed on the Linux server.

Copy Java zip from local to Linux server in any directory of  choice.
C:\Users\a5143522\RenesasCodebase>scp C:\Users\a5143522\RenesasCodebase\openlogic-openjdk-11.0.19+7-windows-x64.zip ssingh@slsrvcomp-01.diasemi.com:/users/ssingh/perforce

Unzip the file
[ssingh@slsrvcomp-01 ~/perforce]$ unzip openlogic-openjdk-11.0.19+7-windows-x64.zip
Find the Linux distribution being used, based on the output of the uname -a command, it is clear that we are using Linux operating system you are using is CentOS 7.
[ssingh@slsrvcomp-01 ~/perforce]$ uname -a
Linux slsrvcomp-01 3.10.0-1160.el7.x86_64 #1 SMP Tue Aug 18 14:50:17 EDT 2020 x86_64 x86_64 x86_64 GNU/Linux
[ssingh@slsrvcomp-01 ~/perforce]$

We can install OpenJDK using the package manager available for Linux distribution. example, on Ubuntu, we can use the following command:
[ssingh@slsrvcomp-01 ~/perforce]$  sudo yum install openlogic-openjdk-11.0.19+7-windows-x64
Sorry, user ssingh is not allowed to execute '/bin/yum install openlogic-openjdk-11.0.19+7-windows-x64' as root on slsrvcomp-01.
[ssingh@slsrvcomp-01 ~/perforce]$

[icetest@slsrvcomp-01 ~]$ cd /users/ssingh/perforce
[icetest@slsrvcomp-01 perforce]$ sudo yum install openlogic-openjdk-11.0.19+7-windows-x64
Sorry, user icetest is not allowed to execute '/bin/yum install openlogic-openjdk-11.0.19+7-windows-x64' as root on slsrvcomp-01.
[icetest@slsrvcomp-01 perforce]$
4. Start the application: Open a terminal on the Linux server and navigate to the directory where the application file (generated JAR) is copied.

5. Run the application: Execute the following command to start the Spring Boot application with the in-memory Tomcat server

java -jar PerforceListener-0.0.1-SNAPSHOT.jar

6. Access the application: http://localhost:8080/api/v1/viewLogEvents


########## To kill the Process on Linux Server ##########

1. Find the process ID (PID): Open a terminal on the Linux server and execute the following command to list the running Java processes: 
ps -ef | grep java  
Look for the Java process associated with your Spring Boot application. Note down the process ID (PID) of the Java process.

2. Stop the application: In the terminal, execute the following command to stop the Spring Boot application:  kill -9 <pid>
Replace <pid> with the process ID (PID) you noted down in Step 1. The -9 option sends a SIGKILL signal to force the process to terminate.

3. Verify the application is stopped: execute the ps -ef | grep java command again to ensure that the Java process associated with your Spring Boot application is no longer running. Alternatively, try accessing the application's URL in a web browser to confirm that it is no longer accessible.


