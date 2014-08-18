package helpers;

import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

/**
 * Created by andreystakhievich on 5/1/14.
 */
public class Actions {
    private static final By POPUP = get("Popup");
    private static final By PROFILE_BUTTON = get("Header.ProfileButton");
    private static final By PROFILE_WINDOW = get("Header.ProfileWindow");
    private static final By LOGOUT = get("Header.ProfileLogoutButton");

    public static void handlePopup(String buttonText){
        $(POPUP).$(byText(buttonText)).click();
    }

    public static void checkExpectedElements(List<By> expectedElements) {
        for (By elem : expectedElements) {
            $(elem).shouldBe(visible);
        }
    }

    public static void logout() {
        $(PROFILE_BUTTON).click();
        $(PROFILE_WINDOW).waitUntil(appears, 3000);
        $(LOGOUT).click();
    }
}
