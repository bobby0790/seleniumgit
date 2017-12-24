package com.testNg;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Irctc
{
	public WebDriver driver;
	public WebElement tex(String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	public void eWait(int sec, String xpath){
		(new WebDriverWait(driver, sec)).until(ExpectedConditions.visibilityOf(tex(xpath)));
	}
	
	
	@Test(priority=0)
	public void browser(){
		 driver=new FirefoxDriver();
		  driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		  driver.manage().window();
		  
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException, TimeoutException 
	{
		try {
			 // driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			  
			  eWait(10, "//*[@id='usernameId']");
			  tex("//*[@id='usernameId']").sendKeys("bobby0709");
			  //driver.findElement(By.xpath("//*[@id='usernameId']")).sendKeys("bobby0709");
			  
			  tex("//input[@type='password']").sendKeys("bobby@123");
			  //driver.findElement(By.xpath("//*[@id='loginFormId']/div[1]/div[2]/table[1]/tbody/tr[2]/td[2]/input")).sendKeys("bobby@123");
			  
			  
			  Scanner scan=new Scanner(System.in);
			  System.out.println("ENTER CAPTCHA");
			  String cap=scan.nextLine();
			  eWait(20, "//input[@class='loginCaptcha']");
			  driver.findElement(By.xpath("//input[@class='loginCaptcha']")).sendKeys(cap);
			 
			  
			  System.out.println(scan);
			  Hashtable<String, String> ht=new Hashtable<>();
			  ht.put("userName", tex("//*[@id='usernameId']").getAttribute("value"));
			  ht.put("passWord", tex("//input[@type='password']").getAttribute("value"));
			  ht.put("capta", tex("//input[@class='loginCaptcha']").getAttribute("value"));
			  
			  driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
			  
			  try {
				  eWait(10, "//*[@id='loginerrorpanel_header_content']");
				  if(tex("//*[@id='loginerrorpanel_header_content']").isDisplayed()){
					  tex("//*[@id='loginerrorpanelok']").click();
				  }
				  login();
			  } 
			  catch (Exception e) {
				  e.printStackTrace();
			  }
			  if(ht.get("userName").equalsIgnoreCase("bobby0709")){
				  System.out.println("pass");
			  }
			  else{
				  System.out.println("fail");
			  }
			  if(ht.get("passWord").equalsIgnoreCase("bobby@123")){
				  System.out.println("pass");
			  }
			  else{
				  System.out.println("fail");
			  }
			  if(ht.get("capta").equalsIgnoreCase(cap)){
				  System.out.println("pass");
			  }
			  else{
				  System.out.println("fail");
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void book(){
		
	}

}
