package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.Actions;
import helpers.Creds;
import helpers.FolderType;
import helpers.HtmlEditor;
import helpers.email.Email;
import helpers.email.GuerrillaMail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static base.TestBase.PATH;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.ie;
import static helpers.Locators.get;
import static helpers.Utils.waitForAjax;
import static java.util.Arrays.asList;

public class RoomPage {
    private static final By BODY = get("Room.Body");
    private static final By ADVANCED_FOLDER_BUTTON = get("Room.New.Menu.Item", "Utvidet mappe");
    private static final By WORKFLOW_FOLDER_BUTTON = get("Room.New.Menu.Item", "Arbeidsflytmappe");
    private static final By TREE_VIEW = get("Navigation.TreeView");
    private static final By CONTEXT_MENU = get("ContextMenu");
    private static final By POPUP = get("Popup");

    private static final By SEARCH_TEXT_FIELD = get("Room.SearchTextField");
    private static final By SEARCH_TEXT_BUTTON = get("Room.SearchTextButton");
    public static final By SEARCH_RESULT_FIELD = get("Room.SearchResultField");
    public static final By BACK_LINK = get("Room.SearchResultPhLink");
    private static final By SEARCH_RESULTS = get("Room.SearchResults");
    private static final By SHOW_FOLDER_CONTENTS = get("Room.ShowFolderContents");
    private static final By FILE_CHECK_BOX = get("Room.FileCheckBox");
    private static final By MORE_OPTIONS_BUTTON = get("Room.MoreOptionsButton");
    private static final By MORE_OPTIONS_WINDOW = get("Room.MoreOptionsWindow");
    private static final By MENU_CONTEXT_MOVE = get("Room.MenuContextMove");
    private static final By MENU_MOVE = get("Room.MenuMove");
    private static final By MENU_VIEW = get("Room.MenuView");
    private static final By FOLDERS_TREE = get("Room.FoldersTree");
    private static final By EXPANDER = get("Room.Expander");
    public static final By FILE_FULL_PATH = get("Room.FileFullPath");

    private static final By NEW_FOLDER_BUTTON = get("Room.NewButton");
    private static final By NEW_FILE_BUTTON = get("Room.AddFileButton");
    private static final By SHARE_BUTTON = get("Room.ShareButton");
    private static final By NEW_FOLDER_PANEL = get("NewFolder.Window.Panel");
    private static final By NEW_FOLDER_BAR = get("NewFolder.Window.Bar");
    private static final By NEW_FOLDER_DIALOG = get("NewFolder.Window");
    private static final By FOLDER_FIELDS = get("NewFolder.Window.Field");
    private static final By FOLDER_NAME_INPUT = get("NewFolder.NameInput");
    private static final By ACCESS_LEVEL_CONTAINER = get("NewFolder.AccessLevelContainer");
    private static final By CUSTOM_FIELD_WINDOW = get("CustomField.Window");
    private static final By BOUND_LIST = get("BoundList");
    private static final By CUSTOM_FIELD_NAME = get("CustomField.Fieldname");
    private static final By CUSTOM_FIELD_DESCRIPTION = get("CustomField.Description");
    private static final By CUSTOM_FIELD_CHOICE = get("CustomField.Choice");
    private static final By ROOM_TOOLBAR = get("Room.Toolbar");
    private static final By DOCUMENT_CONTAINER = get("Room.DocumentPanel");
    private static final By SETTINGS_BUTTON = get("Room.SettingsButton");
    private static final By ROOM_STEPS_CONTAINER = get("Room.StepsContainer");
    private static final By PATH_CONTAINER = get("Room.PathContainer");

    private static final By NEW_ENTRY_WINDOW = get("NewEntry.Window");
    private static final By NEW_ENTRY_FIELDS = get("NewEntry.FieldContainer");
    private static final By NEW_ENTRY_TEXT = get("NewEntry.Field.Text");
    private static final By NEW_ENTRY_NUMBER = get("NewEntry.Field.Number");
    private static final By NEW_ENTRY_DATE = get("NewEntry.Field.Date");
    private static final By NEW_ENTRY_RICH = get("NewEntry.Field.RichText");
    private static final By NEW_ENTRY_MEMBERLIST = get("NewEntry.Field.MemberList.Container");
    private static final By NEW_ENTRY_FIELDS_CONTAINER = get("NewEntry.Field.List.Container");
    private static final By ENTRIES = get("Entry.Panel.Entries");

