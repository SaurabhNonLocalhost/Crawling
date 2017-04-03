<<<<<<< HEAD
package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManipulation {
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	int rowCount;
	int columnCount;
	FileOutputStream outputStream;
	FileInputStream inputStream;

	public ExcelManipulation() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("LearningExchangeData");
		rowCount = 0;
		columnCount = 0;
	}

	public void excelWrite(String data) throws IOException {
		XSSFRow row = sheet.createRow(++rowCount);
		XSSFCell cell = row.createCell(2);
		cell.setCellValue(data);
		//cell.setCellValue(data);

		try {
			outputStream = new FileOutputStream("LearningExchangeData.xlsx");
			workbook.write(outputStream);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> excelRead() throws IOException {

		String excelFilePath = "LearningExchangeData.xlsx";
		inputStream = new FileInputStream(new File(excelFilePath));
		String cellvalue = null;
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		ArrayList<String> a = new ArrayList<String>();
		
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Cell cell = nextRow.getCell(2);
			cellvalue = cell.getStringCellValue();
			a.add(cellvalue);
		}

		workbook.close();
		
		return a;

		
	}
	
	public void destroy() throws IOException
	{
		outputStream.close();
		inputStream.close();
	}

=======
package Pages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelManipulation {
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	int rowCount;
	int columnCount;
	public ExcelManipulation()
	{
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("LearningExchangeData");
		 rowCount=0;
		columnCount = 0;	
	}

	public void excelWrite(String data) throws IOException {
		Row row = sheet.createRow(++rowCount);
		Cell cell = row.createCell(2);
		cell.setCellValue(data);
		cell.setCellValue(data);
	

	try
	{
		FileOutputStream outputStream = new FileOutputStream("LearningExchangeData.xlsx");
		workbook.write(outputStream);
	}
	 catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
}
>>>>>>> origin/master
}