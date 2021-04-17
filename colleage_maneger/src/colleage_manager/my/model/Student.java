package colleage_manager.my.model;

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
	private int realGrade;
	@Column
	private int maxAttendance; // 과목 출석시간
	@Column
	private int attendance; // 출석시간
	@Column
	private int lateness; // 지각
	@Column
	private int absence; // 결석

	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getSubject() {
		return subject;
}
	public void setSubject(String subject) {
		this.subject = subject;
}
	
	public String getGrade() {
		return grade;
}
	public void setGrade(String Grade) {
		this.grade = grade;
}
	
	public int getRealGrade() {
		return realGrade;
	}
	public void setRealGrade() {
		this.realGrade = realGrade;
	}
	public int getMaxAttendance() {
		return maxAttendance;
	}
	public void setMaxAttendance() {
		this.maxAttendance = maxAttendance;
	}
	public int getAttendancee() {
		return attendance;
	}
	public void setAttendance() {
		this.attendance = attendance;
	}
	public int getLateness() {
		return lateness;
	}
	public void setLateness() {
		this.lateness = lateness;
	}
	public int getAbsence() {
		return absence;
	}
	public void setAbsence() {
		this.absence = absence;
	}
}