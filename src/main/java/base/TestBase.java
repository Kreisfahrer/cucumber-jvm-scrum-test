package base;

public class TestBase {
    private static final String URL = "https://ph-stage.joint.no/prosjekthotell/index.html";
    public static final String PATH = System.getProperty("user.dir");

//    @BeforeMethod()
//    public void setup() throws MalformedURLException {
//        FirefoxProfile f = new FirefoxProfile();
//        f.setPreference("browser.download.folderList", 2);
//        f.setPreference("browser.download.dir", PATH);
//        f.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed, application/octet-stream, application/x-compress, application/x-compressed, multipart/x-zip");
//        WebDriver driver = new FirefoxDriver(f);
//        Configuration.baseUrl = URL;
//        Configuration.timeout = 10000;
//        WebDriverRunner.setWebDriver(driver);
//        driver.manage().window().maximize();
//    }
}
