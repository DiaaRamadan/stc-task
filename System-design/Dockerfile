FROM openjdk:17 as build

LABEL author="Diaa"

WORKDIR /app

COPY target/*.jar /app/app.jar

FROM openjdk:17 as runtime

LABEL author="Diaa"

RUN groupadd -r appuser && useradd -r -g appuser appuser

USER appuser

WORKDIR /app

COPY --from=build /app/app.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar" ,"app.jar"]

