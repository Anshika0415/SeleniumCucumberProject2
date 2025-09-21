package stepdef;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.Scenario;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import pages.LoginPage;
import pages.HomePage;
import pages.SignupPage;
import commonutil.ScreenshotUtil;

// ===== Extent Report Utility (moved from ReportUtil.java) =====
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class StepDefinition {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SignupPage signupPage;

    // Only keep the essential Extent Report fields
    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @BeforeAll
    public static void beforeAllScenarios() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Cucumber Selenium Execution");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterAll
    public static void afterAllScenarios() {
        if (extent != null) {
            extent.flush();
        }
    }

    @Before
    public void beforeEachScenario(Scenario scenario) {
        scenarioTest = extent.createTest(scenario.getName());
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (driver != null) {
            String ssPath = ScreenshotUtil.takeScreenshot(driver, "Step_AfterStep");
            java.io.File f = new java.io.File(System.getProperty("user.dir") + "/test-output/" + ssPath);
            System.out.println("[DEBUG] Screenshot relative path: " + ssPath);
            if (ssPath != null && f.exists()) {
                scenarioTest.addScreenCaptureFromPath(ssPath);
            } else {
                System.out.println("[DEBUG] Screenshot not found or path is null: " + ssPath);
            }
        }
    }

    @Given("I launch the Automation Exercise website")
    public void i_launch_the_automation_exercise_website() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
    }

    @When("I navigate to Signup page")
    public void i_navigate_to_signup_page() {
        loginPage.clickSignupLogin();
    }

    @When("I enter name {string} and email {string}")
    public void i_enter_name_and_email(String name, String email) {
        signupPage.enterNameAndEmail(name, email);
    }

    @When("I fill the registration form with valid details")
    public void i_fill_the_registration_form_with_valid_details() {
        signupPage.fillRegistrationForm();
    }

    @When("I submit the registration form")
    public void i_submit_the_registration_form() {
        signupPage.submitRegistration();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        String message = homePage.getAccountCreatedMessage();
        assertTrue(message.contains(expectedMessage));
    }

    @Then("I should be logged in as {string}")
    public void i_should_be_logged_in_as(String username) {
        homePage.clickContinue();
        assertTrue(homePage.isLoggedInAs(username));
    }
    @When("I enter login email {string} and password {string}")
    public void i_enter_login_email_and_password(String email, String password) {
        loginPage.enterEmailAndPassword(email, password);
    }

    @When("I click login button")
    public void i_click_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should see login result {string} as {string}")
    public void i_should_see_login_result_as(String result, String username) {
        if(result.equals("success")) {
            assertTrue(loginPage.isLoggedIn(username));
        } else if(result.equals("failure")) {
            assertTrue(loginPage.isLoginErrorVisible());
        }
    }
    
    @When("I click the logout button")
    public void i_click_the_logout_button() {
        loginPage.logout();
    }

    @Then("I should be navigated to the login page")
    public void i_should_be_navigated_to_the_login_page() {
        assertTrue(loginPage.isOnLoginPage());
    }
}
