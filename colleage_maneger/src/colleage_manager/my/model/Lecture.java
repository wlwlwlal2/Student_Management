package colleage_manager.my.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Lecture {

	@Id
	@Column
	private String lecturenumber; // 강의코드
	@Column
	private String subnumber; // 과목코드
	@Column
	private String subname; // 과목명
	@Column
	private String lecturename; // 강의명
	@Column
	private String name; // 교수이름
	@Column
	private String grade; // 점수
	@Column
	private String day; // 요구출석일
	
	private ArrayList listeningStudent = new ArrayList();

	public String getSubNumber() {
		return subnumber;
	}

	public void setSubNumber(String subnumber) {
		this.subnumber = subnumber;
	}

	public String getLectureNumber() {
		return lecturenumber;
	}

	public void setLectureNumber(String lecturenumber) {
		this.lecturenumber = lecturenumber;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
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
	
	public ArrayList getlisteningStudent() {
		return listeningStudent;
	}

	public void setlisteningStudent(String Student) {
		listeningStudent.add(Student);
		
	}
	public int listCheck(String Student) {
		if(listeningStudent.contains(Student) == true) {
			return 1;
		}
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return subname;
	}
}