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
}