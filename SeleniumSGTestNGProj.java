package TESTNGSTUDY;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SeleniumSGTestNGProj {

	static WebDriver driver;

//	public static void main(String[] args) throws InterruptedException, IOException {
//		// TODO Auto-generated method stub
//		LaunchBrowser();
//		SignIn("username","password");
//		women();
//		addTocardAndCompareprice();
//		proceedAndComfirm();
//	}

	@BeforeTest
	public static void LaunchBrowser() {
//		System.setProperty("webdriver.chrome.driver","E:\\chromedriver_win32\\chromedriver.exe");
//		driver=new ChromeDriver();
		
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
    public  void startb() {
	driver.get("http://automationpractice.com//index.php");
	}
	
	@Test(priority=2)
	@Parameters({"username","password"})
	public static void SignIn(String username,String Password) throws IOException {
		driver.findElement(By.xpath("//a[normalize-space()='Sign in']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(Password);
		driver.findElement(By.xpath("//span[normalize-space()='Sign in']")).click();
	}
	@Test(priority=3)
	public static void women() {
		//click on women tab
		driver.findElement(By.xpath("//a[@title='Women']")).click();
		//click on sortBy and select lowest price
		WebElement sortBy=driver.findElement(By.xpath("//select[@id='selectProductSort']"));
		
		Select select=new Select(sortBy);
		select.selectByIndex(1);
		
	}@Test(priority=4)
	public static void addTocardAndCompareprice() throws InterruptedException {
		
		driver.findElement(By.xpath("//h5[@itemprop='name']//a[@title='Printed Chiffon Dress'][normalize-space()='Printed Chiffon Dress']")).click();
		
		Thread.sleep(5000);
		WebElement price1 = driver.findElement(By.xpath("//p[@class='our_price_display']"));
		String unitprice1=price1.getText();
		System.out.println(unitprice1);
		
		//add to cart
		driver.findElement(By.xpath("//span[normalize-space()='Add to cart']")).click();
		Thread.sleep(5000);
		//proceed1
		WebElement proceed=driver.findElement(By.xpath("//span[normalize-space()='Proceed to checkout']"));
		proceed.click();
		
		//get unit price
		WebElement price2 = driver.findElement(By.xpath("//span[@class='price special-price']"));
		String unitprice2=price2.getText();
		System.out.println(unitprice2);
		
		//compare price
		if(unitprice1.contentEquals(unitprice2)) {
			   System.out.println("both price match");  	 
		}
		else {
			System.out.println("unit price does not match");
		}
		//proceed2
		driver.findElement(By.xpath("(//span[contains(text(),'Proceed to checkout')])[2]")).click();
		
	}
	@Test(priority=5)
	public static void proceedAndComfirm() throws InterruptedException {
		
		//proceed3
		WebElement proceed3=driver.findElement(By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]"));
		proceed3.click();
		
		
		//select Checkbox and proceed
		Thread.sleep(4000);
		driver.findElement(By.xpath("//label[contains(text(),'I agree to the terms of service and will adhere to')]")).click();

		WebElement proceed4 = driver.findElement(By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]"));
		proceed4.click();
		
		//select pay by bank wire and  order
		driver.findElement(By.xpath("//a[@title='Pay by bank wire']//span[contains(text(),'(order processing will be longer)')]")).click();
			
		WebElement order= driver.findElement(By.xpath("//span[normalize-space()='I confirm my order']"));
		order.click();
			
		WebElement completeOrder=driver.findElement(By.xpath("//strong[normalize-space()='Your order on My Store is complete.']"));
		System.out.println(completeOrder.getText());
		

	}
	
}
