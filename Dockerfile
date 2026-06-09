# Usa uma imagem base com Java 21
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado para dentro da imagem
COPY target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]