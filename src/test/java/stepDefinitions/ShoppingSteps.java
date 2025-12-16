package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.LandingPage;
import utils.DriverManager;
import org.junit.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingSteps {

    private final LandingPage landingPage = new LandingPage();

    @Given("I go to the website")
    public void i_go_to_the_website() {
        Assert.assertNotNull(DriverManager.getDriver());
    }

    @When("I click on the Sign in button")
    public void i_click_on_the_sign_in_button() {
        landingPage.clickLoginMenu();
        landingPage.openLoginPopup();
        Assert.assertTrue("Login popup should be visible", landingPage.waitForWelcomeText());
    }

    @And("I specify my credentials")
    public void i_specify_my_credentials() {
        Credentials creds = readCredentialsFromJson("loginData.json");
        landingPage.enterUsername(creds.username);
        landingPage.enterPassword(creds.password);
        landingPage.setRememberMe(creds.rememberMe);
        landingPage.submitLogin();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {

        Assert.assertTrue("Welcome Text is displayed after login", landingPage.waitForWelcomeText());
    }

    private static class Credentials {
        String username = "";
        String password = "";
        boolean rememberMe = false;
    }


    private Credentials readCredentialsFromJson(String fileName) {
        Path path = Paths.get("src", "test", "resources", "testData", fileName);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(path.toFile(), Credentials.class);
        } catch (IOException e) {
            return new Credentials();
        }
    }
}
