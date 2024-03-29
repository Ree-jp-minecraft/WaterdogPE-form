plugins {
    id("maven-publish")
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.ree_jp"
version = "2.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(
        url = uri("https://repo.waterdog.dev/artifactory/main")
    )
    maven(
        url = uri("https://jitpack.io")
    )
}

dependencies {
//    compileOnly("dev.waterdog.waterdogpe:waterdog:1.2.*")
    compileOnly("com.github.WaterdogPE:WaterdogPE:waterdog-2-SNAPSHOT")
    implementation(kotlin("stdlib"))
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ree-jp-minecraft/waterdogpe-form")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
