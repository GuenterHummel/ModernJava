plugins {
    application
}

application {
    mainClass.set("com.gh.playground.LicenseTest")
}

dependencies {
    implementation (libs.bouncycastle.prov)
    implementation(libs.guava)
}

tasks.test {
    ignoreFailures = true
    maxParallelForks = 1
}

defaultTasks("clean", "build", "javadoc")

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Specification-Title" to "Learning",
            "Implementation-Title" to "license-test"
        )
    }
}