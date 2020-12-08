rootProject.name = "pde-example-master"

plugins {
    id("com.gradle.enterprise") version "3.3.3"
}

listOf("ExampleCore", "ExampleFrontend").forEach { dir ->
    include(dir)
    project(":$dir").projectDir = file(dir)
}
