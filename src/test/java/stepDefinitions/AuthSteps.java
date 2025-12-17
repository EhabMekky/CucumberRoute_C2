package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LandingPage;
import utils.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AuthSteps {

        private final LandingPage landingPage = new LandingPage();

        @Given("I go to the website")
        public void i_go_to_the_website() {
            Assert.assertNotNull(DriverManager.getDriver());
        }

        @When("I click on the Sign in button")
        public void i_click_on_the_sign_in_button() {
            landingPage.clickLoginMenu();
            landingPage.openLoginPopup();
        }

        @And("I enter my login credentials")
        public void i_enter_my_login_credentials() {
            Credentials creds = readCredentialsFromJson("loginData.json");
            System.out.println("Read credentials - Username: " + creds.username + ", Password: " + creds.password);
            landingPage.enterUsername(creds.username);
            landingPage.enterPassword(creds.password);
            landingPage.setRememberMe(creds.rememberMe);
            landingPage.submitLogin();
        }

        @Then("I should be logged in successfully")
        public void i_should_be_logged_in_successfully() {
            Assert.assertTrue(landingPage.waitForWelcomeText(), "Welcome Text is displayed after login");
        }

        public static class Credentials {
            public String username = "";
            public String password = "";
            public boolean rememberMe = false;
        }

        private static class LoginData {
            public String URL = "";
            public Credentials validUser = new Credentials();
        }

        private Credentials readCredentialsFromJson(String fileName) {
            Path path = Paths.get("src", "test", "resources", "testData", fileName);
            System.out.println("Reading from: " + path.toAbsolutePath());
            System.out.println("File exists: " + path.toFile().exists());

            try {
                String content = Files.readString(path);
                System.out.println("JSON content: " + content);

                ObjectMapper mapper = new ObjectMapper();
                LoginData loginData = mapper.readValue(path.toFile(), LoginData.class);

                System.out.println("Parsed username: " + loginData.validUser.username);
                return loginData.validUser;
            } catch (IOException e) {
                System.err.println("Failed to read credentials: " + e.getMessage());
                e.printStackTrace();
                return new Credentials();
            }
        }
}
