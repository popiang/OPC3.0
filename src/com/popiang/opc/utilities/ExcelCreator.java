package com.popiang.opc.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import com.popiang.opc.dao.Lead;

/*
 * this class create excel file 
 */

@Component
public class ExcelCreator 
{
	//this method creates excel file using leads passed through method arguments
	public String createExcel(List<Lead> leads, HttpServletRequest context, String eventName)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("Leads");
		
		//CREATE TABLE TITLE
		Row rowTitle = worksheet.createRow(0);
		rowTitle.createCell(0).setCellValue(eventName);
		CellStyle titleStyle = workbook.createCellStyle();
		Font titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontHeightInPoints((short)18);
		titleStyle.setFont(titleFont);
		rowTitle.getCell(0).setCellStyle(titleStyle);
		
		worksheet.addMergedRegion( CellRangeAddress.valueOf("A1:C1"));
		
		//CREATE HEADING
		Row rowHeading = worksheet.createRow(2);
		rowHeading.createCell(0).setCellValue("No");
		rowHeading.createCell(1).setCellValue("Name");
		rowHeading.createCell(2).setCellValue("IC");
		rowHeading.createCell(3).setCellValue("HP Number");
		rowHeading.createCell(4).setCellValue("Marital Status");
		rowHeading.createCell(5).setCellValue("Email");
		rowHeading.createCell(6).setCellValue("Occupation");
		rowHeading.createCell(7).setCellValue("Income");
		rowHeading.createCell(8).setCellValue("Often Travel");
		rowHeading.createCell(9).setCellValue("Local Or Overseas");
		rowHeading.createCell(10).setCellValue("Prefered Payment");		
		rowHeading.createCell(11).setCellValue("TM Code");
		rowHeading.createCell(12).setCellValue("Date");
		rowHeading.createCell(13).setCellValue("Event");
		
		for( int i = 0; i < 14; ++i )
		{
			CellStyle styleRowHeading = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setFontHeightInPoints((short)11);
			styleRowHeading.setFont(font);
			styleRowHeading.setVerticalAlignment(VerticalAlignment.CENTER);
			rowHeading.getCell(i).setCellStyle(styleRowHeading);
		}
		
		//CREATE CONTENT
		
		int r = 3;
		
		try {
			for(Lead lead : leads)
			{
				Row row = worksheet.createRow(r);
				
				//CELL NO
				Cell cellNo = row.createCell(0);
				cellNo.setCellValue(r - 2);
				
				//CELL NAME
				Cell cellName = row.createCell(1);
				cellName.setCellValue(lead.getName());

				//CELL IC
				Cell cellIC = row.createCell(2);
				cellIC.setCellValue(lead.getIC());

				//CELL HP NUMBER
				Cell cellHPNumber = row.createCell(3);
				cellHPNumber.setCellValue(lead.getHpNumber());

				//CELL MARITAL STATUS
				Cell cellMaritalStatus = row.createCell(4);
				cellMaritalStatus.setCellValue(lead.getMaritalStatus());

				//CELL OCCUPATION
				Cell cellOccupation = row.createCell(5);
				cellOccupation.setCellValue(lead.getOccupation());

				//CELL EMAIL
				Cell cellEmail = row.createCell(6);
				cellEmail.setCellValue(lead.getEmail());

				//CELL INCOME
				Cell cellIncome = row.createCell(7);
				cellIncome.setCellValue(lead.getHouseholdIncome());

				//CELL OFTEN TRAVEL
				Cell cellOftenTravel = row.createCell(8);
				cellOftenTravel.setCellValue(lead.isOftenTravel());

				//CELL LOCAL OR OVERSEAS
				Cell cellLocalOrOverseas = row.createCell(9);
				cellLocalOrOverseas.setCellValue(lead.getLocalOrOversea());

				//CELL PREFERED PAYMENT
				Cell cellPreferedPayment = row.createCell(10);
				cellPreferedPayment.setCellValue(lead.getPreferedPayment());
				
				//CELL TM CODE
				Cell cellTMCode = row.createCell(11);
				cellTMCode.setCellValue(lead.getTmCode());

				//CELL LEAD'S DATE
				Cell cellLeadsDate = row.createCell(12);
				cellLeadsDate.setCellValue(lead.getDate());
				
				//CELL LEAD'S EVENT
				Cell cellEvent = row.createCell(13);
				cellEvent.setCellValue(lead.getEventName());
				
				r++;
			}
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		//SET AUTOFIT
		
		for( int i = 0; i < 14; ++i )
		{
			worksheet.autoSizeColumn(i);
		}

		
		//SAVE TO EXCEL FILE
		
		String filePath = null;
		
		try
		{
			File file = new File(context.getServletContext().getRealPath("/WEB-INF/leads.xls"));
			
			filePath = file.getAbsolutePath();
			FileOutputStream out = new FileOutputStream( file );
			workbook.write(out);
			out.close();
			workbook.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return filePath;
	}
}


































