FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:11-slim

WORKDIR /
COPY --from=build /usr/src/app/target/campus-social-media-0.0.1-SNAPSHOT.jar /usr/app/config-server-0.0.1-SNAPSHOT.jar
# EXPOSE 8888 
CMD java -jar config-server-0.0.1-SNAPSHOT.jar --server.port=$PORT
