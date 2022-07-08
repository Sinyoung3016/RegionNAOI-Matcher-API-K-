FROM adoptopenjdk/openjdk15
ARG JAR_FILE=build/libs/matcher_kotlin_api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} matcher.jar
ENTRYPOINT ["java","-jar","/matcher.jar"]