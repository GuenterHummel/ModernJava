plugins {
    java
}

group = "org.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Specification-Title" to "Learning",
            "Implementation-Title" to "machine-learning",
        )
    }
}
