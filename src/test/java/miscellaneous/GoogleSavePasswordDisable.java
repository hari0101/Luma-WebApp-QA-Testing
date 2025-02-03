package miscellaneous;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSavePasswordDisable {

	public static void main(String[] args) {
		
		Map<String, Boolean> optionmap = new ConcurrentHashMap<>(); 
		optionmap.put("credentials_enable_service", false);
		
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", optionmap);
		
		WebDriver driver = new ChromeDriver(option);
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a")).click();
		driver.findElement(By.id("email")).sendKeys("jessiemagento27@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Magento890@!#");
		driver.findElement(By.id("send2")).click();
		
	}
}
