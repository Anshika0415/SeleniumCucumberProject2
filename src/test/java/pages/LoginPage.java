package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSignupLogin() {
        driver.findElement(By.linkText("Signup / Login")).click();
    }
    public void enterEmailAndPassword(String email, String password) {
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.clear();
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }
    public boolean isLoggedIn(String username) {
        try {
            return driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]/b")).getText().contains(username);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isLoginErrorVisible() {
        try {
            return driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("login");
    }
    public String getLoggedInAsText() {
        return driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]")).getText();
    }
}
