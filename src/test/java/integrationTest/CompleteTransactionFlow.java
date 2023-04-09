package integrationTest;

import api.configs.APIPath;
import com.pojo.request.AdminPojo.AdminDepositFund;
import com.pojo.request.AdminPojo.AdminWithdrawFund;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import userController.LoginTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;
import static userController.LoginTest.test_InvestorLogin;

public class CompleteTransactionFlow {
    LoginTest getTokenId = new LoginTest();

    public static int adminDepositFundForConsumer(int userId, String tokenId) {
        AdminDepositFund admin_deposit = new AdminDepositFund();
        admin_deposit.setUserId(userId);
        admin_deposit.setAmount("2000");
        admin_deposit.setType("CREDIT");
        admin_deposit.setOnHold(false);
        admin_deposit.setDescription("Admin Deposit");
        admin_deposit.setReferenceId("Admin fund deposit");
        admin_deposit.setSourceId(null);
        admin_deposit.setSourceType("BANK_TRANSFER");
        admin_deposit.setAmountType("main");

        Response depositFund = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .header("x-auth-token", tokenId)
                .body(admin_deposit).log().all()
                .when().post(APIPath.apiPath.AdminDepositFund).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + depositFund.getBody().asString());

        String isFundDeposited = depositFund.path("status.message");
        assertThat(isFundDeposited, equalTo("successfully executed"));

