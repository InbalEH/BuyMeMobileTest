import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Constants {

    //Capabilities
    public static final String platformName = "Android";
    public static final String deviceName = "Inbal's Device";
    public static final String appPackage = "appPackage";
    public static final String appActivity = "appActivity";
    public static final String appiumURL = "http://0.0.0.0:4723/wd/hub/";

    //Paths
    public static final String xmlPathName = "C:\\Users\\Inbal\\Desktop\\QA\\BuyMeMobileTest\\BuyMeMobile.xml";
    public static final String imageFolderPath = "C:\\Users\\Inbal\\Desktop\\QA\\BuyMeMobileTest\\";
    public static final String pngImgType = ".png";

    //SendKeys Strings
    public static final String receiverName = "שולה";
    public static final String blessing = "המון מזל טוב ואהבה";
    public static final String senderName = "ענבל";
    public static final String receiverEmail = "shula@gmail.com";


    public static void implicitWait(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void goBack(WebDriver driver) throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(2000);
    }

    public static void swipeRight (AndroidDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        TouchAction action = new TouchAction(driver);
        Duration oneHundredMillis = Duration.ofMillis(100);
        LongPressOptions longPressOptions = new LongPressOptions();
        PointOption rightPoint = new PointOption();
        rightPoint.withCoordinates(900,1000);
        PointOption leftPoint = new PointOption();
        leftPoint.withCoordinates(100,1000);
        longPressOptions.withDuration(oneHundredMillis).withPosition(leftPoint).build();
        action.longPress(longPressOptions).moveTo(rightPoint).release().perform();
    }

    public static void scrollDown (AndroidDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        TouchAction action = new TouchAction(driver);
        Duration oneHundredMillis = Duration.ofMillis(100);
        LongPressOptions longPressOptions = new LongPressOptions();
        PointOption bottomPoint = new PointOption();
        bottomPoint.withCoordinates(500,2000);
        PointOption topPoint = new PointOption();
        topPoint.withCoordinates(500,500);
        longPressOptions.withDuration(oneHundredMillis).withPosition(bottomPoint).build();
        action.longPress(longPressOptions).moveTo(topPoint).release().perform();
    }

}
