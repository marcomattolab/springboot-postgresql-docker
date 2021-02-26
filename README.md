# Spring Boot with PostgreSQL with docker compose for URBANITE demo (Target System)
## STEPS FOR THIS SPRING BOOT APP
- Define dependencies in build.gradle
- Create a Spring Boot Main @SpringBootApplication
- Create components @Entity / @RestController / @Repository
- Create application.properties
- Build 
- Check app using curls 

## BUILD the application 
./gradlew build   

## BUILD AND UP Docker Compose 
docker-compose up --build   
docker-compose down <- down docker compose     

## Use case one - ETL Orchestrator Demo (This is the Target System)
## EXECUTION

### API post-job  / POST : (Post job in order to be executed. Invoke in async another process to do transformation.)
body => {"uuid":"2"}  

header => Content-Type:application/json

url => http://localhost:5000/post-job   

### API get-status / GET : (Get Status)
http://localhost:5000/get-status?uuid=2

### API execution / GET : (Show execution data - only for tests)
http://localhost:5000/execution/2   

### API set-complete / GET : (set task to COMPLETED - only for tests) 
http://localhost:5000/set-complete?uuid=2 

### API execution/all / GET : (Show all execytions - only for tests)
http://localhost:5000/execution/all

### API get-json  / GET : (Show json retrieved by provided URL (cabled)  - only for tests)
http://localhost:5000/get-json

 

## CURLS 

### POST /user/save 
curl -s -X POST \
  http://localhost:8080/user/save \
  -H 'Content-Type: application/json' \
  -d '{"name":"Your Name"}'


### GET /user/{id}
curl -s -X GET \
  http://localhost:8080/user/1 
  
  