# Користиме официјална Java 17 слика (можеш и 21 ако користиш понова)
FROM eclipse-temurin:21-jdk-alpine

# Креирај директорија во image-от
WORKDIR /app

# Копирај jar фајлот (ќе го правиме подоцна)
COPY target/mastering_spring_boot_3-0.0.1-SNAPSHOT.jar app.jar

# Дефинирај entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
