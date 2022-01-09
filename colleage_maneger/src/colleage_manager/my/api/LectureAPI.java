package colleage_manager.my.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;

import colleage_manager.my.model.Subject;

public class LectureAPI extends BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "h2";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static EntityManager em = factory.createEntityManager();

	private static LectureAPI instance;
	EntityTransaction transaction;
	
	public static LectureAPI getInstance() {
		if (instance == null) {
			instance = new LectureAPI();
		}
		return instance;
	}

	public boolean Register(String lecturenumber, Subject subject, String subname, String lecturename, String name,
			String grade, String day, String starttime, String endtime, boolean[] chk) {
		
		SubjectAPI sapi = SubjectAPI.getInstance();
		
		try {
//
			Lecture lecture = new Lecture();
			lecture.setLectureNumber(lecturenumber);
//			lecture.setSubNumber(subnumber);
			lecture.setSubject(subject);
			lecture.setSubname(subname);
			lecture.setName(name);
			lecture.setLectureName(lecturename);
			lecture.setGrade(grade);
			lecture.setDay(day);
			lecture.setStartTime(starttime);
			lecture.setEndTime(endtime);
			lecture.setStudyingDay(chk);
			
			transaction = em.getTransaction();
			transaction.begin();

			em.persist(lecture);

			transaction.commit();
		} catch (EntityExistsException e1) {
			JOptionPane op1 = new JOptionPane();
			// op1.showMessageDialog(null, "과목추가 실패");
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean InfoUpdate(String lecturenumber, String subnumber, String subname, String lecturename, String name,
			String grade, String day, String starttime, String endtime, boolean[] chk) {

		try {

			Lecture lecture = em.find(Lecture.class, lecturenumber);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			lecture.setLectureNumber(lecturenumber);
			lecture.setSubname(subname);
			lecture.setName(name);
			lecture.setLectureName(lecturename);
			lecture.setGrade(grade);
			lecture.setDay(day);
			lecture.setStartTime(starttime);
			lecture.setEndTime(endtime);
			lecture.setStudyingDay(chk);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void Delete(String subnumber) {

		Lecture subject = em.find(Lecture.class, subnumber);
		//subject.setSubNumber(subnumber);

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.remove(subject);

		transaction.commit();

	}

	public Lecture Read(String lecturenumber) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Lecture> cQuery = criteriaBuilder.createQuery(Lecture.class);
		Root<Lecture> from = cQuery.from(Lecture.class);
		Predicate where1 = criteriaBuilder.equal(from.get("lectureNumber"), lecturenumber);

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
	public boolean AddListener(String lecturenumber, String Student) {
		try {
			Lecture lecture = em.find(Lecture.class, lecturenumber);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			lecture.setlisteningStudent(Student);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean DeleteListener(String lecturenumber, String Student) {
		try {
			Lecture lecture = em.find(Lecture.class, lecturenumber);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			lecture.dellisteningStudent(Student);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List GetListener(String lecturenumber) {
		Lecture lecture = em.find(Lecture.class, lecturenumber);
		return lecture.getlisteningStudent();
	}
	
	public int listCheck(String lecturenumber,String Student) {
		Lecture lecture = em.find(Lecture.class, lecturenumber);
		ArrayList list = lecture.getlisteningStudent();
		if(list.contains(Student) == true) {
			System.out.println(list.size());
			return 1;
		}
		else
			return 0;
	}
	public Lecture getLecture(String number) {
		return em.find(Lecture.class, number);
	}
}
