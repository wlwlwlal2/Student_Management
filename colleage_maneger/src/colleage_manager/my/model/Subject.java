package colleage_manager.my.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Subject {

	@Id
	@Column
	private String subnumber;
	@Column
	private String subname;

	@Column
	private String professor;
	@Column
	private String lecturetype;
	@Column
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