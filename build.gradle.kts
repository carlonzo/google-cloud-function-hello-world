val invoker by configurations.creating

plugins {
  kotlin("jvm") version "1.6.10"
  id("com.github.johnrengelman.shadow") version "7.1.2"
  application
}

group = "com.carlonzo"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  compileOnly("com.google.cloud.functions:functions-framework-api:1.0.4")
  invoker("com.google.cloud.functions.invoker:java-function-invoker:1.1.0")
}

val appClassName = "com.carlonzo.Application"

application {
  mainClass.set("${appClassName}Kt")
}

task<JavaExec>("runFunction") {
  mainClass.set("com.google.cloud.functions.invoker.runner.Invoker")

  classpath(invoker)
  inputs.files(configurations.runtimeClasspath, sourceSets["main"].output)
  args(
    "--target", project.findProperty("runFunction.target") ?: appClassName,
    "--port", project.findProperty("runFunction.port") ?: 8080
  )
  doFirst {
    args("--classpath", files(configurations.runtimeClasspath, sourceSets["main"].output).asPath)
  }
}

tasks.named("build") {
  dependsOn(":shadowJar")
}

task("buildFunction") {
  dependsOn("build")
  copy {
    from("build/libs/${rootProject.name}-${rootProject.version}-all.jar")
    into("build/deploy")
  }
}