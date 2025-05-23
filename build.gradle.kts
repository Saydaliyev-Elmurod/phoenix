plugins {
    java
    id("org.springframework.boot") version "3.4.6"
    id("io.spring.dependency-management") version "1.1.7"
}

ext {
    set("lombokVersion", "1.18.30")
    set("mapstructVersion", "1.6.2")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

repositories {
    mavenCentral()
}
dependencyManagement {
    imports {
        mavenBom("org.immutables:bom:2.10.1")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")

    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    //jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
    //
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    implementation("org.mapstruct:mapstruct:1.6.2")
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")

    //immutable
    annotationProcessor("org.immutables:value:2.10.1")
    compileOnly("org.immutables:builder:2.10.1")
    compileOnly("org.immutables:value-annotations:2.10.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.2")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}