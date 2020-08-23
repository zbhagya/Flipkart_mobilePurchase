package factory;

import dataProvider.Config_DataProvider;
import dataProvider.Excel_DataProvider;

public class DataProviderFactory {
	public static Config_DataProvider getConfig()
	{
		Config_DataProvider config=new Config_DataProvider();
		return config;

	}

	public static Excel_DataProvider getExcelConfig()
	{
		Excel_DataProvider excelConfig=new Excel_DataProvider();
		return excelConfig;
	}

}
