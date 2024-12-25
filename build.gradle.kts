plugins {
    id("java")
    id("checkstyle")
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter для работы с Web (REST API)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot Starter для работы с PostgreSQL
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Spring Boot Starter для валидации
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Swagger/OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // H2 (встроенная база данных для локальной разработки и тестирования)
    runtimeOnly("com.h2database:h2")

    // DevTools для горячей перезагрузки во время разработки
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Логирование
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // Тестирование
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Подключение Lombok (по желанию, для упрощения кода)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    implementation("org.thymeleaf:thymeleaf-spring6:3.1.3.RELEASE")
}

tasks.test {
    useJUnitPlatform()
}

// Настраиваем Checkstyle
checkstyle {
    toolVersion = "10.17.0"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

