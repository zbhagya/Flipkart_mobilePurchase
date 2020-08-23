package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_DataProvider {

	static XSSFWorkbook wb;
	public static String mobilename;
	public static String price;
	public static String ramsize;
	public static String brand;
	public static String range;
	public static String readExcelData() throws IOException {
		File file = new File(
				"C:\\Users\\Raja_Bhagya\\eclipse-workspace\\Online_Mobile_Store\\Application_Test_Data\\testData.xlsx");

		FileInputStream fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		{
			for (int i = 0; i < rowCount; i++) {

				// Read data from excel sheet
				mobilename = sheet.getRow(1).getCell(0).getStringCellValue();
				range=sheet.getRow(1).getCell(1).getStringCellValue();
				price = sheet.getRow(1).getCell(2).getStringCellValue();
				ramsize = sheet.getRow(1).getCell(3).getStringCellValue();
				brand = sheet.getRow(1).getCell(4).getStringCellValue();
				System.out.println("Reading Execl  "  +  mobilename +" "  +range  + " " + price + " " +
						 " " +ramsize +  " " + brand );
			}

		}
		return null;
	}
}
