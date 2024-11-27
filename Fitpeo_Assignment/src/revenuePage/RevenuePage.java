package revenuePage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fitpeo.Fitpeo_Testing;

public class RevenuePage extends Fitpeo_Testing{
	JavascriptExecutor js;
	@BeforeMethod
	public void sliderSection() throws InterruptedException {
		// Scroll down to slider Section
		js= ((JavascriptExecutor)driver);
		WebElement Ele = driver.findElement(By.xpath("//div[@class = 'MuiBox-root css-79elbk']"));
		js.executeScript("arguments[0].scrollIntoView();", Ele);
		Thread.sleep(2000);
	}
	@Test
	public void setValue() throws InterruptedException, Exception{
		//setting slider Value
		WebElement slider = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/span[3]"));
		WebElement textBox = driver.findElement(By.cssSelector("input[id=':r0:']"));
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		Thread.sleep(2000);
		action.dragAndDropBy(slider,-50, 0).build().perform();
		//setting values in Value box
		textBox.click();
		textBox.sendKeys(Keys.CONTROL, "a");
		textBox.sendKeys(Keys.BACK_SPACE);
		textBox.sendKeys("820");
		Thread.sleep(2000);
		action.dragAndDropBy(slider,-150, 0).build().perform();
		textBox.sendKeys(Keys.CONTROL, "a");
		textBox.sendKeys(Keys.BACK_SPACE);
		textBox.sendKeys("560");
		Thread.sleep(2000);
		//Validating slider value and editbox value
		String sliderValueAfterEnterValue =  driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/span[3]/input")).getAttribute("aria-valuenow");

		String textBoxBySliderValue = textBox.getAttribute("value");

		System.out.println("Slider current Value is " + sliderValueAfterEnterValue);
		System.out.println("ValueBox current Value is " + textBoxBySliderValue);

		Assert.assertEquals(textBoxBySliderValue, sliderValueAfterEnterValue);
		//setting again 820 to get final value
		textBox.sendKeys(Keys.CONTROL, "a");
		textBox.sendKeys(Keys.BACK_SPACE);
		textBox.sendKeys("820");
		Thread.sleep(2000);
	}
	@AfterMethod
	public void checkboxClick() throws InterruptedException {
		
		ArrayList<String> cptList = new ArrayList<>();

		cptList.add("CPT-99091");
		cptList.add("CPT-99453");
		cptList.add("CPT-99454");
		cptList.add("CPT-99474");

		for (int i = 1; i <= 14; i++) {
			//Dynamic xpath 
			WebElement takeTextCPT = driver.findElement(By.xpath(" //div[@class='MuiBox-root css-1eynrej']["+i+ "]/p[contains(text(),'CPT')]"));

			// Scroll the page based on the Element
			js= ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].scrollIntoView();", takeTextCPT);
			String cptValueStore = takeTextCPT.getText();

			Thread.sleep(2000);
		
			// Compare the Value
			if (cptList.contains(cptValueStore)) {
				//Dynamic xpath 
				WebElement checkbox = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1eynrej'][" + i + "]//label//span//input[@type='checkbox']"));
				checkbox.click();
			}
		}
	}
	@AfterClass
	public void totalReimbursementValidation() {
		//Validating Final value is 110700 or not
		String actualRcurringAmount = "110700";
		WebElement reccuringAmount = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/p[4]/p")); 
		String amount = reccuringAmount.getText().substring(1);
		
		System.out.println("Total Recurring Reimbursement Amount is: "+amount);
		
		Assert.assertEquals(amount, actualRcurringAmount);
	}
}
