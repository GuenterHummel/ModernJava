plugins {
    java
}

group = "com.gh.playground"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform("org.junit:junit-bom:5.12.0")
    testImplementation "org.junit.jupiter:junit-jupiter"
}

tasks.test {
    useJUnitPlatform()
}