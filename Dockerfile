FROM openjdk:17
EXPOSE 5558

COPY target/onlinestoreback-0.0.1-SNAPSHOT.jar onlinestoreback.jar

#ADD build/libs/onlinestore-0.0.1-SNAPSHOT.jar onlinestore-0.0.1-SNAPSHOT.jar
#ADD entrypoint.sh entrypoint.sh

ENTRYPOINT ["java","-jar","onlinestoreback.jar"]
#ENTRYPOINT ["sh", "/entrypoint.sh"]
