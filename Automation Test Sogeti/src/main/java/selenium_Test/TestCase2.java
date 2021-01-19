package selenium_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/* Test Case 2
 *   @ author Ruth Pamen
 *   Datum 19.01.2021
 */
public class TestCase2 {
    WebDriver driver;

    @Test
    void SetUp() {

        //calling Chrome Driver on my local device
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SMI-HW-108\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //create instance of Chrome Web Driver
        driver = new ChromeDriver();

        //Open Sogeti Home Page
        driver.get("https://www.sogeti.com");

        //after it maximise the Page
        driver.manage().window().maximize();
        System.out.println("assert that Chrome Driver works fine, and the page has been maximized");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //wait for the element before the exception occurs
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is" + currentUrl);

        //Allow the cookie consent by clicking on the Button
        driver.findElement(By.xpath("//*[@id=\"CookieConsent\"]/div[1]/div/div[2]/div[2]/button[1]")).click();

        //Navigate over Services
        WebElement services = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/nav/ul/li[3]/div/span"));
        Actions act = new Actions(driver);
        act.moveToElement(services).perform();

        //here click the Automation link
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[5]/ul/li[7]/a")).click();


        /*
         *  here scroll down to the Contact us Form using the JavaScriptExecutor
         * find the form contact element
         */

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement scrollToContact = driver.findElement(By.xpath("//*[@id=\"left-column\"]/div/div[3]/div[2]/div[6]/div[1]/div/h2"));
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollToContact);


    }


        @Test
        void fieldContactFormTest() {
            /*
             * Fill the First Name, Last Name, Email, Phone and Message fields with Random Generated Data.
             * find the ID of the first Name text field
             * find the ID of the Last Name text field
             * find the ID of the Email field
             * find the ID of Phone field
             * find the ID of Country field
             * find the ID of the Message field
             */

            WebElement firstname = driver.findElement(By.id("4ff2ed4d-4861-4914-86eb-87dfa65876d8"));
            firstname.sendKeys("ruth-test");
            System.out.println("First Name inserted");

            WebElement lastname = driver.findElement(By.id("11ce8b49-5298-491a-aebe-d0900d6f49a7"));
            lastname.sendKeys("pamen-Test");
            System.out.println("Last Name inserted");

            WebElement email = driver.findElement(By.id("056d8435-4d06-44f3-896a-d7b0bf4d37b2"));
            email.sendKeys("ruth-testautomation@example.com");
            System.out.println("E-mail inserted");

            WebElement phone = driver.findElement(By.id("755aa064-7be2-432b-b8a2-805b5f4f9384"));
            phone.sendKeys("017676358737");
            System.out.println("Phone Number inserted");

            WebElement country = driver.findElement(By.xpath("//select[@id='e74d82fb-949d-40e5-8fd2-4a876319c45a']"));
            Select co = new Select(country);
            co.selectByVisibleText("Germany");
            System.out.println("Germany as been selected");


            WebElement message = driver.findElement(By.id("88459d00-b812-459a-99e4-5dc6eff2aa19"));
            message.sendKeys("test the message field with Random Generated Data");
            System.out.println("Message inserted");


        }

    /*
     * check I agree checkbox
     */

    @Test
    void checkboxTest() {
        WebElement radiobutton = driver.findElement(By.xpath("//*[@id=\"863a18ee-d748-4591-bb64-ef6eae65910e\"]/fieldset/label/input"));
        radiobutton.click();
    }

    /*
     * click the Submit Button
     */
    @Test
    void setSubmitButtonTest() {
        WebElement submitButton = driver.findElement(By.id("06838eea-8980-4305-83d0-42236fb4d528"));
        submitButton.click();

    }

    /*
     * Assert the thank you page is displayed
     */
    @Test
    void assertThanksPageTest() {

        String thanks_message = driver.findElement(By.className("Form__Status__Message Form__Success__Message")).getText();;
        Assert.assertEquals(thanks_message, "Thanks for your Information");


    }

}


