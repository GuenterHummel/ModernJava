plugins {
    application
}

application {
    mainClass.set("com.gh.playground.rxjava.RxJavaTest")
}


dependencies {
    implementation (libs.rxjava)
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