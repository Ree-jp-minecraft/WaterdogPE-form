plugins {
    kotlin("jvm") version "1.6.10"
}

group = "net.ree_jp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(
        url = uri("https://repo.waterdog.dev/artifactory/main")
    )
}

dependencies {
    compileOnly("dev.waterdog.waterdogpe:waterdog:1.1.5")
    implementation(kotlin("stdlib"))
}
