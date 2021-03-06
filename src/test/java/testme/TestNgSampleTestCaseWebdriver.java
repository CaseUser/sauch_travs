package testme;



import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
/**
 * Test class generated by the Selenium IDE
 *
 */
public class TestNgSampleTestCaseWebdriver {
	//------------------------------
	
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
    protected String browser="chrome";
    
    protected String os="Windows 8.1";
    
    protected String version ="46";
    
    protected String sessionId;

    protected String baseUrl;
    protected WebDriver driver;
	//-------------------------------
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest
	public void setUp() throws Exception {
		//driver = new FirefoxDriver();
		DesiredCapabilities capabilities= DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);

        capabilities.setCapability(CapabilityType.PLATFORM, os);
		
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		
		this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

		String message = String.format("SauceOnDemandSessionID=%1$s", this.sessionId);
	    System.out.println(message);
		baseUrl = "http://eherrera.net/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testSampleTestCaseWebdriver() throws Exception {
		driver.get(baseUrl + "/markdowntutorial/tutorial/emphasis.html");
		driver.findElement(By.cssSelector("button.button-primary.button-next")).click();
		try {
			Assert.assertTrue(isElementPresent(By.id("btn_answer_1-1")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("editor_1-1")).clear();
		driver.findElement(By.id("editor_1-1")).sendKeys("The music video for Rihanna's song **American Oxygen** depicts various moments from American history, including the inauguration of Barack Obama.");
		// ERROR: Caught exception [ERROR: Unsupported command [fireEvent | id=editor_1-1 | keyup]]
		try {
			Assert.assertTrue(isElementPresent(By.xpath("//body/div[5]")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}