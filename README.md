# mutools
Java framework build on top of JUnit used to test student submissions

Eclipse instructions:

Move mutools.jar into the Eclipse workspace folder
Create Eclipse Java project (without modules).
Add jar files to project and JUnit 5:
  + Right click on project name in Project Explorer
  + Select Build Path-Configure Build Path-Configure
  + Select the Libraries tab
  + Select Classpath
  + Click Add External JARs. Find mutools.jar click open
  + Click Add Library. Select JUnit 5.
Copy the Java files to the project's src folder
Run LinkedListMUTests.java as a Java Application

For other IDEs, follow a process similar to the Eclipse process.

Command line instructions:

Download JUnit jar file from https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone/1.10.2
Put mutools.jar, junit-platform-console-standalone-1.10.2.jar, and all java files into a directory.

$ javac -cp ".:mutools.jar:junit-platform-console-standalone-1.10.2.jar" *.java
$ java -cp ".:mutools.jar:junit-platform-console-standalone-1.10.2.jar" ListMUTests

Substitute ":" with ";" on Windows.
