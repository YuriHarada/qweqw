FROM openjdk:17-oracle
ARG JAR_FILE
VOLUME /tmp
COPY ${JAR_FILE} yellow.jar
ENTRYPOINT ["java","-jar","/yellow.jar"]