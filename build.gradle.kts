val kotlin_version: String by project
val project_version: String by project
val jdk_version: String by project
val kotlinx_datetime_version: String by project
val kotlinx_serialization_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "studio.hcmc"
version = project_version

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

kotlin {
    jvmToolchain(jdk_version.toInt())
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "studio.hcmc"
            artifactId = project.name
            version = project_version
            from(components["java"])
        }
        create<MavenPublication>("jitpack") {
            groupId = "com.github.hcmc-studio"
            artifactId = project.name
            version = project_version
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$kotlinx_datetime_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:$kotlinx_serialization_version")
}