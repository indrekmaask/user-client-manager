import com.github.gradle.node.npm.task.NpxTask

plugins {
  id("com.github.node-gradle.node") version "3.0.0-rc2"
}

node {
  version.set("12.7.0")
  npmVersion.set("6.13.4")
  download.set(true)
}

tasks.register<NpxTask>("npmBuild") {
  command.set("ng")
  args.addAll(listOf("build"))
}

tasks.jar {
  enabled = true
  dependsOn("npmBuild")
  from("dist/ucm")
  into("static")
}
