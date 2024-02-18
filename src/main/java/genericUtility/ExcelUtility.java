package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public String fetchStringDataFromExcel(String sheetName, int rowNumber, int cellNumber)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);

		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
				.getStringCellValue();
	}

	public int fetchIntegerDataFromExcel(String sheetName, int rowNumber, int cellNumber)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);

		return ((int) WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
				.getNumericCellValue());
	}

	public LocalDateTime fetchLocalDateTimeDataFromExcel(String sheetName, int rowNumber, int cellNumber)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);

		LocalDateTime localDateTime = WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowNumber)
				.getCell(cellNumber).getLocalDateTimeCellValue();
		return localDateTime;
	}

	public void writeStringDataInExcel(String sheetName, int rowNumber, int cellNumber, String text)
			throws IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = new XSSFWorkbook(fis);
		workbook.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(text);
		FileOutputStream fos = new FileOutputStream(IPathConstant.EXCEL_FILE_PATH);
		workbook.write(fos);
		workbook.close();
	}

	public void fillColorInExcelCell(String sheetName, int rowNumber, int cellNumber, String color) throws IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = new XSSFWorkbook(fis);
		CellStyle cellStyle = workbook.createCellStyle();

		if (color.equals(IPathConstant.GREEN_COLOR_KEY)) {
			cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		} else if (color.equals(IPathConstant.RED_COLOR_KEY)) {
			cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		}

		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).setCellStyle(cellStyle);
		FileOutputStream fos = new FileOutputStream(IPathConstant.EXCEL_FILE_PATH);
		workbook.write(fos);
		workbook.close();
	}
}
