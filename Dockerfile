#IMAGEN MODELO

FROM openjdk:17-jdk-slim

#INFORMAR EL PUERTO DONDE SE EJECUTA  EL CONTENEDOR (INFORMATIVO)
EXPOSE 8090

#Defino directorio raiz

WORKDIR /app

COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# OTORGAR PERMISOS DE EJECUCIÓN AL SCRIPT mvnw
RUN chmod +x mvnw



# DESCARGO LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline


# CONSTRUYO MI APLICACIÓN
RUN ./mvnw clean install -DskipTests



# LEVANTAR MI APLICACIÓN CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java", "-jar", "target/HR-Medical-Records-Management-System-0.0.1-SNAPSHOT.jar"]