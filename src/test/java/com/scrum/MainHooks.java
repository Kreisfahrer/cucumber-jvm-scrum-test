package com.scrum;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Cucumber hooks are executed before or after each scenario
 * @author Andrii.Dzynia
 */
public class MainHooks {
    private static final String URL = "https://ph-stage.joint.no/prosjekthotell/index.html";
    protected static final String PATH = System.getProperty("user.dir");

    @Before
    public void prepare() {
        FirefoxProfile f = new FirefoxProfile();
        f.setPreference("browser.download.folderList", 2);
        f.setPreference("browser.download.dir", PATH);
        f.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed, application/octet-stream, application/x-compress, application/x-compressed, multipart/x-zip");
        WebDriver driver = new FirefoxDriver(f);
        Configuration.baseUrl = URL;
        Configuration.timeout = 10000;
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        open(Configuration.baseUrl);
    }

    @After
    public void after() throws IOException {
        getWebDriver().quit();
    }

}

