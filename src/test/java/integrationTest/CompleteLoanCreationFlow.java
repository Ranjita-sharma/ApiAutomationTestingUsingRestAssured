package integrationTest;

import api.configs.APIPath;
import api.configs.DocumentType;
import api.configs.LoanApplicationStatus;
import api.configs.otpType;
import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.UploadDocument_Investors.FileList;
import com.pojo.request.loanPojo.Admin_CreateLoanProposal.Admin_Create_Loan_Proposal;
import com.pojo.request.loanPojo.ApplyForLoan.DocumentReqDTOList;
import com.pojo.request.loanPojo.ApplyForLoan.PostApplyLoan;
import com.pojo.request.loanPojo.LoanPublished;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import qaFactory.DB_Connections;
import userController.LoginTest;
import utils.ExtentReportListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static api.configs.APIPath.apiPath.GetDashboardDetails;
import static api.configs.API_TestData.api_TestData.*;
import static integrationTest.CompleteTransactionFlow.admin_DepositFundForConsumer;
import static integrationTest.Complete_Borrower_Flow.getUserWalletDetail;
import static integrationTest.Complete_Investor_Flow.GetDashboardDetails;
import static integrationTest.Complete_Investor_Flow.GetLoansStatusForInvestment;
import static integrationTest.Complete_Investor_Flow.getLoanDetails;
import static integrationTest.Complete_Investor_Flow.investorApplyBidManually;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static userController.LoginTest.test_InvestorLogin;
import static userController.LoginTest.userLogin;
import static userController.SignUpTest.*;
import static userController.SignUpTest.test_BorrowerProfileCompletion;

@Listeners(ExtentReportListener.class)
public class CompleteLoanCreationFlow extends BaseTest {
    public String getTokenId;
    //get the admin token
    static LoginTest gettoken = new LoginTest();

