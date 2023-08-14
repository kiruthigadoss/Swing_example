package SeleniumExample;

import java.time.Duration;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Opengoogle {

	/**
	 * @param args
	 */
	public static WebDriver driver;

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		// Enter into Swing login page
		System.setProperty("webdriver.gecko.driver", "C://Users//kmurugadoss//Documents//geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.get("http://dew1lxmttdev02.dni.dev.internal:8443/swing/login.html\r\n" + "");

		driver.get("http://dew1lxmttdev01.dni.dev.internal:8443/swing/login.html");
		// Maximize window
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		// Enter the user name
		WebElement usernameone = driver.findElement(By.name("username"));
		usernameone.sendKeys("kmurugadoss");

		// Enter Password and it is decoded here
		String decode = new String(Base64.getDecoder().decode("S2VlcnRoaWsxMTIyKg==".getBytes()));
		WebElement passwordone = driver.findElement(By.name("password"));
		passwordone.sendKeys(decode);

		// clicking on Login button
		WebElement loginbuttonone = driver.findElement(By.id("loginButton"));
		loginbuttonone.click();

		// clicking on quickcreatestory
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-edit"))).click();

		WebElement quickcreatestorybutton = driver.findElement(By.cssSelector(".icon-edit"));
		highlightElement(quickcreatestorybutton);
		quickcreatestorybutton.click();

	}

//Highlight selected item
	public static void highlightElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					element);
			Thread.sleep(1000);
			js.executeScript("arguments[0].setAttribute('style', 'none');", element);
		} catch (Exception e) {

			System.err.println("There is an error while highlighting an element");

		}

		//String mainwindow = driver.getWindowHandle();
		//Set<String> s1 = driver.getWindowHandles();
		//Iterator<String> i1 = s1.iterator();

		//while (i1.hasNext()) {
			//String ChildWindow = i1.next();
			//if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				//driver.switchTo().window(ChildWindow);
				// WebElement text = driver.findElement(By.id("sampleHeading"));
				//String pagetitle=driver.getTitle();
				//System.out.println("Heading of child window is:"+pagetitle);
				//driver.close();
				//System.out.println("Child window closed");
			//}
		//}

		// Switch back to the main window which is the parent window.
		//driver.switchTo().window(mainwindow);
		//driver.quit();
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);

		System.out.println(driver.switchTo().window(child_window).getTitle());

		driver.close();
		}

		}
		//switch to the parent window
		driver.switchTo().window(parent);

		
	}
	

}

