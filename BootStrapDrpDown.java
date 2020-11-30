package com.Assign1;



import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BootStrapDrpDown {

	
	public static WebDriver driver;	
	
	 @Test
	    public void bootStrap() throws InterruptedException
	    {
	        //WebDriverManager.chromedriver().setup();
		    System.setProperty("webdriver.chrome.driver" , "D:\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();

	        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

	        driver.findElement(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']")).click();

	        By bootStrapDropDown = By.xpath("//ul[contains(@class,'multiselect-container')]//li//a//label");

	        BootStrapDrpDown.printBootStrapDropDownValues(bootStrapDropDown);

	        BootStrapDrpDown.selectValueFromBootStrapDropDown(bootStrapDropDown, "HTML");
	        BootStrapDrpDown.selectValueFromBootStrapDropDown(bootStrapDropDown, "CSS");
	        BootStrapDrpDown.selectValueFromBootStrapDropDown(bootStrapDropDown, "Angular");

	    }

	    //Function to Select a Value from BootStrap Drop Down using Streams.
	    public static void selectValueFromBootStrapDropDown(By locator, String value)
	    {
	        List<WebElement> dropDownValues = driver.findElements(locator);
	        dropDownValues.stream().filter(values -> values.getText().matches(value)).forEach(values -> values.click());
	    }

	    //Function to Print BootStrap Drop Down Values using Streams.
	    public static void printBootStrapDropDownValues(By locator)
	    {
	        List<WebElement> dropDownValues = driver.findElements(locator);
	        dropDownValues.stream().map(values -> values.getText()).collect(Collectors.toList()).forEach(values -> System.out.println(values));
	    }
	    
	    @AfterTest 
	    public void close() {
	    	driver.close();
	    }
	    }
	