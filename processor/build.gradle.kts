import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

group = "dev.davidsth"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
}

apply {
    plugin("kotlin")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":annotation"))
    implementation("com.google.auto.service:auto-service:1.0-rc2")
    implementation("com.squareup:kotlinpoet:0.7.0")

    //test
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
}