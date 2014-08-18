package dialogs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;
import static helpers.Utils.waitForAjax;

/**
 * Created by EugenBorisik on 8/13/2014.
 */
public class ShareDialog {

    private static final By WINDOW = get("ShareDialog.Window");
    private static final By USER_TEXTBOX = get("ShareDialog.UserInput");
    private static final By USERS = get("ShareDialog.Users");
    private static final By TOOLBAR = get("ShareDialog.ButtonsToolbar");
    private static final By CHECKBOX = get("ShareDialog.Checkbox");

    public static void addUser(String name) {
        $(USER_TEXTBOX).val(name);
        $(USERS).waitUntil(appears, 1000);
        $(USERS).$(byText(name)).click();
    }

    public static void send() {
        if ($(CHECKBOX).isSelected()) {
            $(CHECKBOX).click();
        }
        $(TOOLBAR).$(byText("Ok")).click();
        waitForAjax();
    }

    public static void shouldAppear() {
        $(WINDOW).waitUntil(appears, 3000);
    }
}
