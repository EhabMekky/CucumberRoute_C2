package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;

public class CartSteps {
    private final CartPage cartPage = new CartPage();

    @When("I click on the Cart button")
    public void i_click_on_the_cart_button() {
        try {
            cartPage.clickCartIcon();
        } catch (Exception e) {
            Assert.fail("Failed to click on Cart button: " + e.getMessage());
        }
    }

    @Then("I should be on the Cart page")
    public void i_should_be_on_the_cart_page() {
        try {
            cartPage.verifyPageTitle();
            Assert.assertTrue("Cart page should be displayed successfully", true);
        } catch (Exception e) {
            Assert.fail("Failed to verify Cart page: " + e.getMessage());
        }
    }
}