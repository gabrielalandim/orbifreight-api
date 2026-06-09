# ETAPA 1: Construção (Build)
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
# Copia todos os arquivos do projeto para dentro do Docker
COPY . .
# Roda o Maven para baixar as dependências e gerar a pasta target
RUN ./mvnw clean package -DskipTests

# ETAPA 2: Execução (Run)
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copia APENAS o .jar gerado na Etapa 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]