package integrationTest;

import api.configs.APIPath;
import api.configs.otpType;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import qaFactory.DB_Connections;
import userController.LoginTest;
import utils.ExtentReportListener;

import java.sql.SQLException;
import java.util.Map;

import static api.configs.API_TestData.api_TestData.Borrower_SignUpPhone;
import static io.restassured.RestAssured.given;
import static userController.SignUpTest.*;

@Listeners(ExtentReportListener.class)
public class Complete_Borrower_Flow {

    LoginTest get_token = new LoginTest();
    @Test
    public static void Validate_BorrowerProfileCreationFlow() throws SQLException {
        DB_Connections dbConnections = new DB_Connections();
        dbConnections.DB();
        test_SendOTP_UserProfileCreation(Borrower_SignUpPhone, String.valueOf(otpType.signup));
        String getOTP = dbConnections.get_otp(Borrower_SignUpPhone,String.valueOf(otpType.signup));
        test_VerifyOTP_UserProfileCreation(Borrower_SignUpPhone,getOTP,String.valueOf(otpType.signup));
        //Borrower account creation
        Map<String,Object> getUserDetails = test_BorrowerProfileCreation();
        int userId = (int) getUserDetails.get("userId");
        String token = (String) getUserDetails.get("authToken");
        String phoneNumber = (String) getUserDetails.get("phoneNumber");
        System.out.println("user id is : "+ userId);
        Map<String,Object> getProfileDetails = test_BorrowerProfileCompletion(token,phoneNumber);
        int id = (int) getProfileDetails.get("id");
        String crExpiryDate = (String) getProfileDetails.get("crExpiryDate");
        System.out.println("crExpiryDate is : "+ crExpiryDate);
    }
    @Test
    public int getUserWalletDetailAndDepositFund() {
//        //get the borrower token id
        Map<String,Object> getBorrowerTokenId = get_token.test_BorrowerLogin();

        Response getWalletDetails = given().baseUri(APIPath.apiPath.BaseURL).header("x-auth-token", getBorrowerTokenId)
                .log()
                .all().when().get(APIPath.apiPath.GetWalletDetails).then().assertThat().statusCode(200).extract().response();
        System.out.println(getWalletDetails.getBody().asString());
        String amount = getWalletDetails.path("data.amount");
        String bank_name = getWalletDetails.path("data.bankName");
        String vIBAN = getWalletDetails.path("data.virtualIban");
        int accountNum = getWalletDetails.path("data.accountNumber");
        String onHoldAmount = getWalletDetails.path("data.onHoldAmount");
        String overDraftAmount = getWalletDetails.path("data.overDraftAmount");
        int userId = getWalletDetails.path("data.userId");
        //int borrowerId = getWalletDetails.path("data.userId");
        double walletAmount = Double.parseDouble(amount);

        if (walletAmount <= 5000.00) {
            //get the admin token Id
            String getAdminTokenId = get_token.test_adminLogin();
            CompleteTransactionFlow amountDeposit = new CompleteTransactionFlow();
            int transactionId = amountDeposit.adminDepositFundForConsumer(userId, getAdminTokenId);
            System.out.println("Admin deposit the fund :" + transactionId);
        } else {
            System.out.println("Available wallet balance is : " + amount);

        }
        return userId;
    }

    public static double getUserWalletDetail(String token) {
//        //get the borrower token id
//        String getBorrowerTokenId = get_token.test_BorrowerLogin();

        Response getWalletDetails = given().baseUri(APIPath.apiPath.BaseURL).header("x-auth-token", token)
                .log()
                .all().when().get(APIPath.apiPath.GetWalletDetails).then().assertThat().statusCode(200).extract().response();
        System.out.println(getWalletDetails.getBody().asString());
        String amount = getWalletDetails.path("data.amount");
        String bank_name = getWalletDetails.path("data.bankName");
        String vIBAN = getWalletDetails.path("data.virtualIban");
        int accountNum = getWalletDetails.path("data.accountNumber");
        String onHoldAmount = getWalletDetails.path("data.onHoldAmount");
        String overDraftAmount = getWalletDetails.path("data.overDraftAmount");
        int userId = getWalletDetails.path("data.userId");
        //int borrowerId = getWalletDetails.path("data.userId");
        double walletAmount = Double.parseDouble(amount);
        System.out.println("wallet amount is : " + walletAmount );


        return walletAmount;
    }
}