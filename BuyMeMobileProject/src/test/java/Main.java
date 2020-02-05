import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {

    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test = extent.createTest(ReportConstants.testName, ReportConstants.testDescription);

    private static AndroidDriver<MobileElement> driver;

    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File(Constants.xmlPathName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    @BeforeClass
    public static void setUp() throws IOException, ParserConfigurationException, SAXException {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getData(ReportConstants.reportPath));
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo(ReportConstants.sysInfoK,ReportConstants.sysInfoV);
        test.log(Status.INFO, ReportConstants.beforeClassInfo);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.platformName);

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.deviceName);

        capabilities.setCapability(Constants.appPackage, getData(Constants.appPackage));
        capabilities.setCapability(Constants.appActivity, getData(Constants.appActivity));

        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        driver = new AndroidDriver(new URL(Constants.appiumURL), capabilities);

        //Clears app data
        driver.resetApp();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test01_signUp() throws IOException {

        //Implicit wait
        Constants.implicitWait(driver);
        test.log(Status.INFO, ReportConstants.testOneInfo);

        //Welcome Screen screenshot
        test.pass(ReportConstants.welcomeScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.welcomeScreenPath + ReportConstants.currentTime)).build());

        //Click on "Registration" button in welcome screen
        SignUpScreen.clickRegisterButton(driver);

        //Choose to sign up using google
        SignUpScreen.clickGoogleButton(driver);

        //Registration Screen screenshot
        test.pass(ReportConstants.registrationScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.registrationScreenPath + ReportConstants.currentTime)).build());

        //Choose the google account you want to sign up with
        SignUpScreen.chooseGoogleAccount(driver);

        try {
            //Check if proceeded to main screen
            GiftChoiceScreen.bottomBar(driver);
            test.log(Status.PASS, ReportConstants.signUpPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.signUpFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.signUpFailPath + ReportConstants.currentTime)).build());
        }
    }

    @Test
    public void test02_searchGift() throws IOException, InterruptedException {
        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testTwoInfo);

        //Main (Category) Screen screenshot
        test.pass(ReportConstants.categoryScreenScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.categoryScreenPath + ReportConstants.currentTime)).build());

        //Swipe right 3 times to reach "Breakfast and coffee shop" gift card
        Constants.swipeRight(driver);
        Constants.swipeRight(driver);
        Constants.swipeRight(driver);

        //Choose third business from list
        GiftChoiceScreen.chooseThirdBusiness(driver);

        //Business Screen screenshot
        test.pass(ReportConstants.businessScreenScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.businessScreenPath + ReportConstants.currentTime)).build());

        //Scroll to bottom of page
        Constants.scrollDown(driver);
        Constants.scrollDown(driver);

        //Choose the last gift on screen (most expensive)
        GiftChoiceScreen.lastGift(driver);

        try {
            //Check if proceeded to "Sender info" screen
            SenderInfoScreen.giftForSomeoneElse(driver);
            test.log(Status.PASS, ReportConstants.giftChoicePass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.giftChoiceFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.giftChoiceFailPath + ReportConstants.currentTime)).build());
        }
    }

    @Test
    public void test03_enterInfo() throws IOException {
        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testThreeInfo);

        //Scroll down by clicking scroll button
        SenderInfoScreen.scrollDown(driver);

        //Sender/Receiver Info Screen screenshot
        test.pass(ReportConstants.infoScreenScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.infoScreenPath + ReportConstants.currentTime)).build());

        //Enter receiver name
        SenderInfoScreen.enterReceiver(driver);

        //Enter blessing
        SenderInfoScreen.enterBlessing(driver);

        //Enter sender name
        SenderInfoScreen.enterSender(driver);

        //Press continue button
        SenderInfoScreen.pressContinue(driver);

        try {
            //Check if proceeded to "How to send" screen
            HowToSendScreen.giftImage(driver);
            test.log(Status.PASS, ReportConstants.enterInfoPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.enterInfoFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.enterInfoFailPath + ReportConstants.currentTime)).build());
        }
    }

    @Test
    public void test04_sendGift() throws IOException {
        //Implicit wait
        Constants.implicitWait(driver);

        test.log(Status.INFO, ReportConstants.testFourInfo);

        //"How to Send" Screen screenshot
        test.pass(ReportConstants.howToSendScreenScrnSht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.howToSendScreenPath + ReportConstants.currentTime)).build());

        //Click on "send after payment" radio button
        HowToSendScreen.sendNowRadioButton(driver);

        //Click on email sending option
        HowToSendScreen.sendByEmail(driver);

        //Enter email
        HowToSendScreen.enterEmail(driver);

        //Press continue button
        HowToSendScreen.pressContinue(driver);

        try {
            //Check if proceeded to "Payment" screen
            HowToSendScreen.paymentTitle(driver);
            test.log(Status.PASS, ReportConstants.howToSendPass);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, ReportConstants.howToSendFail);
            test.fail(ReportConstants.scrnsht, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.imageFolderPath + ReportConstants.howToSendFailPath + ReportConstants.currentTime)).build());
        }
    }

    @Test
    public void test05_settingsScreen() throws InterruptedException {
        //Implicit wait
        Constants.implicitWait(driver);

        //Return to main page (where the bottom bar appears)
        Constants.goBack(driver);
        Constants.goBack(driver);
        Constants.goBack(driver);
        Constants.goBack(driver);

        //Press on "settings" button
        SettingScreen.pressSettings(driver);

        //Press on "About Buy Me"
        SettingScreen.pressAboutBuyMe(driver);

        //Print text in "About Buy Me" Page
        SettingScreen.printAboutText(driver);
    }


    @AfterClass
    public static void tearDown(){
        test.log(Status.INFO, ReportConstants.afterClassInfo);
        extent.flush();
    }

    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + Constants.pngImgType);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + Constants.pngImgType;
    }

}
