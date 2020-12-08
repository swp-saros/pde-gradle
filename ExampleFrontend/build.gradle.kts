plugins {
    id("saros.gradle.eclipse.plugin")
}

val versionQualifier = ext.get("versionQualifier") as String
val eclipseVersionNr = ext.get("eclipseVersion") as String

configurations {
    val testConfig by getting {}
    getByName("testImplementation") {
        extendsFrom(testConfig)
    }
}

sarosEclipse {
    manifest = file("META-INF/MANIFEST.MF")
    excludeManifestDependencies = listOf("example.core", "org.eclipse.gef")
    isAddPdeNature = true
    isCreateBundleJar = true
    isAddDependencies = true
    pluginVersionQualifier = versionQualifier
    eclipseVersion = eclipseVersionNr
}

sourceSets {
    main {
        java.srcDirs("src", "ext-src")
        resources.srcDirs("src")
        resources.exclude("**/*.java")
    }
}

dependencies {
    implementation(project(":ExampleCore"))
    // This is a workaround for https://github.com/saros-project/saros/issues/1086
    implementation("org.eclipse.platform:org.eclipse.urischeme:1.1.0")
}

tasks {

    jar {
        into("assets") {
            from("assets")
        }
        into("icons") {
            from("icons")
        }
        from(".") {
            include("*.properties")
            include("readme.html")
            include("plugin.xml")
            include("LICENSE")
            include("CHANGELOG")
        }
    }
}
