package pages;

import helpers.Creds;
import helpers.Utils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class LoginPage {
    private static final By NAME_FIELD = get("LoginPage.UsernameField");
    private static final By PASS_FIELD = get("LoginPage.PasswordField");
    private static final By LOGIN = get("LoginPage.SubmitButton");
    private static final By LOGIN_FORM = get("LoginPage.LoginBlock");

    public static void login(String name, String pass) {
        shouldAppear();
        $(NAME_FIELD).val(name);
        $(PASS_FIELD).val(pass);
        $(LOGIN).click();
    }

    public static void login() {
        String [] creds = Creds.get("user.admin");
        login(creds[0], creds[1]);
    }

    public static void login(String credType) {
        String [] creds = Creds.get(credType);
        login(creds[0],creds[1]);
    }

    public static void shouldAppear() {
        $(LOGIN_FORM).should(appear);
        Utils.waitForAjax();
    }
}
