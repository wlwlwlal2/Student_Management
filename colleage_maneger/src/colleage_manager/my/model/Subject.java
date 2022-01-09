package colleage_manager.my.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@Column(name = "subject_code")
	private String subnumber;
	@Column(name = "subject_name")
	private String subname;
	@Column(name = "subject_professor")
	private String professor;
	@Column(name = "subject_type")
	private String lecturetype;
	@Column(name = "subject_needday")
	private String needday;

	public String getSubNumber() {
		return subnumber;
	}

	public void setSubNumber(String subnumber) {
		this.subnumber = subnumber;
	}

	public String getSubName() {
		return subname;
	}

	public void setSubName(String subname) {
		this.subname = subname;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getLectureType() {
		return lecturetype;
	}

	public void setLectureType(String lecturetype) {
		this.lecturetype = lecturetype;
	}

	public String getNeedDay() {
		return needday;
	}

	public void setNeedDay(String needday) {
		this.needday = needday;
	}

	@Override
	public String toString() {
		return subname;
	}
}