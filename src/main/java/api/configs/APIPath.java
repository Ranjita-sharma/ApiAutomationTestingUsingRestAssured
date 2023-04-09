package api.configs;

public class APIPath {

	public static final class apiPath {
		//***************************Admin details including URL,query params***********************
		public static final String AdminBaseURL = "http://stg.adminbff.newlendo.com";
		public static final String Admin_queryParamEmail = "admin@gmail.com";
		public static final String AdminCreateLoanProposal= "api/v1/auth/loan-proposal";
		public static final String AdminAddGaurantees= "/api/v1/admin/loan-application/add_update_loan_gaurantee";
		public static final String AdminGetLoanGaurantees= "/api/v1/admin/loan-application/get_loan_gaurantee";
		public static final String AdminGetAllGaurantees= "/api/v1/admin/loan-application/get_all_gaurantee_name";
		public static final String AdminGetAuthorizedSignatories= "/api/v1/admin/loan-application/authorized-signatories";
		public static final String AdminSentLoanProposal  = "/api/v1/status-transition/auth/proposal-sent";
		public static final String AdminProposalApproved = "/api/v1/status-transition/auth/proposal-approved";
		public static final String AdminLoanApproved = "/api/v1/status-transition/auth/loan-approved";
		public static final String AdminContractAccepted = "/api/v1/status-transition/auth/contract-accepted";
		public static final String AdminEnabledAutoInvest = "/api/v1/admin/auto-invest/enabled-auto-invest";
		public static final String AdminPartiallyFunded = "/api/v1/status-transition/opportunity-partially-funded";
		public static final String AdminDisbursedLoan = "/api/v1/status-transition/auth/disbursed";
		public static final String AdminLoanPublished = "/api/v1/status-transition/auth/published";                                       
		public static final String AdminAutoLoanCompletePercent = "/api/v1/admin/loan-application/loan-percent";
		public static final String AdminGetLoanDetails = "/api/v1/admin/loan-application/{loanId}";
		public static final String AdminLoanUnderRepayment = "/api/v1/status-transition/auth/under-repayment";
		public static final String AdminLoanAmountPaid = "/api/v1/admin/repayment/pay-loan-amount";
		public static final String AdminLoanCompleted = "/api/v1/status-transition/auth/completed";
		public static final String AdminLoanDelayed = "/api/v1/status-transition/delayed";
		public static final String AdminDepositFund = "/api/v1/wallet/auth/desposit";
		public static final String AdminWithdrawFund = "/api/v1/wallet/auth/deductAmount";
		public static final String AdminCreateContract = "/api/v1/admin/repayment/create-contract-standalone";
		public static final String AdminDownloadContract = "/api/v1/admin/document/download/contract-participation-id--loan-id-832_1622192987187.pdf";
		public static final String AdminLoanAllFundsDetails = "/api/v1/admin/funds/all-funds-details";
		public static final String AdminLoanFilterList  = "/api/v1/admin/loan-application/loan-filter-listings";
		//Admin Corporate Investors Api's
		public static final String AdminCreateCorporateInvestor  ="/api/v1/admin/enterprise/registration ";
		public static final String AdminGetInstitutionalInfo = "/api/v1/Corporate/getInstitutionalInfo";
		public static final String AdminGetInvestorInfo = "/api/v1/Corporate/getInstitutionalInfo";
		public static final String AdminGetRiskConfigInfo = "/api/v1/admin/auto-invest/risk-config";
		public static final String AdminGetAdditionalManagerAndOwnerInfo = "/api/v1/admin/enterprise/getAdditionalManagerAndOwner-investor";
		public static final String AdminGetInvestmentTerms = "/api/v1/Corporate/getInvestmentTerms";
		// Base URL,query params
		public static final String BaseURL = "http://stg.consumerbff.newlendo.com";
		public static final String BaseURL_dev = "http://consumer-client-dev.newlendo.com/";
		public static final String Investor_queryParamEmail = "tah16@gmail.com";
		public static final String QueryParamPWD = "Test@123";
		public static final String Borrower_queryParamEmail = "br02@gmail.com";
		//"sme1@gmail.com";
		public static final String TestParamPWD = "Test@123";
		// User Login controller
		public static final String ConsumerLoginURL = "/api/v1/user/login/v2";
		public static final String adminLoginURL = "/api/v1/user/login";
		public static final String LoginOTPVerify = "/api/v1/user/login/otp/verify";
		public static final String GetUserDetails = "/api/v1/user/mixpanel/getUserDetails";
		public static final String loginDefaultOTP = "1111";
		//*********************Investor apis**************************************************************
		public static final String GetWalletDetails = "/api/v1/wallet/auth/details";
		public static final String GetDashboardDetails = "/api/v1/signup/userInvestor/auth/dashboard";
		public static final String GetLoansStatus = "/api/v1/loan-application/auth/status";
		public static final String GetAutoInvestDetails = "/api/v1/auto-invest/dataAuth/details";
		public static final String GetLoanAmountDetails = "/api/v1//dataAuth/loan-amount-funded-percent/";

