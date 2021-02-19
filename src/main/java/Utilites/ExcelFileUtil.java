package Utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String excelpath) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb = WorkbookFactory.create(fi);
		
	}
	//count no of rows in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}

	//count no of cells in first row
	public int colCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//read cell data from sheet
	public String getCellData(String sheetname,int row,int colum)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(colum).getCellType()== Cell.CELL_TYPE_NUMERIC)
		{
			//store numeric type cell
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(colum).getNumericCellValue();
			//convert celldata cell into string type
			data=String.valueOf(celldata);
		}
		else
		{
			 data=wb.getSheet(sheetname).getRow(row).getCell(colum).getStringCellValue();
		}
		return data;
			
	}
	//setting results in new Workbook
	public void setCellData(String sheetname,int row,int column,String status,String writefile) throws Throwable
	{
		//get sheet from wb
		Sheet ws=wb.getSheet(sheetname);
		//get row from sheet
		Row rownum = ws.getRow(row); 	
		//create cell
		Cell cell = rownum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.GREEN.getIndex());
		    ////Apply Bold To The Text
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.RED.getIndex());
		    ////Apply Bold To The Text
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.BLUE.getIndex());
		    ////Apply Bold To The Text
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writefile);
		wb.write(fo);
		
	}

}
