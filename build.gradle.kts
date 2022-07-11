import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.sia"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.hibernate:hibernate-spatial:5.6.9.Final")
    implementation ("org.locationtech.jts:jts-core:1.19.0")

    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.kotest:kotest-runner-junit5:5.2.3")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.2.3")
    testImplementation ("io.kotest:kotest-assertions-core:5.2.3")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation ("org.testcontainers:testcontainers:1.16.2")
    testImplementation("org.testcontainers:junit-jupiter:1.16.2")
    testImplementation("org.testcontainers:postgresql:1.16.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
