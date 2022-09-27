## Log Auditing and Blockhain System for IY3821 Individual Project In Information Security

This project involves retrieving data relating to monitor readings of hospital patients
and medical environments, parsing this data on the client side and sending this data
to a small blockchain network. This project also serves as a simulation for 
real-life IOT-based medical systems.

## Technologies

This project has been completely solely in Kotlin. 

The Ktor framework written by JetBrains was used to create the server applications reposible 
for sending data objects to the client as well as sending log messages to the blockchain.
(iot-simulator folder contains soley a Ktor server)

The network of nodes was created using R3's Corda. (Java 8 required!)
(source code can be found in the cordapp folder)

The client application was written as an Android Apllication 
(source code can be found in the client folder)

An up-to-date version of docker is essential for running applications locally.

## Useful commands

(in iot-simulator project)

```
docker build -t iot-simulator // build the simulator project in docker
docker run --name iot-simulator -p 8080:8080 iot-simulator // run the project in docker
docker stop iot-simulator // stop the project in docker
```

(in cordapp project)
```
gradlew clean deployDockerNodes // build the network of nodes
docker-compose up // launch nodes in docker from the same location
docker ps // good for checking status of the nodes
docker stop // terminates all nodes instantly


docker build -t cordapp // build the rpc client in docker
docker run --name cordapp -p 8080:8080 iot-simulator // run the client in docker
docker stop cordapp // stop the client in docker
```
Useful note: The default user name and passwords of all nodes in build.gradle NOT production ready!

## Demo link
https://www.youtube.com/watch?v=nLbTgqKE_dg
