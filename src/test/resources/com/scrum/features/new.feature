
  @done @admin @users
  Feature: Users Management on Admin Panel

    Scenario: As Participant I want to subscribe to a folder
      Given   User logs in with user.participant1
      When    User goes to room Created for testing
      And     User creates folder of name TestQA and type Simple folder
      Then    Folder TestQA should appear
      And     Subscribe link should be present
      When    User subscribes
      Then    Number of subscribers should be 1
      And     Stop Subscribing link should be present
      When    User creates folder of name TestQA2 and type Simple folder
      Then    User user.participant1 gets email with subject Information created in Created for testing by Andy Test: TestQA
      When    Another user user.participant2 logs in
      And     User goes to room Created for testing
      And     User goes to folder TestQA
      And     User subscribes
      Then    Number of subscribers should be 2
      When    Another user user.participant1 logs in
      And     User goes to room Created for testing
      And     User goes to folder TestQA
      Then    User can see subscribers Andy Test, Andy Grr.la
      When    User unsubscribes
      Then    Number of subscribers should be 1
      When    User renames folder TestQA to folder TestQA3
      Then    Folder TestQA3 should appear
      And     User user.participant2 gets email with subject Information updated in Created for testing by Andy Test TestQA3
      And     User user.participant1 does not get email with subject Information updated in Created for testing by Andy Test: TestQA3

    Scenario: As Participant I want to move files and folders to another destination using drag&drop
      Given User logs in with user.participant1
      When User goes to room Created for testing
      Then Folders First, Second, ProtectedFolder should appear
      When User goes by tree-view to First folder
      Then User see FirstTestDocument, SecondTestDocument documents
      Then User drag FirstTestDocument file to Second folder
      And User see FirstTestDocument document in current folder
      When User drag First folder to Second folder
      And User goes by tree-view to First folder
      Then Path contains Second, First folders
      And User drag First folder to Created for testing folder
      Then User goes by tree-view to Second folder
      And User drag FirstTestDocument file to First folder
      When User drag ProtectedFolder folder to First folder
      And User goes by tree-view to First folder
      Then User can not see Folder folder in selected folder
      Then User select all content in current folder
      And Drag files to Second folder
      And User see FirstTestDocument, SecondTestDocument documents
      Then User select all content in current folder
      And Drag files to First folder
      When User goes by tree-view to ProtectedFolder folder
      Then User select all content in current folder
      And Drag files to First folder
      And User goes by tree-view to First folder
      Then User can not see any, some folders in selected folder

    Scenario: As Observer I want to download multiple files
      Given User logs in with user.observer
      Then User goes to room Created for testing
      When User goes by tree-view to TestArchive folder
      And User select all content in current folder
      And Click download button
      Then Download window appears and archive is creating
      When User click open button and archive saves
      Then User get content of archive
      And Content of TestArchive folder
      Then User compare these content




