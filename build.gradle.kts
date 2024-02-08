import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.google.protobuf") version "0.9.2"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group= "t"
version= "0.0.1-SNAPSHOT"
java.sourceCompatibility= JavaVersion.VERSION_11

ext["grpcKotlin"] = "1.3.0"
ext["grpcVersion"] = "1.53.0"
ext["protobufVersion"] = "3.22.0"

repositories{
    mavenCentral()
}

springBoot{
    mainClass.set("t.DogsTestApp")
}

protobuf{
    protoc{
        artifact = "com.google.protobuf:protoc:${rootProject.ext["protobufVersion"]}"
    }
    plugins{
        id("grpc"){
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.ext["grpcVersion"]}"
        }
        id("grpckt"){
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${rootProject.ext["grpcKotlin"]}:jdk8@jar"
        }
    }
    generateProtoTasks{
        all().forEach{
            it.plugins{
                id("grpc")
                id("grpckt")
            }
            it.builtins{
                id("kotlin")
            }
        }
    }
}

dependencies{
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("net.devh:grpc-client-spring-boot-starter:2.14.0.RELEASE")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.grpc:grpc-kotlin-stub:${rootProject.ext["grpcKotlin"]}")
    implementation("io.grpc:grpc-protobuf:${rootProject.ext["grpcVersion"]}")
    implementation("com.google.protobuf:protobuf-kotlin:${rootProject.ext["protobufVersion"]}")
    implementation("com.google.protobuf:protobuf-java:${rootProject.ext["protobufVersion"]}")
    runtimeOnly("io.grpc:grpc-netty-shaded:${rootProject.ext["grpcVersion"]}")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile>{
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test>{
    useJUnitPlatform()
}