plugins {
    id("java")
}

group = "yingyongwatthanakit.metee"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-swing-junit5:3.17.1")
}

tasks.test {
    useJUnitPlatform()
}