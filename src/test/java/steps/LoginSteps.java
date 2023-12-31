package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

import java.time.Duration;

public class LoginSteps extends CommonMethods {


    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndNavigateToURL();

    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {

        DOMConfigurator.configure("Log4j.xml");
        Log.startTestCase("My batch 16 test starts here");
        sendText(ConfigReader.getPropertyValue("username"), loginPage.usernameField);
        Log.info("my username has been entered");
        sendText(ConfigReader.getPropertyValue("password"), loginPage.passwordField);
        Log.info("My password has been entered");
    }

    @When("user click on login button")
    public void user_click_on_login_button() {

        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("Logged in");
    }

    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {


        sendText("dalima123", loginPage.usernameField);
        sendText("Hum@nhrm123", loginPage.passwordField);
    }

    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {


        sendText("123", loginPage.usernameField);
        sendText("Hum@nhrm123", loginPage.passwordField);
    }

    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed");
    }

    @When("user enters {string} and {string} and verifying the {string} for the combination")
    public void user_enters_and_and_verifying_the_for_the_combination
            (String username, String password, String errorMessageExpected) {
        sendText(username, loginPage.usernameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);

        String errorMessageActual = loginPage.errorMessageField.getText();
        Assert.assertEquals("value does not match",errorMessageExpected, errorMessageActual);


    }

}
