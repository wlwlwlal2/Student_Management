package colleage_manager.my.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Lecture {

	
	@Id
	@Column
	private String subnumber;
	@Column
	private String number;
	@Column
	private String grade;
	@Column
	private int gradeint;
	@Column
	private int realgrade;
	@Column
	private int day;
	@Column
	private int late;
	@Column
	private int absent;

	public String getSubNumber() {
		return subnumber;
	}
	public void setSubNumber(String subnumber) {
		this.subnumber = subnumber;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getGradeint() {
		return gradeint;
	}
	public void setGradeint(int gradeint) {
		this.gradeint = gradeint;
	}
	public int getRealGrade() {
		return realgrade;
	}
	public void setRealGrade(int realgrade) {
		this.realgrade = realgrade;
		
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getAbsent() {
		return absent;
	}
	public void setAbsent(int absent) {
		this.absent = absent;
	}
}