    @Test
    public void GetAutoInvestDetails() {
        test.log(LogStatus.INFO, "GetToken test is starting...");
        //GettokeniD = GetToken();
        Map<String,Object> getUser_Token_Id = test_InvestorLogin();
        getUser_Token_Id.get("userId");
        getUser_Token_Id.get("token");
        int userId = (int) getUser_Token_Id.get("userId");
        String token = (String)getUser_Token_Id.get("token") ;
        test.log(LogStatus.INFO, "GetAutoInvestDetails test is starting...");
        Response autoInvest_response = given().baseUri(APIPath.apiPath.BaseURL).header("x-auth-token", token)
                .log()
                .all().when().get(APIPath.apiPath.GetAutoInvestDetails)
                .then().log().all().assertThat().statusCode(200).extract().response();
        test.log(LogStatus.INFO, "Test is ended...");

    }

//	@Test
/*	public void GetRiskConfig() {
		test.log(LogStatus.INFO, "Test is started...");
		Response getRiskConfig = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
				.header("x-auth-token", getToken).when().get(APIPath.apiPath.GetRiskConfig);
		APIVerification.responseCodeValidation(getRiskConfig, 200);
		APIVerification.responseTimeValidation(getRiskConfig);
		test.log(LogStatus.INFO, "Test is ended...");

	}*/

//    @Test
//    public void CreateLoanAndPublished_ManualInvest() throws IOException, InterruptedException, ParseException {
//        test.log(LogStatus.INFO, "Test is started...");
//
//        Map<String,Object> get_BorrowerDetails = gettoken.test_BorrowerLogin();
//        String get_BorrowerToken_id = (String) get_BorrowerDetails.get("authToken");
//        int borrower_userId = (int) get_BorrowerDetails.get("userId");
//        System.out.println("Borrower output is" + get_BorrowerToken_id);
//        int borrowerId = borrowerDashboardDetails(get_BorrowerToken_id);
//        GetStaticData();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> upload_file = objectMapper.readValue(new File(".\\src\\main\\resources\\JsonFiles\\PostLoanApply.json"),
//                new TypeReference<Map<String, Object>>() {
//                });
//        upload_file.put("borrowerId", borrowerId);
//
//        String updated = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(upload_file);
//        System.out.println(updated);
//        // loan has been created
//        Response createLoan = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
//                .contentType("application/json").header("x-auth-token", get_BorrowerToken_id).log().all().body(updated).when()
//                .post(APIPath.apiPath.PostApplyLoan).then().extract().response();
//        int loanid = createLoan.path("data.loanId");
//
//        System.out.println("loanid is :" + loanid);
//        System.out.println("Borrower has applied for loan " + createLoan.getBody().asString());
//        borrowerFetchLoanDetails(loanid, get_BorrowerToken_id);
//        borrowerUploadInvoiceDocument(loanid, borrowerId, get_BorrowerToken_id);
//        borrowerLoanUploadDocument(loanid, borrowerId, get_BorrowerToken_id);
//        String getAdmintokeniD = gettoken.test_adminLogin();
//        adminAddandUpdateLoanGuarantees(loanid, getAdmintokeniD);
//        adminCreateLoanProposal(loanid, getAdmintokeniD);
//
//        adminSentLoanProposal(loanid, getAdmintokeniD);
//        Map<String, Object> filtered_ProposalSent = admin_loanFilterationList("PROPOSAL_SENT", getAdmintokeniD,loanid);
//        System.out.println("ProposalSent Filtered response is :" + filtered_ProposalSent);
//        Assert.assertSame(loanid, filtered_ProposalSent.get("loanId"), "loan id is not available in the PROPOSAL_SENT filter list");
//
//        adminProposalApproved(loanid, getAdmintokeniD);
//        Map<String, Object> filter_ProposalApproved = admin_loanFilterationList("PROPOSAL_APPROVED", getAdmintokeniD,loanid);
//        System.out.println("Proposal Approved Filtered response is :" + filter_ProposalApproved);
//        Assert.assertSame(loanid, filter_ProposalApproved.get("loanId"), "loan id is not available in the PROPOSAL_APPROVED filter list");
//
//        adminLoanApproved(loanid, getAdmintokeniD);
//        Map<String, Object> filter_LoanApproved = admin_loanFiltrationList("LOAN_APPROVED", getAdmintokeniD,loanid);
//        System.out.println("loan approved Filtered response is :" + filter_LoanApproved);
//        Assert.assertSame(loanid, filter_LoanApproved.get("loanId"), "loan id is not available in the LOAN_APPROVED filter list");
//
//        adminContractAccepted(loanid, getAdmintokeniD);
//        Map<String, Object> filter_ContractAccepted = admin_loanFilterationList("CONTRACT_ACCEPTED", getAdmintokeniD,loanid);
//        System.out.println("Contract Accepted filter response is :" + filter_ContractAccepted);
//        Assert.assertSame(loanid, filter_ContractAccepted.get("loanId"), "loan id is not available in the ContractAccepted filter list");
//
//        adminLoanPublished(loanid, getAdmintokeniD);
//        int getOpportunity_Id = adminGetLoanDetails(loanid, getAdmintokeniD);
//        System.out.println("Opportunity id is :" + getOpportunity_Id);
//
////        Map<Object, Object> filter_published = admin_loanFilterationList("PUBLISHED",getAdmintokeniD);
////        System.out.println(" Published loan Filtered response is :" + filter_published);
////        Assert.assertSame(loanid,filter_published.get("loanId"),"loan id is not available in the Published filter list");
//
//        Vector getInvestorDetails = gettoken.test_InvestorLoginUsingVector();
//        String InvestorTokenId = (String) getInvestorDetails.get(0);
//        int userId = (int) getInvestorDetails.get(1);
//        String investorPhoneNum = (String) getInvestorDetails.get(2);
//        System.out.println("Investor token ID is : " + InvestorTokenId);
//        System.out.println("userId is : " + userId);
//        System.out.println("Investor phone number is : " + investorPhoneNum);
//
//        Complete_Investor_Flow investorFlow = new Complete_Investor_Flow();
//        Map<String,Object> get_investorId = GetDashboardDetails(InvestorTokenId);
//        System.out.println("Investor id is : " + get_investorId);
//
//        int requestedLoanAmount = getLoanDetails(InvestorTokenId, loanid);
//
//        System.out.println("value of requestedLoanAmount " + requestedLoanAmount);
//       // investorApplyBidManually(InvestorTokenId, loanid, get_investorId, getOpportunity_Id, 1000, requestedLoanAmount);
//
//        adminAllFundsDetails(loanid, getAdmintokeniD);
//        adminPartiallyFunded(getOpportunity_Id, getAdmintokeniD);
//        adminDisbursedLoan(loanid, getAdmintokeniD);
//        adminCreateContractDuringRepayment(loanid, getAdmintokeniD);
//        adminLoanUnderRepayment(loanid, getAdmintokeniD);
//        adminLoanAmountPaid(loanid, getAdmintokeniD);
//        adminLoanCompleted(loanid, getAdmintokeniD);
//        test.log(LogStatus.INFO, "Complete loan creation flow has been tested successfully...");
//
//    }
    @Test
    public void Create_LoanAndPublished_ManualInvest() throws IOException, ParseException, SQLException {
   // public void integration() throws IOException, InterruptedException, ParseException {
        test.log(LogStatus.INFO, "Test is started...");
        DB_Connections dbConnections = new DB_Connections();
        dbConnections.DB();
        test_SendOTP_UserProfileCreation(Borrower_SignUpPhone, String.valueOf(otpType.signup));
        String getOTP = dbConnections.get_otp(Borrower_SignUpPhone,String.valueOf(otpType.signup));
        test_VerifyOTP_UserProfileCreation(Borrower_SignUpPhone,getOTP,String.valueOf(otpType.signup));
        //Borrower account creation
        Map<String,Object> getUserDetails = test_BorrowerProfileCreation();
        int userId_br = (int) getUserDetails.get("userId");
        String token = (String) getUserDetails.get("authToken");
        String phoneNumber = (String) getUserDetails.get("phoneNumber");
        System.out.println("user id is : "+ userId_br);
        Map<String,Object> getProfileDetails = test_BorrowerProfileCompletion(token,phoneNumber);
        int id = (int) getProfileDetails.get("id");
        String crExpiryDate = (String) getProfileDetails.get("crExpiryDate");
        System.out.println("crExpiryDate is : "+ crExpiryDate);
        String user_emailId = dbConnections.get_UserEmail(userId_br);
        //endregion
        //region Investor login
        Map<String, Object> get_BorrowerDetails = userLogin(user_emailId);
        //Calling borrower login api to get the token id and borrower id
        //Map<String,Object> get_BorrowerDetails = gettoken.test_BorrowerLogin();
        String borrowerToken_id = (String) get_BorrowerDetails.get("authToken");
        int borrower_userId = (int)get_BorrowerDetails.get("userId");
        System.out.println("Borrower output is" + borrowerToken_id);
        int borrowerId = borrowerDashboardDetails(borrowerToken_id);
        //Get the borrower static data
        GetStaticData();
        //get the loan id after applying for a loan
//        Map<String,Object>  getLoanId = applyForLoan(get_BorrowerTokenId,borrowerId);
        int loanId = applyForLoan(borrowerToken_id,borrowerId);
        System.out.println("Integration test loanId is :" + loanId);
        //Get the loan details after applying a loan from borrower's end
        borrowerFetchLoanDetails(loanId, borrowerToken_id);
        //Borrower upload the Invoice document
        borrowerUploadInvoiceDocument(loanId, borrowerId, borrowerToken_id);
        //Borrower upload the loan documents
        borrowerLoanUploadDocument(loanId, borrowerId, borrowerToken_id);
        //Calling admin login api to get the token id
        String getAdminTokenId = gettoken.test_adminLogin();
        //Get all the guarantees name
        adminGetAllGuaranteesName(getAdminTokenId);
        //Add and update the loan gurantees name
        adminAddandUpdateLoanGuarantees(loanId, getAdminTokenId);
        //Get the loan gurantees name
        adminGetLoanGuarantees(loanId,getAdminTokenId);
        //Loan Filtration list based on loan status"UNDER_REVIEW'
        Map<String, Object> filtered_underReview = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.UNDER_REVIEW),getAdminTokenId, loanId);
        int getLoanId_UR = (int) filtered_underReview.get("loanId");
        int getBorrowerId_UR = (int) filtered_underReview.get("borrowerId");
        String getStatus_UR = String.valueOf(filtered_underReview.get("status"));
        System.out.println("UNDER_REVIEW page filter loanId is : " + getLoanId_UR );
        System.out.println("UNDER_REVIEW page filter borrowerId is : " + getBorrowerId_UR );
        System.out.println("UNDER_REVIEW page filter status is : " + getStatus_UR );
        Assert.assertSame(loanId, filtered_underReview.get("loanId"), "loan id is not available in the UNDER_REVIEW filter list");
       //admin create the loan Proposal
        admin_CreateLoanProposal(loanId,getAdminTokenId);
        //admin sent the loan Proposal
        adminSentLoanProposal(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"PROPOSAL_SENT'
        Map<String, Object> filtered_ProposalSent = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PROPOSAL_SENT),getAdminTokenId, loanId);
        int getLoanId_PS = (int) filtered_ProposalSent.get("loanId");
        int getBorrowerId_PS = (int) filtered_ProposalSent.get("borrowerId");
        String getStatus_PS = String.valueOf(filtered_ProposalSent.get("status"));
        System.out.println("ProposalSent page filter loanId is : " + getLoanId_PS );
        System.out.println("ProposalSent filter borrowerId is : " + getBorrowerId_PS );
        System.out.println("ProposalSent filter status is : " + getStatus_PS );
        Assert.assertSame(loanId, filtered_ProposalSent.get("loanId"), "loan id is not available in the PROPOSAL_SENT filter list");
        //admin approve the loan Proposal
       adminProposalApproved(loanId, getAdminTokenId);
       //Loan Filtration list based on loan status"PROPOSAL_APPROVED'
         Map<String, Object> filter_ProposalApproved = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PROPOSAL_APPROVED),getAdminTokenId, loanId);
        int getLoanId_PA = (int) filter_ProposalApproved.get("loanId");
        int getBorrowerId_PA = (int) filter_ProposalApproved.get("borrowerId");
        String getStatus_PA = String.valueOf(filter_ProposalApproved.get("status"));
        System.out.println("PROPOSAL_APPROVED page filter loanId is : " + getLoanId_PA );
        System.out.println("PROPOSAL_APPROVED page filter borrowerId is : " + getBorrowerId_PA );
        System.out.println("PROPOSAL_APPROVED page filter status is : " + getStatus_PA );
        Assert.assertSame(loanId, filter_ProposalApproved.get("loanId"), "loan id is not available in the PROPOSAL_APPROVED filter list");
       //admin approve the loan
       adminLoanApproved(loanId, getAdminTokenId);
       //Loan Filtration list based on loan status"LOAN_APPROVED'
       Map<String, Object> filter_LoanApproved = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.LOAN_APPROVED),getAdminTokenId, loanId);
        int getLoanId_LA = (int) filter_LoanApproved.get("loanId");
        int getBorrowerId_LA = (int) filter_LoanApproved.get("borrowerId");
        String getStatus_LA = String.valueOf(filter_LoanApproved.get("status"));
        System.out.println("LOAN_APPROVED page filter loanId is : " + getLoanId_LA );
        System.out.println("LOAN_APPROVED page filter borrowerId is : " + getBorrowerId_LA );
        System.out.println("LOAN_APPROVED page filter status is : " + getStatus_LA );
        Assert.assertSame(loanId, filter_LoanApproved.get("loanId"), "loan id is not available in the LOAN_APPROVED filter list");
        //admin accept the contract
        adminContractAccepted(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"LOAN_APPROVED'
        Map<String, Object> filter_ContractAccepted = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.CONTRACT_ACCEPTED),getAdminTokenId, loanId);
        int getLoanId_CA = (int) filter_ContractAccepted.get("loanId");
        int getBorrowerId_CA = (int) filter_ContractAccepted.get("borrowerId");
        String getStatus_CA = String.valueOf(filter_ContractAccepted.get("status"));
        System.out.println("CONTRACT_ACCEPTED page filter loanId is : " + getLoanId_CA );
        System.out.println("CONTRACT_ACCEPTED page filter borrowerId is : " + getBorrowerId_CA );
        System.out.println("CONTRACT_ACCEPTED page filter status is : " + getStatus_CA );
        Assert.assertSame(loanId, filter_ContractAccepted.get("loanId"), "loan id is not available in the CONTRACT_ACCEPTED filter list");
        //admin publish the loan
        adminLoanPublished(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"PUBLISHED'
        Map<String, Object> filter_LoanPublished = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PUBLISHED),getAdminTokenId, loanId);
        int getLoanId_PUB = (int) filter_LoanPublished.get("loanId");
        int getBorrowerId_PUB = (int) filter_LoanPublished.get("borrowerId");
        String getStatus_PUB = String.valueOf(filter_LoanPublished.get("status"));
        System.out.println("Published page filter loanId is : " + getLoanId_PUB );
        System.out.println("Published page filter borrowerId is : " + getBorrowerId_PUB );
        System.out.println("Published page filter status is : " + getStatus_PUB );
        Assert.assertSame(loanId, filter_LoanPublished.get("loanId"), "loan id is not available in the Published filter list");
        //admin get the loan details and get the opportunity id
        Map<String,Object> admin_getLoanDetails = admin_GetLoanDetails(loanId,getAdminTokenId);
        int getOpportunity_Id = (int) admin_getLoanDetails.get("opportunityId");
        System.out.println("Opportunity id is  : " + getOpportunity_Id);
        //get the user wallet details
        //double walletAmount = getUserWalletDetail(getTokenId);
       // System.out.println("Opportunity id is :" + getOpportunity_Id);
        //Calling investor login api to get the token id and user id
        Map<String,Object>  getInvestorToken = test_InvestorLogin();
        String InvestorTokenId = (String)getInvestorToken.get("authToken");
        int userId = (int)getInvestorToken.get("userId");
        String phoneNumber_INv = (String) getInvestorToken.get("phoneNumber");
        //Get the investor dashboard details
        Map<String,Object> get_investorId = GetDashboardDetails(InvestorTokenId);
        System.out.println("Investor id is : " + get_investorId);
        int investorId = (int) get_investorId.get("investorId");
        System.out.println("Investor id is : " + investorId);
        //Investor get the loan details
        int requestedLoanAmount = getLoanDetails(InvestorTokenId, loanId);
         System.out.println("value of requestedLoanAmount " + requestedLoanAmount);
         //Investor get the loan status for investment and get the opportunity id
        Map<String, Integer> loan_opportunity_map = GetLoansStatusForInvestment(InvestorTokenId);
        int opportunityId =  loan_opportunity_map.get(String.valueOf(loanId));
        System.out.println("opportunity id fetched: "+ opportunityId);
        //Investor invest on the loan via manual investment flow
        investorApplyBidManually(InvestorTokenId, loanId, investorId, opportunityId, 1000, requestedLoanAmount);
        //Admin get the all fund details after investment is done on this particular loan
        adminAllFundsDetails(loanId, getAdminTokenId);
        //Admin update the loan status to the partially funded loan stage
        adminPartiallyFunded(opportunityId, getAdminTokenId);
        //Admin update the loan status to Disbursed loan stage
        adminDisbursedLoan(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"DISBURSED'
        Map<String, Object> filter_LoanDisbursed = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.DISBURSED),getAdminTokenId, loanId);
        int getLoanId_Disb = (int) filter_LoanDisbursed.get("loanId");
        int getBorrowerId_Disb = (int) filter_LoanDisbursed.get("borrowerId");
        String getStatus_Disb = String.valueOf(filter_LoanDisbursed.get("status"));
        System.out.println("Disbursed page filter loanId is : " + getLoanId_Disb );
        System.out.println("Disbursed page filter borrowerId is : " + getBorrowerId_Disb );
        System.out.println("Disbursed page filter status is : " + getStatus_Disb );
        Assert.assertSame(loanId, filter_LoanDisbursed.get("loanId"), "loan id is not available in the Disbursed filter list");
        //Admin create the Contract during repayment
        adminCreateContractDuringRepayment(loanId, getAdminTokenId);
        //Admin update the loan status to under repayment stage
        adminLoanUnderRepayment(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"UNDER_REPAYMENT'
        Map<String, Object> filter_UnderRepayment = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.UNDER_REPAYMENT),getAdminTokenId, loanId);
        int getLoanId_URep = (int) filter_UnderRepayment.get("loanId");
        int getBorrowerId_URep = (int) filter_UnderRepayment.get("borrowerId");
        String getStatus_URep = String.valueOf(filter_UnderRepayment.get("status"));
        System.out.println("Under-repayment page filter loanId is : " + getLoanId_URep );
        System.out.println("Under-repayment page filter borrowerId is : " + getBorrowerId_URep );
        System.out.println("Under-repayment page filter status is : " + getStatus_URep );
        Assert.assertSame(loanId, filter_UnderRepayment.get("loanId"), "loan id is not available in the Under-repayment filter list");
        //Admin get the loan details and fetch the repayment amount
        Map<String,Object> getLoanDetails = admin_GetLoanDetails(loanId,getAdminTokenId);
        float getrepaymentAmountBorrower = (float) getLoanDetails.get("repaymentAmountBorrower");
        System.out.println("repayment amount is : " + getrepaymentAmountBorrower);
        //Get the investor wallet details and fetch the wallet amount
        double walletAmount = getUserWalletDetail(borrowerToken_id);

        if (walletAmount<getrepaymentAmountBorrower) {
          int transactionId = admin_DepositFundForConsumer(borrower_userId, getAdminTokenId,getrepaymentAmountBorrower);
            System.out.println("Admin deposit the fund :" + transactionId);
        } else {
            System.out.println("Available wallet balance is : " + walletAmount);
        }
        //Admin paid the repayment amount on behalf of borrower
        adminLoanAmountPaid(loanId, getAdminTokenId);
        //Admin completes the loan and move to Complete stat
         adminLoanCompleted(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"COMPLETED'
        Map<String, Object> filter_Completed = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.COMPLETED),getAdminTokenId, loanId);
        int getLoanId_Completed = (int) filter_Completed.get("loanId");
        int getBorrowerId_Completed = (int) filter_Completed.get("borrowerId");
        String getStatus_Completed = String.valueOf(filter_Completed.get("status"));
        System.out.println("Loan Completed page filter loanId is : " + getLoanId_Completed );
        System.out.println("Loan Completed page filter borrowerId is : " + getBorrowerId_Completed );
        System.out.println("Loan Completed page filter status is : " + getStatus_Completed );
        Assert.assertSame(loanId, filter_Completed.get("loanId"), "loan id is not available in the Completed filter list");
        test.log(LogStatus.INFO, "Complete loan creation flow has been tested successfully...");

        //    List<LoanApplication> getList =  GetLoansStatusForInvestment(InvestorTokenId,loanId);
