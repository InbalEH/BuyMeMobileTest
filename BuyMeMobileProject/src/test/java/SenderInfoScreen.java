import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SenderInfoScreen {

    public static void scrollDown (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/scrollArrows")).click();
    }

    public static void enterReceiver (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/toEditText")).sendKeys(Constants.receiverName);
    }

    public static void enterBlessing (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/blessEditText")).sendKeys(Constants.blessing);
    }

    public static void enterSender (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/userFrom")).sendKeys(Constants.senderName);
    }

    public static void pressContinue (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
    }

    public static void giftForSomeoneElse (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/someOneElseButton"));
    }





}
