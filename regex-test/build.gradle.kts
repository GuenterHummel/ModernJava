plugins {
    application
}

application {
    mainClass.set("com.gh.playground.regex.PciTestAppender")
}

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
            "Implementation-Title" to "regex-test"
        )
    }
}