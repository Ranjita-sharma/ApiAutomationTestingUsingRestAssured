package integrationTest;

import api.configs.APIPath;
import com.pojo.request.AdminPojo.AdminAddInvestmentTerms;
import com.pojo.request.AdminPojo.adminCreatesCorporateInvestors;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import qaFactory.DB_Connections;

import java.sql.Connection;
import java.util.Vector;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CompleteCorporateInvestorFlow {
    @Test
    public void adminCreatesInstitutionalInvestor() {
        DB_Connections dbconnection = new DB_Connections();
        Connection con = dbconnection.connect_to_db("stg_user_service","dblenadmin","lendbk3s");
    //    String fetchtableName = dbconnection.read_data(con,"users");
        //if (fetchtableName! )

        adminCreatesCorporateInvestors adminCreateCorporateInvestor = new adminCreatesCorporateInvestors();
        adminCreateCorporateInvestor.setEmail("ranjita1@lendo.sa");
        adminCreateCorporateInvestor.setCrNumber(1010626585);
        adminCreateCorporateInvestor.setPhoneNumber("+919211057857");
        adminCreateCorporateInvestor.setInvestorType("CORPORATE");

        Response createNewCorporateInv = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").body(adminCreateCorporateInvestor).log().all()
                .when().post(APIPath.apiPath.AdminCreateCorporateInvestor).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + createNewCorporateInv.getBody().asString());
        int investorId = createNewCorporateInv.path("data.investorId");
        System.out.println("Investor id is :" + investorId );
       // return investorId;
    }

    @Test
    public static Vector adminGetCorporateInvestorInfo() {
        Response adminGetInvestorInfo = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId", 9259).when().get(APIPath.apiPath.AdminGetInvestorInfo).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminGetInvestorInfo.getBody().asString());

        String email = adminGetInvestorInfo.path("data.email");
        System.out.println("Investor email id is :" + email);

        String phoneNumber = adminGetInvestorInfo.path("data.email");
        System.out.println("Investor phoneNumber is :" + phoneNumber);
        assertNotNull(phoneNumber, "phoneNumber is null");

        int investorId = adminGetInvestorInfo.path("data.investorId");
        System.out.println("investorId is :" + investorId);
        assertNotNull(investorId, "investorId is null");

        String userType = adminGetInvestorInfo.path("data.userType");
        System.out.println("userType is :" + userType);

        String virtualIban = adminGetInvestorInfo.path("data.virtualIban");
        System.out.println("virtualIban is :" + virtualIban);
        assertNotNull(virtualIban, "virtualIban detail is null");

        String corporateId = adminGetInvestorInfo.path("data.corporateId");
        assertNotNull(corporateId, "corporateId is null");

        String crNumber = adminGetInvestorInfo.path("data.crNumber");
        assertNotNull(corporateId, "crNumber is null");

        Boolean emailVerified = adminGetInvestorInfo.path("data.emailVerified");
        assertTrue(emailVerified, "email is not verified");

        Vector vector = new Vector();
        vector.add(phoneNumber);
        vector.add(emailVerified);
        vector.add(corporateId);

        return vector;
    }

    @Test
    public static void getAdditionalManagerAndOwner_inv() {
        Response adminGetManagerOwnerInfo = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId", 9259).when().get(APIPath.apiPath.AdminGetAdditionalManagerAndOwnerInfo).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminGetManagerOwnerInfo.getBody().asString());
    }
    @Test
    public static void getRiskConfigInfo_inv(){
        Response adminGetManagerOwnerInfo = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId",9259).when().get(APIPath.apiPath.AdminGetRiskConfigInfo).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminGetManagerOwnerInfo.getBody().asString());
        assertNotNull(adminGetManagerOwnerInfo.getBody().asString(),"Risk config info is null");
    }
    @Test
    public static void adminUpdateInvestmentTerms_inv(String corporateId){
        AdminAddInvestmentTerms addInvestmentTerms = new AdminAddInvestmentTerms();
        addInvestmentTerms.setRiskCategory("A");
        addInvestmentTerms.setSuccessFees("20");
        addInvestmentTerms.setLoanTenure("60");
        addInvestmentTerms.setMinCap("10");
        addInvestmentTerms.setMaxCap("40");
        addInvestmentTerms.setCorporateId(corporateId);

        Response adminAddInvestmentTerms = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId",9259).body(addInvestmentTerms).when().post(APIPath.apiPath.AdminGetRiskConfigInfo).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminAddInvestmentTerms.getBody().asString());
      //  assertNotNull(adminGetManagerOwnerInfo.getBody().asString(),"Risk config info is null");
    }
    @Test
    public static void adminGetInstitutionalInfo(String investorId){

        Response adminGetInvestmentTerms = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId",9259).when().get(APIPath.apiPath.AdminGetInvestmentTerms).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminGetInvestmentTerms.getBody().asString());

       int investor_Id = adminGetInvestmentTerms.path("data.investorId");
       assertNotNull(investor_Id,"investorId is null");

       int coporate_Id = adminGetInvestmentTerms.path("data.corporateId");
       assertNotNull(coporate_Id,"corporateId is null");

       String riskCategory = adminGetInvestmentTerms.path("data.riskCategory");
        assertNotNull(riskCategory,"riskCategory is null");

        int successFees = adminGetInvestmentTerms.path("data.successFees");
        assertNotNull(successFees,"successFees is null");

        int loanTenure = adminGetInvestmentTerms.path("data.loanTenure");
        assertNotNull(loanTenure,"loanTenure is null");

        int minCap = adminGetInvestmentTerms.path("data.minCap");
        assertNotNull(minCap,"minCap     is null");

        int maxCap = adminGetInvestmentTerms.path("data.maxCap");
        assertNotNull(maxCap,"maxCap is null");
    }
    @Test
    public static void adminGetInvestmentTerms_inv(String investorId){

        Response adminGetInvestmentTerms = RestAssured.given().log().all().baseUri(APIPath.apiPath.AdminBaseURL)
                .header("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MDU2IiwiaWF0IjoxNjQ4NTU2MDI4fQ.B12gvgVyDN127MP9yL68zGqYuO1YLTd1q2Gv3DkRD-lOu6L_TDa7N1s4kx9hRzACF5BXT61V2vHn7izofNaQzA").log().all()
                .queryParams("investorId",9259).when().get(APIPath.apiPath.AdminGetInstitutionalInfo).then().assertThat().statusCode(200).extract().response();
        System.out.println("Response body is :" + adminGetInvestmentTerms.getBody().asString());

        int investor_Id = adminGetInvestmentTerms.path("data.investorId");
        assertNotNull(investor_Id,"investorId is null");

        String crNumber = adminGetInvestmentTerms.path("data.crNumber");
        assertNotNull(crNumber,"crNumber is null");

        String extraNotes = adminGetInvestmentTerms.path("data.extraNotes");
        System.out.println("extraNotes is : " + extraNotes);
    }

}
