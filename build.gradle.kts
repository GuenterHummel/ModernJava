import java.text.SimpleDateFormat
import org.gradle.internal.jvm.Jvm

def current_version = version

ext {
    log4j2_version = "2.25.0"
 	junit_version = '5.13.1'
 	guava_version = '33.4.8-jre'
 	bouncy_castle_version = '1.81'
}

buildscript {
    dependencies {
        classpath (libs.kotlinx.serialization.json)
    }
}

plugins {
    // Language plugins
    java
    alias(libs.plugins.kotlin.serialization)
    // IDE plugins
    eclipse
    idea
    // Check plugins
    jacoco
}

allprojects {
    apply (plugin = "jacoco")
    apply (plugin = "eclipse")
}

fun buildTime() : String {
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    df.setTimeZone(TimeZone.getTimeZone("UTC"))
    return df.format(Date())
}

// def currentBuildJvm = Jvm.current()
//def currentBuildTime = buildTime()

tasks.wrapper {
    gradleVersion = gradle.gradleVersion
}

subprojects {
    apply (plugin = "java")
    apply (plugin = "idea")

	repositories {
        mavenCentral()
    }

	dependencies {
        testImplementation "org.junit.jupiter:junit-jupiter:${junit_version}"
        testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
        implementation "org.apache.logging.log4j:log4j-api:${log4j2_version}"
        implementation "org.apache.logging.log4j:log4j-core:${log4j2_version}"
        implementation "com.google.guava:guava:${guava_version}"
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
        withSourcesJar()
    }

    tasks.withType<Jar> { // Betrifft auch War-Tasks, weil die von Jar abgeleitet sind
        val projectVersion = project.version
        val projectName = project.name
        manifest {
            attributes(mapOf("Built-By" to System.getenv("USERNAME"),
					   "Built-With" : "Java ${Jvm.current()}",
					   "Gradle-Version" to gradle.gradleVersion",
				       "Specification-Title" to project.name.toUpperCase(),
				       "Specification-Vendor" to vendor,
				       "Specification-Version" to archiveVersion,
				       "Implementation-Title" to project.name,
				       "Implementation-Vendor" to vendor,
				       "Implementation-Version" to archiveVersion
        }
    }
	
	javadoc {
        val tagList = listOf("remarks:a:Remarks:",
            "example:a:Example Code:",
            "format:a:Format:",
            "guidance:a:Recommended Reaction:")

        options {
            this as StandardJavadocDocletOptions

            addBooleanOption("Werror", true)
            // Enable all Javadoc warnings, except for missing Javadoc comments (unnecessary for non-API classes, private fields, etc.)
            addBooleanOption("Xdoclint:all,-missing", true)

            encoding = "UTF-8"
            memberLevel = JavadocMemberLevel.PRIVATE
            setAuthor(true)
            windowTitle = "${project.name}, ${version} - Java Documentation"
            header = "${project.name} ${version}"
            //options.overview="src/main/java/overview.html"
            setTags(tagList)
        }
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    tasks.jacocoTestReport {
        reports {
            html.required = true
            html.outputLocation = project.layout.buildDirectory.dir("reports/jacoco/html")
            xml.required = false
            csv.required = false
        }
    }

    tasks.test {

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
        }

        extensions.configure(JacocoTaskExtension::class) {
            destinationFile = project.layout.buildDirectory.file("reports/jacoco/test.exec").get().asFile
        }

    }

    tasks.register<Jar>("sourcesJar") {
        dependsOn (tasks.classes)
        archiveClassifier = "sources"
        from (sourceSets.main)
    }

    tasks.register<Jar>("testSourcesJar") {
        dependsOn (tasks.classes)
        archiveClassifier = "testsources"
        from (sourceSets.test)
    }

    tasks.register<Jar>("javadocJar") {
        dependsOn (tasks.javadoc)
        archiveClassifier = "javadoc"
        from ((tasks.get("javadoc") as Javadoc).destinationDir)
    }

	group = 'at.co.sbs.gh.learning'
}

task createDistDirectory() {
	def distDir = file("${rootProject.projectDir}/distrib")
	distDir.mkdirs()
}

task jacocoRootReport(type: JacocoReport) {
    dependsOn = subprojects.test
    getExecutionData().from(files(subprojects.jacocoTestReport.executionData))
    getSourceDirectories().from(files(subprojects.sourceSets.main.allSource.srcDirs))
    getClassDirectories().from(files(subprojects.sourceSets.main.output))
    reports {
    	html.outputLocation = file("jacoco")
        html.required = true
        xml.required = true
        csv.required = false
    }
    onlyIf = {
        true
    }
    doFirst {
        //noinspection GroovyAccessibility
        executionData = files(executionData.findAll {
            it.exists()
        })
    }    
}

defaultTasks("wrapper", "clean", "build")
