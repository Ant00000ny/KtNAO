plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.isekaiofficial"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
