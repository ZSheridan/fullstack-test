#!/bin/bash

kill $(lsof -t -i:5000)
kill $(lsof -t -i:5001)

script_dir=$(dirname $0)
echo $script_dir
cd $script_dir

cd music-suggestion-service
mvn clean package -q
wait
cd ..

cd front-end-application
mvn clean package -q
wait
cd ..

java -jar music-suggestion-service/target/music-suggestion-service-0.0.1-SNAPSHOT.jar &
java -jar front-end-application/target/front-end-application-0.0.1-SNAPSHOT.jar
