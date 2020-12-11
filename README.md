# Saros build system analogue

This is an example Eclipse plugin that uses a build system that should be as close to the build system used by the [Saros](https://github.com/saros-project/saros/) project as possible to draw conclusions about possible changes to make the core-bundle indipendent from the eclipse bundle/subproject.

## Build instructions
Using the exact same commands like in the Saros project just omitting tasks concerning Intellij, etc.
```
./gradlew cleanAll sarosEclipse
```

## Troubleshooting

**Error**:
```shell
$ ./gradlew cleanAll sarosEclipse dropin
[...]
> Task :ExampleFrontend:updateSiteFeaturesAndBundlesPublishing FAILED
java.lang.NoClassDefFoundError: org/gradle/api/file/FileCollection
        at java.lang.Class.getDeclaredMethods0(Native Method)
        at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
        at java.lang.Class.privateGetMethodRecursive(Class.java:3048)
        at java.lang.Class.getMethod0(Class.java:3018)
        at java.lang.Class.getMethod(Class.java:1784)
        at sun.launcher.LauncherHelper.validateMainClass(LauncherHelper.java:650)
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:632)
Caused by: java.lang.ClassNotFoundException: org.gradle.api.file.FileCollection
        at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
        ... 7 more
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" Installing p2 bootstrap 4.7.2... Success.
FAILURE: Build failed with an exception.
```

**Problem**: Most probably using the wrong `gradlew` version.

**Solution**:

Make sure that you started the Gradle-wrapper (`gradlew`) and **not** `gradle` (the system-version, if installed).
