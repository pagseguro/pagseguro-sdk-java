package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public final class Driver {
	
	private static WebDriver driver;
	

	public static WebDriver getInstance() {
		if (driver == null) {
			System.setProperty(
					"webdriver.chrome.driver"
					, "src/test/resources/driver/win32/chromedriver.exe");
			
//			DesiredCapabilities phantomjs = new DesiredCapabilities();
//			phantomjs.setJavascriptEnabled(true);
//			System.getProperty("phantomjs.binary.path");
//			phantomjs.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "src/test/resources/drivers/win32/bin/phantomjs.exe");
////			
//			Capabilities caps = new DesiredCapabilities();
//            ((DesiredCapabilities) caps).setJavascriptEnabled(true);                
//            ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);  
//            ((DesiredCapabilities) caps).setCapability(
//                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                    "src/test/resources/drivers/linux32/phantomjs"
//                    
//                );
//            
//            driver = new PhantomJSDriver(caps);
//			
//;				
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

}