    private static final By NEW_COMMENT_WINDOW = get("NewComment.Window");
    private static final By COMMENT_ICON = get("CommentIcon");
    private static final By COMMENTS_LIST_ITEM = get("EntryView.Comments.ListItem");
    private static final By COMMENTS_CONTAINER = get("EntryView.Comments.Container");
    private static final By HTML_EDITOR = get("Htmleditor");
    private static final By TOOLS = get("Tools.Edit");

    private static final By TABS = get("Room.Tabs");
    private static final By CONTAINER = get("Room.Container");
    private static final By GROUPS_CONTAINER = get("Room.GroupsContainer");
    private static final By GROUP_NAMES = get("Room.GroupsNames");
    private static final By ACCESS_LEVEL = get("Room.AccessLevel");
    private static final By GROUPS_DESCRIPTIONS = get("Room.GroupsDescriptions");

    private static final By NEW_GROUP_WINDOW = get("NewGroup.Window");
    private static final By NEW_GROUP_NAME = get("NewGroup.NameInput");
    private static final By NEW_GROUP_DESCRIPTION = get("NewGroup.DescriptionInput");
    private static final By NEW_GROUP_MEMBER = get("NewGroup.MemberInput");
    private static final By NEW_GROUP_MEMBERS_COMBO_BOX = get("NewGroup.MembersComboBox");
    private static final By NEW_GROUP_CONTAINER = get("NewGroup.Container");
    private static final By NEW_GROUP_MESSAGE = get("NewGroup.Message");
    private static final By MEMBERS_PANEL = get("NewGroup.MembersPanel");
    private static final By NEW_GROUP_ADD_BUTTON = get("NewGroup.AddButton");
    private static final By NEW_GROUP_SAVE_MEMBERS = get("NewGroup.SaveMembersButton");
    private static final By EDIT_GROUP_WINDOW = get("EditGroup.Window");
    private static final By DELETE_BUTTON = get("Room.DeleteButton");
    private static final By NEW_WORKFLOW_WINDOW = get("NewWorkflow.Window");
    private static final By NEW_WORKFLOW_NAME = get("NewWorkflow.NameInput");
    private static final By NEW_WORKFLOW_BAR = get("NewWorkflow.Bar");
    private static final By ADD_FIELD_BUTTON = get("NewWorkflow.AddFieldButton");
    private static final By NEW_WORKFLOW_STEP = get("NewWorkflow.StepInput");
    private static final By ADD_STEP_BUTTON = get("NewWorkflow.AddStepButton");
    private static final By STEPS_CONTAINER = get("NewWorkflow.StepsContainer");
    private static final By DELETE_ICON = get("NewWorkflow.DeleteIcon");
    private static final By UP_ICON = get("NewWorkflow.ArrowUp");
    private static final By DOWN_ICON = get("NewWorkflow.ArrowDown");

    private static final By DELETE_WINDOW = get("DeleteDialog.Window");
    private static final By DELETE_BUTTONS = get("DeleteDialog.Buttons");

    private static final By COMBOBOX_BUTTON = get("CopyDialog.ComboBoxButton");
    private static final By COMBOBOX_CONTAINER = get("CopyDialog.ComboBoxContainer");
    private static final By COPY_TREE_VIEW = get("CopyDialog.TreeContainer");

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/YYYY", Locale.US);
    private static final Date CURRENT_DATE = new Date();
    private static final String GROUP_ROW = "//*[contains(@id, 'groupgrid')]//tr[.//*[contains(text(), '%s')]]";
    private static final String GENERAL_TAB = "Generelt";
    private static final String FIELDS_TAB = "Felt";
    private static final String WORKFLOW_TAB = "Arbeidsflyt";
    private static final String STEP_NEW = "Ny";
    private static final String STEP_OPEN = "Åpen";
    private static final String STEP_CLOSED = "Lukket";
    private static final String JOINT_NO = "https://ph-stage.joint.no/prosjekthotell/index.html#/joint.no/overview";

