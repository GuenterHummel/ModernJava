import com.github.spotbugs.snom.SpotBugsTask
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.internal.jvm.Jvm
import org.gradle.kotlin.dsl.withType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

// Root Project Properties
val vendor : String by project
val group = "at.co.sbs.gh.learning"

plugins {
    java
    `java-test-fixtures`
    `maven-publish`
    jacoco
    eclipse
    idea
    pmd
    alias(libs.plugins.spotbugs)
}

fun buildTime() : String {
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm")
    df.setTimeZone(TimeZone.getTimeZone("UTC"))
    return df.format(Date())
}

val current_version = version
val currentBuildJvm = Jvm.current()
val java_runtime_version=JavaVersion.VERSION_25

tasks.wrapper {
    gradleVersion = gradle.gradleVersion
}

allprojects {
    // Gemeinsame Konfiguration für alle Projekte
}

subprojects {
    apply (plugin = "idea")
    apply (plugin = "jacoco")
    apply (plugin = "java")
    apply (plugin = "java-test-fixtures")
    apply (plugin = "pmd")

    apply (plugin = rootProject.libs.plugins.spotbugs.get().pluginId)

    repositories {
        mavenCentral()
    }

    dependencies {
        spotbugs (rootProject.libs.spotbugs)

        testFixturesImplementation(rootProject.libs.junit.jupiter)
        testFixturesImplementation(rootProject.libs.mockito.junit.jupiter)
        testFixturesImplementation(rootProject.libs.mockito.core)
        testRuntimeOnly(rootProject.libs.junit.platform.launcher)

        compileOnly(rootProject.libs.spotbugs.annotations)
        testCompileOnly(rootProject.libs.spotbugs.annotations)
        testFixturesCompileOnly(rootProject.libs.spotbugs.annotations)
        implementation(rootProject.libs.log4j.api)
        implementation(rootProject.libs.log4j.core)
    }
    configurations {
        testImplementation {
            extendsFrom(testFixturesImplementation.get())
        }
    }

    tasks.jar {
        manifest {
            attributes(mapOf("Built-By" to System.getenv("USERNAME"),
                "Built-With" to "Java $currentBuildJvm",
                "Built-For" to "Java $java_runtime_version",
                "Gradle-Version" to "${gradle.gradleVersion}",
                "Specification-Title" to "${project.name.uppercase()}",
                "Specification-Vendor" to "${vendor}",
                "Specification-Version" to "${current_version}",
                "Implementation-Title" to	"${project.name}",
                "Implementation-Vendor" to "${vendor}",
                "Implementation-Version" to "${current_version}"
            ))
        }
    }

    tasks.javadoc {
        val tagList = listOf("remarks:a:Remarks:",
            "example:a:Example Code:",
            "format:a:Format:",
            "guidance:a:Recommended Reaction:")

        // Fail on Javadoc errors and warnings
        setFailOnError(true)
        options {
            this as StandardJavadocDocletOptions
            addBooleanOption("Werror", true)
            // Enable all Javadoc warnings, except for missing Javadoc comments (unnecessary for non-API classes, private fields, etc.)
            addBooleanOption("Xdoclint:all,-missing", true)

            title="${project.name}, ${version} <br>API Specification"
            memberLevel = JavadocMemberLevel.PRIVATE
            setAuthor(true)
            windowTitle = "${project.name}, ${version} - Java Documentation"
            header = "${project.name} ${version}"
            overview="src/main/java/overview.html"
            setTags(tagList)
            // stylesheetFile = File("${rootProject.projectDir}/util", "stylesheet.css")
            // bottom="Copyright &#169; 2017-2022 <a href=\"https://www.salzburgerbankensoftware.com}\" target=\"_blank\">SBS Software Ges.m.b.H.</a><br><font size=\"-1\">Creation Date: ${buildTime()}</font></br>"
        }
    }

    spotbugs {

        showProgress = false
        ignoreFailures = false
        reportsDir = layout.buildDirectory.file("/reports/spotbugs").get().asFile
        showStackTraces = false
        excludeFilter = file("../util/spotbugs-exclude.xml")
    }

    tasks.withType<SpotBugsTask>().configureEach {
        reports.create("html").required.set(true)
    }

    pmd {
        toolVersion = rootProject.libs.versions.pmd.get()
        setConsoleOutput(true)
        setIgnoreFailures(false)
        ruleSets = listOf()
        ruleSetFiles = files("../util/pmd-ruleset.xml")
        reportsDir = file("build/reports/pmd/")
    }

    tasks.test {
        useJUnitPlatform()

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
        }


        extensions.configure(JacocoTaskExtension::class) {
            destinationFile = layout.buildDirectory.file("/reports/jacoco/test.exec").get().asFile

            reports {
                html.required = true
                html.outputLocation = layout.buildDirectory.file("/reports/jacoco/html").get().asFile
            }
        }
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }
}


defaultTasks("clean", "build", "javadoc")