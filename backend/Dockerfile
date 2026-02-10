# Dockerfile is based on Spring Boot Documentation:
# https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html

FROM gradle:9.2.1-jdk21 AS builder
WORKDIR /builder
ARG SUBPROJECT

# Copy root gradle files
COPY gradlew settings.gradle ./
COPY gradle ./gradle

# Copy all subproject gradle files
COPY shared/build.gradle ./shared/
COPY search-service/build.gradle ./search-service/
COPY forum-service/build.gradle ./forum-service/

# Download dependencies
RUN gradle dependencies

# Copy all source code
COPY shared/src ./shared/src
COPY search-service/src ./search-service/src
COPY forum-service/src ./forum-service/src

# Build the specific subproject
RUN gradle :${SUBPROJECT}:bootJar && mv ${SUBPROJECT}/build/libs/*.jar application.jar
# Extract the jar file using an efficient layout
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted


FROM bellsoft/liberica-openjre-debian:21.0.6
WORKDIR /application
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

ENTRYPOINT ["java", "-jar", "application.jar"]