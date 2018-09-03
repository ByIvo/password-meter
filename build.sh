#!/bin/bash
echo 'FIRST npm install'
cd src/main/resources
npm install
npx bower install
npx grunt dist
cd ../../..

echo 'Clean and package the springboot application'
mvn clean package

#echo 'Move the candidate release to a know version (no params this time)'
#mv ./target/password-meter*.war ./target/password-meter-deploy.war

echo 'Create a image of project'
docker build -t password-meter .

echo 'DONE!!!!! Just run docker run -i password-meter'