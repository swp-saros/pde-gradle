# Saros build system analogue

This is an example Eclipse plugin that uses a build system that should be as close to the build system used by the [Saros](https://github.com/saros-project/saros/) project as possible to draw conclusions about possible changes to make the core-bundle indipendent from the eclipse bundle/subproject.

## Build instructions
Using the exact same commands like in the Saros project just omitting tasks concerning Intellij, etc.
```
./gradlew cleanAll sarosEclipse
```
