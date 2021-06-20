package colleage_manager.my.model;

import java.io.Serializable;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "lecture_history")
public class LectureHistory implements Serializable {
	@EmbeddedId
	private StudentID id = new StudentID();
	
	@ManyToOne
	@MapsId("lecture")
	@JoinColumn(name = "lecture_code", referencedColumnName = "lecture_number")
	private Lecture lecture; // 강의 코드

	@ManyToOne
	@MapsId("student")
	@JoinColumn(name = "student_id", referencedColumnName = "number")
	private Student student; // 학생 번호
	
	@Column(name = "grade")
	//@JoinColumn(name = "grade", insertable = false, updatable = false)
	private String grade; // 강의 성적
	
	@Column(name = "realGrade")
	private String realGrade;
	
	@Column(name = "maxAttendance")
	private String maxAttendance; // 과목 출석시간
	
	@Column(name = "attendance")
	private String attendance; // 출석시간
	
	@Column(name = "lateness")
	private String lateness; // 지각
	
	@Column(name = "absence")
	private String absence; // 결석
	
	public void setId(StudentID id) {
		this.id = id;
	}
	
	public StudentID getId() {
		return id;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public Student getStudent() {
		return student;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public void setStudent(Student student) {
		this.student = student;
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

//package colleage_manager.my.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.MapsId;
//import javax.persistence.Table;
//
//
//@SuppressWarnings("serial")
//@Entity
////@IdClass(LectureHistoryId.class)
//@Table(name = "lecture_history")
//public class LectureHistory implements Serializable {
//	@EmbeddedId
//	private StudentID id = new StudentID();
//
//	@ManyToOne
//	@MapsId("lecture")
//	@JoinColumn(name = "lecture_code", referencedColumnName = "code")
//	private Lecture lecture; // 강의 코드
//
//	@ManyToOne
//	@MapsId("student")
//	@JoinColumn(name = "student_id", referencedColumnName = "id")
//	private Student student; // 학생 번호
//
//	@Column(name = "grade")
//	//@JoinColumn(name = "grade", insertable = false, updatable = false)
//	private String grade; // 강의 성적
//
//	@Column(name = "problem")
//	//@JoinColumn(name = "problem", insertable = false, updatable = false)
//	private String problem; // 강의 이의 제기
//
//	@Column(name = "opinion")
//	//@JoinColumn(name = "opinion", insertable = false, updatable = false)
//	private String opinion; // 강의 평가
//
//	public Lecture getLecture() {
//		return lecture;
//	}
//
//	public Student getStudent() {
//		return student;
//	}
//
//	public void setLecture(Lecture lecture) {
//		this.lecture = lecture;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//
//	public String getGrade() {
//		return grade;
//	}
//
//	public void setGrade(String grade) {
//		this.grade = grade;
//	}
//
//	public String getProblem() {
//		return problem;
//	}
//
//	public void setProblem(String problem) {
//		this.problem = problem;
//	}
//
//	public String getOpinion() {
//		return opinion;
//	}
//
//	public void setOpinion(String opinion) {
//		this.opinion = opinion;
//	}
//
//
//}