		// Auto-Invest
		public static final String GetAutoInvestorDetails = "/api/v1/auto-invest/dataAuth/autoinvestor/details";
		public static final String GetRiskConfig = "/api/v1/auto-invest/dataAuth/risk-config";
		public static final String PostApplyAutoInvest = "/api/v1/auto-invest/dataAuth/apply";
		public static final String PostSaveAutoBid = "/api/v1/auto-invest/bidding/auto-bid";
		public static final String PutUpdateAutoInvest = "/api/v1/auto-invest/dataAuth/update";
		public static final String PutEnabledAutoInvest = "/api/v1/auto-invest/enabled-auto-invest";
		//Transaction Controller
		public static final String GetTransactionDetails = "/api/v1/transaction/filter-transactionList";
		//Notification controller
		public static final String Get_NotificationDetails ="/api/v1/newNotification/auth/internalNotifications";
		// Sign up and Utility controller
		public static final String PostAddAdminSignup = "/api/v1/signup/admin/registration";
		public static final String Post_ChangeForgetPassword = "/api/v1/signup/forgetPassowrd/changePassword";
		public static final String Post_SendOTP_ForgetPWD = "/api/v1/signup/forgetPassword/send/otp";
		public static final String Post_VerifyOTP_ForgetPWD = "/api/v1/signup/forgetPassowrd/otp/verify";
		public static final String PostSaveIbanDetail = "/api/v1/signup/investor/auth/saveIbanDetail";
		public static final String PostVerifyInvestor = "/api/v1/signup/investor/auth/verify";
		public static final String PostSaveIbanDetail_id = "/api/v1/signup/investor/check/nationalIdOriqamaId";
		public static final String PostCreateInvestorProfile = "/api/v1/signup/investor/profile";
		public static final String Post_CompleteInvestorProfile = "/api/v1/signup/investor/auth/verify/v2";
		public static final String Post_SendSignUpOTP = "/api/v1/util/otp/send";
		public static final String Post_SignUpOTPVerify = "/api/v1/util/otp/verify";
		public static final String PostProfileComplete_borrower = "/api/v1/signup/organisation/auth/profile/complete";
		public static final String GetOrganisationDetails = "/api/v1/signup/organisation/details";
		public static final String Post_CreateBorrowerAccount = "/api/v1/signup/organisation/registration";
		public static final String GetRegistrationCRDetail = "	/api/v1/signup/organisation/registration/crDetail";
		public static final String GetRegistrationIBANDetail = "/api/v1/signup/organisation/registration/IbanDetail";
		public static final String GetStaticData = "/api/v1/signup/staticData";
		public static final String Post_ChangePassword = "/api/v1/signup/user/auth/changePassword";
		public static final String Post_VerifyOTP_ChangePassword = "/api/v1/signup/user/auth/verify/otp/changePassword";
		public static final String PostDepositeFundDummy = "/api/v1/signup/user/auth/depositeFundDummy";
		public static final String PostInvestFundDummy = "/api/v1/signup/user/auth/investDummy";
		public static final String GetCheckCRNumberExists = "/api/v1/signup/user/checkCRNumber";
		public static final String GetCheckEmailExists = "/api/v1/signup/user/checkEmail";
		public static final String GetCheckPhoneNoExists = "/api/v1/signup/user/checkPhoneNumber";
		public static final String GetGenerateOtp = "/api/v1/signup/user/generateOtp";
		public static final String GetverifyOtp = "/api/v1/signup/user/validateOtp";
		public static final String GetUserProfilefile = "/api/v1/signup/userInvestor/auth/profile";

		// Active Loan Controller
		public static final String GetActiveLoanDetails = "/api/v1/active-loan/auth/{loanId}";
		public static final String GetLoanFees = "/api/v1/active-loan/auth/loan/fees/{loanId}";
		public static final String GetEarlyRepayStats = "/api/v1/active-loan/dataAuth/earlyrepay-stats";
		public static final String GetActiveLoansStats = "/api/v1/active-loan/dataAuth/stats";
		
		//Analytics Controller
		public static final String GetIncome = "/api/v1/analytics/dataAuth/income";
		public static final String GetInvestmentData = "/api/v1/analytics/dataAuth/investmentData";
		public static final String GetRiskRate = "/api/v1/analytics/dataAuth/riskRate";
		public static final String GetRoiAnalyticsData = "/api/v1/analytics/dataAuth/roiAnalyticsData";

