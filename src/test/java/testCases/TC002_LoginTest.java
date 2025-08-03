package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity", "Master"})
	public void verify_login() throws InterruptedException {
		
		try {
		logger.info("** Starting test **");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		Thread.sleep(5000);
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailString(p.getProperty("email"));
		lp.setPasswordString(p.getProperty("password"));
		lp.clickLogin();   
		
		//MyAccount page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
//		Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		
		logger.info("** Execution completed **");
		}
		catch(Exception e) {
			Assert.fail();
		}
	} 
}
