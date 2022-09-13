
FROM openjdk:8-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update
RUN apt-get install -y dos2unix
RUN dos2unix gradlew

RUN bash gradlew jar

WORKDIR /run
RUN cp /src/clients/build/libs/*.jar /run/server.jar

## EXPOSE 8080

CMD java -jar /run/server.jar

# docker build -t iot-simulator
# docker run --name iot-simulator -p 8080:8080 iot-simulator
# docker stop iot-simulator
