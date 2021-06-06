package colleage_manager.my.api;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import colleage_manager.my.model.Common;
import colleage_manager.my.model.Student;


//	public int login(int studentNumber, String passward) {
//		
//		// 로그인 : 학번 , 비밀번호 
//		// 데이터베이스 참조
//		
//		return 0;
//	}
//	
//	public int studentRegistration(int studentNumber, String passward, int classNumber) {
//		
//		// 학생 회원가입 : 학번, 비밀번호 , 학과코드(ex: 1, 2, 3)
//		// 데이터베이스에 입력된 정보 추가
//		
//		return 0;
//	}
//	
//	public int teacherRegistration(int teacherNumber, String passward, int classNumber) {
//		
//		// 교수 회원가입 : 학번, 비밀번호 , 학과코드(ex: 1, 2, 3)
//		// 데이터베이스에 입력된 정보 추가
//		
//		return 0;
//	}
//
//	public int studentInformationUpdate(int studentNumber, String name, int classNumber, int birth, int phoneNumber,
//				String email, String address, String family, int militaryCheck, int restSchoolCheak, int restSchoolLength) {
//		// 학생 정보 추가 : 학번, 이름, 학과(학과모드에 따라 바뀜), 생년월일, 전화번호, 이메일, 집주소, 가족관계, 군대유무, 휴학유무, 휴학기간 
//		return 0;
//	}
//	
//	public int teacherInformationUpdate(int teacherNumber, String name, int classNumber, int birth, int phoneNumber,
//			String email, String address, String family) {
//		// 교수 정보 추가 : 학번, 이름, 학과(학과모드에 따라 바뀜), 생년월일, 전화번호, 이메일, 집주소, 가족관계, 
//		return 0;
//	}
// 
//	
//	public int studyInfomationUpdate(String lectureName, int lectureCode, String teacherName, String lectureTime, String lectureLocation) {
//		
//		return 0;
//	}
//	
//	public int gradeUpdate(String studentName, int studentNumber, int semester, String lectureName, String grade, int realGrade) {
//		
//		// 교수가 학생의 성적을 입력,수정
//		
//		return 0;
//	}
//	public int studyScheduleInformaton(int studentNumber, String name, int classNumber) {
//	
//		// 시간표에 학번, 이름, 학과 넣기
//		return 0;
//	}
//	
//	public int studyScheduleUpdate(int lectureName,String teacherName, String lectureLocation, String lectureTime, String lectureWeek) {
//		
//		// 시간표 갱신
//		return 0;
//	
//	}
//	
//	public int studentGradeView(int semester, String lectureName, String lectureType, String teacherName, String lectureLocation, String grade, int realGrade) {
//		
//		return 0;
//	}
//	
//	public int attendanceView(int semester, int lectureName, String lectureType, String teacherName, int maxAttendance, int Attendance, int lateness, int absence ) {
//		
//		return 0;
//	}

public class StudentAPI extends BaseRepoAPI {
	private static StudentAPI instance;

	public static StudentAPI getInstance() {
		if (instance == null) {
			instance = new StudentAPI();
		}
		return instance;
	}
	
	public boolean Register(String role, String number, String password) {
		try {
			Common user = new Common();
			user.setNumber(number);
			user.setPassword(password);
			user.setRole("학생");

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public Student Read(String number) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Student> cQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> from = cQuery.from(Student.class);
		Predicate where1 = criteriaBuilder.equal(from.get("number"), number);

		Predicate whereFinal = criteriaBuilder.and(where1);
		cQuery.where(whereFinal);

		Query query = em.createQuery(cQuery);
		List<Student> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}
//	public Student getStudent(String number, String lecturenumber) {
//		Student student =  em.find(Student.class, number);
//	}

}