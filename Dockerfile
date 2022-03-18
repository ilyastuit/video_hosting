FROM adoptopenjdk/openjdk15:ubi

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY /backend/build/libs/app.jar $APP_HOME/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]