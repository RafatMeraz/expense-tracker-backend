## Development
This project is using 
- Java 21 (Amazon Corretto 21.0.6)
- Spring Boot 3
- Spring Security 6
- Postgres

To run this project, first run `docker-compose.yml` to set up the database. 

## Deploy
To build an image to deploy, run on the terminal

```
docker build --platform linux/amd64 -t expense-tracker:version .
```
It will generate an image for you.

## RUN

To make a Jar, run in the terminal

```java
mvn clean package
```

You will find your output .jar at `target/your-project-name-version.jar`

To run your .jar

```java
java -jar your-project-name-version.jar
```


