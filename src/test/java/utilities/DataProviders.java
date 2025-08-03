package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {
	//Data Providers 1

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testData/Opencart_LoginData.xlsx"; // taking excel file from test data folder
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating and object of xlutility
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols]; // created 2d array which can store values
		
		for(int i = 1; i<=totalRows; i++) {    // reading data from excel file and storing it into 2d array
			
			for(int j = 0; j<totalCols; j++) { //  i - row, j-col
				
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);  // 1 0
			}
		}
		return loginData; 
	}
}
