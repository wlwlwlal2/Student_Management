package colleage_manager.my.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	
	@Id
	@OneToOne
	@JoinColumn(name = "number", referencedColumnName = "number")
	private Common common;
	
	@OneToMany(mappedBy = "student")
	private Set<LectureHistory> history = new HashSet<LectureHistory>();
	
	@Column(name = "student_number")
	private String number;
	@Column(name = "student_code")
	private String subject;
	@Column(name = "student_grade")
	private String grade;
	@Column(name = "student_realGrade")
	private String realGrade;
	@Column(name = "student_maxAttendance")
	private String maxAttendance; // 과목 출석시간
	@Column(name = "student_attendancee")
	private String attendance; // 출석시간
	@Column(name = "student_lateness")
	private String lateness; // 지각
	@Column(name = "student_absence")
	private String absence; // 결석
	@Column(name = "student_lectureList")
	private ArrayList lectureList = new ArrayList();
	
	public Common getCommon() {
		return common;
	}
	public void setCommon(Common common) {
		this.common = common;
	}
	
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