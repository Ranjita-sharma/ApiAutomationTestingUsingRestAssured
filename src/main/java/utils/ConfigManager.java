package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
private static ConfigManager manager;
private static final Properties prop = new Properties();

private ConfigManager() throws IOException
{
	InputStream inputstream = ConfigManager.class.getResourceAsStream("../src/main/resources/config.properties");
	prop.load(inputstream);
}
public static ConfigManager getInstance()
{
	if(manager == null) {
		synchronized (ConfigManager.class) {
			try {
				manager = new ConfigManager();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return manager;
}

public String getString(String Key) {
	return System.getProperty(Key,prop.getProperty(Key));
}
}
