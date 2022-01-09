package colleage_manager.my.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import colleage_manager.my.model.LectureHistory;

public class style {

	public static XSSFCellStyle style1(XSSFWorkbook workbook) {

		XSSFCellStyle Style = workbook.createCellStyle();
		Style.setAlignment(HorizontalAlignment.CENTER);
		Style.setBorderRight(BorderStyle.MEDIUM);
		Style.setBorderLeft(BorderStyle.MEDIUM);
		Style.setBorderTop(BorderStyle.MEDIUM);
		Style.setBorderBottom(BorderStyle.MEDIUM);
		Font fontOfGothicBlackBold16 = workbook.createFont();
		fontOfGothicBlackBold16.setFontName("≥™¥Æ∞ÌµÒ"); // ±€ææ√º
		fontOfGothicBlackBold16.setFontHeight((short) (24 * 20)); // ªÁ¿Ã¡Ó
		Style.setFont(fontOfGothicBlackBold16);

		return Style;
	}

	public static XSSFCellStyle style2(XSSFWorkbook workbook) {

		XSSFCellStyle Style = workbook.createCellStyle();
		Style.setBorderTop(BorderStyle.THIN);
		return Style;
	}

	public static XSSFCellStyle style3(XSSFWorkbook workbook) {

		XSSFCellStyle Style = workbook.createCellStyle();
		Style.setAlignment(HorizontalAlignment.CENTER);
		Style.setBorderTop(BorderStyle.THIN);
		Style.setBorderBottom(BorderStyle.THIN);
		Style.setBorderRight(BorderStyle.THIN);
		Style.setBorderLeft(BorderStyle.THIN);
		
		return Style;
	}

}