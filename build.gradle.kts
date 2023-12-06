plugins {
    alias(libs.plugins.jvm)
}

group = "be.moac"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit5.params)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}