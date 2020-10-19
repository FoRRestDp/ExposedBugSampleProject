import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "me.egorponomaryov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://jitpack.io")
    }
}
dependencies {
    testImplementation(kotlin("test-junit"))
    implementation(group = "com.github.kotlin-telegram-bot", name = "kotlin-telegram-bot", version = "5.0.0")
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect", version = "1.4.10")
    implementation(group = "org.jetbrains.exposed", name = "exposed-core", version = "0.28.1")
    implementation(group = "org.jetbrains.exposed", name = "exposed-jdbc", version = "0.28.1")
    implementation(group = "org.jetbrains.exposed", name = "exposed-dao", version = "0.28.1")
    implementation(group = "org.postgresql", name = "postgresql", version = "42.2.18")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}