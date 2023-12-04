import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
}

allprojects {

    group = "dduddoo"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "21"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}


subprojects {
    apply(plugin = "kotlin")

    dependencies {
        testImplementation(kotlin("test"))
    }

    kotlin {
        jvmToolchain(21)
    }
}
