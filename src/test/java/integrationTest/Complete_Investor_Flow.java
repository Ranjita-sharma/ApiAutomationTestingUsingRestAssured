package integrationTest;

import api.configs.APIPath;
import api.configs.DocumentType;
import com.pojo.UploadDocument_Investors.DocumentReqDTOList_inv;
import com.pojo.UploadDocument_Investors.FileList;
import com.pojo.UploadDocument_Investors.Upload_Document_Pojo;
import com.pojo.request.AdminPojo.AdminDepositFund;
import com.pojo.request.bid.InvestmentPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.parser.ParseException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import userController.LoginTest;
import userController.SignUpTest;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configs.API_TestData.api_TestData.*;
import static io.restassured.RestAssured.given;
import static java.lang.System.out;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static userController.LoginTest.*;
import static userController.SignUpTest.test_CompleteInvestorProfile;
import static userController.SignUpTest.test_createResidentInvestorProfile;

@Test
public class Complete_Investor_Flow {
	LoginTest get_token = new LoginTest();
	SignUpTest signup_token = new SignUpTest();

	@Test
	public void complete_Investor_Flow() throws SQLException {
		//region DB tables modification for testing purpose
		qaFactory.DB_Connections dbConnections = new qaFactory.DB_Connections();
		 dbConnections.DB();
		//endregion
   		//region Investor account creation
		Map<String,Object> createInvProfile = test_createResidentInvestorProfile();
		createInvProfile.get("userId");
		createInvProfile.get("token");
		int userId = (int) createInvProfile.get("userId");
		String token = (String)createInvProfile.get("token") ;
		out.println("user id while account creation is " + userId );
		//endregion
        //region Investor profile update and verification
		Map<String,Object> completeInvProfile = test_CompleteInvestorProfile(token,userId);
		completeInvProfile.get("userId");
		completeInvProfile.get("investorId");
		int userID_inv = (int) completeInvProfile.get("userId");
		int investor_id = (int)completeInvProfile.get("investorId") ;
		out.println("user id is :" + userID_inv);
		out.println("investor id is :" + investor_id);
		String user_emailId = dbConnections.get_UserEmail(userID_inv);
        //endregion
        //region Investor login
		Map<String, Object> getUser_Token_Id = userLogin(user_emailId);
		int user_Id = (int) getUser_Token_Id.get("userId");
		String authToken = (String) getUser_Token_Id.get("authToken");
		out.println("user id is : " + user_Id);
		String phoneNumber = (String)getUser_Token_Id.get("phoneNumber");
		//endregion
		//region Investor get the welcome notification
		get_Investor_NotificationDetails(authToken,"Welcome");
		//endregion after
		//region Investor upload upgrade document
		investor_UploadUpgradeDocument(authToken, user_Id);
		investor_Submit_UpgradeDocument(authToken, user_Id);
		//endregion
		//region Investor submit the Upgrade document
		investor_Submit_UpgradeDocument(authToken, user_Id);
		//endregion
		//region Investor get the notification after uploading the document
		get_Investor_NotificationDetails(authToken,"Thank you for submitting");
		//endregion
		//region Investor save the IBAN details
		test_SaveIBANDetails(authToken);
		//endregion
		//region Investor change the password after login
		test_ChangePassword(authToken,phoneNumber);
		//region Get the changePassword otp from DB
		String getOTP = dbConnections.get_otp(phoneNumber,"changePassword");
		//endregion
		//region Verify the OTP of ChangePassword
		test_VerifyOTP_ChangePassword(authToken,phoneNumber,getOTP);
		//endregion
		//endregion
		//region Investor forget the password and change a new password flow
		//region Send the OTP in case of ForgetPassword before login
		String otpType = test_SendOTP_ForgetPassword(authToken,phoneNumber);
		//endregion
		//region Get the forgetPassword otp from DB
		String getOTP_ForgetPwd = dbConnections.get_otp(phoneNumber,otpType);
		//endregion
		//region Verify the OTP of ForgetPassword
		test_VerifyOTP_ForgetPassword(authToken,phoneNumber,getOTP_ForgetPwd,otpType);
		//endregion
		//region Investor change the new password in case of Forget Password
		test_ChangeForgetPassword(authToken,phoneNumber,user_Id);
		//endregion
		//endregion
		//region Investor upload and submit the IBAN document in case when he wants to withdraw more than 10k per month
		investor_UploadAndSubmit_IBAN_Document(authToken,user_Id);
		//endregion
		//region Investor get the notification after uploading the document
		get_Investor_NotificationDetails(authToken,"Thank you for submitting");
		//endregion
		//region Get the Investor wallet details
		String wallet_amount = GetWalletDetails(authToken);
		out.println("wallet amount is : " + wallet_amount);
		//endregion
		//region Get the Investor Dashboard Details
		 Map<String,Object> get_Dashboard_Details = GetDashboardDetails(authToken);
		 String userType = (String) get_Dashboard_Details.get("userType");
		 int investorId = (int) get_Dashboard_Details.get("investorId");
		 String investorStatus = (String) get_Dashboard_Details.get("investorStatus");
		 boolean emailVerified = (boolean) get_Dashboard_Details.get("emailVerified");
		 String contractAction = (String) get_Dashboard_Details.get("contractAction");
 	    //endregion
		//region Get the Investor Risk config details
		GetRiskConfig(authToken);
		//endregion
		//region Get the Investor auto invest status
		//Map<Object, Object> getAutoInvest = GetAutoInvestFetch(authToken);
		 GetAutoInvestFetch(authToken);
		// getAutoInvest.get("autoInvestment[0]");
		//endregion
        //region Investor get the investment statement
		getInvestmentStat(token);
		//endregion
	}
	public void validate_Investor_Upload_UpgradeDoc_Flow() throws IOException {
		Map<String, Object> getUser_Token_Id = test_InvestorLogin();
		getUser_Token_Id.get("userId");
		getUser_Token_Id.get("authToken");
		int userId = (int) getUser_Token_Id.get("userId");
		String token = (String) getUser_Token_Id.get("authToken");
		out.println("user id is : " + userId);
		out.println("token id is : " + token);
		investor_UploadUpgradeDocument(token, userId);
		investor_Submit_UpgradeDocument(token, userId);
	}
	public void validateInvestor_Upload_IBANLetter_Flow() throws IOException {
		Map<String, Object> getUser_Token_Id = test_InvestorLogin();
		getUser_Token_Id.get("userId");
		getUser_Token_Id.get("authToken");
		int userId = (int) getUser_Token_Id.get("userId");
		String token = (String) getUser_Token_Id.get("authToken");
		out.println("user id is : " + userId);
		out.println("token id is : " + token);
		investor_UploadUpgradeDocument(token, userId);
		investor_Submit_IBANDocument(token, userId);
	}
	public  void test_SaveIBANDetails(String TokenID){
		HashMap<String,Object> saveIBANDetail = new HashMap<>();
		saveIBANDetail.put("bankName",bankName_inv);
		saveIBANDetail.put("IBAN",IBAN_inv);
		saveIBANDetail.put("benefeciaryName",benefeciaryName_inv);

		Response addIbanDetails_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(saveIBANDetail).when().post(APIPath.apiPath.PostSaveIbanDetail).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + addIbanDetails_Inv.getBody().asString());
		String message = addIbanDetails_Inv.path("data.description");
		assertEquals(message, "Successfully save Bank Detail");
	}
	public  void test_ChangePassword(String TokenID,String phoneNumber){
		HashMap<String,Object> changePassword = new HashMap<>();
		changePassword.put("oldPassword",userPassword);
		changePassword.put("newPassword",user_NewPassword);
		changePassword.put("phoneNumber",phoneNumber);

		Response changePassword_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(changePassword).when().post(APIPath.apiPath.Post_ChangePassword).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + changePassword_Inv.getBody().asString());
		String message = changePassword_Inv.path("data.message");
		assertEquals(message, "OTP Send Successfully");
		String otpType = changePassword_Inv.path("data.otpType");
		assertEquals(otpType, "changePassword");
	}
	public  void test_VerifyOTP_ChangePassword(String TokenID,String phoneNumber,String OTP){
		HashMap<String,Object> verifyOTP_changePassword = new HashMap<>();
		verifyOTP_changePassword.put("otp",OTP);
		verifyOTP_changePassword.put("oldPassword",userPassword);
		verifyOTP_changePassword.put("newPassword",user_NewPassword);
		verifyOTP_changePassword.put("phoneNumber",phoneNumber);

		Response verifyOTP_changePassword_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(verifyOTP_changePassword).when().post(APIPath.apiPath.Post_VerifyOTP_ChangePassword).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + verifyOTP_changePassword_Inv.getBody().asString());
		String message = verifyOTP_changePassword_Inv.path("data.message");
		assertEquals(message, "Successfully change the password");
	}
	public  String test_SendOTP_ForgetPassword(String TokenID,String phoneNumber){
		HashMap<String,Object> verifyOTP_changePassword = new HashMap<>();
		verifyOTP_changePassword.put("mobile",phoneNumber);

		Response sendOTP_changePassword_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(verifyOTP_changePassword).when().post(APIPath.apiPath.Post_SendOTP_ForgetPWD).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + sendOTP_changePassword_Inv.getBody().asString());
		String message = sendOTP_changePassword_Inv.path("data.message");
		assertEquals(message, "OTP Send Successfully");
		String otpType = sendOTP_changePassword_Inv.path("data.otpType");
		assertEquals(otpType, "forgetPassword");
		return otpType;
	}
	public  void test_VerifyOTP_ForgetPassword(String TokenID,String phoneNumber,String OTP,String otpType){
		HashMap<String,Object> verifyOTP_changePassword = new HashMap<>();
		verifyOTP_changePassword.put("mobile",phoneNumber);
		verifyOTP_changePassword.put("otp",OTP);
		verifyOTP_changePassword.put("otpType",otpType);

		Response verifyOTP_ForgetPwd_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(verifyOTP_changePassword).when().post(APIPath.apiPath.Post_VerifyOTP_ForgetPWD).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + verifyOTP_ForgetPwd_Inv.getBody().asString());
		String message = verifyOTP_ForgetPwd_Inv.path("data.description");
		assertEquals(message, "OTP verify successfully");
	}
	public  void test_ChangeForgetPassword(String TokenID,String phoneNumber,int userId){
		HashMap<String,Object> changeForgetPwd = new HashMap<>();
		changeForgetPwd.put("userId",userId);
		changeForgetPwd.put("newPassword",userPassword);

		Response changeForgetPwd_Inv = RestAssured.given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(changeForgetPwd).when().post(APIPath.apiPath.Post_ChangeForgetPassword).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + changeForgetPwd_Inv.getBody().asString());
		String message = changeForgetPwd_Inv.path("data.message");
		assertEquals(message, "Successfully change the password");
	}
	public void investor_UploadAndSubmit_IBAN_Document(String token,int userId){
		File upgradeDoc = new File(".\\src\\main\\resources\\Upload-documents\\invoice.pdf");
		Response upload_IBANDoc = RestAssured.given()
				.log().all().baseUri(APIPath.apiPath.BaseURL)
				.contentType("multipart/form-data").header("x-auth-token", token).log().all()
				.multiPart("file", upgradeDoc, "multipart/form-data")
				.when().post(APIPath.apiPath.UploadDocument)
				.then().log().all().assertThat().statusCode(200).extract().response();
		out.println(upload_IBANDoc.getBody().asString());
		String uploadMessage = upload_IBANDoc.path("status.message");
		assertEquals(uploadMessage, "File has been successfully uploaded");
		investor_Submit_UpgradeDocument(token,userId);
	}
	public void investor_UploadUpgradeDocument(String token,int userId)  {
		File upgradeDoc = new File(".\\src\\main\\resources\\Upload-documents\\invoice.pdf");
		Response upload_InvUpgradeDoc = RestAssured.given()
				.log().all().baseUri(APIPath.apiPath.BaseURL)
				.contentType("multipart/form-data").header("x-auth-token", token).log().all()
				.multiPart("file", upgradeDoc, "multipart/form-data")
				.when().post(APIPath.apiPath.UploadDocument)
				.then().log().all().assertThat().statusCode(200).extract().response();
		out.println(upload_InvUpgradeDoc.getBody().asString());
		String uploadMessage = upload_InvUpgradeDoc.path("status.message");
		assertEquals(uploadMessage, "File has been successfully uploaded");
	}
	public void investor_Submit_UpgradeDocument(String token,int userId) {
		//object of pojo class
		Upload_Document_Pojo upload_upgradeDocumentPojo = new Upload_Document_Pojo();
        //ListOf Array json Objects
        List<DocumentReqDTOList_inv> documentReqDTOListInv = new ArrayList<>();
		DocumentReqDTOList_inv documentReqDTOList = new DocumentReqDTOList_inv();
        //ListOf Array json Objects
		List<FileList> fileLists = new ArrayList<>();
		FileList fileList = new FileList();
	    fileList.setDocumentUrl("upgrade_1662366502655.pdf");
		fileList.setDocumentName("upgrade.pdf");
		fileLists.add(fileList);

		documentReqDTOList.setDocumentType(String.valueOf(DocumentType.UPGRADE_USER));
		documentReqDTOList.setFileList(fileLists);
		documentReqDTOListInv.add(documentReqDTOList);

		upload_upgradeDocumentPojo.setIsInvestor(true);
		upload_upgradeDocumentPojo.setDocumentReqDTOList(documentReqDTOListInv);

		Response submit_InvUpgradeDoc = RestAssured.given()
				.baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", token)
				.queryParam("userId",userId).queryParam("isInvestor",true).log().all().body(upload_upgradeDocumentPojo)
				.when().post(APIPath.apiPath.SubmitUploadedDocument).then().log().all().assertThat().statusCode(200).extract().response();

		out.println(submit_InvUpgradeDoc.getBody().asString());
		String uploadSuccessMsg = submit_InvUpgradeDoc.path("status.message");
		assertEquals(uploadSuccessMsg, "Successfully executed");
	}
	public void investor_Submit_IBANDocument(String token,int userId) {
		//object of pojo class
		Upload_Document_Pojo upload_IBANDocumentPojo = new Upload_Document_Pojo();

		//ListOf Array json Objects
		List<DocumentReqDTOList_inv> documentReqDTOListInv = new ArrayList<>();
		DocumentReqDTOList_inv documentReqDTOList = new DocumentReqDTOList_inv();
		//ListOf Array json Objects
		List<FileList> fileLists = new ArrayList<>();
		FileList fileList = new FileList();
		fileList.setDocumentUrl("bankwithdraw_1662366502655.pdf");
		fileList.setDocumentName("bankwithdraw.pdf");
		fileLists.add(fileList);

		documentReqDTOList.setDocumentType(String.valueOf(DocumentType.IBAN_LETTER));
		documentReqDTOList.setFileList(fileLists);
		documentReqDTOListInv.add(documentReqDTOList);

		upload_IBANDocumentPojo.setIsInvestor(true);
		upload_IBANDocumentPojo.setDocumentReqDTOList(documentReqDTOListInv);

		Response submit_IBANDoc = RestAssured.given()
				.baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", token)
				.queryParam("userId",userId).queryParam("isInvestor",true).log().all().body(upload_IBANDocumentPojo)
				.when().post(APIPath.apiPath.SubmitUploadedDocument).then().log().all().assertThat().statusCode(200).extract().response();

		out.println(submit_IBANDoc.getBody().asString());
		String uploadSuccessMsg = submit_IBANDoc.path("status.message");
		assertEquals(uploadSuccessMsg, "Successfully executed");
	}
	public void GetLoanDetails() {
		Response loansStatus = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", "TokenID")
				.queryParam("status1", "LOAN_SCHEDULED").queryParam("status2", "PUBLISHED").queryParam("size", 10).queryParam("page", 0)
				.queryParam("sortBy", "date").queryParam("status2", "FUNDED").queryParam("status2", "PARTIALLY_FUNDED")
				.when().get(APIPath.apiPath.GetLoansStatus).then().log()
				.all().assertThat().statusCode(200).extract().response();
	}
	public static void investorApplyBidManually(String TokenID,int loanId, int investorId, int opportunityId, int participationAmount, int opportunityAmount) {
		InvestmentPojo investBid = new InvestmentPojo();
		//need to uncomment below line
		investBid.setInvestorId(investorId);
		investBid.setLoanId(loanId);
		investBid.setOpportunityId(opportunityId);
		investBid.setParticipationAmount(participationAmount);
		investBid.setOpportunityAmount(opportunityAmount);

		Response investManually = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.body(investBid).when().post(APIPath.apiPath.PostSaveBid).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + investManually.getBody().asString());
		String message = investManually.path("data.message");
		assertEquals(message, "Bid has been successfully created");
	}
	public String GetWalletDetails(String TokenID) {
		//	test.log(LogStatus.INFO, "Fetching the token...");
		Response response = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.when().get(APIPath.apiPath.GetWalletDetails).then().log()
				.all().assertThat().statusCode(200).extract().response();

		// System.out.println("Response body is " + response.getBody());
		String amount = response.path("data.amount");
		out.println(amount);
		return amount;
	}
	public static @NotNull HashMap<String,Object> GetDashboardDetails(String TokenID) {
		Response inv_dashboard = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID).log().all()
				.when().get(APIPath.apiPath.GetDashboardDetails).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + inv_dashboard.getBody().asString());
		String contractAction = inv_dashboard.path("data.contractAction");
		out.println("contractAction status is : " + contractAction);
		//assertEquals(contractAction,"ACCEPTED");
		int investorId = inv_dashboard.path("data.investorId");
		out.println("InvestorId id  is : " + investorId);

		String ibanDocumentStatus = inv_dashboard.path("data.ibanDocumentStatus");
		out.println("ibanDocument Status  is : " + ibanDocumentStatus);

		String supportingDocStatus = inv_dashboard.path("data.supportingDocStatus");
		out.println("supportingDocStatus  is : " + supportingDocStatus);

		String userType = inv_dashboard.path("data.userType");
		out.println("userType  is : " + supportingDocStatus);
		assertEquals(userType, "INVESTOR");

		String firstName = inv_dashboard.path("data.firstName");
		assertNotNull(firstName, "firstName is  null");
		String lastName = inv_dashboard.path("data.lastName");
		assertNotNull(lastName, "lastName is  null");
		String email = inv_dashboard.path("data.email");
		assertNotNull(email, "email is  null");
		Boolean emailVerified = inv_dashboard.path("data.emailVerified");
		//assertTrue(emailVerified);
		Boolean documentVerified = inv_dashboard.path("data.documentVerified");
		String userAccountType = inv_dashboard.path("data.userAccountType");
		String nationalId = inv_dashboard.path("data.nationalId");
		String dateOfBirth = inv_dashboard.path("data.dateOfBirth");
		String investorStatus = inv_dashboard.path("data.investorStatus");
		Boolean bankDetailAvailable = inv_dashboard.path("data.bankDetailAvailable");
		String amount = inv_dashboard.path("data.amount");

		HashMap<String,Object> map = new HashMap<>();
		map.put("userType",userType);
		map.put("investorId",investorId);
		map.put("investorStatus",investorStatus);
		map.put("emailVerified",emailVerified);
		map.put("contractAction",contractAction);

		return map;
	}
    public void get_Investor_NotificationDetails(String token,String Notification) {
		Response get_Notification = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", token)
				.when().get(APIPath.apiPath.Get_NotificationDetails).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + get_Notification.getBody().asString());
		//convert JSON to string
		JsonPath js = new JsonPath(get_Notification.asString());
		//get values of JSON array after getting array size
		int s = js.getInt("data.size()");
		out.println("size is: " + s);
		for(int i = 0; i < s; i++) {
			String bodyenglish_msg = js.getString("data[" + i + "].bodyenglish");
		    out.println("Notification msg is :" + bodyenglish_msg);
			assertNotNull(bodyenglish_msg,"bodyenglish message is null");
			//assertThat(bodyenglish_msg.contains(Notification));
			out.println(bodyenglish_msg.contains(Notification));
		}
		}
	@Test
	public static Map<String, Integer> GetLoansStatusForInvestment(String TokenID) throws ParseException {

		Response loansStatus = given().baseUri(APIPath.apiPath.BaseURL)
				.contentType("application/json").header("x-auth-token", TokenID)
				.queryParam("status1", "LOAN_SCHEDULED").queryParam("status2", "PUBLISHED").queryParam("size", 10).queryParam("page", 0)
				.queryParam("sortBy", "date").queryParam("status2", "FUNDED").queryParam("status2", "PARTIALLY_FUNDED")
				.when().get(APIPath.apiPath.GetLoanApplications).then().log()
				.all().assertThat().statusCode(200).extract().response();

		out.println("Response body is " + loansStatus.getBody().asString());
		String loansStatus_inv = loansStatus.path("status.message");
		assertThat(loansStatus_inv, equalTo("Loan Details Successfully Fetched"));
		//convert JSON to string
		JsonPath j = new JsonPath(loansStatus.asString());

		//get values of JSON array after getting array size
		int s = j.getInt("data.loanApplications.size()");
		out.println("Array size is :" + s);
		JSONArray jsonArray = new JSONArray();
		//	JSONArray getArray = getObject
		JSONObject obj = new JSONObject();

		Map<String, Integer> mapObj = new HashMap<>();

		for (int i = 0; i < s; i++) {
		//	int getOpportunityId = 0;
			if (!(j.getJsonObject("data.loanApplications[" + i + "].opportunityId") == null) && !(j.getJsonObject("data.loanApplications[" + i + "].id") == null)) {
				int getOpportunityId = j.getJsonObject("data.loanApplications[" + i + "].opportunityId");
				out.println("getOpportunityId is : " + getOpportunityId);

				int getLoanId = j.getInt("data.loanApplications[" + i + "].id");
				out.println("get Loan Id is : " + getLoanId);

				obj.put("opportunityId", getOpportunityId);
				obj.put("id", getLoanId);
				jsonArray.put(obj);

				//	TODO
				mapObj.put(String.valueOf(getLoanId), getOpportunityId);
			}
		//	System.out.println("Get opportunity id based on loan id is "+mapObj.put(String.valueOf(loanId), getOpportunityId));
			//		System.out.println("-----------");


			//		int getOpportunityId = j.getInt("data.loanApplications["+i+"].opportunityId");
//		System.out.println("getOpportunityId is : " + getOpportunityId);
//		int getLoanId = j.getInt("data.loanApplications["+i+"].id");
//		System.out.println("get Loan Id is : " + getLoanId);
//		HashMap<String,Object> loanApplications_map = new HashMap<>();
//		loanApplications_map.put("id",getLoanId);
			//  responseKeyValidationFromJsonObject(loansStatus,"getLoanRequestedAmount");
			//responseKeyValidationFromJsonObject(loansStatus,"opportunityId");
			//	InvestorGETStatusOfLoans investorLoans = loansStatus.as(InvestorGETStatusOfLoans.class, ObjectMapperType.GSON);
//		SoftAssert softassert = new SoftAssert();
//		List<LoanApplication> loanApplications = loansStatus.jsonPath().getList("data.loanApplications");
//		for (LoanApplication loanApplication : loanApplications) {
//			System.out.println("For loop iterator is : " + loanApplications.listIterator().toString());
//			if(loanApplication.getId().equals(loanId)){
//				System.out.println("Loan application Opportunity id is : "+loanApplication.getOpportunityId());
//				System.out.println("Loan application approved loan amount  id is : "+ loanApplication.getApprovedLoanAmount());
//				responseKeyValidationFromJsonArray(loansStatus,"loanApplications");
//
//				//	InvestorGETStatusOfLoans investorLoans = loansStatus.as(InvestorGETStatusOfLoans.class, ObjectMapperType.GSON);
//
//			}
//			else {
//				assertTrue(!loanApplication.getOpportunityId().equals("Opportunity ID is Blank"));
//			}
//			loanApplication.getOpportunityId();
//			loanApplication.getApprovedLoanAmount();
//
//			softassert.assertTrue(!loanApplication.getOpportunityId().equals("Opportunity ID is Blank"));
//			softassert.assertTrue(!loanApplication.getApprovedLoanAmount().equals("ApprovedLoanAmount is Blank"));
			//}
			//softassert.assertAll();
			//	List<loanApplication> loanApplications = loansStatus.getBody.("data.loanApplications[]")

			//Map<Object,Object> gettheLatestLoanStatus = loansStatus.jsonPath().getJsonObject("data.loanApplications[]");
			//Retrieving the array
			//JSONArray jsonArray = (JSONArray) loansStatus.jsonPath().getJsonObject("data.loanApplications");
//		JsonObject obj_JsonObject = new JsonObject(loansStatus.toString());
//		loansStatus.getBody().asByteArray().length;
//		return loanApplications;
			//return jsonArray;

		}

			return mapObj;

	}
	@Test
	public void GetRiskConfig(String tokenId) {
		Response getRiskConfig = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
				.header("x-auth-token", tokenId).when().get(APIPath.apiPath.GetRiskConfig)
				.then().assertThat().statusCode(200).extract().response();
		String riskConfig = getRiskConfig.getBody().asString();
		out.println("Risk config response body is :" + riskConfig);
	}

	@Test
	public void GetAutoInvestFetch(String tokenId) {
	//	public Map<Object, Object> GetAutoInvestFetch(String tokenId) {
		Response getAutoInvest = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
				.header("x-auth-token", tokenId).when().get(APIPath.apiPath.GetAutoInvestDetails)
				.then().assertThat().statusCode(200).extract().response();
		out.println("Response body is :" + getAutoInvest.getBody());

		String autoInvestDetail = getAutoInvest.path("status.message");
		assertThat(autoInvestDetail, equalTo("Auto Investment Successfully Fetched"));

		//Assert.assertEquals(getAutoInvest.jsonPath().getMap("data.autoInvestment[0]"),true);

	//	return getAutoInvest.jsonPath().getMap("data.autoInvestment[0]");
	}

	@Test
	public void ApplyAutoInvest(String tokenId) {
		Response applyAutoInvest = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
				.header("x-auth-token", tokenId).when().post(APIPath.apiPath.PostApplyAutoInvest)
				.then().assertThat().statusCode(200).extract().response();
		out.println("Response body is :" + applyAutoInvest.getBody());


		String _applyautoInvest = applyAutoInvest.path("status.message");
		assertThat(_applyautoInvest, equalTo("Auto Investment Successfully Fetched"));

	}
	@Test
	public void AdminDepositFund(int userId, String token_id) {
		AdminDepositFund admin_deposit = new AdminDepositFund();
		admin_deposit.setUserId(userId);
		admin_deposit.setAmount("2000");
		admin_deposit.setType("CREDIT");
		admin_deposit.setOnHold(false);
		admin_deposit.setReferenceId("Admin fund deposit");
		admin_deposit.setSourceId(null);
		admin_deposit.setSourceType("BANK_TRANSFER");

		Response depositFund = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
				.header("x-auth-token", token_id).body(admin_deposit).log().all()
				.when().post(APIPath.apiPath.AdminDepositFund).then().assertThat().statusCode(200).extract().response();
		out.println("Response body is :" + depositFund.getBody());

		String isFundDeposited = depositFund.path("status.message");
		assertThat(isFundDeposited, equalTo("successfully executed"));

		int transactionId = depositFund.path("data.transactionId");
		out.println("deposit fund transaction id is : " + transactionId);
	}
	public static int getLoanDetails(String token_Id, int loanID) {
		//String TokenId,int loanId
		Response getLoanDetails = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.header("x-auth-token", token_Id).log().all()
				.pathParams("loanId", loanID)
				.when().get(APIPath.apiPath.GetLoanDetails)
				.then().assertThat().statusCode(200).extract().response();

		out.println("getLoanDetails Response body is " + getLoanDetails.getBody().asString());
		String uploadMessage = getLoanDetails.path("status.message");
		assertEquals(uploadMessage, "Loan Details Successfully Fetched");

		float requested_LoanAmount = getLoanDetails.path("data.requestedLoanAmount");

		return Math.round(requested_LoanAmount);
		//return token;

	}
	public static void getAccountStatment(String tokenId, int loanID) {
		//String TokenId,int loanId
		Response getAccountStatment = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.header("x-auth-token", tokenId).log().all()
				.queryParams("paymentType","").queryParams("dateType","ALL_TIME").queryParams("status","")
				.when().get(APIPath.apiPath.GetAccountStatment)
				.then().assertThat().statusCode(200).extract().response();

		out.println("getAccountStatement response body is " + getAccountStatment.getBody().asString());
		String message = getAccountStatment.path("status.message");
		assertEquals(message, "Account statement Successfully Fetched");

		float totalIncome = getAccountStatment.path("data.totalIncome");
	}
	public static void getInvestmentStat(String tokenId) {
		//String TokenId,int loanId
		Response getInvestmentStat = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.header("x-auth-token", tokenId).log().all()
				.when().get(APIPath.apiPath.GetInvestmentStat)
				.then().assertThat().statusCode(200).extract().response();

		out.println("getInvestmentStatement response body is " + getInvestmentStat.getBody().asString());
		String InvestmentMessage = getInvestmentStat.path("status.message");
		assertEquals(InvestmentMessage,  "Investment Stat Successfully Fetched");

        int muchOverdueCount = getInvestmentStat.path("data.muchOverdueCount");
		out.println("muchOverdueCount is : " + muchOverdueCount);
		int totalInvested = getInvestmentStat.path("data.totalInvested");
		out.println("totalInvested is : " + totalInvested);
		int myPortfolioCount = getInvestmentStat.path("data.myPortfolioCount");
		out.println("myPortfolioCount is : " + myPortfolioCount);
		int activeLoan = getInvestmentStat.path("data.activeLoan");
		out.println("activeLoan is : " + activeLoan);
		int pending = getInvestmentStat.path("data.pending");
		out.println("pending is : " + pending);
		int bedDebt = getInvestmentStat.path("data.bedDebt");
		out.println("bedDebt is : " + bedDebt);
		int portfolioInvestor = getInvestmentStat.path("data.portfolioInvestor");
		out.println("portfolioInvestor is : " + portfolioInvestor);
		int avgROI = getInvestmentStat.path("data.avgROI");
		out.println("avgROI is : " + avgROI);
		int totalNetEarned = getInvestmentStat.path("data.totalNetEarned");
		out.println("totalNetEarned is : " + totalNetEarned);
		int current = getInvestmentStat.path("data.current");
		out.println("current is : " + current);
		int bedDebtCount = getInvestmentStat.path("data.bedDebtCount");
		out.println("bedDebtCount is : " + bedDebtCount);
		int overdue = getInvestmentStat.path("data.overdue");
		out.println("overdue is : " + overdue);
		int currentCount = getInvestmentStat.path("data.currentCount");
		out.println("currentCount is : " + currentCount);
		int myPortfolio = getInvestmentStat.path("data.myPortfolio");
		out.println("myPortfolio is : " + myPortfolio);
		int muchOverdue = getInvestmentStat.path("data.muchOverdue");
		out.println("muchOverdue is : " + muchOverdue);
		int avgAnnualRoi = getInvestmentStat.path("data.avgAnnualRoi");
		out.println("avgAnnualRoi is : " + avgAnnualRoi);
		int avgRemainingTerm = getInvestmentStat.path("data.avgRemainingTerm");
		out.println("avgRemainingTerm is : " + avgRemainingTerm);
		int overdueCount = getInvestmentStat.path("data.overdueCount");
		out.println("overdueCount is : " + overdueCount);
	}

}