FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests


FROM openjdk:11-slim

WORKDIR /
COPY --from=build /usr/src/app/target/campus-social-media-0.0.1-SNAPSHOT.jar campus-social-media-0.0.1-SNAPSHOT.jar
# EXPOSE 8888 
CMD java -jar campus-social-media-0.0.1-SNAPSHOT.jar --server.port=$PORT --spring.cloud.config.username=$configUserName --spring.cloud.config.password=$configPassword --url=$URL --spring.mail.password=$mailPassword

