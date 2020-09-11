plugins {
  id("java-library")
  id("org.springframework.boot") version "2.3.0.RELEASE"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

allprojects {
  apply(plugin = "java-library")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
  }

  repositories {
    mavenCentral()
  }
}

subprojects {
  tasks.jar {
    enabled = true
  }

  tasks.bootJar {
    enabled = false
  }
}

dependencies {
  implementation(project(":api"))
  implementation(project(":front-end"))
}

springBoot {
  mainClassName = "com.github.ucm.UserClientManagerApplication"
}
