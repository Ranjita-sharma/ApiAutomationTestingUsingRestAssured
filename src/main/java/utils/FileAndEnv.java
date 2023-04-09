package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileAndEnv {
public static Map<String,String> fileAndenv = new HashMap<String,String>();
public static Properties propMain = new Properties();
public static Properties propPreSet = new Properties();

public static Map<String,String> envAndfile(){

	String enviornment = System.getProperty("env");
	try 
	{
		//need to update below configuration for dev and prod
		if (enviornment.equalsIgnoreCase("dev")) {
			FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/dev.properties");
			propMain.load(fisDev);
			fileAndenv.put("ServerURL", propMain.getProperty("ServerURL"));
			fileAndenv.put("PortNo", propMain.getProperty("PortNo"));
			fileAndenv.put("InvestorUsername", propMain.getProperty("InvestorUsername"));
			fileAndenv.put("InvestorPwd", propMain.getProperty("InvestorPwd"));
		} else if (enviornment.equalsIgnoreCase("stg")) {
			FileInputStream fisStg = new FileInputStream(System.getProperty("user.dir") + "/inputs/stg.properties");
			propMain.load(fisStg);
			fileAndenv.put("ServerURL", propMain.getProperty("ServerURL"));
			fileAndenv.put("PortNo", propMain.getProperty("PortNo"));
			//fileAndenv.put("InvestorUsername", propMain.getProperty("InvestorUsername"));
			//fileAndenv.put("InvestorPwd", propMain.getProperty("InvestorPwd"));
		} else if (enviornment.equalsIgnoreCase("prod")) {
			FileInputStream fisProd = new FileInputStream(System.getProperty("user.dir") + "/inputs/prod.properties");
			propMain.load(fisProd);
			fileAndenv.put("ServerURL", propMain.getProperty("ServerURL"));
			fileAndenv.put("PortNo", propMain.getProperty("PortNo"));
			fileAndenv.put("InvestorUsername", propMain.getProperty("InvestorUsername"));
			fileAndenv.put("InvestorPwd", propMain.getProperty("InvestorPwd"));
		}
	}catch (Exception e){
				
		}
	return fileAndenv;
	}

public static Map<String, String> getConfigReader(){
	if(fileAndenv == null) {
		fileAndenv = envAndfile();
	}
		return fileAndenv;
	}
}

	

