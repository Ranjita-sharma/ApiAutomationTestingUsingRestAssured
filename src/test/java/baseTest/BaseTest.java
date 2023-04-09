package baseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.ExtentReportListener;
import utils.FileAndEnv;
import io.restassured.RestAssured;

@Listeners(ExtentReportListener.class)
public class BaseTest extends ExtentReportListener{
	//@BeforeTest
	@BeforeClass
	public void baseTest() {
		//RestAssured.baseURI = "http://stg.consumerbff.newlendo.com/";
				//FileAndEnv.envAndfile().get("ServerURL");
		//Assert.assertTrue(false);
	}
}
