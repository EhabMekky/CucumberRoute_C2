package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.initDriver();
        WebDriver driver = DriverManager.getDriver();
        String url = readUrlFromJson();
        driver.get(url);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    private String readUrlFromJson() {
        Path path = Paths.get("src", "test", "resources", "testData", "loginData.json");
        try {
            String content = Files.readString(path);
            Pattern p = Pattern.compile("\"URL\"\\s*:\\s*\"([^\"]+)\"");
            Matcher m = p.matcher(content);
            if (m.find()) {
                return m.group(1);
            }
        } catch (IOException ignored) {}
        return "about:blank";
    }
}