//        System.out.println("value of requestedLoanAmount " + requestedLoanAmount);
//     investorApplyBidManually(InvestorTokenId, loanid, get_investorId, getOpportunity_Id, 1000, requestedLoanAmount);
//
//        adminAllFundsDetails(loanid, getAdmintokeniD);
//        adminPartiallyFunded(getOpportunity_Id, getAdmintokeniD);
//        adminDisbursedLoan(loanid, getAdmintokeniD);
//        Map<String, Object> filter_LoanDisbursed = admin_loanFilterationList("Disbursed",getAdminTokenId, loanId);
//        int getLoanId_Disb = (int) filter_LoanDisbursed.get("loanId");
//        int getBorrowerId_Disb = (int) filter_LoanDisbursed.get("borrowerId");
//        String getStatus_Disb = String.valueOf(filter_LoanDisbursed.get("status"));
//        System.out.println("Disbursed page filter loanId is : " + getLoanId_Disb );
//        System.out.println("Disbursed page filter borrowerId is : " + getBorrowerId_Disb );
//        System.out.println("Disbursed page filter status is : " + getStatus_Disb );
//        Assert.assertSame(loanId, filter_LoanDisbursed.get("loanId"), "loan id is not available in the Disbursed filter list");
//
////        adminCreateContractDuringRepayment(loanid, getAdmintokeniD);
////        adminLoanUnderRepayment(loanid, getAdmintokeniD);
//        Map<String, Object> filter_UnderRepayment = admin_loanFilterationList("Under-repayment",getAdminTokenId, loanId);
//        int getLoanId_URep = (int) filter_UnderRepayment.get("loanId");
//        int getBorrowerId_URep = (int) filter_UnderRepayment.get("borrowerId");
//        String getStatus_URep = String.valueOf(filter_UnderRepayment.get("status"));
//        System.out.println("Under-repayment page filter loanId is : " + getLoanId_URep );
//        System.out.println("Under-repayment page filter borrowerId is : " + getBorrowerId_URep );
//        System.out.println("Under-repayment page filter status is : " + getStatus_URep );
//        Assert.assertSame(loanId, filter_UnderRepayment.get("loanId"), "loan id is not available in the Under-repayment filter list");
//
////        adminLoanAmountPaid(loanid, getAdmintokeniD);
////        adminLoanCompleted(loanid, getAdmintokeniD);
//        Map<String, Object> filter_Completed = admin_loanFilterationList("Completed",getAdminTokenId, loanId);
//        int getLoanId_Completed = (int) filter_Completed.get("loanId");
//        int getBorrowerId_Completed = (int) filter_Completed.get("borrowerId");
//        String getStatus_Completed = String.valueOf(filter_Completed.get("status"));
//        System.out.println("Loan Completed page filter loanId is : " + getLoanId_Completed );
//        System.out.println("Loan Completed page filter borrowerId is : " + getBorrowerId_Completed );
//        System.out.println("Loan Completed page filter status is : " + getStatus_Completed );
//        Assert.assertSame(loanId, filter_Completed.get("loanId"), "loan id is not available in the Completed filter list");
//
//       test.log(LogStatus.INFO, "Complete loan creation flow has been tested successfully...");

    }
