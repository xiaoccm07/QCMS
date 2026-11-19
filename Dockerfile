FROM maven:3.6.3-jdk-8 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q dependency:go-offline || true
COPY src ./src
RUN mvn -q package -DskipTests

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /build/target/qcms-1.0.0.war app.war
RUN mkdir -p /app/static/file /app/static/images /app/static/attachment /app/export
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.war"]
