group = "dev.davidsth"
version = "1.0-SNAPSHOT"


plugins {
    kotlin("jvm") version "1.3.11"
    kotlin("kapt") version "1.3.11"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}


allprojects {
    ext {
        set("kotlinVersion", "1.3.21")
    }

}