    private static final By SUBSCRIBE_BLOCK = get("Room.Subscribe");
    private static final By TOOLTIP_WINDOW = get("Room.TooltipWindow");
    private static final By SUBSCRIBING_USERS = get("Room.UsersSubscribing");
    private static final By DOCUMENTS = get("Room.DocumentsInFolder");
    private static final By SELECT_ALL_CHECKBOX = get("Room.SelectAllCheckBox");

    private static final By NEW_NAME_FOLDER_FIELD = get("NewFolder.Window.TextField");
    private static final By DOWNLOAD_BUTTON = get("Room.DownloadButton");

    private static final By DOWNLOAD_WINDOW = get("Room.DownloadDialog.Window");
    private static final By DOWNLOAD_MESSAGE = get("Room.DownloadDialog.Message");
    private static final By DOWNLOAD_OPEN_BUTTON = get("Room.DownloadDialog.SaveButton");

    private static final List<ZipEntry> FILES_IN_ARCHIVE = new LinkedList<>();
    private static final List<String> FILES_IN_FOLDER = new LinkedList<>();

    private static String mailBody;
    private static String link;

    public enum AccessLevel {
        Share, Collaborate, Private, Custom
    }

    public static SelenideElement getToolbarButton(String name) {
        return $(ROOM_TOOLBAR).$(byText(name));
    }

    private static void openMoreOptions() {
        $(MORE_OPTIONS_BUTTON).click();
    }

    public static void openAccessRights() {
        openMoreOptions();
        $(MORE_OPTIONS_WINDOW).waitUntil(appears, 3000);
        $(MORE_OPTIONS_WINDOW).$(byText("Access rights")).click();
        waitForAjax();
    }

    public static void checkBreadCrumbAndTreeView(String folderName) {
        By partsOfPath = By.cssSelector("[id*=breadcrumb] .x-toolbar-item .x-btn-inner");
        int size = $$(partsOfPath).size();
        $$(partsOfPath).get(size - 1).shouldHave(text(folderName));
        $$(By.cssSelector("[id*=treeview] .x-grid-row")).findBy(text(folderName)).shouldHave(cssClass("x-grid-row-selected"));
    }

    public static void download() {
        $(DOWNLOAD_BUTTON).waitUntil(appears, 1000);
        $(DOWNLOAD_BUTTON).click();
    }

    public static void waitForCreating() {
        $(DOWNLOAD_WINDOW).waitUntil(appears, 3000);
        $(DOWNLOAD_MESSAGE).waitUntil(appears, 3000).shouldHave(text("Zip file ready for download"));
    }

    public static void saveArchive() throws IOException{
        File f = new File(PATH + "\\JOINT_ProsjektHotell.zip");
        Path p = Paths.get(f.getPath());
        if(Files.exists(p)){
            Files.delete(p);
        }
        $(DOWNLOAD_OPEN_BUTTON).shouldBe(enabled);
        $(DOWNLOAD_OPEN_BUTTON).click();
        sleep(2000);
    }

    public static void getArchiveContent() throws Exception{
        ZipFile z = new ZipFile(PATH + "\\JOINT_ProsjektHotell.zip");
        Enumeration n = z.entries();
        while (n.hasMoreElements()) {
            FILES_IN_ARCHIVE.add((ZipEntry)n.nextElement());
        }
    }

    public static void getFoldersContent(String folderName) {
        By folders = By.cssSelector("[src=\"resources/images/folders_and_files/folder_24x24.png\"]");
        int size = $$(folders).size();
        for (int i = 0; i < size; ++i) {
            $(folders, i).click();
            waitForAjax();
            By files = By.cssSelector("[src=\"resources/images/filetypes/text_24px.png\"]");
            if ($$(files).size() > 0) {
                int x = $$(files).size();
                for (int y = 0; y < x; ++y) {
                    $(files, y).click();
                    waitForAjax();
                    By partsOfPath = By.cssSelector("[id*=breadcrumb] .x-toolbar-item .x-btn-inner");
                    int count = 0;
                    String path = "";
                    for (SelenideElement sr : $$(partsOfPath)) {
                        if (count == 0 || count == 1 || count == 2) {
                            count++;
                        }
                        else {
                            path = path + $(sr).text() + "/";
                            count++;
                        }
                    }
                    path = path.substring(0, path.length() - 1);
                    FILES_IN_FOLDER.add(path);
                    $(partsOfPath, 2).click();
                }
            }
            getFolder(folderName).click();
            waitForAjax();
        }
    }