//    @Test
//    public void TestInvestorInvestment() throws ParseException {
//        Map<String, Object> getInvestorToken = test_InvestorLogin();
//        String InvestorTokenId = (String) getInvestorToken.get("authToken");
//        String getAdmintokeniD = gettoken.test_adminLogin();
//        int userId = (int) getInvestorToken.get("userId");
//        String phoneNumber = (String) getInvestorToken.get("phoneNumber");
//
//       // Complete_Investor_Flow investorFlow = new Complete_Investor_Flow();
//        Map<String, Object> get_investorId = GetDashboardDetails(InvestorTokenId);
//        int investorId = (int) get_investorId.get("investorId");
//        System.out.println("Investor id is : " + investorId);
//        int loanId = 1612;
//        int requestedLoanAmount = getLoanDetails(InvestorTokenId, loanId);
//        System.out.println("value of requestedLoanAmount " + requestedLoanAmount);
//       // JSONArray jsonArray = GetLoansStatusForInvestment(InvestorTokenId, loanId);
//       // Map<String, Integer> loan_opportunity_map =
//                GetLoansStatusForInvestment(InvestorTokenId);
//        //int opportunityId = loan_opportunity_map.get(loanId);
//
//     //System.out.println("opportunity id fetched: "+ opportunityId);
////        investorApplyBidManually(InvestorTokenId, loanId, investorId, opportunityId, 1000, requestedLoanAmount);
////        adminAllFundsDetails(loanId, getAdmintokeniD);
////        adminPartiallyFunded(opportunityId, getAdmintokeniD);
////        adminDisbursedLoan(loanId, getAdmintokeniD);
////        adminCreateContractDuringRepayment(loanId, getAdmintokeniD);
////        adminLoanUnderRepayment(loanId, getAdmintokeniD);
////        Map<String,Object> getLoanDetails = admin_GetLoanDetails(loanId,getAdmintokeniD);
////        float getrepaymentAmountBorrower = (float) getLoanDetails.get("repaymentAmountBorrower");
////        System.out.println("repayment amount is : " + getrepaymentAmountBorrower);
////        double walletAmount = getUserWalletDetail(getTokenId);
////
////        if (walletAmount<getrepaymentAmountBorrower) {
////            //get the admin token Id
////           // String getAdminTokenId = get_token.test_adminLogin();
////          //
////            int transactionId = admin_DepositFundForConsumer(userId, getAdmintokeniD,getrepaymentAmountBorrower);
////            System.out.println("Admin deposit the fund :" + transactionId);
////        } else {
////            System.out.println("Available wallet balance is : " + walletAmount);
////
////        }
////        adminLoanAmountPaid(loanId, getAdmintokeniD);
////        adminLoanCompleted(loanId, getAdmintokeniD);
//
//        //       // jsonArray.put("opportunityId");
////      //  jsonArray.put("id ");
//
//
////        System.out.println("Loans opportunity id and loan id  list is : " + jsonArray);
////        JSONObject jsonobj = new JSONObject();
////        jsonobj.put("opportunityId", jsonArray );
////        System.out.println("json object is in string: " + jsonobj.toString());
////        int getOpportunityId = jsonobj.getInt("id");
////        System.out.println("OpportunityId is: " + getOpportunityId);
//        // System.out.println("json object is : " + jsonobj.getInt("data.loanApplications[].opportunityId"));
//        //  System.out.println("value of getList " + getList);
//
//    }

    public static int applyForLoan(String token,int borrowerId) throws IOException {
            //get the current date time
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String dateString = dateFormat.format(date);
            System.out.println("date is : " + dateString);
            //send the pojo as payload
            PostApplyLoan postApplyLoan = new PostApplyLoan();
            postApplyLoan.setRequestedLoanAmount(requestedLoanAmount);
            postApplyLoan.setRequestDuration(requestDuration);
            postApplyLoan.setProductId(productId);
            postApplyLoan.setInvoiceDate(dateString);
            postApplyLoan.setBuyerName(buyerName);
            postApplyLoan.setPurposeOfUsage(purposeOfUsage);
            postApplyLoan.setBorrowerId(String.valueOf(borrowerId));
            postApplyLoan.setInvoiceDocument("");
            postApplyLoan.setInvoiceDocumentUrl("");
            postApplyLoan.setDeliveryNoteDocument("");
            postApplyLoan.setDeliveryNoteDocumentUrl("");
            postApplyLoan.setContractDocument("");
            postApplyLoan.setContractDocumentUrl("");

            List<DocumentReqDTOList> documentReqDTOList = new ArrayList();
            DocumentReqDTOList documentReqDTO_1 = new DocumentReqDTOList();

            List<FileList> fileLists = new ArrayList<>();
            FileList fileList = new FileList();

            // create first document
            documentReqDTO_1.setDocumentType(String.valueOf(DocumentType.CONTRACT_DOCUMENT));
            documentReqDTO_1.setFileList(fileLists);

            //create second document
            DocumentReqDTOList documentReqDTO_2 = new DocumentReqDTOList();
            documentReqDTO_2.setDocumentType(String.valueOf(DocumentType.INVOICE));
            documentReqDTO_2.setFileList(fileLists);

            //create third document
            DocumentReqDTOList documentReqDTO_3 = new DocumentReqDTOList();
            documentReqDTO_3.setDocumentType(String.valueOf(DocumentType.DELIVERY_DOCUMENT));
            documentReqDTO_3.setFileList(fileLists);
            //adding all docs in the list
            documentReqDTOList.add(documentReqDTO_1);
            documentReqDTOList.add(documentReqDTO_2);
            documentReqDTOList.add(documentReqDTO_3);
            postApplyLoan.setDocumentReqDTOList(documentReqDTOList);

            //converting a Java class object to a JSON Array payload as String
            ObjectMapper objectMapper = new ObjectMapper();
            String postApplyLoan_Json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postApplyLoan);

            // loan has been created
            Response createLoan = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
                    .contentType("application/json").header("x-auth-token", token).log().all().body(postApplyLoan).when()
                    .post(APIPath.apiPath.PostApplyLoan).then().assertThat().statusCode(200).extract().response();
            int loanid = createLoan.path("data.loanId");

            System.out.println("loanid is :" + loanid);
            System.out.println("Borrower has applied for loan " + createLoan.getBody().asString());

            // Converting postapplyLoan json Array string to PostApplyLoan class object
            ObjectMapper objectMapper2 = new ObjectMapper();
            PostApplyLoan allDocDetails = objectMapper2.readValue(postApplyLoan_Json, PostApplyLoan.class);

            System.out.println("========================================================");
            System.out.println(" Borrower id is : " + allDocDetails.getBorrowerId());
            System.out.println("requestedLoanAmount is  : " + allDocDetails.getRequestedLoanAmount());
            System.out.println("requestDuration is : " + allDocDetails.getRequestDuration());
            System.out.println("========================================================");

