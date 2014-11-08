Nailed API
=============
The API for Nailed, the pvp game server framework. It is licensed under the [MIT License]. 

* [Homepage]
* [Source]
* [Issues]
* [Nailed API Wiki]
* [Nailed Wiki]

## Prerequisites
* [Java] 6

## Clone
The following steps will ensure your project is cloned properly.  
  1. `git clone git@github.com:nailed/nailed.git`  
  2. `cd nailed`  

## Setup
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows systems instead of any 'gradle' command.

__For [IntelliJ]__  
  2. Run `gradle idea`  
  3. Open the generated nailed.ipr file in IntelliJ  
  4. When IntelliJ asks you to import the gradle project, click on `Import gradle project`
  
__For [Eclipse]__  
* Currently we do not support Eclipse. [IntelliJ] is our only supported IDE

## Building
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows systems instead of any 'gradle' command.

In order to build Nailed API you simply need to run the `gradle build` command. You can find the compiled JAR files in `./build/libs` labeled similarly to 'nailed-api-x.x.x-SNAPSHOT.jar'.

## Contributing
Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines](CONTRIBUTING.md).
* Please follow the above guidelines for your pull request(s) to be accepted.

[Eclipse]: http://www.eclipse.org/
[Gradle]: http://www.gradle.org/
[Homepage]: http://nailed.jk-5.tk/
[IntelliJ]: http://www.jetbrains.com/idea/
[Issues]: https://github.com/nailed/nailed-api/issues
[Nailed API Wiki]: https://github.com/nailed/nailed-api/wiki/
[Nailed Wiki]: https://github.com/nailed/nailed/wiki/
[Java]: http://java.oracle.com/
[Source]: https://github.com/nailed/nailed-api/
[MIT License]: http://www.tldrlegal.com/license/mit-license
