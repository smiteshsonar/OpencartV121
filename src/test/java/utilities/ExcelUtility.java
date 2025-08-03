package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
//		fi = new FileInputStream(sheetName);   // ---------------------------------
		fi = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
//		fi = new FileInputStream(sheetName); ---------------------------
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		DataFormatter formater = new DataFormatter();
		String data;
		
		try {
			data = formater.formatCellValue(cell);
		}
		catch(Exception e) {
			data = "";
		}
		
		workbook.close();
		fi.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int colNum, String data) throws IOException {
		File xlfile = new File(path);
		if(!xlfile.exists()) {     // if file does not exists create new file
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook();
		
		if(workbook.getSheetIndex(sheetName) ==-1) {   // if sheet does not exist create new sheet
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
			
		}
		
		if(sheet.getRow(rownum)==null) {   // if row not exist create new row
			sheet.createRow(rownum);
			row=sheet.getRow(rownum);
			
			cell=row.createCell(colNum);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fo.close();
		} 
	}
}
