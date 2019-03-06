group = "dev.davidsth"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
}

apply {
    plugin("kotlin")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":annotation"))
    implementation("com.google.auto.service:auto-service:1.0-rc2")
    kapt("com.google.auto.service:auto-service:1.0-rc2")
}
