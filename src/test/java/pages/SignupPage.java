package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {
    WebDriver driver;
    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterNameAndEmail(String name, String email) {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[text()='Signup']")).click();
    }
    public void fillRegistrationForm() {
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("Password123");
        new Select(driver.findElement(By.id("days"))).selectByValue("10");
        new Select(driver.findElement(By.id("months"))).selectByValue("5");
        new Select(driver.findElement(By.id("years"))).selectByValue("1995");
        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("company")).sendKeys("TestCompany");
        driver.findElement(By.id("address1")).sendKeys("123 Main Street");
        driver.findElement(By.id("state")).sendKeys("Delhi");
        driver.findElement(By.id("city")).sendKeys("New Delhi");
        driver.findElement(By.id("zipcode")).sendKeys("110001");
        driver.findElement(By.id("mobile_number")).sendKeys("9999999999");
    }
    public void submitRegistration() {
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();
    }
}