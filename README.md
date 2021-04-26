
# CREDIT CARD PROCESSING


Spring Boot REST API to add new credit card accounts and list them all.

 **Steps to Setup**

1. Clone the application

   https://github.com/neelusharma80/cardServiceRestAPI.git

2. Change inmemory HS2 Database username and password as per your installation

   open src/main/resources/application.properties

   change spring.datasource.username and spring.datasource.password as per your installation
 
3. Build and run the app using gradle

    gradlew clean build

    If you want to run test separately
    gradlew test

   gradlew bootrun

   The app will start running at http://localhost:8080.

4. If you wat you can validate HS2 database creation

     http://localhost:8080/h2
     

5.Explore Rest APIs
   The app defines following APIs

         GET /api/v1/listCards

         POST /api/v1/addCard

