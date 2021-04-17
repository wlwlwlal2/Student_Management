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

public class LectureAPI {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	protected static EntityManager em = factory.createEntityManager();

	
	public boolean Register(String subnumber, String number, String grade, int gradeint, int realgrade, int day, int late, int absent ) {
		try {
			
		//	Subject subject = new Subject();
			Lecture lecture = new Lecture();
		//	Student student = new Student();
			
			lecture.setNumber(number);
			lecture.setSubNumber(subnumber);
		
		lecture.setGrade(grade);
		lecture.setGradeint(gradeint);
		lecture.setRealGrade(realgrade);
		lecture.setDay(day);
		lecture.setLate(late);
		lecture.setAbsent(absent);

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
		
	public boolean GradeAdd(String subnumber, String number, String grade, int gradeint, int realgrade) {
		try {
			
			Subject subject = new Subject();
			Lecture lecture = new Lecture();
			Student student = new Student();
			
		student.setNumber(number);
		subject.setSubNumber(subnumber);
		
		lecture.setGrade(grade);
		lecture.setGradeint(gradeint);
		lecture.setRealGrade(realgrade);

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
	
	public List<Subject> readAll() {
		return null;
	}
}