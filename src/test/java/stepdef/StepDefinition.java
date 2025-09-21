package stepdef;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class StepDefinition {
    WebDriver driver;

    @Given("I launch the Automation Exercise website")
    public void i_launch_the_automation_exercise_website() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @When("I navigate to Signup page")
    public void i_navigate_to_signup_page() {
        driver.findElement(By.linkText("Signup / Login")).click();
    }

    @When("I enter name {string} and email {string}")
    public void i_enter_name_and_email(String name, String email) {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[text()='Signup']")).click();
    }

    @When("I fill the registration form with valid details")
    public void i_fill_the_registration_form_with_valid_details() {
        // Title
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("Password123");

        // DOB
        new Select(driver.findElement(By.id("days"))).selectByValue("10");
        new Select(driver.findElement(By.id("months"))).selectByValue("5");
        new Select(driver.findElement(By.id("years"))).selectByValue("1995");

        // Address info
        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("company")).sendKeys("TestCompany");
        driver.findElement(By.id("address1")).sendKeys("123 Main Street");
        driver.findElement(By.id("state")).sendKeys("Delhi");
        driver.findElement(By.id("city")).sendKeys("New Delhi");
        driver.findElement(By.id("zipcode")).sendKeys("110001");
        driver.findElement(By.id("mobile_number")).sendKeys("9999999999");
    }

    @When("I submit the registration form")
    public void i_submit_the_registration_form() {
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        String message = driver.findElement(By.xpath("//b")).getText();
        assertTrue(message.contains(expectedMessage));
    }

    @Then("I should be logged in as {string}")
    public void i_should_be_logged_in_as(String username) {
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        String loggedInUser = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]")).getText();
        assertTrue(loggedInUser.contains(username));
        
    }
    @When("I enter login email {string} and password {string}")
    public void i_enter_login_email_and_password(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @When("I click login button")
    public void i_click_login_button() {
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

    @Then("I should see login result {string} as {string}")
    public void i_should_see_login_result_as(String result, String username) {
        if(result.equals("success")) {
            String loggedInUser = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]/b")).getText();
            assertTrue(loggedInUser.contains(username));
        } else if(result.equals("failure")) {
            String errorMessage = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']")).getText();
            assertTrue(errorMessage.contains("incorrect"));
        }
        driver.quit();
    }
    
}
