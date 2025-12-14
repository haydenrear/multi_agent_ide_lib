plugins {
    id("com.hayden.jpa-persistence")
    id("org.hibernate.orm") version "6.4.4.Final"
    id("com.hayden.mcp-server")
    id("com.hayden.log")
    id("com.hayden.spring-app")
}

group = "com.hayden"
version = "1.0.0"


tasks.register("prepareKotlinBuildScriptModel") {}

dependencies {
    project(":jpa-persistence")
}

tasks.bootJar {
    enabled = false
}