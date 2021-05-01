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
import colleage_manager.my.model.Student;
import colleage_manager.my.model.Subject;

public class LectureAPI extends BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "h2";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static EntityManager em = factory.createEntityManager();

	private static LectureAPI instance;

	public static LectureAPI getInstance() {
		if (instance == null) {
			instance = new LectureAPI();
		}
		return instance;
	}
	
	public boolean Register(String subnumber,String subname,String lecturename,String name, String grade, String day ) {
		try {
			
			
			Lecture lecture = new Lecture();
			
			lecture.setSubNumber(subnumber);
			lecture.setName(name);
			lecture.setLectureName(lecturename);
			lecture.setGrade(grade);
			lecture.setDay(day);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.persist(lecture);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void Delete(String subnumber) {

		Lecture subject = em.find(Lecture.class, subnumber);
		subject.setSubNumber(subnumber);
		
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.remove(subject);
			
			transaction.commit();
		
	}	
	
	public Lecture Read(String subnumber) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Lecture> cQuery = criteriaBuilder.createQuery(Lecture.class);
		Root<Lecture> from = cQuery.from(Lecture.class);
		Predicate where1 = criteriaBuilder.equal(from.get("subnumber"), subnumber);
		

		Predicate whereFinal = criteriaBuilder.and(where1);
		cQuery.where(whereFinal);
		
		Query query = em.createQuery(cQuery);
		List<Lecture> resultList = query.getResultList();
			
		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}	
	
	
	
	public List<Lecture> readAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Lecture> cQuery = criteriaBuilder.createQuery(Lecture.class);
		Root<Lecture> from = cQuery.from(Lecture.class);
		
		Query query = em.createQuery(cQuery);
		List<Lecture> resultList = query.getResultList();
			
//			for(int i = 0; i < resultList.size(); i++ ) {
//				
//			}
		
			return resultList;
		
	}
	public Lecture getLecture(String number) {
		return em.find(Lecture.class, number);
	}
}
