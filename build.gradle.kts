plugins {
  java
  idea
  id("io.freefair.lombok") version "5.1.0"
  id("org.springframework.boot") version "2.3.0.RELEASE"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

allprojects {
  apply(plugin = "java")
  apply(plugin = "io.freefair.lombok")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
  }

  repositories {
    mavenCentral()
  }

  dependencies {
    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.data:spring-data-commons")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
  }

  tasks.test {
    useJUnitPlatform()
  }

  tasks.generateLombokConfig {
    enabled = false
  }
}

subprojects {
  tasks.bootJar {
    enabled = false
  }
}

dependencies {
  implementation(project(":app-main"))
  implementation(project(":app-domain"))
  implementation(project(":adapter:jdbc"))
  implementation(project(":adapter:web"))
}

springBoot {
  mainClassName = "com.github.ucm.UserClientManager"
}
