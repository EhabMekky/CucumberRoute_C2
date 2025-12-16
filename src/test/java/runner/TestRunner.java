package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG-based Cucumber test runner.
 * Features: src/test/resources/feature
 * Glue: stepDefinitions package (where your StepDefinition and Hooks live)
 */
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}