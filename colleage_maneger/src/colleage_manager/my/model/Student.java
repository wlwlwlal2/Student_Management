package colleage_manager.my.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student {

	
	@Id
	@Column
	private String number;
	@Column
	private String subject;
	@Column
	private String grade;
	@Column
	private String realGrade;
	@Column
	private String maxAttendance; // 과목 출석시간
	@Column
	private String attendance; // 출석시간
	@Column
	private String lateness; // 지각
	@Column
	private String absence; // 결석

	private ArrayList lectureList = new ArrayList();
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public ArrayList getLectureList() {
		return lectureList;
}
	public void setLectureList(String lectureNumber) {
		lectureList.add(lectureNumber);
}
	
	public String getGrade(String lecturenumber) {
		
		return grade;
}
	public void setGrade(String lecturenumber, String Grade) {
		this.grade = grade;
}
	
	public String getRealGrade() {
		return realGrade;
	}
	public void setRealGrade() {
		this.realGrade = realGrade;
	}
	public String getMaxAttendance() {
		return maxAttendance;
	}
	public void setMaxAttendance() {
		this.maxAttendance = maxAttendance;
	}
	public String getAttendancee() {
		return attendance;
	}
	public void setAttendance() {
		this.attendance = attendance;
	}
	public String getLateness() {
		return lateness;
	}
	public void setLateness() {
		this.lateness = lateness;
	}
	public String getAbsence() {
		return absence;
	}
	public void setAbsence() {
		this.absence = absence;
	}
}