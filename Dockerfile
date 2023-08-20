FROM  openjdk:17
ARG JAR_FILE=target/OnlineResellingProject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} online-reselling-project.jar
ENTRYPOINT ["java","-jar","online-reselling-project.jar"]
