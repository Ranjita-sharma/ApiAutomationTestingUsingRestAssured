package api.configs;

public class API_TestData {

	public static final class api_TestData {
		//***************************Test data for Signup flow ***********************

		// Sign up controller - Create an Investor account
		public static final String Investor_SignUpEmail = "ranjeeta@lendo.sa";
		public static final String Investor_SignUpPhone = "+966534221248";
		public static final String Investor_SignUp_Nationality = "IND";
		public static final String Investor_SignUp_Iqama = "2508820178";
		public static final String SignUp_UserType = "INVESTOR";
		public static final String userPassword = "Test@123";
		public static final String user_NewPassword = "Test@1234";

		// Sign up controller - Create a Borrower account
		public static final String Borrower_SignUpEmail = "ranjeeta@lendo.sa";
		public static final String Borrower_SignUpPhone = "+966534221248";
		public static final String Borrower_SignUp_Nationality = "IND";
		public static final String Borrower_SignUp_Iqama = "2508820178";
		public static final String Borrower_SignUp_firstName = "RANJEETA";
		public static final String Borrower_SignUp_lastName = "SHARMA";
		public static final int Borrower_SignUp_crNumber = 1010499636;
		public static final Boolean Borrower_SignUp_crStatus = Boolean.TRUE;
		public static final String Borrower_SignUp_crExpiryDate = "1447-04-20";
		public static final String Borrower_SignUp_jobTitle = "QA";
		public static final String Borrower_SignUp_companyNameArabic = "شركة ليندو السعودية للتمويل - مساهمة مقفلة";
		public static final int Borrower_SignUp_capital = 5000000;
		public static final String Borrower_SignUp_entityStructure = "مســــــــــاهمة";
		public static final String Borrower_SignUp_nationalAddress = "\"Al Olaya Dist.\" ,\"Prince Mohammed Bin Abdulaziz Rd\",3074,12249,0,12213,\"RIYADH\"";
		public static final long Borrower_SignUp_crEntityNumber = 7005485003L;
		public static final String Borrower_SignUp_issueDate = "1440-04-11";

        // Sign up controller - Borrower profile completion
        public static final String Borrower_tradingName = "Lendo Test";
		public static final String Borrower_phoneNumber = "0534221248";
		public static final String Borrower_vatNumber = "111111111111111";
		public static final String Borrower_annualRevenue = "BETWEEN_3_10";
		public static final String Borrower_currentClientsCount = "BETWEEN_11_20";
		public static final String Borrower_currentEmployeesCount = "BETWEEN_26_50";
		public static final String Borrower_nitiqat = "PLATINUM";
		public static final String Borrower_orgStatus = "INCOMPLETE";
		public static final String Borrower_bankName = "THE SAUDI INVESTMENT BANK";
		public static final String Borrower_IBAN = "SA8745000000042176883060";
		public static final String Borrower_benefeciaryName = "SIBCSARIXXX";
		public static final int Borrower_dashboardStageStatus = 1;

		// Sign up controller - Investor profile completion
		public static final String Post_Inv_annualIncome = "BETWEEN_50_250";
		public static final String Post_Inv_companyName = "Test";
		public static final int Post_Inv_dashboardStageStatus = 1;
		public static final String Post_Inv_dateOfBirth = "1991-04-04";
		public static final String Post_Inv_employer = "FINANCE";
		public static final String Post_Inv_estimateAnnualDeposit = "SAR_5K_20K";
		public static final String Post_Inv_estimateAnnualInvestment = "SAR_5K_20K";
		public static final String Post_Inv_focalResponse = "not-matching";
		public static final String Post_Inv_fullNameArab = "عربي اسم اسم عربي ";
		public static final String Post_Inv_fullNameEng = "Mandy Mendell Zavala";
		public static final String Post_Inv_gender = "FEMALE";
		public static final String Post_Inv_idExpiryDate = "2090-03-24";
		public static final String Post_Inv_Iqama = "2508820178";
		public static final Boolean Post_Inv_isCreditedInvestor = false;
		public static final Boolean Post_Inv_isSaudi = false;
		public static final String Post_Inv_jobStatus = "EMPLOYED";
		public static final String Post_Inv_jobTitle = "CAR_DEALER";
		public static final String Post_Inv_nationalAddress = "Random 5,Tester’s street,222,4551,12312,160015,Random";
		public static final String Post_Inv_nationality = "IND";
		public static final String Post_Inv_natureOfBusiness = "FINANCE";
		public static final String Post_Inv_quetionId = "B";
		public static final int Post_Inv_ramAnswer_A = 2;
		public static final int Post_Inv_ramAnswer_B = 1;
		public static final int Post_Inv_ramAnswer_E = 2;
		public static final int Post_Inv_ramAnswer_G = 2;
		public static final int Post_Inv_ramQuestionA_optionId = 7;
		public static final String Post_Inv_sourceOfInvestment = "SELF_EMPLOYED_COMPANY_PROFILE";

		//Sign up controller -save IBAN details for Investor
		public static final String bankName_inv = "THE SAUDI INVESTMENT BANK";
		public static final String IBAN_inv = "SA8745000000042176883060";
		public static final String benefeciaryName_inv = "SIBCSARIXXX";

		//Borrower - Apply for a loan
		public static final int requestedLoanAmount = 100000;
		public static final int requestDuration = 65;
		public static final int productId = 1;
		public static final String buyerName = "Lendo Tester";
		public static final String purposeOfUsage = "WORKING_CAPITAL_FINANCE";

	}
}