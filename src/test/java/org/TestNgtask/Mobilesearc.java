package org.TestNgtask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class Mobilesearc {
	
	static WebDriver driver;
	@DataProvider (name = "mobile name")
	public Object[][] getmobile() {
		return new Object[][] {{"apple mobiles"},{"oneplus mobiles"}};
	}
	
	@BeforeClass
	private void cDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
	}
	@AfterClass
	private void quit() {
		driver.manage().window().maximize();
	}
	@BeforeMethod
	private void starttime() {
		System.out.println(java.time.LocalTime.now());
	}
	@AfterClass
	private void endtime() {
			System.out.println(java.time.LocalTime.now());
	}
	@Test(priority = 1)
	public void login() {
		driver.findElement(By.xpath("//button[text()='✕']")).click();
}
	@Test(priority = 2,dataProvider = "mobile name")
	public void mobileSearch(String name) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
		driver.findElement(By.name("q")).sendKeys(name);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		List<String> li = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		for (int i = 0; i < elements.size(); i++) {
			WebElement gett = elements.get(i);
			String text = gett.getText();
			li.add(text);
		}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			File file = new File("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\excel\\task1.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet Sheet = wb.createSheet("mobilelist");
			for (int j = 0; j < li.size(); j++) {
				XSSFRow row = Sheet.createRow(j);
				XSSFCell cell = row.createCell(0);
				cell.setCellValue(li.get(j));
			}
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			driver.findElement(By.xpath("(//div[@class='_4rR01T'])[3]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String parent = driver.getWindowHandle();
			Set<String> all = driver.getWindowHandles();
			for (String child : all) {
		    if (!parent.equals(child)) {
			driver.switchTo().window(child);	
}}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element2 = driver.findElement(By.xpath("//span[@class='B_NuCI']"));
			String text1 = element2.getText();
			System.out.println(text1);
			FileInputStream fi = new FileInputStream("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\excel\\task1.xlsx");
			XSSFWorkbook w= new XSSFWorkbook(fi);
			XSSFSheet sheetAt = w.getSheetAt(0);
			XSSFRow row = sheetAt.getRow(2);
			XSSFCell cell = row.getCell(0);
			String value = cell.getStringCellValue();
			String value2 = "128 GB";
			boolean b = value.contains(value2);
			Assert.assertTrue(b);
			System.out.println("Strict valiation :"+value);

			JavascriptExecutor jss = (JavascriptExecutor)driver;
			WebElement element3 = driver.findElement(By.xpath("//div[text()='Specifications']"));
			jss.executeScript("arguments[0].scrollIntoView(true)", element3);
			Thread.sleep(3000);	
			TakesScreenshot tss = (TakesScreenshot)driver;
			File as = tss.getScreenshotAs(OutputType.FILE);
			File Despath = new File("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\ssc\\spec.png");
			FileUtils.copyFile(as, Despath);
}
}