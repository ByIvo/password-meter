FROM tomcat:8.0-jre8
WORKDIR $CATALINA_HOME
COPY ./target/password-meter*.war ./webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]