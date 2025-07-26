
# 🏋️‍♂️ GymLog API

Uma aplicação para registrar e acompanhar treinos de musculação, ajudando usuários a monitorar seu progresso, exercícios, séries, repetições e cargas ao longo do tempo.

---

## 🧰 Tecnologias e Bibliotecas

### ✅ Frameworks & Bibliotecas Principais
- **Spring Boot Starter Web** – APIs REST.
- **Spring Boot Starter Security** – Segurança e autenticação.
- **Spring Boot Starter Data JPA** – Integração com banco de dados via JPA.
- **Spring Boot Starter Validation** – Validações com anotações.
- **Spring Boot Starter Mail** – Envio de e-mails (SMTP).

### 🔐 Segurança & Autenticação
- **Java JWT** – Geração e validação de tokens JWT (`com.auth0:java-jwt`).

### 📦 Documentação
- **SpringDoc OpenAPI (Swagger)** – Documentação interativa da API.

### 🗃️ Banco de Dados & Migrations
- **PostgreSQL Driver** – Conector PostgreSQL.
- **Flyway Core** – Migrations de schema.
- **Flyway PostgreSQL** – Integração com PostgreSQL.

### 🛠️ Utilitários
- **Lombok** – Redução de boilerplate.
- **JetBrains Annotations** – Anotações úteis.

### 🧪 Testes
- **Spring Boot Starter Test** – Testes integrados.
- **Spring Security Test** – Testes de segurança/autenticação.

---

## ⚙️ Build & Configuração

- **Gerenciador de dependências:** Maven  
- **Plugins:**  
  - `maven-compiler-plugin`  
  - `spring-boot-maven-plugin`
- **Versão Java:** 21  
- **Versão Spring Boot:** 3.4.5  

---

## 🔐 Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes chaves:

```env
DB_USERNAME=postgres
DB_PASSWORD=senha123
DB_URL=jdbc:postgresql://localhost:5432/gymlog

JWT_SECRET=minha-chave-secreta
JWT_ISSUER=gymlog-api

EMAIL_USERNAME=seuemail@gmail.com
EMAIL_PASSWORD=suasenha
```

---

## 📦 Instalação

### ✅ Pré-requisitos

Certifique-se de ter instalado:

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

### 📁 Clonando o repositório

```bash
git clone https://github.com/seu-usuario/gymlog.git
cd gymlog
```

### ⚙️ Configurando as variáveis de ambiente

Crie o arquivo `.env` e preencha com os valores necessários.

### 🛠️ Compilando o projeto

```bash
mvn clean install
```

### ▶️ Executando a aplicação

```bash
mvn spring-boot:run
```

Ou rode pela sua IDE preferida.

---

## 🌐 Endpoints Principais

- API: [http://localhost:8080](http://localhost:8080)
- Swagger: [http://localhost:8080/swagger/swagger-ui/index.html](http://localhost:8080/swagger/swagger-ui/index.html)

---

## 👨‍💻 Autor

- [@PatrickStar](https://www.github.com/octokatherine)

---
