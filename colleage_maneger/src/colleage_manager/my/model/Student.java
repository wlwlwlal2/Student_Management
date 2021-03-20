package colleage_manager.my.model;

public class Student {

	private String subject;
	
	private String grade;
	
	private int realGrade;
	
	private int maxAttendance; // 과목 출석시간
	
	private int attendance; // 출석시간
	
	private int lateness; // 지각
	
	private int absence; // 결석

	public String getSubject() {
		return subject;
}
	public void setSubject(String subject) {
		this.subject = subject;
}
	
	public String getGrade() {
		return grade;
}
	public void setGrade(String Grade) {
		this.grade = grade;
}
	
	public int getRealGrade() {
		return realGrade;
	}
	public void setRealGrade() {
		this.realGrade = realGrade;
	}
	public int getMaxAttendance() {
		return maxAttendance;
	}
	public void setMaxAttendance() {
		this.maxAttendance = maxAttendance;
	}
	public int getAttendancee() {
		return attendance;
	}
	public void setAttendance() {
		this.attendance = attendance;
	}
	public int getLateness() {
		return lateness;
	}
	public void setLateness() {
		this.lateness = lateness;
	}
	public int getAbsence() {
		return absence;
	}
	public void setAbsence() {
		this.absence = absence;
	}
}