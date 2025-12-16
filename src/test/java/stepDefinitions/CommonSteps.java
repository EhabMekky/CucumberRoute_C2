package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LandingPage;
import utils.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonSteps {
    private final LandingPage landingPage = new LandingPage();

    @Given("I navigate to the website")
    public void i_navigate_to_the_website() {
        Assert.assertNotNull("Driver should be initialized", DriverManager.getDriver());
    }

    @When("I open the login form")
    public void i_open_the_login_form() {
        landingPage.clickLoginMenu();
        landingPage.openLoginPopup();
    }

    @And("I specify my credentials")
    public void i_specify_my_credentials() {
        Credentials creds = readCredentialsFromJson("loginData.json");
        landingPage.enterUsername(creds.username);
        landingPage.enterPassword(creds.password);
        landingPage.setRememberMe(creds.rememberMe);
        landingPage.submitLogin();
    }

    @Then("I should see the welcome message")
    public void i_should_see_the_welcome_message() {
        Assert.assertTrue("Welcome text should be displayed", landingPage.waitForWelcomeText());
    }

    private static class Credentials {
        public String username = "";
        public String password = "";
        public boolean rememberMe = false;
    }

    private Credentials readCredentialsFromJson(String fileName) {
        Path path = Paths.get("src", "test", "resources", "testData", fileName);
        try {
            ObjectMapper mapper = new ObjectMapper();
            var rootNode = mapper.readTree(path.toFile());
            var validUserNode = rootNode.get("validUser");
            if (validUserNode != null) {
                return mapper.treeToValue(validUserNode, Credentials.class);
            }
            // Fallback: try to read directly from root
            return mapper.readValue(path.toFile(), Credentials.class);
        } catch (IOException e) {
            System.err.println("Failed to read credentials: " + e.getMessage());
            e.printStackTrace();
            return new Credentials();
        }
    }
}