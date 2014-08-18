package helpers;

import java.io.InputStream;
import java.util.Properties;

public class Creds {
    private static final Properties creds;

    static {
        creds = new Properties();
        InputStream is = Locators.class.getResourceAsStream("/creds.properties");
        try {
            creds.load(is);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[] get(String locatorName) {
        return creds.getProperty(locatorName).split(",");
    }
}
