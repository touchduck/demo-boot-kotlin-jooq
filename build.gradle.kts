import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import com.rohanprabhu.gradle.plugins.kdjooq.*

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    id("org.springframework.experimental.aot") version "0.11.2"
    id("org.flywaydb.flyway") version "8.4.4"
    id("com.rohanprabhu.kotlin-dsl-jooq") version "0.4.6"  // need jooqGen
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    mavenCentral()
}

extra["springCloudGcpVersion"] = "3.1.0"
extra["springCloudVersion"] = "2021.0.0"

dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-rest")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//    implementation("org.springframework.boot:spring-boot-starter-quartz")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("com.google.cloud:spring-cloud-gcp-starter-storage")
//    implementation("org.springframework.data:spring-data-rest-hal-explorer")
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")

    // https://github.com/googleapis/google-cloud-java
    // implementation("com.google.cloud:google-cloud-storage:1.113.9")

    // https://github.com/aws/aws-sdk-java-v2
    // implementation("software.amazon.awssdk:s3:2.15.81")

    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.boot:spring-boot-starter-jooq") // need jooqGen
    jooqGeneratorRuntime("org.postgresql:postgresql:42.2.19")  // need jooqGen

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.valiktor:valiktor-spring-boot-starter:0.12.0")

    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
    imports {
        mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/hawaii"
    schemas = arrayOf("public")
    user = "rockman"
    password = "mokuren"
    baselineOnMigrate = true
}

jooqGenerator {
    configuration("hawaii", project.the<SourceSetContainer>()["main"]) {
        configuration = jooqCodegenConfiguration {
            jdbc {
                username = "rockman"
                password = "mokuren"
                driver = "org.postgresql.Driver"
                url = "jdbc:postgresql://localhost:5432/hawaii"
            }

            generator {
                generate {
                    isJavaTimeTypes = true
                    isPojos = true
                    isDaos = true
                }
                target {
                    packageName = "com.example.demo.infra.hawaii"
                    directory = "${project.rootDir}/src/main/java"
                }

                database {
                    name = "org.jooq.meta.postgres.PostgresDatabase"
                    inputSchema = "public"
                }
            }
        }
    }
}

