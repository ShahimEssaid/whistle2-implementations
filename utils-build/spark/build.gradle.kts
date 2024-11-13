plugins {
    `java-library`
    `maven-publish`
    idea
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Lib")
                description.set("Lib description.")
                developers {
                    developer {
                        name.set("Shahim Essaid")
                        email.set("shahim@essaid.com")
                    }
                }
            }
        }
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    implementation(libs.google.whistle.runtime)
    implementation(libs.spark.sql)
    implementation(project(":common"))
    implementation(libs.picocli.picocli)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withSourcesJar()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs = listOf("--add-exports",  "java.base/sun.nio.ch=ALL-UNNAMED")
}

idea(){

}