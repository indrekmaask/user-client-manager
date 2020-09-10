dependencies {
  implementation(project(":app-domain"))

  implementation("com.h2database:h2")
  implementation("org.liquibase:liquibase-core")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
}

tasks.jar {
  archiveFileName.set("ucm-jdbc.jar")
}