        int transactionId = depositFund.path("data.transactionId");
        System.out.println("deposit fund tansaction id is : " + transactionId);
        return transactionId;
    }
    public static int admin_DepositFundForConsumer(int userId, String tokenId,double amount) {
        AdminDepositFund admin_deposit = new AdminDepositFund();
        admin_deposit.setUserId(userId);
        admin_deposit.setAmount(String.valueOf(amount));
        admin_deposit.setType("CREDIT");
        admin_deposit.setOnHold(false);
        admin_deposit.setDescription("Admin Deposit");
        admin_deposit.setReferenceId("Admin fund deposit");
        admin_deposit.setSourceId(null);
        admin_deposit.setSourceType("BANK_TRANSFER");
        admin_deposit.setAmountType("main");

        Response depositFund = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .header("x-auth-token", tokenId)
                .body(admin_deposit).log().all()
                .when().post(APIPath.apiPath.AdminDepositFund).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + depositFund.getBody().asString());

        String isFundDeposited = depositFund.path("status.message");
        assertThat(isFundDeposited, equalTo("successfully executed"));

        int transactionId = depositFund.path("data.transactionId");
        System.out.println("deposit fund tansaction id is : " + transactionId);
        return transactionId;
    }

    public int adminWithdrawFundForConsumer(int userId, String tokenId) {
        //adminDepositFundForConsumer
        AdminWithdrawFund admin_withdraw = new AdminWithdrawFund();
        admin_withdraw.setUserId(userId);
        admin_withdraw.setAmount("500");
        admin_withdraw.setReferenceId("Admin withdraw amount");
        Response withdrawFund = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .header("x-auth-token", tokenId)
                .body(admin_withdraw).log().all()
                .when().post(APIPath.apiPath.AdminWithdrawFund).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + withdrawFund.getBody().asString());

        String fundWithdraw = withdrawFund.path("status.message");
        assertThat(fundWithdraw, equalTo("successfully executed"));

        int transactionId = withdrawFund.path("data.transactionId");
        System.out.println("deposit fund tansaction id is : " + transactionId);

        int withdraw_userId = withdrawFund.path(("data.userId"));
        System.out.println("user id is " + withdraw_userId);

        return transactionId;
    }

    @Test
    public Map<Object, Object> adminGetTransactionDetails(String tokenId, int transactionId) {
        List<Integer> getTransacIds = new ArrayList<Integer>();
        //passing the multiple query parameters as a map
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", "0");
        map.put("limit", "10");
        map.put("userIds", "Default");
        map.put("userType", "Default");
        map.put("bankName", "Default");
        map.put("sourceType", "Default");
        map.put("status", "Default");
        map.put("uniqueId", "Default");
        map.put("loanId", "Default");
        map.put("iban", "Default");
        map.put("viban", "Default");
        map.put("fromAmount", "Default");
        map.put("toAmount", "Default");
        map.put("startDate", "Default");
        map.put("endDate", "Default");
        map.put("referenceId", "Default");

        Response getTransacDetails = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .header("x-auth-token", tokenId)
                .queryParams(map).log().all()
                .when().get(APIPath.apiPath.GetTransactionDetails).then().body("data.transactions[0]", hasValue(transactionId)).assertThat().statusCode(200).extract().response();
        System.out.println("Transactions response body is :" + getTransacDetails.getBody().asString());

        String getDetails = getTransacDetails.path("status.message");
        assertThat(getDetails, equalTo("successfully executed"));

        return getTransacDetails.jsonPath().getMap("data.transactions[0]");
    }

    public int test_getUserDetails() {
        Map<String,Object> getUser_Token_Id = test_InvestorLogin();
        getUser_Token_Id.get("userId");
        getUser_Token_Id.get("token");
        int userId = (int) getUser_Token_Id.get("userId");
        String token = (String)getUser_Token_Id.get("token") ;

        Response getWalletDetails = given().baseUri(APIPath.apiPath.BaseURL).header("x-auth-token", token)
                .log()
                .all().when().get(APIPath.apiPath.GetWalletDetails).then().assertThat().statusCode(200).extract().response();
        System.out.println(getWalletDetails.getBody().asString());
        String amount = getWalletDetails.path("data.amount");
        String bankname = getWalletDetails.path("data.bankName");
        String vIBAN = getWalletDetails.path("data.virtualIban");
        int accountNum = getWalletDetails.path("data.accountNumber");
        String onHoldAmount = getWalletDetails.path("data.onHoldAmount");
        String overDraftAmount = getWalletDetails.path("data.overDraftAmount");
        int user_Id = getWalletDetails.path("data.userId");

        return userId;
    }

    @Test
    public void test_getTransactionsIdForAdminDepositFund() {
        String getadminTokenId = getTokenId.test_adminLogin();
        int userId = test_getUserDetails();
        int transactionId = adminDepositFundForConsumer(userId, getadminTokenId);
        Map<Object, Object> adminGetTransacDetails = adminGetTransactionDetails(getadminTokenId, transactionId);
        System.out.println("Transaction id is :" + adminGetTransacDetails.get("id"));
        int actualTransactionId = (int) adminGetTransacDetails.get("id");
        assertThat(actualTransactionId, equalTo(transactionId));
        String status = (String) adminGetTransacDetails.get("status");
        assertThat(status, equalTo("COMPLETED"));
        String type = (String) adminGetTransacDetails.get("type");
        assertThat(type, equalTo("CREDIT"));
    }

    @Test
    public void test_getTransactionsIdForAdminWithdrawFund() {
        String getadminTokenId = getTokenId.test_adminLogin();

        Complete_Borrower_Flow borrowerFlowAfterLogin = new Complete_Borrower_Flow();
        int actualUserId = borrowerFlowAfterLogin.getUserWalletDetailAndDepositFund();
     //   int userId = test_getUserDetails();
        int transaction_Id = adminWithdrawFundForConsumer(actualUserId, getadminTokenId);
        System.out.println("Admin withdraw amount transacation id is :" + transaction_Id);

        Map<Object, Object> adminGetWithdrawTransacDetails = adminGetTransactionDetails(getadminTokenId, transaction_Id);
        System.out.println("Transaction id is :" + adminGetWithdrawTransacDetails.get("id"));

        int expectedUserId = (int) adminGetWithdrawTransacDetails.get("userid");
        assertThat(actualUserId,equalTo(expectedUserId));

        int actualwithdrawTransactionId = (int) adminGetWithdrawTransacDetails.get("id");
        assertThat(actualwithdrawTransactionId, equalTo(transaction_Id));

        String status = (String) adminGetWithdrawTransacDetails.get("status");
        assertThat(status, equalTo("COMPLETED"));

        String type = (String) adminGetWithdrawTransacDetails.get("type");
        assertThat(type, equalTo("DEBIT"));
    }
}