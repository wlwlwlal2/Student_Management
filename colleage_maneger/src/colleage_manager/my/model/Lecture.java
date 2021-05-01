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
	private String subname;
	@Column
	private String lecturename;
	@Column
	private String name;
	@Column
	private String grade;
	
	
	@Column
	private String day;
	

	public String getSubNumber() {
		return subnumber;
	}
	public void setSubNumber(String subnumber) {
		this.subnumber = subnumber;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLectureName() {
		return lecturename;
	}
	public void setLectureName(String lecturename) {
		this.lecturename = lecturename;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}