//            HashMap<String,Object> map = new HashMap<>();
//            map.put("loanid",loanid);

      return loanid;
        }

    @Test
    public void CreateLoanAndPublished_AutoInvest() throws IOException, InterruptedException, ParseException {
        test.log(LogStatus.INFO, "Test is started...");
        //String Gettoken_iD = Get_BorrowerLogin();
        Map<String,Object> get_BorrowerDetails = gettoken.test_BorrowerLogin();
        String borrowerToken_id = (String) get_BorrowerDetails.get("authToken");
        int borrower_userId = (int)get_BorrowerDetails.get("userId");
        int borrowerId = borrowerDashboardDetails(borrowerToken_id);
        System.out.println("Borrower output is" + borrowerToken_id);
        GetStaticData();
        int loanId = applyForLoan(borrowerToken_id,borrowerId);
        System.out.println("Integration test loanId is :" + loanId);

        borrowerFetchLoanDetails(loanId, borrowerToken_id);
        //Borrower upload the Invoice document
        borrowerUploadInvoiceDocument(loanId, borrowerId, borrowerToken_id);
        //Borrower upload the loan documents
        borrowerLoanUploadDocument(loanId, borrowerId, borrowerToken_id);
        //Calling admin login api to get the token id
        String getAdminTokenId = gettoken.test_adminLogin();
        //Get all the guarantees name
        adminGetAllGuaranteesName(getAdminTokenId);
        //Add and update the loan gurantees name
        adminAddandUpdateLoanGuarantees(loanId, getAdminTokenId);
        //Get the loan gurantees name
        adminGetLoanGuarantees(loanId,getAdminTokenId);
        //Loan Filtration list based on loan status"UNDER_REVIEW'
        Map<String, Object> filtered_underReview = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.UNDER_REVIEW),getAdminTokenId, loanId);
        int getLoanId_UR = (int) filtered_underReview.get("loanId");
        int getBorrowerId_UR = (int) filtered_underReview.get("borrowerId");
        String getStatus_UR = String.valueOf(filtered_underReview.get("status"));
        System.out.println("UNDER_REVIEW page filter loanId is : " + getLoanId_UR );
        System.out.println("UNDER_REVIEW page filter borrowerId is : " + getBorrowerId_UR );
        System.out.println("UNDER_REVIEW page filter status is : " + getStatus_UR );
        Assert.assertSame(loanId, filtered_underReview.get("loanId"), "loan id is not available in the UNDER_REVIEW filter list");
        //admin create the loan Proposal
        admin_CreateLoanProposal(loanId,getAdminTokenId);
        //admin sent the loan Proposal
        adminSentLoanProposal(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"PROPOSAL_SENT'
        Map<String, Object> filtered_ProposalSent = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PROPOSAL_SENT),getAdminTokenId, loanId);
        int getLoanId_PS = (int) filtered_ProposalSent.get("loanId");
        int getBorrowerId_PS = (int) filtered_ProposalSent.get("borrowerId");
        String getStatus_PS = String.valueOf(filtered_ProposalSent.get("status"));
        System.out.println("ProposalSent page filter loanId is : " + getLoanId_PS );
        System.out.println("ProposalSent filter borrowerId is : " + getBorrowerId_PS );
        System.out.println("ProposalSent filter status is : " + getStatus_PS );
        Assert.assertSame(loanId, filtered_ProposalSent.get("loanId"), "loan id is not available in the PROPOSAL_SENT filter list");
        //admin approve the loan Proposal
        adminProposalApproved(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"PROPOSAL_APPROVED'
        Map<String, Object> filter_ProposalApproved = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PROPOSAL_APPROVED),getAdminTokenId, loanId);
        int getLoanId_PA = (int) filter_ProposalApproved.get("loanId");
        int getBorrowerId_PA = (int) filter_ProposalApproved.get("borrowerId");
        String getStatus_PA = String.valueOf(filter_ProposalApproved.get("status"));
        System.out.println("PROPOSAL_APPROVED page filter loanId is : " + getLoanId_PA );
        System.out.println("PROPOSAL_APPROVED page filter borrowerId is : " + getBorrowerId_PA );
        System.out.println("PROPOSAL_APPROVED page filter status is : " + getStatus_PA );
        Assert.assertSame(loanId, filter_ProposalApproved.get("loanId"), "loan id is not available in the PROPOSAL_APPROVED filter list");
        //admin approve the loan
        adminLoanApproved(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"LOAN_APPROVED'
        Map<String, Object> filter_LoanApproved = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.LOAN_APPROVED),getAdminTokenId, loanId);
        int getLoanId_LA = (int) filter_LoanApproved.get("loanId");
        int getBorrowerId_LA = (int) filter_LoanApproved.get("borrowerId");
        String getStatus_LA = String.valueOf(filter_LoanApproved.get("status"));
        System.out.println("LOAN_APPROVED page filter loanId is : " + getLoanId_LA );
        System.out.println("LOAN_APPROVED page filter borrowerId is : " + getBorrowerId_LA );
        System.out.println("LOAN_APPROVED page filter status is : " + getStatus_LA );
        Assert.assertSame(loanId, filter_LoanApproved.get("loanId"), "loan id is not available in the LOAN_APPROVED filter list");
        //admin enable the Auto invest
        adminEnabledAutoInvestFeature(loanId,getAdminTokenId);
        //admin accept the contract
        adminContractAccepted(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"LOAN_APPROVED'
        Map<String, Object> filter_ContractAccepted = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.CONTRACT_ACCEPTED),getAdminTokenId, loanId);
        int getLoanId_CA = (int) filter_ContractAccepted.get("loanId");
        int getBorrowerId_CA = (int) filter_ContractAccepted.get("borrowerId");
        String getStatus_CA = String.valueOf(filter_ContractAccepted.get("status"));
        System.out.println("CONTRACT_ACCEPTED page filter loanId is : " + getLoanId_CA );
        System.out.println("CONTRACT_ACCEPTED page filter borrowerId is : " + getBorrowerId_CA );
        System.out.println("CONTRACT_ACCEPTED page filter status is : " + getStatus_CA );
        Assert.assertSame(loanId, filter_ContractAccepted.get("loanId"), "loan id is not available in the CONTRACT_ACCEPTED filter list");
        //admin publish the loan
        adminLoanPublished(loanId, getAdminTokenId);
        Thread.sleep(1000 * 60 * 10);
        //adminCollectedLoanPercentage(loanid,getAdmintokeniD);
        adminAllFundsDetails(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"PUBLISHED'
        Map<String, Object> filter_LoanPublished = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.PUBLISHED),getAdminTokenId, loanId);
        int getLoanId_PUB = (int) filter_LoanPublished.get("loanId");
        int getBorrowerId_PUB = (int) filter_LoanPublished.get("borrowerId");
        String getStatus_PUB = String.valueOf(filter_LoanPublished.get("status"));
        System.out.println("Published page filter loanId is : " + getLoanId_PUB );
        System.out.println("Published page filter borrowerId is : " + getBorrowerId_PUB );
        System.out.println("Published page filter status is : " + getStatus_PUB );
        Assert.assertSame(loanId, filter_LoanPublished.get("loanId"), "loan id is not available in the Published filter list");
        //admin get the loan details and get the opportunity id
        Map<String,Object> admin_getLoanDetails = admin_GetLoanDetails(loanId,getAdminTokenId);
        int getOpportunity_Id = (int) admin_getLoanDetails.get("opportunityId");
        System.out.println("Opportunity id is  : " + getOpportunity_Id);
        //get the user wallet details
        //double walletAmount = getUserWalletDetail(getTokenId);
        // System.out.println("Opportunity id is :" + getOpportunity_Id);
        //Calling investor login api to get the token id and user id