    public static void checkContent() {
        List<String> li = new LinkedList<>();
        for (ZipEntry z : FILES_IN_ARCHIVE) {
            li.add(z.toString());
        }
        li.containsAll(FILES_IN_FOLDER);
    }

    public static void checkExpectedDocuments(List<String> names) {
        for (String s : names) {
            $$(DOCUMENTS).findBy(text(s)).should(exist);
        }
    }

    public static void checkExpectedDocument(String fileName, Condition con) {
        $$(DOCUMENTS).findBy(text(fileName)).should(con);
    }

    public static void dragFilesTo(String fileName, String folderName) {
        waitForAjax();
        actions().dragAndDrop($$(DOCUMENTS).findBy(text(fileName)), $(TREE_VIEW).$(byText(folderName))).perform();
        waitForAjax();
    }

    public static void dragFilesTo(String folderName) {
        waitForAjax();
        actions().dragAndDrop($(DOCUMENTS),$(TREE_VIEW).$(byText(folderName))).perform();
        waitForAjax();
    }

    public static void dragFolderTo(String sourceFolderName, String targetFolderName) {
        waitForAjax();
        actions().dragAndDrop($(TREE_VIEW).$(byText(sourceFolderName)),$(TREE_VIEW).$(byText(targetFolderName))).perform();
        waitForAjax();
    }

    public static void checkPath(List<String> pathItems) {
        $(PATH_CONTAINER).waitUntil(appears, 3000);
        for (String s : pathItems) {
            $(PATH_CONTAINER).$(byText(s)) .should(exist);
        }
    }

    public static void selectAll() {
        waitForAjax();
        $(SELECT_ALL_CHECKBOX).click();
    }

    public static void clickSubscribe() {
        $(SUBSCRIBE_BLOCK).click();
        waitForAjax();
    }

    public static SelenideElement getNumberOfSubscribers() {
        return $(SUBSCRIBE_BLOCK, 0);
    }

    public static SelenideElement getLabelOfSubscribers() {
        return $(SUBSCRIBE_BLOCK, 1);
    }

    public static void moveToSubscribers() {
        sleep(300);
        actions().moveToElement($(SUBSCRIBE_BLOCK, 1)).perform();
        $(TOOLTIP_WINDOW).waitUntil(appears, 5000);
    }

    public static void checkSubscribers(String firstUser, String secondUser) {
        List<String> expectedNames = new LinkedList<String>();
        List<String> actualNames = new LinkedList<String>();
        expectedNames.add(Creds.get(firstUser)[2]);
        expectedNames.add(Creds.get(secondUser)[2]);
        for (SelenideElement se : $$(SUBSCRIBING_USERS)) {
            actualNames.add(se.text());
        }
        Assert.assertTrue(actualNames.containsAll(expectedNames));
    }

    public static void checkSubscribers(String [] subscribers) {
        List<String> texts = asList($$(SUBSCRIBING_USERS).getTexts());
        Assert.assertTrue(texts.containsAll(asList(subscribers)));
    }

    public static String checkMail(String user) throws Exception{
        GuerrillaMail mailBox = new GuerrillaMail();
        mailBox.setEmailUser(Creds.get(user)[0]);
        ArrayList<Email> newMails = mailBox.checkEmail();
        String subject = "";
        if(newMails.size() > 0){
            Email latestMail = newMails.get(0);
            subject = latestMail.getSubject();
            ArrayList<Integer> mail_ids = new ArrayList<Integer>();
            for(Email _mail: newMails){
                mail_ids.add(_mail.getId());
            }
            if (mail_ids.size() != 0) {
                mailBody = mailBox.fetchEmail(mail_ids.get(0)).getBody();
            }
            parserForHTML();
            mailBox.delEmail(mail_ids);
        }
        return subject;
    }

    private static void parserForHTML() throws IOException{
        Document d = Jsoup.parse(mailBody);
        Elements elem = d.select("a");
        if (elem.size() != 0) {
            link = elem.get(0).attr("href");
        }
    }

