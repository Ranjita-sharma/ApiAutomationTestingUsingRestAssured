package userController;

import api.configs.APIPath;
import api.configs.UserType;
import baseTest.BaseTest;
import com.pojo.request.signUpPojo.Borrower.SignUP_Borrower_ProfileCompletion_Pojo;
import com.pojo.request.signUpPojo.Borrower.SignUp_Borrower_Pojo;
import com.pojo.request.signUpPojo.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.configs.APIPath.apiPath.*;
import static api.configs.API_TestData.api_TestData.*;
import static org.testng.Assert.*;

public class SignUpTest extends BaseTest {

	/**
	 * The method used for creating the Resident type Investor profile
	 */
	public static @NotNull HashMap<String, Object> test_createResidentInvestorProfile() {
		SignUp_Investor_Resident_Pojo signup_resident_pojo = new SignUp_Investor_Resident_Pojo();
		signup_resident_pojo.setEmail(Investor_SignUpEmail);
		signup_resident_pojo.setIqamaId(Investor_SignUp_Iqama);
		signup_resident_pojo.setIsSaudi(false);
		signup_resident_pojo.setNationality(Investor_SignUp_Nationality);
		signup_resident_pojo.setPassword(QueryParamPWD);
		signup_resident_pojo.setPhoneNumber(Investor_SignUpPhone);
		signup_resident_pojo.setUserType(String.valueOf(UserType.INVESTOR));

		Response createResidentInvestor = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
					.body(signup_resident_pojo)
				.when().post(PostCreateInvestorProfile).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body of create resident investor profile is " + createResidentInvestor.getBody().asString());

		Boolean success_status = createResidentInvestor.path("data.success");
		Assert.assertTrue(success_status);
		String token = createResidentInvestor.path("data.authToken");
		int userId = createResidentInvestor.path("data.userId");

		HashMap<String,Object> signup_resident_response_map = new HashMap<>();
		signup_resident_response_map.put("token", token);
		signup_resident_response_map.put("userId", userId);
		return signup_resident_response_map;
	}
	/**
	 * The method used for completing the Resident type Investor profile after Registration
	 */
	public static @NotNull HashMap<String, Object>test_CompleteInvestorProfile(String token, int userId)
	{
		List<RamAnswerListDTO> ramAnswerList= new ArrayList<>();
		RamAnswerListDTO ramAnswerListDTO = new RamAnswerListDTO();
		ramAnswerListDTO.setUserId(userId);
		ramAnswerListDTO.setQuetionId(Post_Inv_quetionId);

		List<OptionSelectionDTO>optionSelectionDTOS = new ArrayList<>();

		ramAnswerListDTO.setOptionSelectionDTO(optionSelectionDTOS);

		ramAnswerList.add(ramAnswerListDTO);

		RamAnswers ramAnswers = new RamAnswers();
		ramAnswers.setA(Post_Inv_ramAnswer_A);
		ramAnswers.setB(Post_Inv_ramAnswer_B);
		ramAnswers.setE(Post_Inv_ramAnswer_E);
		ramAnswers.setG(Post_Inv_ramAnswer_G);

		List<RamQuestionA> ramQuestionAList = new ArrayList<>();
		RamQuestionA ramQuestionA = new RamQuestionA();
		ramQuestionA.setOptionId(Post_Inv_ramQuestionA_optionId);
		ramQuestionA.setUserId(userId);

		List<subOption> subOptions = new ArrayList<>();

		ramQuestionA.setSubOption(subOptions);
		ramQuestionAList.add(ramQuestionA);

		SignUP_Investor_ProfileCompletion_Pojo signup_profileCompletion_pojo = new SignUP_Investor_ProfileCompletion_Pojo();
		signup_profileCompletion_pojo.setAnnualIncome(Post_Inv_annualIncome);
		signup_profileCompletion_pojo.setCompanyName(Post_Inv_companyName);
		signup_profileCompletion_pojo.setDashboardStageStatus(Post_Inv_dashboardStageStatus);
		signup_profileCompletion_pojo.setDateOfBirth(Post_Inv_dateOfBirth);
		signup_profileCompletion_pojo.setEmployer(Post_Inv_employer);
		signup_profileCompletion_pojo.setEstimateAnnualDeposit(Post_Inv_estimateAnnualDeposit);
		signup_profileCompletion_pojo.setEstimateAnnualInvestment(Post_Inv_estimateAnnualInvestment);
		signup_profileCompletion_pojo.setFocalResponse(Post_Inv_focalResponse);
		signup_profileCompletion_pojo.setFullNameArab(Post_Inv_fullNameArab);
		signup_profileCompletion_pojo.setFullNameEng(Post_Inv_fullNameEng);
		signup_profileCompletion_pojo.setGender(Post_Inv_gender);
		signup_profileCompletion_pojo.setIdExpiryDate(Post_Inv_idExpiryDate);
		signup_profileCompletion_pojo.setIqamaId(Post_Inv_Iqama);
		signup_profileCompletion_pojo.setCreditedInvestor(Post_Inv_isCreditedInvestor);
		signup_profileCompletion_pojo.setJobStatus(Post_Inv_jobStatus);
		signup_profileCompletion_pojo.setJobTitle(Post_Inv_jobTitle);
		signup_profileCompletion_pojo.setNationalAddress(Post_Inv_nationalAddress);
		signup_profileCompletion_pojo.setNationality(Post_Inv_nationality);
		signup_profileCompletion_pojo.setNatureOfBusiness(Post_Inv_natureOfBusiness);
		signup_profileCompletion_pojo.setNationality(Post_Inv_nationality);
		signup_profileCompletion_pojo.setRamAnswerListDTO(ramAnswerList);
		signup_profileCompletion_pojo.setRamAnswers(ramAnswers);
		signup_profileCompletion_pojo.setRamQuestionA(ramQuestionAList);
		signup_profileCompletion_pojo.setSourceOfInvestment(Post_Inv_sourceOfInvestment);
		signup_profileCompletion_pojo.setUserId(userId);

		signup_profileCompletion_pojo.setEstimateAnnualDeposit(Post_Inv_estimateAnnualDeposit);

		Response completeInvestorProfile = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.header("x-auth-token",token).log().all().body(signup_profileCompletion_pojo)
				.when().post(Post_CompleteInvestorProfile).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body of complete resident investor profile is " + completeInvestorProfile.getBody().asString());
		Boolean success_status = completeInvestorProfile.path("data.success");
		Assert.assertTrue(success_status);
		int user_Id = completeInvestorProfile.path("data.userId");
		System.out.println("user id after investor profile completion is : " +user_Id);
		int investorId = completeInvestorProfile.path("data.investorId");
		System.out.println("investorId after investor profile completion is : " +investorId);

		HashMap<String,Object> investorProfileCompletion_response_map = new HashMap<>();
		investorProfileCompletion_response_map.put("userId", user_Id);
		investorProfileCompletion_response_map.put("investorId", investorId);

		return investorProfileCompletion_response_map;
	}
	/**
	 * The method used for send the OTP during the user profile creation
	 */
	public static void test_SendOTP_UserProfileCreation(String mobile, String otpType){
		HashMap<String,Object> sendOTP_SignUp = new HashMap<>();
		sendOTP_SignUp.put("mobile",mobile);
		sendOTP_SignUp.put("otpType",otpType);

		Response sendOTP_SignUp_res = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.body(sendOTP_SignUp)
				.when().post(APIPath.apiPath.Post_SendSignUpOTP).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + sendOTP_SignUp_res.getBody().asString());
		String message = sendOTP_SignUp_res.path("status.message");
		assertEquals(message, "OTP sent successfully");
	}
	/**
	 * The method used for verifying the OTP during the user profile creation
	 */
	public static void test_VerifyOTP_UserProfileCreation(String mobile,String otp, String otpType){
		HashMap<String,Object> sendOTP_SignUp = new HashMap<>();
		sendOTP_SignUp.put("mobile",mobile);
		sendOTP_SignUp.put("otp",otp);
		sendOTP_SignUp.put("otpType",otpType);

		Response verifyOTP_SignUp = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.body(sendOTP_SignUp)
				.when().post(APIPath.apiPath.Post_SignUpOTPVerify).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + verifyOTP_SignUp.getBody().asString());
		String message = verifyOTP_SignUp.path("data.description");
		assertEquals(message, "OTP verify successfully");
	}
	/**
	 * The method used for creating the Borrower profile (Registration)
	 */
	public static @NotNull HashMap<String, Object> test_BorrowerProfileCreation(){
		SignUp_Borrower_Pojo signUp_Borrower_Pojo = new SignUp_Borrower_Pojo();
		signUp_Borrower_Pojo.setFirstName(Borrower_SignUp_firstName);
		signUp_Borrower_Pojo.setLastName(Borrower_SignUp_lastName);
		signUp_Borrower_Pojo.setPhoneNumber(Borrower_SignUpPhone);
		signUp_Borrower_Pojo.setPassword(userPassword);
		signUp_Borrower_Pojo.setEmail(Borrower_SignUpEmail);
		signUp_Borrower_Pojo.setUserType(String.valueOf(UserType.BORROWER));
		signUp_Borrower_Pojo.setCrNumber(Borrower_SignUp_crNumber);
		signUp_Borrower_Pojo.setCrStatus(Borrower_SignUp_crStatus);
		signUp_Borrower_Pojo.setCrExpiryDate(Borrower_SignUp_crExpiryDate);
		signUp_Borrower_Pojo.setJobTitle(Borrower_SignUp_jobTitle);
		signUp_Borrower_Pojo.setCompanyNameArabic(Borrower_SignUp_companyNameArabic);
        signUp_Borrower_Pojo.setCapital(Borrower_SignUp_capital);
		signUp_Borrower_Pojo.setEntityStructure(Borrower_SignUp_entityStructure);
		signUp_Borrower_Pojo.setNationalAddress(Borrower_SignUp_nationalAddress);
		signUp_Borrower_Pojo.setCrEntityNumber(Borrower_SignUp_crEntityNumber);
		signUp_Borrower_Pojo.setIssueDate(Borrower_SignUp_issueDate);

		Response signUp_borrower = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.body(signUp_Borrower_Pojo)
				.when().post(APIPath.apiPath.Post_CreateBorrowerAccount).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body is " + signUp_borrower.getBody().asString());

		int userId = signUp_borrower.path("data.userId");
		String actual_firstName = signUp_borrower.path("data.firstName");
		assertEquals(actual_firstName,Borrower_SignUp_firstName,"firstname is mismatched");
		String actual_LastName = signUp_borrower.path("data.lastName");
		assertEquals(actual_LastName,Borrower_SignUp_lastName,"lastname is mismatched");
		String actual_phoneNumber = signUp_borrower.path("data.phoneNumber");
		assertEquals(actual_phoneNumber,Borrower_SignUpPhone,"phoneNumber is mismatched");
		String token = signUp_borrower.path("data.authToken");
		assertNotNull(token,"token is null");
		String capital = signUp_borrower.path("data.capital");
		assertNotNull(capital,"capital value is null");
		Boolean success = signUp_borrower.path("data.success");
		assertTrue(success,"success status is false");

		HashMap<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("firstName",actual_firstName);
		map.put("lastName",actual_LastName);
		map.put("phoneNumber",actual_phoneNumber);
		map.put("authToken",token);
		map.put("capital",capital);
		map.put("success",true);

      return map;
	}
	/**
	 * The method used for completing the Borrower profile after Registration
	 */
	public static @NotNull HashMap<String, Object> test_BorrowerProfileCompletion(String token, @NotNull String phoneNumber) {
		SignUP_Borrower_ProfileCompletion_Pojo borrowerProfileCompletion_pojo = new SignUP_Borrower_ProfileCompletion_Pojo();
		borrowerProfileCompletion_pojo.setTradingName(Borrower_tradingName);
		borrowerProfileCompletion_pojo.setPhoneNumber(phoneNumber.replace("+966","0"));
		borrowerProfileCompletion_pojo.setVatNumber(Borrower_vatNumber);
		borrowerProfileCompletion_pojo.setAnnualRevenue(Borrower_annualRevenue);
		borrowerProfileCompletion_pojo.setCurrentClientsCount(Borrower_currentClientsCount);
		borrowerProfileCompletion_pojo.setCurrentEmployeesCount(Borrower_currentEmployeesCount);
		borrowerProfileCompletion_pojo.setNitiqat(Borrower_nitiqat);
		borrowerProfileCompletion_pojo.setOrgStatus(Borrower_orgStatus);
		borrowerProfileCompletion_pojo.setBankName(Borrower_bankName);
		borrowerProfileCompletion_pojo.setIBAN(Borrower_IBAN);
		borrowerProfileCompletion_pojo.setBenefeciaryName(Borrower_benefeciaryName);
		borrowerProfileCompletion_pojo.setDashboardStageStatus(Borrower_dashboardStageStatus);

		Response completeBorrowerProfile = RestAssured.given().baseUri(APIPath.apiPath.BaseURL).contentType("application/json")
				.header("x-auth-token", token).log().all().body(borrowerProfileCompletion_pojo)
				.when().post(PostProfileComplete_borrower).then().log()
				.all().assertThat().statusCode(200).extract().response();
		System.out.println("Response body of complete borrower profile is " + completeBorrowerProfile.getBody().asString());

		int id = completeBorrowerProfile.path("data.id");

		String crExpiryDate = completeBorrowerProfile.path("data.crExpiryDate");
		assertNotNull(crExpiryDate,"crExpiryDate is null");

		String capital = completeBorrowerProfile.path("data.capital");
		assertNotNull(capital,"capital is null");

		String companyVintage = completeBorrowerProfile.path("data.companyVintage");
		assertNotNull(companyVintage,"companyVintage is null");

		String organisationPhoneNumber = completeBorrowerProfile.path("data.organisationPhoneNumber");
		assertNotNull(organisationPhoneNumber,"organisationPhoneNumber is null");

		String employeesCountGroup = completeBorrowerProfile.path("data.employeesCountGroup");
		assertNotNull(employeesCountGroup,"employeesCountGroup is null");

		String clientsCountGroup = completeBorrowerProfile.path("data.clientsCountGroup");
		assertNotNull(clientsCountGroup,"clientsCountGroup is null");

		String annualRevenue = completeBorrowerProfile.path("data.annualRevenue");
		assertNotNull(annualRevenue,"annualRevenue is null");

		HashMap<String,Object> signup_borrower_response_map = new HashMap<>();
		signup_borrower_response_map.put("token", token);
		signup_borrower_response_map.put("id", id);
		signup_borrower_response_map.put("crExpiryDate", crExpiryDate);

		return signup_borrower_response_map;
	}
}
