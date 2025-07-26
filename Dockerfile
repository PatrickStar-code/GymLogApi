# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Compila o projeto, ignorando os testes
RUN mvn clean package -DskipTests

# Etapa final (imagem leve para produção)
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho no container final
WORKDIR /app

# Copia o JAR gerado na build
COPY --from=build /app/target/Gymlog-0.0.1-SNAPSHOT.jar app.jar

# Define a porta dinamicamente via variável de ambiente (Render exige isso)
ENV PORT=8080
EXPOSE $PORT

# Comando de inicialização
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