    public static void openLink() {
        open(link);
    }

    public static SelenideElement getGroupNames() {
        return $(GROUP_NAMES);
    }

    public static void goToMembersTab() {
        waitForAjax();
        $(TABS, 1).click();
    }

    public static void createGroup(String name, String description) {
        $(ROOM_TOOLBAR).$(byText("Opprett gruppe")).click();
        waitForAjax();
        $(NEW_GROUP_NAME).setValue(name);
        $(NEW_GROUP_DESCRIPTION).setValue(description);
        waitForAjax();
        $(NEW_GROUP_WINDOW).$("div[id*=toolbar]").$(byText("Lagre")).click();
    }

    public static SelenideElement getGroup(String groupName) {
        return $$(GROUP_NAMES).findBy(text(groupName));
    }

    public static SelenideElement getAccessLevel(String accessLevel) {
        return $$(ACCESS_LEVEL).findBy(text(accessLevel));
    }

    public static SelenideElement getDescription(String description) {
        return $$(GROUPS_DESCRIPTIONS).findBy(text(description));
    }

    public static SelenideElement getMemberName(String name) {
        $(EDIT_GROUP_WINDOW).$(byText("Medlemmer")).click();
        waitForAjax();
        return $(MEMBERS_PANEL).$(byText(name));
    }

    public static void openGroup(String name) {
        waitForAjax();
        $(GROUPS_CONTAINER).$(byText(name)).click();
    }

    public static void closeGroup() {
        $(EDIT_GROUP_WINDOW).$(byText("Lukk")).click();
    }

    public static void addMember(String member) {
        $(EDIT_GROUP_WINDOW).$(byText("Medlemmer")).click();
        waitForAjax();
        $(NEW_GROUP_MESSAGE).click();
        waitForAjax();
        $(NEW_GROUP_MEMBER).val(member);
        $(NEW_GROUP_MEMBERS_COMBO_BOX).waitUntil(appears, 3000);
        $(NEW_GROUP_MEMBERS_COMBO_BOX).click();
        $(NEW_GROUP_ADD_BUTTON).click();
        waitForAjax();
        $(NEW_GROUP_SAVE_MEMBERS, 1).click();
        waitForAjax();
    }

    public static void showAllGroups() {
        waitForAjax();
        $(CONTAINER).$(byText("Alle grupper")).click();
    }

    public static void deleteGroup(String groupName) {
        $(By.xpath(String.format(GROUP_ROW, groupName))).click();
        waitForAjax();
        $(DELETE_BUTTON).click();
        waitForAjax();
    }

    public static void confirmDeletion() {
        $(DELETE_BUTTONS, 0).click();
        sleep(1000);
    }

    public static void cancelDeletion() {
        $(DELETE_BUTTONS, 1).click();
        sleep(1000);
    }

    public static void createFolder(String folderName, String folderType) {
        if(getFolder(folderName).exists()){
            deleteFolder(folderName);
        }
        $(NEW_FOLDER_BUTTON).click();
        waitForAjax();
        $(get("Room.New.Menu.Item", folderType)).click();
        waitForAjax();
        $(NEW_FOLDER_PANEL).$(FOLDER_NAME_INPUT).val(folderName);
        waitForAjax();
        $(NEW_FOLDER_DIALOG).$(byText("Save")).click();
        waitForAjax();
    }

    public static void makeAdministratorUserPreparation() {
        if (!getFolder("ProtectedFolder").exists()) {
            $(NEW_FOLDER_BUTTON).click();
            waitForAjax();
            $(get("Room.New.Menu.Item", "Simple folder")).click();
            waitForAjax();
            $(NEW_FOLDER_PANEL).$(FOLDER_NAME_INPUT).val("ProtectedFolder");
            waitForAjax();
            $(NEW_FOLDER_DIALOG).$(byText("Save")).click();
            waitForAjax();
            $(NEW_FOLDER_BUTTON).click();
            waitForAjax();
            $(get("Room.New.Menu.Item", "Simple folder")).click();
            waitForAjax();
            $(NEW_FOLDER_PANEL).$(FOLDER_NAME_INPUT).val("any");
            waitForAjax();
            $(NEW_FOLDER_DIALOG).$(byText("Save")).click();
            waitForAjax();
            getFolder("ProtectedFolder").click();
            $(NEW_FOLDER_BUTTON).click();
            waitForAjax();
            $(get("Room.New.Menu.Item", "Simple folder")).click();
            waitForAjax();
            $(NEW_FOLDER_PANEL).$(FOLDER_NAME_INPUT).val("some");
            waitForAjax();
            $(NEW_FOLDER_DIALOG).$(byText("Save")).click();
            waitForAjax();
        }
        else {
            getFolder("ProtectedFolder").click();
        }
    }

