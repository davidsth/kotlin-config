
val kotlinVersion = "1.3.11"

plugins {
    kotlin("jvm") version ("1.3.11")
}

group = "dev.davidsth"
version = "1.0-SNAPSHOT"


allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile(kotlin("reflect"))

    compileOnly(project("annotation"))
    kapt(project("processor"))
}

subprojects {
    version = "1.0"
}