//        Map<String,Object>  getInvestorToken = test_InvestorLogin();
//        String InvestorTokenId = (String)getInvestorToken.get("authToken");
//        int userId = (int)getInvestorToken.get("userId");
//        String phoneNumber = (String) getInvestorToken.get("phoneNumber");
//        //Get the investor dashboard details
//        Map<String,Object> get_investorId = GetDashboardDetails(InvestorTokenId);
//        System.out.println("Investor id is : " + get_investorId);
//        int investorId = (int) get_investorId.get("investorId");
//        System.out.println("Investor id is : " + investorId);
//        //Investor get the loan details
//        int requestedLoanAmount = getLoanDetails(InvestorTokenId, loanId);
//        System.out.println("value of requestedLoanAmount " + requestedLoanAmount);
//        //Investor get the loan status for investment and get the opportunity id
//        Map<String, Integer> loan_opportunity_map = GetLoansStatusForInvestment(InvestorTokenId);
//        int opportunityId =  loan_opportunity_map.get(String.valueOf(loanId));
//        System.out.println("opportunity id fetched: "+ opportunityId);
//        //Investor invest on the loan via manual investment flow
//        investorApplyBidManually(InvestorTokenId, loanId, investorId, opportunityId, 1000, requestedLoanAmount);
        //Admin get the all fund details after investment is done on this particular loan
    //    adminAllFundsDetails(loanId, getAdminTokenId);
        //Admin update the loan status to the partially funded loan stage
        adminPartiallyFunded(getOpportunity_Id, getAdminTokenId);
        //Admin update the loan status to Disbursed loan stage
        adminDisbursedLoan(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"DISBURSED'
        Map<String, Object> filter_LoanDisbursed = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.DISBURSED),getAdminTokenId, loanId);
        int getLoanId_Disb = (int) filter_LoanDisbursed.get("loanId");
        int getBorrowerId_Disb = (int) filter_LoanDisbursed.get("borrowerId");
        String getStatus_Disb = String.valueOf(filter_LoanDisbursed.get("status"));
        System.out.println("Disbursed page filter loanId is : " + getLoanId_Disb );
        System.out.println("Disbursed page filter borrowerId is : " + getBorrowerId_Disb );
        System.out.println("Disbursed page filter status is : " + getStatus_Disb );
        Assert.assertSame(loanId, filter_LoanDisbursed.get("loanId"), "loan id is not available in the Disbursed filter list");
        //Admin create the Contract during repayment
        adminCreateContractDuringRepayment(loanId, getAdminTokenId);
        //Admin update the loan status to under repayment stage
        adminLoanUnderRepayment(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"UNDER_REPAYMENT'
        Map<String, Object> filter_UnderRepayment = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.UNDER_REPAYMENT),getAdminTokenId, loanId);
        int getLoanId_URep = (int) filter_UnderRepayment.get("loanId");
        int getBorrowerId_URep = (int) filter_UnderRepayment.get("borrowerId");
        String getStatus_URep = String.valueOf(filter_UnderRepayment.get("status"));
        System.out.println("Under-repayment page filter loanId is : " + getLoanId_URep );
        System.out.println("Under-repayment page filter borrowerId is : " + getBorrowerId_URep );
        System.out.println("Under-repayment page filter status is : " + getStatus_URep );
        Assert.assertSame(loanId, filter_UnderRepayment.get("loanId"), "loan id is not available in the Under-repayment filter list");
        //Admin get the loan details and fetch the repayment amount
        Map<String,Object> getLoanDetails = admin_GetLoanDetails(loanId,getAdminTokenId);
        float getrepaymentAmountBorrower = (float) getLoanDetails.get("repaymentAmountBorrower");
        System.out.println("repayment amount is : " + getrepaymentAmountBorrower);
        //Get the investor wallet details and fetch the wallet amount
        double walletAmount = getUserWalletDetail(borrowerToken_id);

        if (walletAmount<getrepaymentAmountBorrower) {
            int transactionId = admin_DepositFundForConsumer(borrower_userId, getAdminTokenId,getrepaymentAmountBorrower);
            System.out.println("Admin deposit the fund :" + transactionId);
        } else {
            System.out.println("Available wallet balance is : " + walletAmount);
        }
        //Admin paid the repayment amount on behalf of borrower
        adminLoanAmountPaid(loanId, getAdminTokenId);
        //Admin completes the loan and move to Complete stat
        adminLoanCompleted(loanId, getAdminTokenId);
        //Loan Filtration list based on loan status"COMPLETED'
        Map<String, Object> filter_Completed = admin_loanFilterationList(String.valueOf(LoanApplicationStatus.COMPLETED),getAdminTokenId, loanId);
        int getLoanId_Completed = (int) filter_Completed.get("loanId");
        int getBorrowerId_Completed = (int) filter_Completed.get("borrowerId");
        String getStatus_Completed = String.valueOf(filter_Completed.get("status"));
        System.out.println("Loan Completed page filter loanId is : " + getLoanId_Completed );
        System.out.println("Loan Completed page filter borrowerId is : " + getBorrowerId_Completed );
        System.out.println("Loan Completed page filter status is : " + getStatus_Completed );
        Assert.assertSame(loanId, filter_Completed.get("loanId"), "loan id is not available in the Completed filter list");
        test.log(LogStatus.INFO, "Complete loan creation flow has been tested successfully...");

        }

    @Test
    public void borrowerUploadInvoiceDocument(int loanId, int borrowerId, String tokenId)  {
        File invoiceFile = new File(".\\src\\main\\resources\\Upload-documents\\invoice.pdf");
        //File deliveryFile = new File(".\\src\\main\\resources\\Upload-documents\\deliveryFile.pdf");
        //File contractFile = new File(".\\src\\main\\resources\\Upload-documents\\ContractFile.png");
        Response uploadInvoiceDoc = RestAssured.given()
                .log().all().baseUri(APIPath.apiPath.BaseURL)
                .contentType("multipart/form-data").header("x-auth-token", tokenId).log().all()
                .multiPart("file", invoiceFile, "multipart/form-data")
                .when().post(APIPath.apiPath.UploadDocument)
                .then().extract().response();
        System.out.println(uploadInvoiceDoc.getBody().asString());
        String uploadMessage = uploadInvoiceDoc.path("status.message");
        assertEquals(uploadMessage, "File has been successfully uploaded");
    }

    @Test
    public void borrowerLoanUploadDocument(int loanId, int borrowerId, String tokenId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> array_fileList = new LinkedList<>();
        Map<String, Object> file_List = new HashMap<>();
        file_List.put("documentName", "invoice_file");
        file_List.put("documentUrl", "invoice_file.");
        file_List.put("documentSize", 0.0022144317626953125);
        array_fileList.add(file_List);
        Map<String, Object> documentList = new HashMap<>();
        documentList.put("loanId", loanId);
        documentList.put("documentType", "INVOICE");
        documentList.put("fileList", array_fileList);

        List<Map<String, Object>> documentReqDTOList = new LinkedList<>();
        documentReqDTOList.add(documentList);

        Map<String, Object> upload_invoiceDoc = objectMapper.readValue(new File(".\\src\\main\\resources\\JsonFiles\\UploadInvoiceDoc.json"),
                new TypeReference<>() {
                });
        upload_invoiceDoc.put("documentReqDTOList", documentReqDTOList);
        upload_invoiceDoc.put("loanId", loanId);
        upload_invoiceDoc.put("borrowerId", borrowerId);

        String updated = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(upload_invoiceDoc);
        System.out.println(updated);

        Response uploadloanDoc = RestAssured.given()
                .log().all().baseUri(APIPath.apiPath.BaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .body(updated)
                .when().post(APIPath.apiPath.UploadLoanApplicationDoc)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println("response body is : "+ uploadloanDoc.getBody().asString());
    }

    public void borrowerUploadLoanApplicationDocument(int LoanId, String TokenId, String documentType) {
        File file = new File(".\\src\\main\\resources\\Upload-documents\\contract-loan-id.pdf");

        Response uploadInvoiceDoc = RestAssured.given()
                .log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenId).log().all()
                .multiPart("file", file, "multipart/form-data")
                .when().post(APIPath.apiPath.UploadLoanApplicationDoc)
                .then().extract().response();
        System.out.println(uploadInvoiceDoc.getBody().asString());
        String uploadMessage = uploadInvoiceDoc.path("status.message");
        assertEquals(uploadMessage, "File has been successfully uploaded");
    }

    @Test
    public void borrowerFetchLoanDetails(int loanId, String tokenId) {

        Response getLoanDetails = RestAssured.given()
                .log().all().baseUri(APIPath.apiPath.BaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .pathParams("loanId", loanId).when().get(APIPath.apiPath.GetLoanDetails)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(getLoanDetails.getBody().asString());
        String uploadMessage = getLoanDetails.path("status.message");
        assertEquals(uploadMessage, "Loan Details Successfully Fetched");
    }

    @Test
    public void adminAddandUpdateLoanGuarantees(int loanId, String tokenId)  {

        Map<String, Object> map = new HashMap<>();
        map.put("loanid", loanId);
        map.put("gauranteenameen", "Promissory Note");
        map.put("gauranteenamear", "سند لأمر");

        List<Map<String, Object>> gurantee = new ArrayList<>();
        gurantee.add(map);

        Response addGuarantee = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .body(gurantee).when().post(APIPath.apiPath.AdminAddGaurantees)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(addGuarantee.getBody().asString());
        String gaurantees = addGuarantee.path("status.message");
        assertThat(gaurantees, equalTo("Successfully Add/Update Loan Guarantees"));
        System.out.println(addGuarantee.getBody().asString());
        test.log(LogStatus.INFO, "Loan proposal gaurantees has been added successfully...");
    }
    public void adminGetLoanGuarantees(int loanId, String tokenId)  {

   Response getLoanGuarantees = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", tokenId) .contentType("application/json")
                .queryParam("loanId",loanId).log().all().when().get(APIPath.apiPath.AdminGetLoanGaurantees)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(getLoanGuarantees.getBody().asString());
        String gaurantees = getLoanGuarantees.path("status.message");
        assertThat(gaurantees, equalTo("Successfully Executed"));
        test.log(LogStatus.INFO, "Loan gaurantees has been fetched successfully...");
    }
    public void adminGetAllGuaranteesName(String tokenId)  {

        Response getAllGuarantees = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .when().get(APIPath.apiPath.AdminGetAllGaurantees)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(getAllGuarantees.getBody().asString());
        String allGaurantees = getAllGuarantees.path("status.message");
        assertThat(allGaurantees, equalTo("Successfully Executed"));
//        Map<Object, Object> gauranteesNameDTOList = getAllGuarantees.jsonPath().getMap("data.gauranteesNameDTOList[]");
//        System.out.println("Gurantees name list is : " + gauranteesNameDTOList.get("gauranteesNameDTOList"));
//        assertThat(allGaurantees, equalTo("Successfully Executed"));
        test.log(LogStatus.INFO, "All gaurantees name has been fetched successfully...");
    }
    public void adminGetAuthorizedSignatories(String tokenId,int loanId,int orgId)  {

        Response getAuthorizedSignatories = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .queryParam("loanId",loanId).queryParam("orgId",orgId)
                .queryParam("page",0).queryParam("limit",30)
                .when().post(APIPath.apiPath.AdminGetAuthorizedSignatories)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(getAuthorizedSignatories.getBody().asString());
        String authorizedSignatories = getAuthorizedSignatories.path("status.message");
        assertThat(authorizedSignatories, equalTo("Successfully Executed"));
        Map<Object, Object> gauranteesNameDTOList = getAuthorizedSignatories.jsonPath().getMap("data.allAuthorizedSignatories[]");
        System.out.println("Authorized signatories name list is : " + gauranteesNameDTOList.get("allAuthorizedSignatories"));
        assertThat(authorizedSignatories, equalTo("Successfully Executed"));
        test.log(LogStatus.INFO, "Authorized Signatories names has been fetched successfully...");
    }
    public void admin_CreateLoanProposal(int LoanID, String TokenID) throws IOException {
        //current date
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = dateFormat.format(date);
        System.out.println("date is : " + dateString);

        Admin_Create_Loan_Proposal adminCreateLoanProposal = new Admin_Create_Loan_Proposal();
        adminCreateLoanProposal.setApprovedLoanAmount("120000");
        adminCreateLoanProposal.setApprovedLoanDuration(35);
        adminCreateLoanProposal.setApprovedInterestRatePct("9.2");
        adminCreateLoanProposal.setRiskRate("A");
        adminCreateLoanProposal.setManagementFeesPct("0.05");
        adminCreateLoanProposal.setVatOnManagementFeesPct(15);
        adminCreateLoanProposal.setSuccessFeesPct(20);
        adminCreateLoanProposal.setCollectionFeesPct(0);
        adminCreateLoanProposal.setOtherFeeFxd(0);
        adminCreateLoanProposal.setTicketSize("120000");
        adminCreateLoanProposal.setPurposeOfLoan("Working Capital Financing");
        adminCreateLoanProposal.setPurposeOfUsage("WORKING_CAPITAL_FINANCE");
        adminCreateLoanProposal.setCollectionMechanism("BANK_TRANSFER");
        adminCreateLoanProposal.setFinancingStructure("TAWARROUQ");
        adminCreateLoanProposal.setRepaymentType("BULLET_PAYMENT");
        adminCreateLoanProposal.setBuyerName("Test");
        List<Object> gauranteeOthers_list = new ArrayList<>();
        //adminCreateLoanProposal.getGauranteeOthers().add(new GauranteeOthers());
       // GauranteeOthers gauranteeOthers = new GauranteeOthers();

    //    gauranteeOthers_list.add(gauranteeOthers);
        adminCreateLoanProposal.setGauranteeOthers(gauranteeOthers_list);
        adminCreateLoanProposal.setPublishDateTime(dateString);

        ObjectMapper objectMapper = new ObjectMapper();
        String loanCreateProposalDetails = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(adminCreateLoanProposal);

        Admin_Create_Loan_Proposal loanCreateProposalDetails_2 = objectMapper.readValue(loanCreateProposalDetails,Admin_Create_Loan_Proposal.class);
        System.out.println(""+ loanCreateProposalDetails_2.getBuyerName());
        // Admin created a proposal
        Response approveLoan = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).body(loanCreateProposalDetails_2).when().put(APIPath.apiPath.AdminCreateLoanProposal)
                .then().assertThat().statusCode(200).extract().response();
        String risk = approveLoan.path("data.riskRate");
        assertThat(risk, equalTo("A"));
        System.out.println(approveLoan.getBody().asString());
        test.log(LogStatus.INFO, "Loan proposal has been created successfully...");
    }
    public void adminCreateLoanProposal(int LoanID, String TokenID) throws IOException {
        // admin is logged in
        String jsonCreateLoanPropBody = new String(
                Files.readAllBytes(Paths.get(".\\src\\main\\resources\\JsonFiles\\LoanProposalSent.json")));
        // Admin created a proposal
        Response approveLoan = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).body(jsonCreateLoanPropBody).when().put(APIPath.apiPath.AdminCreateLoanProposal)
                .then().assertThat().statusCode(200).extract().response();
        String risk = approveLoan.path("data.riskRate");
        assertThat(risk, equalTo("A"));
        System.out.println(approveLoan.getBody().asString());
        test.log(LogStatus.INFO, "Loan proposal has been created successfully...");
    }

    public static Integer borrowerDashboardDetails(String tokenId) {
        Response borrower_dashboard = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
                .contentType("application/json").header("x-auth-token", tokenId).log().all()
                .when().get(GetDashboardDetails).then().extract()
                .response();
        System.out.println(borrower_dashboard.getBody().asString());
        return borrower_dashboard.path("data.userId");
    }

    public void adminSentLoanProposal(int LoanID, String TokenID) {
        // Admin sent the proposal
        Response sent_proposal = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminSentLoanProposal)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(sent_proposal.getBody().asString());
        String sent_prop = sent_proposal.path("data.updatedLoanStatus");
        assertThat(sent_prop, equalTo("PROPOSAL_SENT"));
        //System.out.println(sent_proposal.getBody().asString());
        test.log(LogStatus.INFO, "Loan proposal has been sent successfully...");
    }

    public void adminProposalApproved(int LoanID, String TokenID) {
        // Admin approved the Proposal
        Response approve_proposal = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminProposalApproved)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(approve_proposal.getBody().asString());
        String approve_prop = approve_proposal.path("data.updatedLoanStatus");
        assertThat(approve_prop, equalTo("PROPOSAL_APPROVED"));
        test.log(LogStatus.INFO, "Proposal has been approved...");
    }

    public void adminLoanApproved(int LoanID, String TokenID) {
        // Admin approved the Loan
        Response approve_Loan = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminLoanApproved)
                .then().assertThat().statusCode(200).extract().response();
        String approve_loan = approve_Loan.path("data.updatedLoanStatus");
        assertThat(approve_loan, equalTo("LOAN_APPROVED"));
        System.out.println(approve_Loan.getBody().asString());
        test.log(LogStatus.INFO, "Loan has been approved...");
    }

    public void adminContractAccepted(int LoanID, String TokenID) {
        // Admin accept the contract
        Response contract_accept = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminContractAccepted)
                .then().assertThat().statusCode(200).extract().response();
        String loan_contract = contract_accept.path("data.message");
        assertThat(loan_contract, equalTo("Loan Status was updated successfully"));
        System.out.println(contract_accept.getBody().asString());
        test.log(LogStatus.INFO, "Admin Contract has been accepted...");
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void adminLoanPublished(int LoanID, String TokenID) throws JsonProcessingException {
        LoanPublished post_LP = new LoanPublished();
        //get the current date time
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = dateFormat.format(date);
        System.out.println("date is : " + dateString);

        post_LP.setPublishDateTime(dateString);
        post_LP.setLoanId(LoanID);
        //POJO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post_LP);
        //JSON to POJO
        LoanPublished post_LP2 = objectMapper.readValue(s, LoanPublished.class);
        System.out.println("publish DT is :" + post_LP2.getPublishDateTime());
        System.out.println("Loan id is :" + post_LP2.getLoanId());

        Response isLoanPublished = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .body(post_LP2).when().put(APIPath.apiPath.AdminLoanPublished)
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(isLoanPublished.getBody().asString());
        String isLoanPublishedUpdated = isLoanPublished.path("status.message");
        assertThat(isLoanPublishedUpdated, equalTo("Loan Status was updated successfully"));
        test.log(LogStatus.INFO, "Loan has been published successfully and this test is ended...");
    }

    public void adminLoanUnderRepayment(int LoanID, String TokenID) {
        // Admin does repayment
        Response under_repayment = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminLoanUnderRepayment)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(under_repayment.getBody().asString());
        String loan_underRepaymentt = under_repayment.path("data.updatedLoanStatus");
        assertThat(loan_underRepaymentt, equalTo("UNDER_REPAYMENT"));
        test.log(LogStatus.INFO, "Loan is under repayment status...");
    }

    public void adminLoanAmountPaid(int LoanID, String TokenID) {

        // Admin paid loan amount
        Response LoanAmount_paid = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).queryParam("isEarlyRepaid", true).when().post(APIPath.apiPath.AdminLoanAmountPaid)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(LoanAmount_paid.getBody().asString());
        String loan_amountPaid = LoanAmount_paid.path("status.message");
        assertThat(loan_amountPaid, equalTo("Loan Amount Successfully Paid"));
        test.log(LogStatus.INFO, "Loan amount successfully paid...");
    }

    public void adminLoanCompleted(int LoanID, String TokenID) {
        // Admin does repayment
        Response under_repayment = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminLoanCompleted).then().extract()
                .response();
        System.out.println(under_repayment.getBody().asString());
        String loan_underRepayment = under_repayment.path("data.updatedLoanStatus");
        assertThat(loan_underRepayment, equalTo("COMPLETED"));

        test.log(LogStatus.INFO, "Loan has been completed successfully...");
    }

    public void adminLoanDelayed(int LoanID, String TokenID) {
        // Admin delayed the Loan
        Response admin_loanDelayed = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminLoanDelayed).then().extract()
                .response();
        System.out.println(admin_loanDelayed.getBody().asString());
        String loanDelayed = admin_loanDelayed.path("data.updatedLoanStatus");
        assertThat(loanDelayed, equalTo("DELAYED"));
        test.log(LogStatus.INFO, "Loan has been delayed..");
    }

    public void adminEnabledAutoInvestFeature(int LoanID, String TokenID) {
        // Admin accept the contract
        Response isAutoEnabled = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("isEnabled", true).queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminEnabledAutoInvest).then().extract()
                .response();
        System.out.println(isAutoEnabled.getBody().asString());
        String isAutoEnabledUpdate = isAutoEnabled.path("status.message");
        assertThat(isAutoEnabledUpdate, equalTo("Auto Invest Updated Successfully"));
        test.log(LogStatus.INFO, "AutoInvest feature is enabled successfully...");
    }

    public void adminPartiallyFunded(int opportunityID, String TokenID) {
        // Admin accept the contract
        Response partiallyFunded = RestAssured.given().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("opportunityId", opportunityID).when().put(APIPath.apiPath.AdminPartiallyFunded)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(partiallyFunded.getBody().asString());
        String isPartiallyFunded = partiallyFunded.path("status.message");
        //assertThat(isPartiallyFunded,equalTo("Opportunity status successfully changed to partially funded"));
        test.log(LogStatus.INFO, "Opportunity status successfully changed to partially funded...");
    }

    public void adminDisbursedLoan(int LoanID, String TokenID) {
        // Admin accept the contract
        Response isLoanDisbursed = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().put(APIPath.apiPath.AdminDisbursedLoan)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println("Disbursed Loan response is : " + isLoanDisbursed.getBody().asString());
        String isLoanDisbursedStatusUpdated = isLoanDisbursed.path("data.updatedLoanStatus");
        assertThat(isLoanDisbursedStatusUpdated, equalTo("DISBURSED"));
        test.log(LogStatus.INFO, "Loan is in Disbursed status...");
    }

    public void adminCollectedLoanPercentage(int LoanID, String TokenID) {
        Response isCollectedLoanPercentage = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .queryParam("loanId", LoanID).when().get(APIPath.apiPath.AdminAutoLoanCompletePercent)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(isCollectedLoanPercentage.getBody().asString());
        float loanCollectPer = isCollectedLoanPercentage.path("data.collectedAmountPercent");
        String LoanCollectPercentageSuccessfully = isCollectedLoanPercentage.path("status.message");
        assertThat(LoanCollectPercentageSuccessfully, equalTo("Successfully fetched the loan amount percentage"));
        test.log(LogStatus.INFO, "Fetched loan percentage amount successfully...");
    }

    public int adminGetLoanDetails(int LoanID, String TokenID) {
        Response getLoanDetails = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .pathParams("loanId", LoanID).when().get(APIPath.apiPath.AdminGetLoanDetails)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(getLoanDetails.getBody().asString());
        int opportunityId = getLoanDetails.path("data.opportunityId");
        System.out.println("Opportunity id is " + opportunityId);
        test.log(LogStatus.INFO, "Fetched loan percentage amount successfully...");
        return opportunityId;
    }
    public Map<String,Object> admin_GetLoanDetails(int LoanID, String TokenID) {
        Response getLoanDetails = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all()
                .pathParams("loanId", LoanID).when().get(APIPath.apiPath.AdminGetLoanDetails)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(getLoanDetails.getBody().asString());
        int opportunityId = getLoanDetails.path("data.opportunityId");
        float requestedLoanAmount = getLoanDetails.path("data.requestedLoanAmount");
        int borrowerId = getLoanDetails.path("data.borrowerId");
        float repaymentAmountBorrower = getLoanDetails.path("data.repaymentAmountBorrower");
        boolean movedToManual = getLoanDetails.path("data.movedToManual");
        String loanStatusValue = getLoanDetails.path("data.loanStatusValue");

        System.out.println("Opportunity id is " + opportunityId);
        Map<String,Object> map = new HashMap<>();
        map.put("opportunityId",opportunityId);
        map.put("loanStatusValue",loanStatusValue);
        map.put("requestedLoanAmount",requestedLoanAmount);
        map.put("borrowerId",borrowerId);
        map.put("repaymentAmountBorrower",repaymentAmountBorrower);
        map.put("movedToManual",movedToManual);

        test.log(LogStatus.INFO, "Fetched loan details successfully...");
        return map;
    }
    public Map<String, Object> admin_loanFilterationList(String status, String TokenID,int loanId) {
        //  List<Integer> getLoansFilterList = new ArrayList<Integer>();
        //passing the multiple query parameters as a map
        HashMap<String, Object> map = new HashMap<>();
        map.put("loanId", "Default");
        map.put("borrowerId", "Default");
        map.put("publishedDateFrom", "Default");
        map.put("publishedDateTo", "Default");
        map.put("dueDateFrom", "Default");
        map.put("dueDateTo", "Default");
        map.put("isEarlySettlement", "Default");
        map.put("actualRepaymentDateFrom", "Default");
        map.put("actualRepaymentDateTo", "Default");
        map.put("riskRate", "Default");
        map.put("isAutoInvest", "Default");
        map.put("scheduledDateFrom", "Default");
        map.put("scheduledDateTo", "Default");
        map.put("startDate", "Default");
        map.put("endDate", "Default");
        map.put("status", "'" + status + "'");
        map.put("sortBy", "date");
        map.put("page", "0");
        map.put("limit", "20");
        map.put("mode", "0");

        Response loanFilter = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .contentType("application/json").header("x-auth-token", TokenID).log().all().when()
                .queryParams(map).get(APIPath.apiPath.AdminLoanFilterList)
                .then().log().all().assertThat().statusCode(200).extract().response();
        //convert JSON to string
        JsonPath j = new JsonPath(loanFilter.asString());

        //get values of JSON array after getting array size
        int s = j.getInt("data.listFilterLoanApplicationResDTO.size()");
        System.out.println("Array size is :" + s);
        JSONArray jsonArray = new JSONArray() ;

        //JSONObject obj = new JSONObject();

        //TODO
        Map<String, Object> mapObj = new HashMap<>();
        for (int i = 0; i < s; i++) {
            String getStatus = j.getString("data.listFilterLoanApplicationResDTO[" + i + "].status");
            System.out.println("Loan status is : " + getStatus);
            int getLoanId = j.getInt("data.listFilterLoanApplicationResDTO[" + i + "].loanId");
            System.out.println("Loan id is : " + getLoanId);
            int getBorrowerId = j.getInt("data.listFilterLoanApplicationResDTO[" + i + "].borrowerId");
            System.out.println("Borrower id is : " + getBorrowerId);

//
            //	JSONObject obj = new JSONObject();
            mapObj.put("loanId", loanId);
            mapObj.put("borrowerId", getBorrowerId);
            mapObj.put("status", getStatus);
            jsonArray.put(mapObj);

            //TODO
       //     mapObj.put(String.valueOf(getLoanId), getOpportunityId);

            //String zip = j.getString("Location["+i+"].zip");
            //System.out.println(state);
            //	System.out.println(zip);

        }
//        HashMap<Object, Object> isLoanFiltered = new HashMap<>();
//                        //loanFilter.jsonPath().getMap("data.listFilterLoanApplicationResDTO[0]");
//        isLoanFiltered.get("listFilterLoanApplicationResDTO");
    //    System.out.println(loanFilter.getBody().asString());
        test.log(LogStatus.INFO, "Test is ended...");
        return mapObj;
    }

    public String PostLoanApply_body() throws IOException {
        String jsonBody = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\PostLoanApply.json")));
        Response createLoan = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL)
                .contentType("application/json").header("x-auth-token", "").log().all().body(jsonBody).when()
                .post(APIPath.apiPath.PostApplyLoan).then().log().all().assertThat().statusCode(200).extract().response();
        String loanid = createLoan.path("data.loanId");
        System.out.println("loanid is :" + loanid);
        System.out.println(createLoan.getBody().asString());
        test.log(LogStatus.INFO, "Test is ended...");
        return loanid;
    }
