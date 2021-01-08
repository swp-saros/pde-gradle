rootProject.name = "pde-example-master"

plugins {
    id("com.gradle.enterprise") version "3.3.3"
}

listOf("LibBundle", "ExampleCore").forEach { dir ->
    include(dir)
    project(":$dir").projectDir = file(dir)
}
