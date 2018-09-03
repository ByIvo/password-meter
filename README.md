# password-meter
A spring project to measure your password strength

# How to run in your local machine
## Requirements
* npm (at least 5.2.0 to use npx)
* docker
* java8
* maven

## Running
To run in your local machine (considering you alredy installed the required tools), just type in your CLI
```sh
./build.sh
```
When the build process finish, a docker image call *password-meter* will be created with the port 8080 exposed
To run the application, type in your CLI
```sh
docker run -i password-meter
```
After that, just open http://localhost:8080 and verify your password strength

# How to create new features

## Test is important
Remember to always run all tests before the commit; You can do it typing
```sh
mvn test
```

## It is a maven project
Just import as a new maven project in your favorite IDE

## The client
The front-end files are coupled with back-end files; They're all cointaned in resources folder.
Grunt is needed in less files compilation, so open a CLI and, in webapp folder, and type:
```sh
grunt
```
