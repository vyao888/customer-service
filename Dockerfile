FROM openjdk:11
LABEL maintainer="vyao888@hotmail.co.uk"
VOLUME /customer-service-app
ADD ./target/customer-service-0.0.1-SNAPSHOT.jar customer-service-app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar", "customer-service-app.jar"]
