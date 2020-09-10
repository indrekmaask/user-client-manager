dependencies {
  implementation(project(":app-domain"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("io.jsonwebtoken:jjwt-api:0.10.6")
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.6")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.6")
}

tasks.jar {
  archiveFileName.set("ucm-web.jar")
}
