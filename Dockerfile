# Etapa de build com JDK 24 manualmente instalado
FROM eclipse-temurin:24-jdk-jammy as build

# Instala Maven manualmente
RUN apt-get update && \
    apt-get install -y wget unzip curl && \
    curl -fsSL https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz | tar -xz -C /opt && \
    ln -s /opt/apache-maven-3.9.6 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Define variáveis de ambiente do Maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

WORKDIR /app
COPY . .

# Compila o projeto (assumindo que seu POM usa Java 24)
RUN mvn clean package -DskipTests

# Etapa final (runtime com JDK 24)
FROM eclipse-temurin:24-jdk-jammy

WORKDIR /app
COPY --from=build /app/target/Gymlog-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE $PORT

CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
