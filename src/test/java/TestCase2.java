import api.configs.APIPath;
import apiVerifications.APIVerification;
import com.pojo.request.loginPojo.loginPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.configs.APIPath.apiPath.TestParamPWD;
import static io.restassured.RestAssured.given;

public class TestCase2 {
    @Test
    void test1(){
        System.out.println("I'm test1");
    }
    @Test
    public void adminLogin() {
        Response admin_login = given().baseUri(APIPath.apiPath.AdminBaseURL).contentType("application/json")
                .queryParam("email", APIPath.apiPath.Admin_queryParamEmail)
                .queryParam("password", TestParamPWD).when().get(APIPath.apiPath.adminLoginURL).then().log()
                .all().extract().response();
        System.out.println("Response body is " + admin_login.getBody().asString());

        String token = admin_login.path("data.authToken");
        System.out.println(token);
       // return token;
    }
}
