FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
ENV MAVEN_OPTS="-Xmx512m"
RUN mvn package -DskipTests -Dcheckstyle.skip=true

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]