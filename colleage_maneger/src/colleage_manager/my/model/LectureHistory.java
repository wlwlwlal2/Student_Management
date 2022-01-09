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
	private LectureHistoryID id = new LectureHistoryID();

	@ManyToOne
	@MapsId("lecture")
	@JoinColumn(name = "lecture_code", referencedColumnName = "lecture_number")
	private Lecture lecture; // 강의 코드

	@ManyToOne
	@MapsId("student")
	@JoinColumn(name = "student_id", referencedColumnName = "number")
	private Student student; // 학생 번호

	@Column(name = "grade")
	// @JoinColumn(name = "grade", insertable = false, updatable = false)
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

	public void setId(LectureHistoryID id) {
		this.id = id;
	}

	public LectureHistoryID getId() {
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

	public String getGrade() {
		if(grade != null) 
			return grade;
		else
			return "미등록";
			
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRealGrade() {
		if(realGrade != null) 
			return realGrade;
		else
			return "미등록";

	}

	public void setRealGrade(String realGrade) {
		this.realGrade = realGrade;
	}

	public String getMaxAttendance() {
		if(maxAttendance != null) 
			return maxAttendance;
		else
			return "미등록";
	}

	public void setMaxAttendance(String maxAttendance) {
		this.maxAttendance = maxAttendance;
		
	}

	public String getAttendance() {
		if(attendance != null) 
			return attendance;
		else
			return "미등록";

	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getLateness() {
		if(lateness != null) 
			return lateness;
		else
			return "미등록";
	}

	public void setLateness(String lateness) {
		this.lateness = lateness;
	}

	public String getAbsence() {
		if(absence != null) 
			return absence;
		else
			return "미등록";
	}

	public void setAbsence(String absence) {
		this.absence = absence;
	}
}
