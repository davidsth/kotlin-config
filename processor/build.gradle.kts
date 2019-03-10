import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mockkVersion = "1.9"
val spekVersion = "2.0.0"

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

    implementation(kotlin("reflect"))
    implementation("com.google.auto.service:auto-service:1.0-rc2")
    implementation("com.squareup:kotlinpoet:0.7.0")

    //test
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude("org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude("org.junit.platform")
        exclude("org.jetbrains.kotlin")
    }
    testCompile("org.junit.platform:junit-platform-engine:1.3.1")
    testImplementation("io.mockk:mockk:$mockkVersion")
    //mock lib
    testCompile("org.mockito:mockito-inline:2.24.5")
    // expect matchers
    testCompile("net.oddpoet:kotlin-expect:1.2.1")

}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
}