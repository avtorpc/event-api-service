# =========================
# BUILD STAGE
# =========================
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# копируем только нужное (лучше для кеша)
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew
RUN ./gradlew clean build -x test


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]