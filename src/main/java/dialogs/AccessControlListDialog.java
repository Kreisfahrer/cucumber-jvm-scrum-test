package dialogs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.Locators.get;
import static helpers.Utils.waitForAjax;

public class AccessControlListDialog {

    private static final By WINDOW = get("AccessControlListDialog.Window");
    private static final By ACCESS_LEVEL_LABELS = get("AccessControlListDialog.AccessLevelsLabels");
    private static final By USER_TEXTBOX = get("AccessControlListDialog.UserInput");
    private static final By USERS = get("AccessControlListDialog.Users");
    private static final By ADD_BUTTON = get("AccessControlListDialog.AddButton");
    private static final By ADDED_USERS_NAMES = get("AccessControlListDialog.AddedUsersNames");
    private static final By EDIT_CHECKBOXES = get("AccessControlListDialog.EditCheckBoxes");
    private static final By FOOTER_TOOLBAR = get("AccessControlListDialog.FooterToolbar");

    public static void changeAccess(String accessLevelName) {
        $$(ACCESS_LEVEL_LABELS).findBy(text(accessLevelName)).click();
    }

    public static void addUser(String name) {
        $(USER_TEXTBOX).waitUntil(appears, 2000);
        $(USER_TEXTBOX).val(name);
        $(USERS).waitUntil(appears, 2000);
        $(USERS).$(byText(name)).click();
        $(ADD_BUTTON).click();
    }

    public static void provideEditAccessToUser(String userName) {
        $$(EDIT_CHECKBOXES).get(getIndex(userName)).click();
    }

    private static int getIndex(String name) {
        int index = 0;
        for (SelenideElement se : $$(ADDED_USERS_NAMES)) {
            if ($(se).text().contentEquals(name)) {
                break;
            }
            else {
                index++;
            }
        }
        return index;
    }

    public static void saveSettings() {
        $(FOOTER_TOOLBAR).$(byText("Ok")).click();
        waitForAjax();
    }

    public static void shouldAppear() {
        $(WINDOW).waitUntil(appears, 5000);
    }
}
