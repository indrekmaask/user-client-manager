dependencies {
  implementation(project(":app-domain"))
  implementation(project(":adapter:jdbc"))
  implementation(project(":adapter:web"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  compileOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.jar {
  archiveFileName.set("ucm-main.jar")
}
