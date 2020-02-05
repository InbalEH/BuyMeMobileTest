import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;


public class GiftChoiceScreen {

    public static void chooseThirdBusiness (AndroidDriver driver) {
        List<MobileElement> businessList = driver.findElements(By.id("il.co.mintapp.buyme:id/businessImage"));
        businessList.get(2).click();
    }

    //This method chooses the last element from the list created
    public static void lastGift (AndroidDriver driver) {
        List<MobileElement> chooseGift = driver.findElements(By.id("il.co.mintapp.buyme:id/purchaseButton"));
        chooseGift.get(chooseGift.size() - 1).click();
    }


    public static void bottomBar (AndroidDriver driver) {
        driver.findElement(By.id("il.co.mintapp.buyme:id/bb_bottom_bar_icon"));
    }

}
