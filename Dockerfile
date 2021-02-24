FROM openjdk:11
LABEL maintainer="vyao888@hotmail.co.uk"
VOLUME /main-app
ADD ./target/customer-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar", "app.jar"]
