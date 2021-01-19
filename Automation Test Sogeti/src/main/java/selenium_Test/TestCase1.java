package selenium_Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/* Test Case 1
 *   @ author Ruth Pamen
 *   Datum 19.01.2021
 */
public class TestCase1 {
    WebDriver driver;

    @BeforeClass
    public void SetUp() {

        //calling Chrome Driver on my local device
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SMI-HW-108\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //create instance of Chrome Web Driver
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //wait for the element before the exception occurs
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        //after it maximise the Page
        driver.manage().window().maximize();
        System.out.println("assert that Chrome Driver works fine, and the page has been maximized");


        //Open Sogeti Home Page
        driver.get("https://www.sogeti.com");

        //Allow the cookie consent by clicking on the Button
        driver.findElement(By.xpath("//*[@id=\"CookieConsent\"]/div[1]/div/div[2]/div[2]/button[1]")).click();


        //Navigate over Services
        WebElement services = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/nav/ul/li[3]/div/span"));
        Actions act = new Actions(driver);
        act.moveToElement(services).perform();

        //click on the Automation subtitle
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[7]/a")).click();



    }

    /* Hier I tested two differents elements to make sure that the Automation Screen was displayed
     */
    @Test
    void validateUrlAutomationPageTest() {
        //check if the redirect Automation Url is the correct one
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.sogeti.com/services/automation/");
    }
    @Test
    void screenDisplayedTest() {
        //check if the Image Screen of Automation Page is displayed
        WebElement headingVisible = driver.findElement(By.xpath("//*[@id=\"primary_content\"]/div/div[2]/div/h1/span"));
        Assert.assertTrue(headingVisible.isDisplayed());

    }

    @Test
    void automationTextTest(){
        //check if the Automation text is visible
        boolean headingVisible = driver.findElement(By.xpath("//*[@id=\"primary_content\"]/div/div[2]/div/h1/span")).isDisplayed();
        Assert.assertTrue(headingVisible);

    }
    @Test
    void navigateBackOverServicesTest() {
        //Navigate back over Services
        WebElement over = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/nav/ul/li[3]/div/span"));
        Actions overAgain = new Actions(driver);
        overAgain.moveToElement(over).perform();
    }

    @Test
    void servicesSelectedTest() {

        //check is servives is selected
        boolean isServicesSelected = driver.findElement(By.className("selected has-children  expanded level2 hover")).isSelected();
        Assert.assertTrue(isServicesSelected);

    }

    @Test
    void automationSelectedTest() {
        //check is automation is selected
        boolean isAutomationSelected = driver.findElement(By.className("selected  current expanded")).isSelected();
        Assert.assertTrue(isAutomationSelected);
    }



}