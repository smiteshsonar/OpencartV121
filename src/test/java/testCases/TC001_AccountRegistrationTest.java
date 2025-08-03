package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {  
		
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		
		try {
			HomePage hp = new HomePage(driver);
			
			logger.info("*** Clicked on MyAccount ***");
			hp.clickMyAccount();
			
			logger.info("*** Clicked on Register ***");
			hp.clickRegister();
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("*** Entering customer details... ***");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password = randomAlphNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("*** Entering expected message ***");
			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("Test Failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("*** Test Finished - TC001_AccountRegistrationTest");
		
	}
	
	
	// 
	
	
}
