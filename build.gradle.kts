plugins {
    id("com.github.johnrengelman.shadow") version "5.2.0" apply false
}

subprojects {
    val projectToConf = this

    apply(plugin = "eclipse")
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    configurations {
        create("testing") // used to reference the testJar
        create("testConfig") // contains test dependencies that are used by all java subprojects
        create("releaseDep") { // contains all dependencies which has to be included into the release jar/zip
            isTransitive = false // avoid that the whole dependency tree is released
        }
    }

    tasks {

        // Otherwise our custom tags (as "@JTourBus") let the javadoc generation fail
        withType<Javadoc> {
            isFailOnError = false
        }

        /* generate lib directory that contains all release dependencies
         * This is necessary to enable eclipse to run the stf tests, because
         * eclipse uses the path of the MANIFEST.MF and is not compatible with
         * gradle dependency resolution
         */
        register("generateLib", Copy::class) {
            into("${project.projectDir}/lib")
            from(projectToConf.configurations.getByName("releaseDep"))
        }
    }

    /*
     * Define default eclipse version that is used for resolving
     * dependencies and building the update site.
     */
    projectToConf.extra["eclipseVersion"] = "4.8.0"

    /*
     * Set <code>./gradlew -PversionQualifier=<qualifier></code> to define
     * a version qualifier for CI build results.
     */
    val versionQualifier: String? by project

    projectToConf.extra["versionQualifier"] = if (versionQualifier.isNullOrBlank()) "" else ".$versionQualifier"
}

tasks {

    /* Internal Tasks (not intended to be called by users) */

    // generate all lib dirs in order to run stf tests
    register("generateLibAll") {
        dependsOn(
                "cleanGenerateLibAll",
                ":pde-example-master.LibBundle:generateLib",
                ":pde-example-master.ExampleCore:generateLib",
                ":pde-example-master.ExampleFrontend:generateLib")
    }

    register("cleanGenerateLibAll") {
        doLast {
            project(":pde-example-master.LibBundle").file("lib").deleteRecursively()
            project(":pde-example-master.ExampleFrontend").file("lib").deleteRecursively()
            project(":pde-example-master.ExampleCore").file("lib").deleteRecursively()
        }
    }

    /* External Tasks */

    // remove all build dirs. The frontend package has no build directory
    register("prepareEclipse") {
        dependsOn(
                subprojects.map { listOf(":${it.name}:cleanEclipseProject", ":${it.name}:cleanEclipseClasspath") }.flatten() +
                subprojects.map { listOf(":${it.name}:eclipseProject", ":${it.name}:eclipseClasspath") }.flatten()
        )

        group = "IDE"
        description = "Generates the 'libs' directories containing the " +
                "dependencies and generates the eclipse configurations for all projects"
    }

    register("cleanAll") {
        dependsOn(subprojects.map { ":${it.name}:clean" })

        group = "Build"
        description = "Utility task that calls 'clean' of all sub-projects"
    }

    register("sarosEclipse", Copy::class) {

        group = "Build"
        description = "Builds and tests all modules required by Saros for Eclipse"

		from(project(":LibBundle").tasks.findByName("jar"))
        from(project(":ExampleCore").tasks.findByName("jar"))
        into("build/distribution/eclipse")
    }
}

