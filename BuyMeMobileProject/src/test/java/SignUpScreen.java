import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SignUpScreen {

    public static void clickRegisterButton(AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/skipButton")).click();
    }

    public static void clickGoogleButton(AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/googleButton")).click();
    }

    public static void chooseGoogleAccount(AndroidDriver driver) {
        driver.findElement(By.id("com.google.android.gms:id/container")).click();
    }

}