		//Loan creation
		public static final String PostApplyLoan = "/api/v1/loan-application/dataAuth/apply";
		public static final String UploadDocument = "/api/v1/util/auth/upload-document";
		public static final String SubmitUploadedDocument = "/api/v1/auth/user/documents/link";
		public static final String UploadLoanApplicationDoc = "/api/v1/loan-application/dataAuth/upload-document";

		// Bidding controller
		public static final String PostSaveBid = "/api/v1/dataAuth/bidding/bid";
		public static final String GetCollectedAmountPercent = "/api/v1/dataAuth/loan-amount-funded-percent";
		
		// Contract Status controller
		public static final String PutContractAccepted = "/api/v1/auth/contract-status/contract-accepted";
		public static final String PutContractRejected = "/api/v1/auth/contract-status/contract-rejected";
		
        //Dashboard Controller
		public static final String GetIndex = "/api/v1/dashboard/dataAuth/index";
		
		//Document Controller
		public static final String PutDeleteDocumentStatus = "/api/v1/document/delete-document-status";
		
       //Email Controller
		public static final String GetVerifyEmail = "/api/v1/user/email/verified";
		
		//Investor Controller
		public static final String GetInvestmentStat = "/api/v1/dataAuth/investor/investment-stat";
		public static final String GetInvestmentStatTotalIncome = "/api/v1/dataAuth/investor/investment-stat-totalincome";
		public static final String GetInvestorDetails = "/api/v1/dataAuth/investor/investor-details";
		public static final String GetInvestmentDetails = "/api/v1/dataAuth/investor/loan";
		public static final String GetLoanApplications = "/api/v1/loan-application/auth/status";
		
		//Loan Application Controller
		public static final String GetDoumentDetails = "/api/v1/loan-application/auth/get-document-details/{loanId}";
		
		public static final String PutUpdateLoanStatus = "/api/v1/loan-application/auth/status";
		public static final String GetLoanDetails =  "/api/v1/loan-application/dataAuth/{loanId}";
		public static final String GetAppliedLoanDetails = "/api/v1/loan-application/dataAuth/applied/loans";
		//public static final String PostApplyLoan = "/api/v1/loan-application/dataAuth/apply";
		public static final String GetLoanApplicationForBorrowerId = "/api/v1/loan-application/dataAuth/borrower";
		public static final String GetRecentLoanListForBorrowerId = "/api/v1/loan-application/dataAuth/borrower/recent-loan-list";
		public static final String GetRepayLoanListForBorrowerId = "/api/v1/loan-application/dataAuth/borrower/repay-loan";
		public static final String PostUploadDocument = "/api/v1/loan-application/dataAuth/upload-document";
		
		// Repayments Controller
		public static final String GetEarlyRepayStatsDetails = "/api/v1/repayment/dataAuth/earlyrepay-stats-details";
		// Organization Controller
		public static final String PostDeposite = "/api/v1/org/deposite";
		public static final String GetDocumentVerified = "/api/v1/org/documentVerified";
		public static final String PostWithdraw = "/api/v1/org/withdraw";
		// User Controller
		public static final String GetDocumentsForUser = "/api/v1/auth/user/documents";
		public static final String PostlinkUserDocuments = "/api/v1/auth/user/documents/link";
		public static final String GetLanguage = "/api/v1/auth/user/get/language";
		public static final String GetNotifications = "/api/v1/auth/user/notifications";
		public static final String PostUpdateLanguage = "/api/v1/auth/user/update/language";
		public static final String PostUpgradeUserProfile = "/api/v1/auth/user/upgrade/profile";
		public static final String PutDeleteDocument = "/api/v1/auth/user/user/document/delete";
		// Utility controller
		public static final String PostUploadFiles = "/api/v1/util/auth/upload-document";
		public static final String PostResendEmail = "/api/v1/util/auth/user/email/resend";
		public static final String GetDownloadFile = "/api/v1/util/download/document/{objectName}";

		// Wallet controller
	
		public static final String PostWalletWithraw = "/api/v1/wallet/auth/withdraw";
		public static final String GetAccountStatment = "/api/v1/wallet/dataAuth/accountStatement";
		public static final String GetTransactionListByDate = "/api/v1/wallet/dataAuth/accountStatementByDate";
		public static final String GetParseTransactionListByDate = "/api/v1/wallet/dataAuth/parseAccountStatementByDate";
		public static final String PostCreateInvestor = "/api/v1/wallet/auth/withdraw";
        
	}

}