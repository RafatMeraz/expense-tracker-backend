## Development
This project is using 
- Java 21 (Amazon Corretto 21.0.6)
- Spring Boot 3
- Spring Security 6
- Postgres

To run this project, first run `docker-compose.yml` to set up the database. 

## Docker Deploy
To build an image to deploy, run on the terminal

```shell
docker build --platform linux/amd64 -t expense-tracker:version .
```
It will generate an image for you.

If you want to run your image with `development` environment

```shell
docker run -d --name et-app -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev
```

For `production`, you have run
```shell
docker run -d --name et-app -p 8080:8080 \
  -v /home/ec2-user/app-logs:/app/logs \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=your-db-host \
  -e DB_PORT=5432 \
  -e DB_NAME=your-db \
  -e DB_USERNAME=admin \
  -e DB_PASSWORD=secret \
  -e JWT_SECRET=your-secret \
  -e ACCESS_TOKEN_EXP=3600000 \
  -e REFRESH_TOKEN_EXP=604800000 \
  expense-tracker:version
```
## RUN AS JAR

To make a Jar, run in the terminal

```java
mvn clean package
```

You will find your output .jar at `target/your-project-name-version.jar`

To run your .jar

```java
java -jar your-project-name-version.jar
```