@Test
    public static void GetStaticData() {
        Response getStaticData = RestAssured.given().log().all().baseUri(APIPath.apiPath.BaseURL).log().all().when()
                .get(APIPath.apiPath.GetStaticData)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(getStaticData.getBody().asString());
        String msg = getStaticData.path("status.message");
        System.out.println("message is :" + msg);
        assertEquals(msg, "SignUp StaticData Details");
        //   test.log(LogStatus.INFO, "Test is ended...");
    }

    @Test
    public void adminAllFundsDetails(int loanId, String TokenId) {
        Response admin_AllFunds = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).log().all()
                .contentType("application/json").header("x-auth-token", TokenId).log().all().when()
                .queryParam("loanId", loanId).get(APIPath.apiPath.AdminLoanAllFundsDetails)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(admin_AllFunds.getBody().asString());
        String msg = admin_AllFunds.path("status.message");
        System.out.println("message is :" + msg);
        assertEquals(msg, "All Funds Details Successfully Fetched");
        Assert.assertNotNull(admin_AllFunds.jsonPath().getMap("data.allFundsDetailsDto[0]"), "All funds detail page is empty");
        test.log(LogStatus.INFO, "Test is ended...");
    }

    @Test
    public void adminCreateContractDuringRepayment(int loanId, String TokenId) {
        Response admin_CreateContract = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).log().all()
                .contentType("application/json").header("x-auth-token", TokenId).log().all().when()
                .queryParam("loanId", loanId).post(APIPath.apiPath.AdminCreateContract)
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println(admin_CreateContract.getBody().asString());
        String msg = admin_CreateContract.path("status.message");
        System.out.println("message is :" + msg);
        assertEquals(msg, "Repayment Loan Details Successfully Fetched");
        test.log(LogStatus.INFO, "Test is ended...");
    }
    @Test
    public void adminDownloandContract(String TokenId)  {
        String TokenIdgg = gettoken.test_adminLogin();
        File baseFile = new File(".\\src\\main\\resources\\Downloads");
        System.out.println("Admin token is :" + TokenIdgg);
        Response dowloadedFile = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).log().all().contentType("application/octet-stream")
                .header("x-auth-token", TokenId)
                .when()
                .get(APIPath.apiPath.AdminDownloadContract);
        //assertEquals(dowloadedFile, "pdf/json");
        byte[] dowloaded = dowloadedFile.getBody().asByteArray();
