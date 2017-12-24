package com.testNg;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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
		  driver.manage().window().maximize();;
		  
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
			 
			  String cap=null;
			  Scanner scan;
			  try {
				  eWait(3, "//div[@id='nlpInputContainer']//div[@class='nlpRefresh']");
				  tex("//div[@id='nlpInputContainer']//div[@class='nlpRefresh']").click();
				  scan=new Scanner(System.in);
				  System.out.println("ENTER CAPTCHA at refresh");
				  cap=scan.nextLine();
				  eWait(20, "//*[@id='nlpAnswer']");
				  driver.findElement(By.xpath("//*[@id='nlpAnswer']")).sendKeys(cap);
				 
			} catch (Exception e) {
				try {
					  scan=new Scanner(System.in);
					  System.out.println("ENTER CAPTCHA");
					  cap=scan.nextLine();
					  eWait(20, "//input[@class='loginCaptcha']");
					  driver.findElement(By.xpath("//input[@class='loginCaptcha']")).sendKeys(cap);
					 
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			 
			  
			  //System.out.println(scan);
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
				  //e.printStackTrace();
				  System.out.println("Captha successful");
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
	
	@Test(priority=2)
	public void book(){
		Scanner scan=new Scanner(System.in);
		  System.out.println("ENTER FROM");
		  String from=scan.nextLine();
		  tex("//*[@id='jpform:fromStation']").sendKeys(from);
		  
		  eWait(10, "//*[@id='ui-id-1']//a");
		  List<WebElement> fromStations=driver.findElements(By.xpath("//*[@id='ui-id-1']//a"));
		  
		  List<String> stationCodes=new ArrayList<>();
		  for(int i=0;i<fromStations.size();i++){
			  stationCodes.add(fromStations.get(i).getText());
		  }
		
		  System.out.println("Select station from the list");
		  System.out.println(stationCodes);
		  
		  scan=new Scanner(System.in);
		  String fromStation=scan.nextLine();
		  
		  tex("//*[@id='jpform:fromStation']").clear();
		  tex("//*[@id='jpform:fromStation']").sendKeys(fromStation);
		  
		  tex("//td[text()='From Station']").click();
		  
		  
		  scan=new Scanner(System.in);
		  System.out.println("ENTER TO");
		  String to=scan.nextLine();
		  tex("//*[@id='jpform:toStation']").sendKeys(to);
		  
		  eWait(10, "//*[@id='ui-id-2']//a");
		  List<WebElement> toStations=driver.findElements(By.xpath("//*[@id='ui-id-2']//a"));
		  
		  List<String> tostationCodes=new ArrayList<>();
		  for(int i=0;i<toStations.size();i++){
			  tostationCodes.add(toStations.get(i).getText());
		  }
		
		  System.out.println("Select station from the list");
		  System.out.println(tostationCodes);
		  
		  scan=new Scanner(System.in);
		  String toStation=scan.nextLine();
		  
		  tex("//*[@id='jpform:toStation']").clear();
		  tex("//*[@id='jpform:toStation']").sendKeys(toStation);
		  
		  tex("//td[text()='To Station']").click();
		  
		  
		  
		  
		  
		  
		  
		  tex("//div[@id='jbtab:content']//img[@class='ui-datepicker-trigger']").click();
		  eWait(5, "//a[contains(@class,'ui-state-default')]");
		  
		  List<WebElement> dates=driver.findElements(By.xpath("//a[contains(@class,'ui-state-default')]"));
		  List<String> days=new ArrayList<>();
		  for(WebElement we:dates){
			  days.add(we.getText());
		  }
		  List<WebElement> parent=driver.findElements(By.xpath("//a[contains(@class,'ui-state-default')]//parent::td"));
		  List<String> month=new ArrayList<>();
		  List<String> year=new ArrayList<>();
		  for(WebElement we:parent){
			  month.add(Integer.toString((Integer.parseInt(we.getAttribute("data-month").trim())+1)));
			  year.add(we.getAttribute("data-year").trim());
		  }
		  
		  System.out.println("below dates are available");
		  for(int i=0;i<days.size();i++){
			  System.out.println(days.get(i)+"-"+month.get(i)+"-"+year.get(i));
		  }
		  scan=new Scanner(System.in);
		  String date=scan.nextLine();
		 
		  tex("//*[@id='jpform:journeyDateInputDate']").sendKeys(date);
		  
		  tex("//div[@id='jbtab:content']//*[text()='Journey Date']").click();
		  tex("//*[@id='jpform:jpsubmit']").click();
		  

		  try {
			  eWait(10, "//*[text()='Train Between Stations']");
			  System.out.println("navigation successful");	
		} catch (Exception e) {
			System.out.println("navigation fail");
			
			e.printStackTrace();
		}
	}

}
