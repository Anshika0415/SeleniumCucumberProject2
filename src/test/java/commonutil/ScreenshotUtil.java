package commonutil;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String stepName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "screenshot_" + stepName + "_AfterStep_" + System.currentTimeMillis() + "_" + timestamp + ".png";
        String dirPath = System.getProperty("user.dir") + "/test-output/screenshots/";
        String relativePath = "screenshots/" + fileName;
        String filePath = dirPath + fileName;
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            return relativePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}