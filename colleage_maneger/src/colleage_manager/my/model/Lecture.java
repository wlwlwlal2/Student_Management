package colleage_manager.my.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lecture")
public class Lecture {

	@Id
	@Column(name = "lecture_number")
	private String lectureNumber; // 강의코드
	
	@OneToMany(mappedBy="lecture")
	private Set<LectureHistory> history = new HashSet<LectureHistory>();
	
	@ManyToOne
	@JoinColumn(name = "subject_code", insertable = false, updatable = false)
	private Subject subject; // 과목코드
	
	
	@Column(name = "pro_number")
	private String proNumber; // 교수 번호

	@Column(name = "sub_name")
	private String subname; // 과목명
	
	@Column(name = "lecture_name")
	private String lecturename; // 강의명
	
	@Column(name = "name")
	private String name; // 교수이름
	
	@Column(name = "grade")
	private String grade; // 점수
	
	@Column(name = "day")
	private String day; // 요구출석일
	
	@Column(name = "start_time")
	private String starttime; // 수업 시작시간
	
	@Column(name = "end_time")
	private String endtime; // 수업 종료시간
	
	@Column(name = "studying_Day")
	private boolean[] studyingday;
	
	@Column(name = "listening_student")
	private ArrayList listeningStudent = new ArrayList();

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subnumber) {
		this.subject = subnumber;
	}

//	public String getSubNumber() {
//		return subnumber;
//	}
//
//	public void setSubNumber(String subnumber) {
//		this.subnumber = subnumber;
//	}
	
	public String getLectureNumber() {
		return lectureNumber;
	}

	public void setLectureNumber(String lecturenumber) {
		this.lectureNumber = lecturenumber;
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
	
	public String getStartTime() {
		return starttime;
	}

	public void setStartTime(String starttime) {
		this.starttime = starttime;
	}
	
	public String getEndTime() {
		return endtime;
	}

	public void setEndTime(String endtime) {
		this.endtime = endtime;
	}
	
	public boolean[] getStudyingDay() {
		return studyingday;
	}
	
	public void setStudyingDay(boolean[] studyingday) {
		this.studyingday = studyingday;
	}
	
	public ArrayList getlisteningStudent() {
		return listeningStudent;
	}

	public void setlisteningStudent(String Student) {
		listeningStudent.add(Student);
		
	}
	
	public void dellisteningStudent(String Student) {
		listeningStudent.remove(Student);
		
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

//package colleage_manager.my.model;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "lecture")
//public class Lecture {
//	@Id
//	@Column(name = "code")
//	private String code; // 강의 코드
//
//	@OneToMany(mappedBy="lecture")
//	private Set<LectureHistory> history = new HashSet<LectureHistory>();
//	
//
//	@ManyToOne
//	@JoinColumn(name = "subject_code", insertable = false, updatable = false)
//	private Subject subject; // 과목 코드
//
//	@Column(name = "description")
//	private String description; // 강의 내용
//
//
//	@Column(name = "year")
//	private int year; // 강의 년도
//	
//	@Column(name = "semester")
//	private int semester; // 강의 학기
//	
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//
//	public Subject getSubject() {
//		return subject;
//	}
//
//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//
//	public int getYear() {
//		return year;
//	}
//
//	public void setYear(int year) {
//		this.year = year;
//	}
//
//	public int getSemester() {
//		return semester;
//	}
//
//	public void setSemester(int semester) {
//		this.semester = semester;
//	}
//}
