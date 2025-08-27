FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Etapa final (imagem leve para produção com JDK 21)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY --from=build /app/target/Gymlog-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE $PORT

CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
