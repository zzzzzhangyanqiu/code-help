plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.22"
  id("org.jetbrains.intellij") version "1.17.2"
}

group = "com.zhangyq"
version = "1.1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
  implementation("org.junit.jupiter:junit-jupiter-engine:5.6.0")
  implementation("commons-io:commons-io:2.5")
  implementation("commons-io:commons-io:2.5")
  implementation("com.alibaba:fastjson:1.2.62")
  implementation("com.github.javafaker:javafaker:1.0.2")
  implementation("org.jeasy:easy-random-core:4.3.0")
  implementation("org.freemarker:freemarker:2.3.32")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.2")
  pluginName.set("Code Helper")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf("com.intellij.java"))
  updateSinceUntilBuild.set(false)
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("222")
    untilBuild.set("242.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
