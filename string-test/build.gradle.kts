plugins {
    application
}

application {
    mainClass.set("com.gh.playground.string.TestString")
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
            "Implementation-Title" to "string-test"
        )
    }
}