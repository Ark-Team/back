FROM openjdk:8
VOLUME /tmp
EXPOSE 9090
COPY Pqrs-1.0.0.jar /Pqrs-1.0.0.jar
ENTRYPOINT ["java","-jar","Pqrs-1.0.0.jar"]