package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.isFirefox;

public class HtmlEditor {

    public static void setText(By container, String text) {
        if(isFirefox()){
            switchTo().frame($(container).$("iframe.x-htmleditor-iframe").toWebElement());
            $("body").sendKeys(Keys.chord(Keys.CONTROL, "a"));
            $("body").sendKeys(text);
            switchTo().defaultContent();
            $(container).$(Locators.get("Htmleditor")).click();
        }
        else {
            $(container).$(Locators.get("Htmleditor")).click();
            actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(text).perform();
        }
        sleep(1000);
    }
    public static void appendText(By container, String text){
        $(container).$(Locators.get("Htmleditor")).click();
        actions().sendKeys(text).perform();
    }
    public static void clear(By container, String text){
        $(container).$(Locators.get("Htmleditor")).click();
        actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).perform();
    }
}
