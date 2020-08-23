package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config_DataProvider {
	Properties pro;

	public Config_DataProvider() {
		File file = new File(
				"C:\\Users\\Raja_Bhagya\\eclipse-workspace\\Online_Mobile_Store\\ConfigurationFile\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {

			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getUrl() {

		String url = pro.getProperty("url");
		return url;

	}

	public String getChromePath() {
		String chromePath = pro.getProperty("chromePath");
		return chromePath;
	}

	public String getIEPath() {
		String IEPath = pro.getProperty("IEPath");
		return IEPath;
	}

}
