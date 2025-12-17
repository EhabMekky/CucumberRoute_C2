package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final By CART_ICON = By.xpath("//div/div/div/a[contains(@href, 'cart')]");
    private static final By CART_TITLE = By.xpath("//h1[contains(text(), 'Cart')]");

    public CartPage clickCartIcon() {
        wait.until(d -> d.findElement(CART_ICON).isDisplayed());
        driver.findElement(CART_ICON).click();
        return this;
    }

    public CartPage verifyPageTitle()
    {
        wait.until(d -> d.findElement(CART_TITLE).isDisplayed());
        return this;
    }
}
