package selenium_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

/* Test Case 3
 *   @ author Ruth Pamen
 *   Datum 19.01.2021
 */
public class TestCase3 {
    WebDriver driver;

    @Test
    void SetUp() {


        //calling Chrome Driver on my local device
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SMI-HW-108\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //create instance of Chrome Web Driver
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //wait for the element before the exception occurs
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Open Sogeti Home Page
        driver.get("https://www.sogeti.com");

        //after it maximise the Page
        driver.manage().window().maximize();
        System.out.println("assert that Chrome Driver works fine, and the page has been maximized");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is" + currentUrl);

        //Allow the cookie consent by clicking on the Button
        driver.findElement(By.xpath("//*[@id=\"CookieConsent\"]/div[1]/div/div[2]/div[2]/button[1]")).click();

    }

    /*
     * click on the Worldwide Dropdown
     * identify all links under the Worldwide Dropdown Element .
     * Get href of anchor tag and store it in url variable.
     * Check if URL is null or Empty and skip the remaining steps if the condition is satisfied.
     * send HTTP request and capture HTTP response code with the HttpURLConnection class.
     */
        @Test
        void checkWorkinglinksTest()
            {

            WebElement country_dropdown = driver.findElement(By.xpath("//div[@class='navbar-global']"));
            country_dropdown.click();


            List<WebElement> links = driver.findElements(By.xpath("//div[@class='country-list']/ul/li/a"));

            for (WebElement we : links) {

                String url = we.getAttribute("href");
                System.out.println(we.getText() + " " + url);

                if (url == null || url.isEmpty()) {
                    System.out.println("URL is either not configured for anchor tag or it is empty");
                    continue;
                }

                try {
                    HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

                    huc.setRequestMethod("HEAD");

                    huc.connect();

                    int respCode = huc.getResponseCode();

                    if (respCode >= 400) {
                        Assert.assertTrue(respCode >= 400);
                        System.out.println(url + " is a broken link");
                    } else {
                        Assert.assertFalse(respCode >= 400);

                        System.out.println(url + " is a valid link");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
        }






        }







