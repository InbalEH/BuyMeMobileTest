import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;

public class HowToSendScreen {

    public static void sendNowRadioButton (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/nowRadioButton")).click();
    }

    public static void sendByEmail (AndroidDriver driver) {
        List<MobileElement> businessList = driver.findElements(By.className("android.widget.CheckBox"));
        businessList.get(2).click();
    }

    public static void enterEmail (AndroidDriver driver) {
        driver.findElement(By.className("android.widget.EditText")).sendKeys(Constants.receiverEmail);
    }

    public static void pressContinue (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/goNextButton")).click();
    }

    public static void giftImage (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/giftImage"));
    }

    public static void paymentTitle (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/paymentTitle"));
    }


}
