package stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import pages.LandingPage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonSteps {
    private final LandingPage landingPage = new LandingPage();

    @And("I specify my credentials")
    public void i_specify_my_credentials() {
        Credentials creds = readCredentialsFromJson("loginData.json");
        landingPage.enterUsername(creds.username);
        landingPage.enterPassword(creds.password);
        landingPage.setRememberMe(creds.rememberMe);
        landingPage.submitLogin();
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
            return mapper.readValue(path.toFile(), Credentials.class);
        } catch (IOException e) {
            System.err.println("Failed to read credentials: " + e.getMessage());
            return new Credentials();
        }
    }
}
