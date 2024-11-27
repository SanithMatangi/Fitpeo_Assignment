package fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class Fitpeo_Testing {
	public WebDriver driver;
	@BeforeClass
	public void setUp() throws InterruptedException {
		//initialize browser and navigate to fitpeo Homepage
		driver = new ChromeDriver();
		driver.get("https://www.fitpeo.com/home");
		driver.manage().window().maximize();
		Thread.sleep(3000); //T wait some time
		
		
		//navigate to Revenue Calculator
		driver.findElement(By.linkText("Revenue Calculator")).click();
		Thread.sleep(4000);	
		
	}

}
