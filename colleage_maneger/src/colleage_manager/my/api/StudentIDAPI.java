package colleage_manager.my.api;

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
import colleage_manager.my.model.StudentID;

public class StudentIDAPI extends BaseRepoAPI {
	
	private static StudentIDAPI instance;

	public static StudentIDAPI getInstance() {
		if (instance == null) {
			instance = new StudentIDAPI();
		}
		return instance;
	}

	
	public boolean Register(Lecture lecture, Student student) {
		try {
			LectureHistory id = new LectureHistory();
			id.setLecture(lecture);
			id.setStudent(student);
			// user.setRole("ÇÐ»ý");

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(id);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public LectureHistory getLectureHistory(String lectureNumber, String studentID) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<LectureHistory> cQuery = criteriaBuilder.createQuery(LectureHistory.class);
		Root<LectureHistory> from = cQuery.from(LectureHistory.class);
		Predicate where1 = criteriaBuilder.equal(from.get("lecture_code"), lectureNumber);
		Predicate where2 = criteriaBuilder.equal(from.get("Student_ID"), studentID);

		Predicate whereFinal = criteriaBuilder.and(where1, where2);
		cQuery.where(whereFinal);
		
		Query query = em.createQuery(cQuery);
		List<LectureHistory> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}
}
