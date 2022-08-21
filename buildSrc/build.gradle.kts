repositories {
    mavenCentral()
    google()
    // Needed only for SNAPSHOT versions
    // maven { url "http://oss.sonatype.org/content/repositories/snapshots/" }
}

plugins {
//  `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("com.android.tools.build:gradle:7.2.2")
}