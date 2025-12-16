package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.Objects;

public class LandingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By LOGIN_MENU = By.xpath("//li[@id='menu-item-2330' and contains(normalize-space(.),'Login')]");
    private static final By LOGIN_POPUP = By.cssSelector("div.xoo-el-form-container.xoo-el-form-popup[data-active='login']");
    private static final By USERNAME_INPUT = By.name("xoo-el-username");
    private static final By PASSWORD_INPUT = By.name("xoo-el-password");
    private static final By REMEMBER_CHECK = By.name("xoo-el-rememberme");
    private static final By SUBMIT_BUTTON = By.cssSelector("button.xoo-el-login-btn");
    private static final By WELCOME_TEXT = By.xpath("//h2[text()='Welcome to bitheap']");

    public LandingPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginMenu() {
        Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(LOGIN_MENU))).click();
    }

    public void openLoginPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_POPUP));
    }

    public void enterUsername(String username) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        el.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        el.sendKeys(password);
    }

    public void setRememberMe(boolean value) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(REMEMBER_CHECK));
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON)).click();
    }

    public void login(String username, String password, boolean rememberMe) {
        openLoginPopup();
        enterUsername(username);
        enterPassword(password);
        setRememberMe(rememberMe);
        submitLogin();
    }

    public boolean waitForWelcomeText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WELCOME_TEXT));
        return true;
    }
}
