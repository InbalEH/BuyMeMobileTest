import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class SettingScreen {

    public static void pressSettings(AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/profileTab")).click();
    }

    public static void pressAboutBuyMe(AndroidDriver driver) {
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"על BUYME\")").click();
    }

    public static void printAboutText(AndroidDriver driver) {
        System.out.println(driver.findElement(By.id("il.co.mintapp.buyme:id/titleText")).getText());
        System.out.println(driver.findElement(By.id("il.co.mintapp.buyme:id/contentText")).getText());
    }

}
