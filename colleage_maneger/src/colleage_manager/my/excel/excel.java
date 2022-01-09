package colleage_manager.my.excel;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.excel.style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class excel {

	private static excel instance;
	private CommonAPI api = CommonAPI.getInstance();
	
	
	public static excel getInstance() {
		if (instance == null) {
			instance = new excel();
		}
		return instance;
	}

	String sheetName = "이름설정안됨";
	XSSFWorkbook workbook = new XSSFWorkbook();
	
	
	public boolean excel(String number, List<LectureHistory> list, int ty) {

		// Style1.setVerticalAlignment(VerticalAlignment.MIDDLE);
		UserAuth auth = UserAuth.getInstance();
		Common user = auth.getUser();

		XSSFCellStyle Style1 = style.style1(workbook);
		XSSFCellStyle Style2 = style.style2(workbook);
		XSSFCellStyle Style3 = style.style3(workbook);
		
		String filePath = "C:\\Users\\samsung\\git\\Student_Management\\colleage_maneger\\excel";
		String fileNm = number + ".xlsx";
		
		if(workbook.getSheetIndex(sheetName) == 0)
		workbook.removeSheetAt(0);
		
		XSSFSheet sheet = workbook.createSheet(sheetName);
		
		sheet.setDefaultColumnWidth(20);
		sheet.setColumnWidth(0, 1000);
		
		sheet.setColumnWidth(7, 1000);
		
		Cell cell;
		Row row;

		if (ty == 0) {
			// 빈 Sheet를 생성
			row = sheet.createRow(1);
			cell = row.createCell(1);
			cell.setCellValue("성적 증명서");
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 6));
			cell.setCellStyle(Style1);
			cell = row.createCell(2);
			cell.setCellStyle(Style1);
			cell = row.createCell(3);
			cell.setCellStyle(Style1);
			cell = row.createCell(4);
			cell.setCellStyle(Style1);
			cell = row.createCell(5);
			cell.setCellStyle(Style1);
			cell = row.createCell(6);
			cell.setCellStyle(Style1);

			row = sheet.createRow(2);
			cell = row.createCell(1);
			cell.setCellStyle(Style1);
			cell = row.createCell(2);
			cell.setCellStyle(Style1);
			cell = row.createCell(3);
			cell.setCellStyle(Style1);
			cell = row.createCell(4);
			cell.setCellStyle(Style1);
			cell = row.createCell(5);
			cell.setCellStyle(Style1);
			cell = row.createCell(6);
			cell.setCellStyle(Style1);

			row = sheet.createRow(4);
			cell = row.createCell(1);
			cell.setCellValue("이름");
			
			cell = row.createCell(2);
			if(user.getName() != null)
			cell.setCellValue(user.getName());
			
			cell = row.createCell(4);
			cell.setCellValue("학과");
			
			cell = row.createCell(5);
			System.out.println(user.getClassNumber());
			cell.setCellValue(user.getClassName());
			
			row = sheet.createRow(8);
			cell = row.createCell(1);
			cell.setCellValue("강의명");
			cell.setCellStyle(Style3);
			cell = row.createCell(2);
			cell.setCellValue("점수");
			cell.setCellStyle(Style3);
			cell = row.createCell(3);
			cell.setCellValue("반영점수");
			cell.setCellStyle(Style3);
			cell = row.createCell(4);
			cell.setCellValue("출석");
			cell.setCellStyle(Style3);
			cell = row.createCell(5);
			cell.setCellValue("지각");
			cell.setCellStyle(Style3);
			cell = row.createCell(6);
			cell.setCellValue("결석");
			cell.setCellStyle(Style3);

			int i = 0;
			int sumGrade = 0;
			int sumRealGrade = 0;
			int grade;
			int realgrade;
			
			for (; i < list.size(); i++) {
				String[] arr = new String[6];
				LectureHistory student = list.get(i);
				arr[0] = student.getLecture().getLectureName();
				arr[1] = student.getGrade();
				
				try {
					grade = Integer.parseInt(arr[1]);
					sumGrade = sumGrade + grade;
					} catch (NumberFormatException e) {
					}
					
				arr[2] = student.getRealGrade();
				try {
					realgrade = Integer.parseInt(arr[2]);
					sumRealGrade = sumRealGrade + realgrade;
					} catch (NumberFormatException e) {	
					}
				
				arr[3] = student.getMaxAttendance();
				arr[4] = student.getAttendance();
				arr[5] = student.getAbsence();

				row = sheet.createRow(i + 9);
				cell = row.createCell(1);
				cell.setCellValue(arr[0]);
				cell = row.createCell(2);
				cell.setCellValue(arr[1]);
				cell = row.createCell(3);
				cell.setCellValue(arr[2]);
				cell = row.createCell(4);
				cell.setCellValue(arr[3]);
				cell = row.createCell(5);
				cell.setCellValue(arr[4]);
				cell = row.createCell(6);
				cell.setCellValue(arr[5]);

			}

			row = sheet.createRow(i + 10);
			cell = row.createCell(1);
			cell.setCellValue("평균 점수");
			cell.setCellStyle(Style3);
			cell = row.createCell(2);
			
			if(list.size() == 0)
				cell.setCellValue("강의없음");
			else {
			cell.setCellValue(sumGrade/list.size());
			}
			
			cell.setCellStyle(Style3);
					
			cell = row.createCell(4);
			cell.setCellValue("평균 반영점수");
			cell.setCellStyle(Style3);
			cell = row.createCell(5);
			
			if(list.size() == 0)
				cell.setCellValue("강의없음");
			else {
			cell.setCellValue(sumRealGrade/list.size());
			}
			
			cell.setCellStyle(Style3);
		
					
			try {
				FileOutputStream out = new FileOutputStream(new File(filePath, fileNm));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return true;
	}

}
