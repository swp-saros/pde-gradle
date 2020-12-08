plugins {
    `java-gradle-plugin`
}

repositories {
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    compile("com.diffplug.gradle:goomph:3.24.0")
}

gradlePlugin {
    plugins {
        create("eclipsePlugin") {
            id = "saros.gradle.eclipse.plugin"
            implementationClass = "saros.gradle.eclipse.SarosEclipsePlugin"
        }
    }
}
