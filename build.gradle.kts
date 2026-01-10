plugins {
    id("com.hayden.spring-app")
    id("com.hayden.kotlin")
    id("com.github.node-gradle.node")
    id("com.hayden.mcp")
    id("com.hayden.paths")
}

group = "com.hayden"
version = "1.0.0"


tasks.register("prepareKotlinBuildScriptModel") {}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation(project(":utilitymodule"))
    implementation("com.agentclientprotocol:acp:0.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.9.0")
    implementation("com.ag-ui.community:kotlin-core-jvm:0.2.4")
    implementation("com.embabel.agent:embabel-agent-starter-openai:0.3.2-SNAPSHOT")
    implementation("org.springframework.ai:spring-ai-starter-mcp-server-webmvc")
    implementation("org.jspecify:jspecify:1.0.0")
    implementation(project(":commit-diff-context"))
    implementation(project(":commit-diff-model"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.bootJar {
    enabled = false
}