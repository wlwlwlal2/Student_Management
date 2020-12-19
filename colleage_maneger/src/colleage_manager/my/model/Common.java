package colleage_manager.my.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Common {
	
	@Id
	@Column
	private String number;
	// 학생의 경우엔 학번, 교수의 경우엔 교수번호
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private int classNumber;
	// 학과별로 존재하는 학과코드
	@Column
	private int birth;
	// 생년월일, ex) 20201205
	@Column
	private int phoneNumber;
	// 휴대폰 번호
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String family;
	@Column
	private String lectureName;
	@Column
	private String lectureLocate;
	
	
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	
	public String getLectureLotate() {
		return lectureLocate;
	}
	public void setLectureLotate(String lectureLotate) {
		this.lectureLocate = lectureLocate;
	}
	
//	@Override
//	public void toString
}
