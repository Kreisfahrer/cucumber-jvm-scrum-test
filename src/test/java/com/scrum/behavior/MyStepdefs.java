package com.scrum.behavior;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Actions;
import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.RoomPage;

import java.io.IOException;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by andreystakhievich on 8/18/14.
 */
public class MyStepdefs {
    private static final String JOINT_NO = "https://ph-stage.joint.no/prosjekthotell/index.html#/joint.no/overview";

    @Given("^User logs in with (.*)$")
    public void User_logs_in(String user) {
        LoginPage.login(user);
        HomePage.shouldAppear();
    }

    @When("^User goes to room (.*)$")
    public void User_goes_to_room(String roomName) {
        HomePage.openRoom(roomName);
        RoomPage.shouldAppear();
    }

    @And("^User creates folder of name (.*) and type (.*)$")
    public void User_creates_folder(String folderName, String folderType) {
        RoomPage.createFolder(folderName, folderType);
    }

    @Then("^Folder (.*) should appear$")
    public void Folder_should_appear(String folderName) {
        RoomPage.getFolder(folderName).should(appear);
    }

    @Then("^Folders (.*), (.*), (.*) should appear$")
    public void Folders_should_appear(String first, String second, String third) {
        RoomPage.getFolder(first).should(appear);
        RoomPage.getFolder(second).should(appear);
        RoomPage.getFolder(third).should(appear);
    }

    @When("^User goes by tree-view to (.*) folder$")
    public void User_goes_by_tree_view_to_folder(String folderName) {
        RoomPage.getFolder(folderName).click();
    }

    @Then("^User see (.*), (.*) documents")
    public void User_see_documents(String first, String second) {
        RoomPage.checkExpectedDocuments(Arrays.asList(first, second));
    }

    @And("^User see (.*) document in current folder$")
    public void User_see_document(String fileName) {
        RoomPage.checkExpectedDocument(fileName, exist);
    }

    @Then("^User drag (.*) file to (.*) folder$")
    public void User_drag_file_to_folder(String fileName, String toFolder) {
        RoomPage.dragFilesTo(fileName, toFolder);
    }

    @When("^User drag (.*) folder to (.*) folder$")
    public void User_drag_folder_to_another_folder(String folderToDrag, String targetFolder) {
        RoomPage.dragFolderTo(folderToDrag, targetFolder);
    }

    @Then("^Path contains (.*), (.*) folders$")
    public void Path_contains_folders(String first, String second) {
        RoomPage.checkPath(Arrays.asList(first, second));
    }

    @And("^Subscribe link should be present$")
    public void Subscribe_link_should_be_present() {
        RoomPage.getLabelOfSubscribers().shouldHave(text("Subscribe"));
    }

    @When("^User subscribes$")
    public void User_subscribes(){
        RoomPage.clickSubscribe();
    }

    @Then("^Number of subscribers should be (.*)$")
    public void Number_of_subscribers_should_be(String subscribersCount) {
        RoomPage.getNumberOfSubscribers().shouldHave(text(subscribersCount));
    }

    @And("^Stop Subscribing link should be present$")
    public void Stop_Subscribing_link_should_be_present(){
        RoomPage.getLabelOfSubscribers().shouldHave(text("Stop subscribing"));
    }

    @Then("^User (.*) gets email with subject (.*)$")
    public void User_gets_email_with_subject(String user, String subject) throws Exception {
        Assert.assertEquals(RoomPage.checkMail(user), subject);
    }

    @When("^Another user (.*) logs in$")
    public void Another_user_logs_in(String user) {
        Actions.logout();
        LoginPage.shouldAppear();
        LoginPage.login(user);
        open(JOINT_NO);
        HomePage.shouldAppear();
    }

    @And("^User goes to folder (.*)$")
    public void User_goes_to_folder(String folderName) {
        RoomPage.openFolder(folderName);
    }

    @Then("^User can see subscribers (.*)$")
    public void User_can_see_subscribers(String subscriberList) throws Throwable {
        String[] subscribers = subscriberList.split(", ");
        RoomPage.moveToSubscribers();
        RoomPage.checkSubscribers(subscribers);
    }

    @When("^User unsubscribes$")
    public void User_unsubscribes() {
        RoomPage.clickSubscribe();
    }

    @When("^User renames folder (.*) to folder (.*)$")
    public void User_renames_folder(String folderName, String newFolderName) {
        RoomPage.openRenameDialog(folderName, newFolderName);
        RoomPage.renameFolder(newFolderName);
    }

    @And("^User (.*) does not get email with subject (.*)$")
    public void User_user_participant_does_not_get_email(String user, String subject) throws Exception {
        Assert.assertNotEquals(RoomPage.checkMail(user), subject);
    }

    @Then("^User can not see (.*) folder in selected folder$")
    public void User_can_not_see_folder_in_selected_folder(String folderName){
        RoomPage.getFolderInView(folderName).should(not(exist));
    }

    @Then("^User select all content in current folder$")
    public void User_select_all_content_in_current_folder(){
        RoomPage.selectAll();
    }

    @And("^Drag files to (.*) folder$")
    public void Drag_files_to_folder(String folder) {
        RoomPage.dragFilesTo(folder);
    }

    @Then("^User can not see (.*), (.*) folders in selected folder$")
    public void User_can_not_see_folders_in_selected_folder(String first, String second) {
        RoomPage.getFolderInView(first).should(not(exist));
        RoomPage.getFolderInView(second).should(not(exist));
    }

    @And("^Click download button$")
    public void Click_download_button() {
        RoomPage.download();
    }

    @Then("^Download window appears and archive is creating$")
    public void Download_window_appears_and_archive_is_creating() {
        RoomPage.waitForCreating();
    }

    @When("^User click open button and archive saves$")
    public void User_click_open_button_and_archive_saves( ) throws IOException {
        RoomPage.saveArchive();
    }

    @Then("^User get content of archive$")
    public void User_get_content_of_archive() throws Exception{
        RoomPage.getArchiveContent();
    }

    @And("^Content of (.*) folder$")
    public void Content_of_folder(String folderName) {
        RoomPage.getFoldersContent(folderName);
    }

    @Then("^User compare these content$")
    public void User_compare_these_content() {
        RoomPage.checkContent();
    }
}
