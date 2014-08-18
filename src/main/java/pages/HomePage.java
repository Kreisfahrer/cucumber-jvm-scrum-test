package pages;

import helpers.Utils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.Locators.get;

public class HomePage {
    private static final By USER_MENU = get("HomePage.UserMenuButton");
    private static final By USER_MENU_BLOCK = get("HomePage.UserMenuBlock");
    private static final By LOGOUT = get("HomePage.UserMenu.Logout");
    private static final By ROOMS = get("HomePage.RoomsBodyItems");
    private static final String LINK = ".ph-link";

    public static void logout(){
        $(USER_MENU).click();
        $(USER_MENU_BLOCK).$(LOGOUT).click();
    }

    public static void shouldAppear(){
        $(USER_MENU).shouldBe(visible);
        Utils.waitForAjax();
    }

    public static void openRoomNum(final int num){
        $(ROOMS, num).$(LINK).click();
    }

    public static void openRoom(final String roomName){
        $$(ROOMS).findBy(text(roomName)).$(LINK).click();
        RoomPage.shouldAppear();
    }
}
