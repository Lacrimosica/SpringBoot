#Template for making a dockerfile

FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY $(JAR_FILE) company.jar
ENTRYPOINT ["java","-jar","\company.jar"] 