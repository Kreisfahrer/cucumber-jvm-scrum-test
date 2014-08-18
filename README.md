Joint-Cucumber
====================
To run test you need:
1. Unarchive Joint.zip
2. Open windows command line go to folder with unzipped files.
3. Run command: "mvn clean compile test" (You need to install Maven and add it to path system variable).
4. Three tests were developed: DARWIN-8273, DARWIN-4865, DARWIN-4605
5. There are tests in a following file: ..\Joint_Cucumber\cucumber-jvm-scrum-test-master\src\test\resources\com\scrum\features\new.feature
    a) DARWIN-8273, 114 Subscribe to a Simple folder - Scenario: As Participant I want to subscribe to a folder
    b) DARWIN-4865, 112 Move files and folders to another destination using drag&drop - Scenario: As Participant I want to move files and folders to another destination using drag&drop
    c) DARWIN-4605, 110 Download multiple files - Scenario: As Observer I want to download multiple files
6. All steps (tests are making) are located in following file: ..\cucumber-jvm-scrum-test\src\test\java\com\scrum\behavior\MyStepdefs.java
7. File, which collects 'new.feature' and 'MyStepdefs.java' together and runs tests, is: ..\cucumber-jvm-scrum-test\src\test\java\com\scrum\MainTest.java
7. When all tests are passed, you can find reports in {project_root_folder}\target\reports\cucumber\html

Optionally you can open pom.xml in IntelliJ Idea and run tests from this IDE.