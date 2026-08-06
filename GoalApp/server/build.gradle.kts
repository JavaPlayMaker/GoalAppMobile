val ktor_version = "3.1.1"
val logback_version = "1.5.16"

plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version "3.1.1"
    kotlin("plugin.serialization")
    application
}

group = "com.example.goalapp"
version = "0.0.1"

application {
    mainClass.set("com.example.goalapp.server.MainKt")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")
    
    // Database
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.jetbrains.exposed:exposed-core:0.59.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.59.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.59.0")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.59.0")

    implementation("ch.qos.logback:logback-classic:$logback_version")
}
