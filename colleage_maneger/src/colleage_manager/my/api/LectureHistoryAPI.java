package colleage_manager.my.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.model.Student;
import colleage_manager.my.model.LectureHistoryID;

public class LectureHistoryAPI extends BaseRepoAPI {

	private static LectureHistoryAPI instance;

	public static LectureHistoryAPI getInstance() {
		if (instance == null) {
			instance = new LectureHistoryAPI();
		}
		return instance;
	}

	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private StudentAPI studentapi = StudentAPI.getInstance();
	private CommonAPI commonapi = CommonAPI.getInstance();

	public boolean Register(String lecture, String student) {
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.createNativeQuery("INSERT INTO lecture_history (lecture_code, student_id) VALUES (?,?)").setParameter(1,lectureapi.Read(lecture) )
			.setParameter(2, studentapi.Read(student)).executeUpdate();
			
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean Delete(String lecture, String student) {
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.createNativeQuery("DELETE FROM lecture_history WHERE lecture_code = ? AND student_id = ?").setParameter(1,lectureapi.Read(lecture) )
			.setParameter(2, studentapi.Read(student)).executeUpdate(); // 쿼리문 작성해야함
			
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean DeleteAll(String student) {
		try {
			EntityTransaction transaction = em.getTransaction();
			//transaction.begin(); CommonAPI에서 이미 실행함
			
			em.createNativeQuery("DELETE FROM lecture_history WHERE student_id = ?")
			.setParameter(1, studentapi.Read(student)).executeUpdate(); // 쿼리문 작성해야함
			
			//transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	@SuppressWarnings("unchecked")
	public LectureHistory Read(String lecture, String student) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<LectureHistory> cQuery = criteriaBuilder.createQuery(LectureHistory.class);
		Root<LectureHistory> from = cQuery.from(LectureHistory.class);
		
		Predicate where1 = criteriaBuilder.equal(from.get("lecture"), lectureapi.Read(lecture));
		Predicate where2 = criteriaBuilder.equal(from.get("student"), studentapi.Read(student));
		
		Predicate whereFinal = criteriaBuilder.and(where1,where2);
		cQuery.where(whereFinal);
		
		Query query = em.createQuery(cQuery);
		List<LectureHistory> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<LectureHistory> StudentListeningLectureRead(String student) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<LectureHistory> cQuery = criteriaBuilder.createQuery(LectureHistory.class);
		Root<LectureHistory> from = cQuery.from(LectureHistory.class);
		Predicate where1 = criteriaBuilder.equal(from.get("student"), studentapi.Read(student));

		Predicate whereFinal = criteriaBuilder.and(where1);
		cQuery.where(whereFinal).orderBy(criteriaBuilder.asc(from.get("lecture").get("starttime")));
		// orderby 사용법
		Query query = em.createQuery(cQuery);
		List<LectureHistory> resultList = query.getResultList();
		System.out.println(resultList.size());
		return resultList;
		
	}
	
	
	
	public boolean Update(String lecture, String student,String grade,String realgrade, String attendance,String lateness,String absence) {
		try {

			LectureHistory LH = Read(lecture, student);
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
		//	em.createNativeQuery("UPDATE lecture_history SET grade = grade WHERE " );
			
			LH.setGrade(grade);
			LH.setRealGrade(realgrade);
			LH.setMaxAttendance(attendance);
			LH.setAttendance(lateness);
			LH.setAbsence(absence);
			
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}