    public static void makeParticipantUserPreparation() {
        if (!getFolder("First").exists()) {

        }
    }

    public static void createAdvancedFolder(String name) {
        if(getFolder(name).exists()){
            deleteFolder(name);
        }
        $(NEW_FOLDER_BUTTON).click();
        $(ADVANCED_FOLDER_BUTTON).click();
        waitForAjax();
        $(NEW_FOLDER_PANEL).$(FOLDER_NAME_INPUT).waitUntil(enabled, timeout).val(name);
        $(NEW_FOLDER_BAR).$(byText("Felt")).click();
        for(FolderType folder : FolderType.values()){
            createCustomField(folder.toStr());
        }
        $(FOLDER_FIELDS).$(get("Favourite")).click();
        $(NEW_FOLDER_DIALOG).$(byText("Lagre")).waitUntil(enabled, 5000).click();
        waitForAjax();
    }

    public static void openCreateWorkflowFolderDialog(String name) {
        if(getFolder(name).exists()){
            deleteFolder(name);
        }
        $(NEW_FOLDER_BUTTON).click();
        $(WORKFLOW_FOLDER_BUTTON).click();
        waitForAjax();
    }

    public static void createWorkflowFolder(String name) {
        $(NEW_WORKFLOW_NAME).val(name);
        $(NEW_WORKFLOW_BAR).$(byText("Felt")).click();
        createCustomField(FolderType.Ren_tekst.toStr());
        $(FOLDER_FIELDS).$(get("Favourite")).click();
        goToWorkflowTab();
    }

    public static void goToWorkflowTab() {
        $(NEW_WORKFLOW_BAR).$(byText("Arbeidsflyt")).waitUntil(appears, 5000);
        $(NEW_WORKFLOW_BAR).$(byText("Arbeidsflyt")).click();
        waitForAjax();
    }

    public static void saveWorkflow() {
        $(NEW_WORKFLOW_WINDOW).$(byText("Lagre")).click();
        waitForAjax();
    }

    public static void openCopyDialog(String name) {
        $(TREE_VIEW).$(byText(name)).waitUntil(appears, 5000);
        sleep(4000);
        $(TREE_VIEW).$(byText(name)).contextClick();
        $(CONTEXT_MENU).$(byText("Copy")).click();
    }

    public static void changeAccessLevel(AccessLevel name) {
        clickSettings();
        $(NEW_FOLDER_BAR).$(byText("Access Control")).click();
        $(ACCESS_LEVEL_CONTAINER).$(byText(name.toString())).click();
        $(NEW_FOLDER_DIALOG).$(byText("Lagre")).click();
    }

    public static void selectRoomAndFolder(String roomName, String folderName) {
        $(COMBOBOX_BUTTON).click();
        sleep(1000);
        $(COMBOBOX_CONTAINER).$(byText(roomName)).click();
        if (!folderName.isEmpty()) {
            sleep(1500);
            $(COPY_TREE_VIEW).$(byText(folderName)).click();
        }
        $(COPY_TREE_VIEW).$(byText("Copy")).click();
    }

    public static void openRenameDialog(String name, String newName) {
        if(getFolder(newName).exists()){
            deleteFolder(newName);
        }
        waitForAjax();
        $(TREE_VIEW).$(byText(name)).contextClick();
        $(CONTEXT_MENU).$(byText("Rename")).click();
        waitForAjax();
    }

    public static void renameFolder(String newName) {
        $(NEW_NAME_FOLDER_FIELD).val(newName);
        $(NEW_FOLDER_DIALOG).$(byText("Save")).click();
    }

    public static void rename(String newName) {
        $(NEW_WORKFLOW_NAME).val(newName);
        saveWorkflow();
        waitForAjax();
    }

