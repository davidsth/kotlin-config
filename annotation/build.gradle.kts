import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = ext.get("kotlinVersion") as String

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
