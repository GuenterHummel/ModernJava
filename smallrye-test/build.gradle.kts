plugins {
    application
}

application {
    mainClass.set("com.gh.playground.smallrye.mutiny.SmallryeMutinyTest")
}


dependencies {
    implementation (libs.smallrye.mutiny)
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
            "Implementation-Title" to "rxjava-test"
        )
    }
}