package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	public TC003_LoginDDT() {
		super();
	}

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="datadriven")
	public void verify_loginDDT(String email, String password, String exp) {

		logger.info("***** stating TC_003_LoginDDT ******");
		System.out.println(email+" "+password+" "+exp);
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();
			// Assert.assertEquals(targetPage, true,"Login failed");
			if (exp.equalsIgnoreCase("valid")) {
				if (targetPage == true) {
					Assert.assertTrue(true);
					macc.clickLogout();
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("*******Finished TC_003_LoginDDT***");

	}

}
