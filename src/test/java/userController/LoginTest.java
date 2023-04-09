package userController;

import api.configs.APIPath;
import baseTest.BaseTest;
import com.pojo.request.loginPojo.loginOTP;
import com.pojo.request.loginPojo.loginPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Vector;

import static api.configs.APIPath.apiPath.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

	public String test_adminLogin() {
		Response admin_login = given().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
				.queryParam("email", APIPath.apiPath.Admin_queryParamEmail)
				.queryParam("password", TestParamPWD).when().get(APIPath.apiPath.adminLoginURL).then().log()
				.all().extract().response();
		System.out.println("Response body is " + admin_login.getBody().asString());

		String token = admin_login.path("data.authToken");
		System.out.println(token);
		return token;
	}
	public static HashMap<String,Object> userLogin(String email){
		loginPojo login_pojo = new loginPojo();
		login_pojo.setEmail(email);
		login_pojo.setPassword("Test@123");

		Response investor_login = RestAssured.given()
				.baseUri(APIPath.apiPath.BaseURL).contentType("application/json").body(login_pojo).when().
				post(ConsumerLoginURL)
				.then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + investor_login.getBody().asString());

		String token = investor_login.path("data.authToken");
		System.out.println(token);

		String phoneNumber = investor_login.path("data.phoneNumber");
		String otpType = investor_login.path("data.otpType");
		// test_OTPVerification(phoneNumber,loginDefaultOTP,otpType);
		int userId = investor_login.path("data.userId");
		System.out.println(userId);
		HashMap<String,Object> map = new HashMap<>();
		map.put("authToken",token);
		map.put("userId",userId);
		map.put("phoneNumber",phoneNumber);
//		Vector vector = new Vector();
//		vector.add(token);
//		vector.add(userId);
		return map;
	}
	public static HashMap<String,Object> test_InvestorLogin(){
		loginPojo login_pojo = new loginPojo();
		login_pojo.setEmail("nada@gmail.com");
		login_pojo.setPassword("Test@123");

		Response investor_login = RestAssured.given()
				.baseUri(APIPath.apiPath.BaseURL).contentType("application/json").body(login_pojo).when().
				post(ConsumerLoginURL)
				.then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + investor_login.getBody().asString());

		String token = investor_login.path("data.authToken");
		System.out.println(token);

		String phoneNumber = investor_login.path("data.phoneNumber");
		String otpType = investor_login.path("data.otpType");
	    // test_OTPVerification(phoneNumber,loginDefaultOTP,otpType);
		 int userId = investor_login.path("data.userId");
		System.out.println(userId);
		HashMap<String,Object> map = new HashMap<>();
		map.put("authToken",token);
		map.put("userId",userId);
		map.put("phoneNumber",phoneNumber);

		return map;
	}
	public HashMap<String,Object>  test_BorrowerLogin() {
		loginPojo login_pojo = new loginPojo();
		login_pojo.setEmail(Borrower_queryParamEmail);
		login_pojo.setPassword(QueryParamPWD);

			Response borrower_login = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.body(login_pojo)
				.when().post(APIPath.apiPath.ConsumerLoginURL).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + borrower_login.getBody().asString());

		String phoneNumber = borrower_login.path("data.phoneNumber");
		String otpType = borrower_login.path("data.otpType");
		String token = borrower_login.path("data.authToken");
		int userId = borrower_login.path("data.userId");
		test_OTPVerification(phoneNumber,loginDefaultOTP,otpType);
		System.out.println(token);
		HashMap<String,Object> map = new HashMap<>();
		map.put("authToken",token);
		map.put("userId",userId);
		map.put("phoneNumber",phoneNumber);
	return map;
	}

	public void test_OTPVerification(String mobile,String otp, String otpType){
	   loginOTP otp_verify = new loginOTP();
	   otp_verify.setMobile(mobile);
	   otp_verify.setOtp(otp);
	   otp_verify.setOtpType(otpType);

	   Response login_OtpVerify = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
			   .body(otp_verify)
			   .when().post(APIPath.apiPath.LoginOTPVerify).then().log()
			   .all().extract().response();
	   System.out.println("Response body is " + login_OtpVerify.getBody().asString());
		System.out.println("Entered OTP is " + otp_verify.getOtp());

	   String token = login_OtpVerify.path("data.authToken");
		System.out.println(token);
	   String userType = login_OtpVerify.path("data.userType");
		System.out.println("Logged in user type is " + userType);
	   int userId = login_OtpVerify.path("data.userId");
	   System.out.println("Logged in user id is "+ userId);
	   boolean isUserVerified = login_OtpVerify.path("data.verified");
	   assertEquals(isUserVerified, true);
	}
	public  Vector test_InvestorLoginUsingVector(){
		loginPojo login_pojo = new loginPojo();
		login_pojo.setEmail("tah16@gmail.com");
		login_pojo.setPassword("Test@123");

		Response investor_login = RestAssured.given()
				.baseUri(APIPath.apiPath.BaseURL).contentType("application/json").body(login_pojo).when().
				post(ConsumerLoginURL)
				.then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + investor_login.getBody().asString());

		String token = investor_login.path("data.authToken");
		System.out.println(token);

		String phoneNumber = investor_login.path("data.phoneNumber");
		String otpType = investor_login.path("data.otpType");
		// test_OTPVerification(phoneNumber,loginDefaultOTP,otpType);
		int userId = investor_login.path("data.userId");
		System.out.println(userId);
		Vector vector = new Vector();
		vector.add(token);
		vector.add(userId);
		vector.add(phoneNumber);
		return (vector);
	}
}