    public static SelenideElement getPath() {
        return $(PATH_CONTAINER);
    }

    public static void openFolder(String folderName) {
        $(DOCUMENT_CONTAINER).$(byText(folderName)).click();
        waitForAjax();
    }

    public static void clickSettings() {
        $(SETTINGS_BUTTON).click();
    }

    public static void addNewStep(String stepName) {
        $(NEW_WORKFLOW_STEP).val(stepName);
        $(ADD_STEP_BUTTON).click();
        waitForAjax();
    }

    public static SelenideElement getStep(String stepName) {
        return $(STEPS_CONTAINER).$(byText(stepName));
    }

    public static void moveToStep(String stepName) {
        actions().moveToElement($(STEPS_CONTAINER).$(byText(stepName))).perform();
    }

    public static void checkStepIcons(String stepName) {
        RoomPage.moveToStep(stepName);
        $(DELETE_ICON).shouldBe(visible);
        $(UP_ICON).shouldBe(visible);
        $(DOWN_ICON).shouldBe(visible);
    }

    public static void checkExpectedElements() {
        Actions.checkExpectedElements(asList(NEW_FILE_BUTTON, NEW_FOLDER_BUTTON, SHARE_BUTTON));
    }

    public static void checkWorkflowBar() {
        $(NEW_WORKFLOW_BAR).shouldHave(text(GENERAL_TAB));
        $(NEW_WORKFLOW_BAR).shouldHave(text(FIELDS_TAB));
        $(NEW_WORKFLOW_BAR).shouldHave(text(WORKFLOW_TAB));
    }

    public static void checkSteps() {
        $(STEPS_CONTAINER).shouldHave(text(STEP_NEW));
        $(STEPS_CONTAINER).shouldHave(text(STEP_OPEN));
        $(STEPS_CONTAINER).shouldHave(text(STEP_CLOSED));
    }

    public static SelenideElement getStepFromRoomContainer(String stepName) {
        return $(ROOM_STEPS_CONTAINER).$(byText(stepName));
    }

    private static void createCustomField(String type) {
        $(NEW_FOLDER_PANEL).$(byText("Legg til felt")).waitUntil(enabled, timeout);
        sleep(1000);
        $(NEW_FOLDER_PANEL).$(byText("Legg til felt")).click();
        $(CUSTOM_FIELD_WINDOW).$(".x-form-trigger").click();
        $(BOUND_LIST).$(byText(type)).click();
        $(CUSTOM_FIELD_NAME).val("My" + type);
        $(CUSTOM_FIELD_DESCRIPTION).val("MyDescription");
        if($(CUSTOM_FIELD_CHOICE).isDisplayed()){
            $(CUSTOM_FIELD_CHOICE).val("Choice1" + Keys.ENTER + "Choice2" + Keys.ENTER + "Choice3");
        }
        sleep(200);
        $(CUSTOM_FIELD_WINDOW).$(byText("Lagre")).waitUntil(enabled, timeout).click();
        waitForAjax();
    }

    public static void createNewEntry() {
        $(ROOM_TOOLBAR).$(byText("Ny oppføring")).click();
        fillNewEntry();
        sleep(300);
        $(NEW_ENTRY_WINDOW).$(byText("Opprett")).click();
    }

    private static void fillNewEntry() {
        SelenideElement fields = $(NEW_ENTRY_FIELDS);
        fields.$(NEW_ENTRY_TEXT).val("My text");
        fields.$(NEW_ENTRY_NUMBER).val("12345");
        fields.$(NEW_ENTRY_DATE).val(DATE_FORMAT.format(CURRENT_DATE));
        fields.$(NEW_ENTRY_FIELDS_CONTAINER).$(".x-form-trigger").click();
        $(BOUND_LIST).$(byText("Choice2")).click();
        fields.$(NEW_ENTRY_MEMBERLIST).$(".x-form-trigger").click();
        $(BOUND_LIST).$(byText("Alle Deltagere")).click();
        actions().moveToElement($(NEW_ENTRY_RICH).toWebElement()).perform();
        if(ie()) {
            $(NEW_ENTRY_RICH).sendKeys("");
        }
        HtmlEditor.setText(NEW_ENTRY_FIELDS, "Some rich text here");
    }

