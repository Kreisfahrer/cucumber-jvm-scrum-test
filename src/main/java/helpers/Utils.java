package helpers;

import com.codeborne.selenide.Configuration;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Utility functions.
 * 
 * @author huguogang
 *
 */
public final class Utils {
    /**
     * Ensure string is legal Windows/Mac file name. All illegal
     * characters will be replaced.
     * 
     * @param filename    Proposed file name that might need clean up
     * @return A string that can be used as file name
     */
    public static String ensureLegalFileName(String filename) {
        String fn = filename.replace("\\", "%5C")
                    .replace("/", "-")
                    .replace(":", "-")
                    .replace("?", "-")
                    .replace("*", "-")
                    .replace("<", "-")
                    .replace(">", "-")
                    .replace("|", "-")
                    .replace("\"", "-")
                    .replace(",", "-")
                    .replace(";", "-");
        return fn;
    }
    
    /**
     * Inject underscore to client side, if it is not already used by the current page.
     *
     * @throws java.io.IOException
     */
    public static void injectUnderscore() {
        injectLibHelper("return this._ === undefined;", "underscore.js");
    }

    /**
     * Inject jQuery to client side if not already used by the current page.
     *
     * @throws java.io.IOException
     */
    public static void injectJQuery() {
        injectLibHelper("return this.$ === undefined", "jquery.js");
    }

    /**
     * Inject utility JS functions to the client side.
     *
     * @throws java.io.IOException
     */
    public static void injectSJTXE() {
        if(!isUnderScorePresent()){
            //dependencies
            injectJQuery();
            injectUnderscore();
            //no need to check, assume it's never loaded
            injectLibHelper("return true;", "SJTXE.js");
        }
    }

    private static void injectLibHelper(String js, String libFilename) {
        JavascriptExecutor je = (JavascriptExecutor) getWebDriver();
        boolean needInjection = (executeJavaScript(js));
        if(needInjection) {
            URL u = Resources.getResource(libFilename);
            try {
                js = Resources.toString(u, Charsets.UTF_8);
            } catch(IOException e) {
                e.printStackTrace();
            }
            je.executeScript(js);
        }
    }

    /**
     * Wait until all ExtJS Ajax call are finished.
     *
     * @param driver
     * @throws InterruptedException
     * @throws java.io.IOException
     */
//    public static void waitForAjax(WebDriver driver)
//            throws InterruptedException, IOException{
//        waitForAjax(driver, Configuration.getInstance().getAjaxWait());
//    }
    /**
     * Wait until all ExtJS Ajax call are finished. 
     *
     * @throws InterruptedException
     */
    public static void waitForAjax(final int extraTime) {
        //this might be called right after a user key press or mouse click
        //we should give brower sometime to react to user action and start Ajax call
        sleep(500);
        if(!isUnderScorePresent()){
            injectSJTXE();
        }
        (new WebDriverWait(getWebDriver(), Configuration.timeout))
            .until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (Boolean) (executeJavaScript("return !SJTXE.hasActiveAjaxCalls();"));
            }
        });
        //extra time for ExtJS to digest data and update GUI
        sleep(extraTime);
    }

    public static void waitForAjax() {
        waitForAjax(0);
    }

    public static boolean isUnderScorePresent(){
        return (boolean) executeJavaScript("return !this.SJTXE === undefined;");
    }
    
    public static ArrayList<Object> getStoreData(String storeID) {
        @SuppressWarnings(value="unchecked")
        ArrayList<Object> ret = executeJavaScript("return SJTXE.getStoreData(arguments[0]);", storeID);
        return ret;
    }
    
//    public static void waitForExtReady(WebDriver driver) throws IOException {
//        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Configuration.getInstance().getImplicitWait())
//            .ignoring(WebDriverException.class);
//        wait.until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return (Boolean) ((JavascriptExecutor) d)
//                        .executeScript("return Ext.isReady;");
//            }
//        });
//    }
    
    public static Boolean hasJSError() {
        return (Boolean) (executeJavaScript("return SJTXE.hasExceptions();"));
    }
    
    public static Boolean hasAjaxError() {
        return (Boolean) (executeJavaScript("return SJTXE.hasAjaxFailure();"));
    }

    public static boolean fromIntToBoolean(int num){
        return num != 0;
    }
}
