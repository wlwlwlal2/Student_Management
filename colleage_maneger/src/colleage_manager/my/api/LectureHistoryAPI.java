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
//			LectureHistoryID studentid = new LectureHistoryID(lecture, student);
//			LectureHistory lecturehistory = new LectureHistory();
//			lecturehistory.setId(studentid);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.createNativeQuery("INSERT INTO lecture_history (lecture, student) VALUES (?,?)").setParameter(1,lectureapi.Read(lecture) )
			.setParameter(2, studentapi.Read(student)).executeUpdate();
			
			
			em.persist(student);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public LectureHistory Read(String lecture, String student) {
		LectureHistoryID studentid = new LectureHistoryID(lecture, student);
		return em.find(LectureHistory.class, studentid);
	}
}
