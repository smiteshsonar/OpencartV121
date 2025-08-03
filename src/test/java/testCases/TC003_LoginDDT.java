package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * valid data - login success - logout
 * valid data - login unsuccess 
 * 
 * invalid data - login success - logout
 * invalid data - login failed 
 * */

public class TC003_LoginDDT extends BaseClass {
	
	//WebDriver driver;
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class, groups= {"DataDriven"})   // getting data provider from different class
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("******** Starting TC003_LoginDDT ***********");
		
		try {
		HomePage hp = new HomePage(driver);
		
		Thread.sleep(5000);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(5000);
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailString(email);
		lp.setPasswordString(pwd);
		lp.clickLogin();   
		
		//MyAccount page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
//		Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true); // 	
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				
//				Thread.sleep(5000);
				macc.clickLogout();
				Assert.assertTrue(false); // 	
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}
	catch(Exception e){
		Assert.fail();
	}
		
		logger.info("******** Finished TC003_LoginDDT ***********");

	}
}
