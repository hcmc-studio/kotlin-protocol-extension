plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("maven-publish")
}

group = "studio.hcmc"
version = "0.0.7"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "studio.hcmc"
            artifactId = "kotlin-protocol-extension"
            version = "0.0.7"
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.5.1")
}