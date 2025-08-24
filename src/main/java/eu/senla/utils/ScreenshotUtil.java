package eu.senla.utils;

import io.qameta.allure.Attachment;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@UtilityClass
public class ScreenshotUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // save to disk
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            File dest = new File("build/reports/tests/test/screenshots/" + timestamp + ".png");
            dest.getParentFile().mkdirs();
            Files.copy(screenshotFile.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotBytes;
    }
}
