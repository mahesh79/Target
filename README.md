# Target-casestudy
myRetail Restful API

## Technical Stack

1. Ratpack-Java for request handling
2. MongoDB on Java Driver for NoSQL
3. Spock and Junit for unit, functional and integration testing
4. Java httpURLconnector for making external httpclient
5. Gradle for build
6. IntelliJ IDE
7. log4j for logging

## Application Setup Guidance:

1. Install MongoDB for Java using homebrew/sdkman/ any package management tool
2. Start local MongoDB by giving command ```sudo mongod```
3. Ensure the local mongoDB instance starts on default port 27017
4. Pull this repo https://github.com/mahesh79/Target.git to your local box
5. Create a fat jar ```./gradlew clean fatJar```
6. Run the jar file ```java -jar myretail-all.jar``` from the library folder under build
   (You may have to be in TMHS to download few libraries if inside Target network)
7. Run task ```./gradlew allTests``` to execute unit, functional and Integration testcases (Please ensure mongoDB and ratpack client both are running before this)
8. All test reports will be under ```build/reports``` under corresponding test
   types
9. The local host API can be accessed under: ```http://localhost:5050/myretail_products/v1/13860428```
