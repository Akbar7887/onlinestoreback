FROM openjdk:17
EXPOSE 5558

COPY build/libs/onlinestore-0.0.1-SNAPSHOT.jar onlinestore.jar

#ADD build/libs/onlinestore-0.0.1-SNAPSHOT.jar onlinestore-0.0.1-SNAPSHOT.jar
#ADD entrypoint.sh entrypoint.sh

ENTRYPOINT ["java","-jar","onlinestore.jar"]
#ENTRYPOINT ["sh", "/entrypoint.sh"]
