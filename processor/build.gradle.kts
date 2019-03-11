import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mockkVersion = "1.9"
val spekVersion = "2.0.0"
val kotlinVersion = ext.get("kotlinVersion") as String

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":annotation"))

    implementation(kotlin("stdlib-jdk8"))
    implementation("com.squareup:kotlinpoet:0.7.0")

    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.1")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.1")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.3.21")
    testImplementation("io.mockk:mockk:$mockkVersion")
    //mock lib
    testCompile("org.mockito:mockito-inline:2.24.5")
    // expect matchers
    testCompile("net.oddpoet:kotlin-expect:1.2.1")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
    testLogging {
        events("passed", "skipped", "failed")
    }
}


sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
}