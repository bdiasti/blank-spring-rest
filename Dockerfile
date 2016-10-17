FROM java:8
ADD target/*.jar app.jar
CMD java -jar app.jar
VOLUME /tmp
EXPOSE 8080