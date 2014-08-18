package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Waiter {
    private static final int DEFAULT_TIME_OUT = 10;

    public static void waitFor(ExpectedCondition condition){
        getWaiter().until(condition);
    }

    public static void waitForAjax(int timeOutInSeconds) {
        getWaiter(timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return (Boolean) executeJavaScript("return !(ExtJSUtil.hasActiveAjaxCalls(Ext.Ajax.requests && !_.isEmpty(Ext.Ajax.requests)));");
                    }
                });
    }

    public static void waitForDom(int timeOutInSeconds) {
        getWaiter(timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (Boolean) executeJavaScript("return !(Ext.Ajax.requests && !Ext.isEmpty(Ext.Ajax.requests));");
            }
        });
    }

    public static void waitForPageTitle(String title){
        getWaiter().until(ExpectedConditions.titleIs(title));
    }

    public static void waitForJquery(){
        getWaiter().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }

    public static void waitForPageToLoad(){
        getWaiter().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return ((String) js.executeScript("return document.readyState")).equalsIgnoreCase("complete");
            }
        });
    }

    private static WebDriverWait getWaiter(){
        return new WebDriverWait(getWebDriver(), DEFAULT_TIME_OUT);
    }

    private static WebDriverWait getWaiter(final int timeout){
        return new WebDriverWait(getWebDriver(), timeout);
    }
}