////        File baseFile = new File(".\\src\\main\\resources\\Upload-documents\\contract-participation-id--loan-id-832_1622192987187.pdf");
//        Files.write(baseFile.toPath(), dowloadedFile);
//        if (responseD.getStatusCode() == 200) {
//
//            if (baseFile.exists()) {
//                baseFile.delete();
//            }
//            Files.write(baseFile.toPath(), dowloadedFile);
//            Assert.assertTrue(baseFile.exists());
//            System.out.println("Downloaded an " + responseD.getHeader("Content-Type"));
//            // output contents to file
//            OutputStream outStream = null;
//
//            try {
//                outStream = new FileOutputStream(baseFile);
//                outStream.write(dowloadedFile);
//            } catch (Exception e) {
//                System.out.println("Error writing file " + baseFile.getAbsolutePath());
//            } finally {
//                if (outStream != null) {
//                    outStream.close();
//                }
//            }

    }

    //public void DownloadContract(String TokenId) throws IOException{
//
//    String TokenId =  gettoken.test_adminLogin();
//    Response responseD = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).log().all()
//            .contentType("application/octet-stream").header("x-auth-token", TokenId).when()
//            .get(APIPath.apiPath.AdminDownloadContract).andReturn();
//    byte[] downloadedFile = responseD.getBody().asByteArray();
//    File baseFile = new File(".\\src\\main\\resources\\Upload-documents\\contract-participation-id--loan-id-832_1622192987187.pdf");
//    Files.write(baseFile.toPath(),downloadedFile);
//
//
//        // check if the URL actually exists
//        if(responseD.getStatusCode() == 200) {
//
//            if (baseFile.exists()) {
//                baseFile.delete();
//            }
//
//            System.out.println("Downloaded an " + responseD.getHeader("Content-Type"));
//
//            //    byte[] fileContents = response.getBody().asByteArray();
//
//            // output contents to file
//            OutputStream outStream = null;
//
//            try {
//                outStream = new FileOutputStream(baseFile);
//                outStream.write(downloadedFile);
//            } catch (Exception e) {
//                System.out.println("Error writing file " + baseFile.getAbsolutePath());
//            } finally {
//                if (outStream != null) {
//                    outStream.close();
//                }
//            }
//        }
//

}