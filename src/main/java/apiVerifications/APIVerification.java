package apiVerifications;

import static io.restassured.RestAssured.given;

import api.configs.APIPath;
import org.json.JSONArray;
import org.testng.Assert;
import utils.ExtentReportListener;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.sql.*;

public class APIVerification extends ExtentReportListener {

	public static void responseCodeValidation(Response response, int statusCode) {
		try {
			Assert.assertEquals(statusCode, response.getStatusCode());
			test.log(LogStatus.PASS, "Successfully validated status code, status code is ::" + response.getStatusCode());
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Expected status code is::", "instead of getting::" + response.getStatusCode());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}

	}

	public static void responseKeyValidationFromJsonArray(Response response, String key) {
		try {
			JSONArray array = new JSONArray(response.getBody().asString());
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				test.log(LogStatus.PASS, "Validated values are " + obj.get(key));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void responseKeyValidationFromJsonObject(Response response, String key) {
		try {
			JSONObject json = new JSONObject(response.getBody().asString());
			if (json.has(key) && json.get(key) != null) {
				test.log(LogStatus.PASS, "Successfully validated value of " + key + "It is " + json.get(key));
			} else {
				test.log(LogStatus.FAIL, "Key is not available");
			}
		} catch (Exception e) {
		}
	}

	public static void responseTimeValidation(Response response) {
		try {
			long time = response.time();
			test.log(LogStatus.INFO, "Api response time is :: " + time);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void main(String args[]) {
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://10.0.1.66:5432/stg_user_service", "dblenadmin", "lendbk3s");
			con.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = con.createStatement();

			String sql = "UPDATE users "
					+ "SET phone_number = '+9669663969856' "
					+ "WHERE email = 'ran@gmail.com'";
			stmt.executeUpdate(sql);
			con.commit();
			ResultSet rs = stmt.executeQuery("SELECT * from users where email ='ran@gmail.com';");
			while (rs.next()) {
				//String id = rs.getInt("email");
				String phone_number = rs.getString("phone_number");
				String email = rs.getString("email");
				System.out.println("phone number = " + phone_number);
				System.out.println("email = " + email);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

//	public void fetchRecordsFromDB() {
//		Statement stmt = null;
//	stmt = c.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
//		while (rs.next()) {
//		}
//	}
}

//	public static void main(String args[]) {
//		{
//			String SQL = "UPDATE users "
//					+ "SET phone_number = '450000112' "
//					+ "WHERE email = 'investor13@email.com'";
//
//			int affectedrows = 0;
//			try (Connection conn = DriverManager.getConnection(
//					"jdbc:postgresql://10.0.1.66:5432/dm_user_service", "dblenadmin", "lendbk3s");
//				 PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//
//				pstmt.setString(6,"450000112");
//				pstmt.setString(1, "investor13@email.com");
//
//				affectedrows = pstmt.executeUpdate();
//				//System.out.println("updated phone num is : " + );
//			} catch (SQLException ex) {
//				System.out.println(ex.getMessage());
//			}
//		}
//	}
//}

//				con.close();
//			System.out.println("program is exited");
//		} catch (SQLException e) {
//			System.out.println("SQL exception occured" + e);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	}
//public static class Main {
//
//	private String url = "jdbc:postgresql://10.0.1.67:5432/dm_user_service";
//	private String user = "dblenadmin";
//	private String password = "lendbk3s";
//
//	/**
//	 * Connect to the PostgreSQL database
//	 *
//	 * @return a Connection object
//	 */
//	public Connection connect() throws SQLException {
//		return DriverManager.getConnection(url, user, password);
//	}
//
//	public int updatePhoneNumber(String email, String phoneNumber){
//	String SQL = "UPDATE users "
//			+ "SET phone_number = ? "
//			+ "WHERE email = ?";
//
//	int affectedrows = 0;
//
//	try (Connection conn = connect();
//		 PreparedStatement pstmt = conn.prepareStatement(SQL)) {
//
//		pstmt.setString(1, phoneNumber);
//		pstmt.setString(2, email);
//
//		affectedrows = pstmt.executeUpdate();
//
//	} catch (SQLException ex) {
//		System.out.println(ex.getMessage());
//	}
//	return affectedrows;
//}
//
//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String[] args) {
//		Main main = new Main();
//		main.updatePhoneNumber("investor7@email.com", "450000001");
//
//	}
//}

