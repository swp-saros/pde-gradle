plugins {
    id("saros.gradle.eclipse.plugin")
}

val versionQualifier = ext.get("versionQualifier") as String
val eclipseVersionNr = ext.get("eclipseVersion") as String

configurations {
    // Defined in root build.gradle
    val testConfig by getting {}
    val releaseDep by getting {}

    // Default configuration
    val compile by getting {
        extendsFrom(releaseDep)
    }
    val testCompile by getting {
        extendsFrom(testConfig)
    }
    val plain by creating {
        extendsFrom(compile)
    }
}

sarosEclipse {
    manifest = file("META-INF/MANIFEST.MF")
    excludeManifestDependencies = listOf("libbundle", "org.eclipse.gef")
    isCreateBundleJar = true
    isAddPdeNature = true
    isAddDependencies = true
    pluginVersionQualifier = versionQualifier
    eclipseVersion = eclipseVersionNr
}

dependencies {
	compile(project(":LibBundle"))
    
	//implementation(project(":LibBundle"))
	
	releaseDep(fileTree("lib"))
}

sourceSets {
    main {
        java.srcDirs("src", "patches")
        resources.srcDirs("resources")
    }
}

tasks {

    // Jar containing only the core code (the default jar is an osgi bundle
    // containing a lib dir with all dependency jars)
    val plainJar by registering(Jar::class) {
        manifest {
            from("META-INF/MANIFEST.MF")
        }
        from(sourceSets["main"].output)
        classifier = "plain"
    }

    artifacts {
        add("plain", plainJar)
    }
}
