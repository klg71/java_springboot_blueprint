plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
    id("net.mayope.deployplugin") version "0.0.50"
}

group = "de.osp"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

deploy {
    var username = "mayope"
    var password = ""
    var dockerRegistry = "registry-8b60bc8e-3ca3-44f7-af77-9982fb7cc09a.dyn.mayope.net"
    serviceName = "todolist"
    default {
        dockerBuild()
        dockerLogin {
            registryRoot = dockerRegistry
            loginMethod = net.mayope.deployplugin.tasks.DockerLoginMethod.CLASSIC
            loginUsername = username
            loginPassword = password
        }
        dockerPush {
            registryRoot = dockerRegistry
            loginMethod = net.mayope.deployplugin.tasks.DockerLoginMethod.CLASSIC
            loginUsername = username
            loginPassword = password
        }
        deploy {
            kubeConfig = System.getProperty("user.home") + "/.kube/config-todo"
            targetNamespaces = listOf("todo-8c1238d5-48f9-4667-a4b5-7a4a62ef211b")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")


    // OpenApi/Swagger documentation
    implementation("org.springdoc:springdoc-openapi-ui:1.5.13")

    testImplementation("io.rest-assured:rest-assured:4.2.1")
    testImplementation("io.rest-assured:json-path:4.2.1")
    testImplementation("io.rest-assured:xml-path:4.2.1")
    testImplementation("org.assertj:assertj-core:3.15.0")

    runtimeOnly("com.h2database:h2")
}
tasks {

    test {
        useJUnitPlatform()
    }
}