    public static void addComment(SelenideElement entryElem, String comment) {
        $(ENTRIES).waitUntil(enabled, timeout);
        sleep(1000);
        entryElem.contextClick();
        $(CONTEXT_MENU).$(byText("Legg til ny kommentar")).click();
        HtmlEditor.setText(NEW_COMMENT_WINDOW, comment);
        $(NEW_COMMENT_WINDOW).$(byText("Lagre")).click();
        waitForAjax();
    }

    public static void editComment(SelenideElement entryElem, String comment) {
        $(ENTRIES).waitUntil(enabled, timeout);
        sleep(1000);
        entryElem.$(COMMENT_ICON).click();
        waitForAjax();
        actions().moveToElement($(COMMENTS_LIST_ITEM).toWebElement()).perform();
        $(COMMENTS_LIST_ITEM).$(TOOLS).waitUntil(visible, timeout).click();
        while(!$(COMMENTS_CONTAINER).$(HTML_EDITOR).isDisplayed()){
            actions().moveToElement($(COMMENTS_LIST_ITEM).toWebElement()).perform();
            $(COMMENTS_LIST_ITEM).$(TOOLS).waitUntil(visible, timeout).click();
            sleep(200);
        }
        actions().moveToElement($(COMMENTS_LIST_ITEM)).click().perform();
        HtmlEditor.setText(COMMENTS_CONTAINER, comment);
        $(COMMENTS_CONTAINER).$(byText("Oppdater")).click();
        waitForAjax();
    }

    public static SelenideElement getFolder(String folderName) {
        waitForAjax();
        return $(TREE_VIEW).$(byText(folderName));
    }

    public static SelenideElement getFolderInView(String folderName) {
        waitForAjax();
        return $(DOCUMENT_CONTAINER).$(byText(folderName));
    }

    public static void deleteFolder(String folderName) {
        $(TREE_VIEW).$(byText(folderName)).contextClick();
        $(CONTEXT_MENU).$(byText("Delete")).click();
        sleep(300);
        Actions.handlePopup("Yes");
    }

    public static void shouldAppear(){
        $(BODY).should(appear);
        waitForAjax();
    }

    public static void searchText(String textValue) {
        waitForAjax();
        $(SEARCH_TEXT_FIELD).val(textValue);
        sleep(200);
        $(SEARCH_TEXT_BUTTON).click();
        waitForAjax();
    }

    public static void clickSearchResult(int rowNumber) {
        $$(SEARCH_RESULTS).get(rowNumber).click();
        waitForAjax();
    }

    public static void clickShowFolderContents() {
        $(SHOW_FOLDER_CONTENTS).click();
        sleep(2000);
    }

    public static void selectFile(int fileNumber) {
        $$(FILE_CHECK_BOX).get(fileNumber).click();
        waitForAjax();
    }

    public static void clickMoreOptionsMove() {
        $(MORE_OPTIONS_BUTTON).click();
        $(MENU_CONTEXT_MOVE).waitUntil(visible, timeout);
        $(MENU_CONTEXT_MOVE).click();
        waitForAjax();
    }

    public static void expandFolder(String folderName) {
        $(FOLDERS_TREE).waitUntil(visible, timeout);
        sleep(3000);
        $(By.xpath("//div[contains (@id,'documentItemsTree')]//tbody//div[text()='" + folderName + "']//i[@class='font-icon font-icon-caret-right x-tree-expander']")).click();
        sleep(2000);
    }

    public static void selectFolder(String folderName) {
        $(FOLDERS_TREE).$(byText(folderName)).click();
    }

    public static void moveTo(String location){
        String[] pathElements = location.split("/");
        RoomPage.clickMoreOptionsMove();
        for(int i = 0; i < pathElements.length; i++){
            if(i + 1 < pathElements.length){
                RoomPage.expandFolder(pathElements[i]);
            }
            else{
                RoomPage.selectFolder(pathElements[i]);
            }
        }
        RoomPage.clickMove();
    }

    public static void clickMove() {
        $(MENU_MOVE).click();
        waitForAjax();
    }

    public static void clickView() {
        $(MENU_VIEW).click();
        waitForAjax();
    }

    public static ElementsCollection getEntries() {
        return $$(ENTRIES);
    }
}
