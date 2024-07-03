
package apTests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
///

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts();

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org");

        String url = driver.getCurrentUrl();
        if(url.contains("wikipedia")){
            System.out.println("Start Test case 1: Passed");
        }else{
            System.out.println("Start Test case 1: Failed");
        }

        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.wikipedia.org");

        Thread.sleep(2000);

       String headerText = driver.findElement(By.xpath("//h1/span")).getText();
        if (headerText.equals("Wikipedia")) {
            System.out.println("Header text is 'Wikipedia': PASS");
        } else {
            System.out.println("Header text is not 'Wikipedia': FAIL");
        }

        WebElement termsOfUseFound = driver.findElement(By.xpath("//small[@class='jsl10n' and @data-jsl10n='terms']/a"));
        WebElement privacyPolicyFound = driver.findElement(By.xpath("//small[@class='jsl10n' and @data-jsl10n='privacy-policy']/a"));

        if (termsOfUseFound.getText().contains("Terms of Use") && privacyPolicyFound.getText().contains("Privacy Policy")) {
            System.out.println("Footer links 'Terms of Use' and 'Privacy policy' found: PASS");
        } else {
            System.out.println("Footer links not found: FAIL");
        }
    

         System.out.println("end Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.wikipedia.org");

        Thread.sleep(2000);
        driver.findElement(By.id("searchInput")).sendKeys("apple");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='suggestions-dropdown']/a[2]")).click();
      

        Thread.sleep(1000);
        WebElement steveJobsFound = driver.findElement(By.xpath("//th[text()='Founders']/following-sibling::td//a[text()='Steve Jobs']"));

        if (steveJobsFound.getText().contains("Steve Jobs")) {
            System.out.println("'Steve Jobs' listed as founder: PASS");
        } else {
            System.out.println("'Steve Jobs' not found: FAIL");
        }

        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.wikipedia.org");
//th[text()='Founders']/following-sibling::td//a[text()='Bill Gates']
        Thread.sleep(2000);
        driver.findElement(By.id("searchInput")).sendKeys("microsoft");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='typeahead-suggestions']//a")).click();
       
        Thread.sleep(1000);
        WebElement billgatesfound = driver.findElement(By.xpath("//th[text()='Founders']/following-sibling::td//a[text()='Bill Gates']"));

        if (billgatesfound.isDisplayed()) {
            billgatesfound.click();
        } else {
            System.out.println("'Bill Gates' not found: FAIL");
        }

        String url = driver.getCurrentUrl();
        if(url.contains("Bill_Gates")){
            System.out.println("opened URL contains \"Bill_Gates\"");
        }else{
            System.out.println("opened URL does not contains \"Bill_Gates\"");
        }

        System.out.println("end Test case: testCase04");
    }

    public void testCase05() throws InterruptedException {
        System.out.println("Start Test case: testCase05");
        driver.get("https://en.wikipedia.org/");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='vector-main-menu-dropdown']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='p-navigation']//li[@id='n-aboutsite']//a")).click();

        String url = driver.getCurrentUrl();
        if(url.contains("About")){
            System.out.println("opened URL contains \"About\"");
        }else{
            System.out.println("opened URL does not contains \\\"About\\\"");
        }

        System.out.println("end Test case: testCase05");
    }
   
}

