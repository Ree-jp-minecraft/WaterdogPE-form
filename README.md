# WaterdogPE-form

## Example

https://github.com/Ree-jp-minecraft/WaterdogPE-form-example

## GitHubPackages

### How to install

#### build.gradle.kts

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ree-jp-minecraft/waterdogpe-form")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    compileOnly("net.ree_jp:waterdog-form-api: <version> -SNAPSHOT")
}
```

#### gradle.properties

```
gpr.user=your github name
gpr.key=github access token
```
