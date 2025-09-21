package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickContinue() {
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
    }
    public String getAccountCreatedMessage() {
        return driver.findElement(By.xpath("//b")).getText();
    }
    public boolean isLoggedInAs(String username) {
        return driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]")).getText().contains(username);
    }
}