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

import colleage_manager.my.model.Subject;

public class SubjectAPI extends BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "h2";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static EntityManager em = factory.createEntityManager();

	private static SubjectAPI instance;
	EntityTransaction transaction;
	
	public static SubjectAPI getInstance() {
		if (instance == null) {
			instance = new SubjectAPI();
		}
		return instance;
	}

	public boolean Register(String subnumber, String lecturename, String professor, String lectureType,
			String needday) {
		try {
			Subject subject = new Subject();
			subject.setSubNumber(subnumber);
			subject.setSubName(lecturename);
			subject.setProfessor(professor);
			subject.setLectureType(lectureType);
			subject.setNeedDay(needday);
			JOptionPane op1 = new JOptionPane();
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(subject);
			transaction.commit();
		} catch (EntityExistsException e1) {
			JOptionPane op1 = new JOptionPane();
			op1.showMessageDialog(null, "과목추가 실패");
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean InfoUpdate(String subnumber, String subname, String professor, String lectureType, String needday) {

		try {

			Subject subject = em.find(Subject.class, subnumber);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			// subject.setSubNumber(subnumber);
			subject.setSubName(subname);
			subject.setProfessor(professor);
			subject.setLectureType(lectureType);
			subject.setNeedDay(needday);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void Delete(String subnumber) {

		Subject subject = em.find(Subject.class, subnumber);
		subject.setSubNumber(subnumber);

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.remove(subject);

		transaction.commit();

	}

	public Subject Read(String subnumber) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Subject> cQuery = criteriaBuilder.createQuery(Subject.class);
		Root<Subject> from = cQuery.from(Subject.class);
		Predicate where1 = criteriaBuilder.equal(from.get("subnumber"), subnumber);

		Predicate whereFinal = criteriaBuilder.and(where1);
		cQuery.where(whereFinal);

		Query query = em.createQuery(cQuery);
		List<Subject> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}

	public List<Subject> readAll() {
		CriteriaQuery<Subject> query;
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			query = builder.createQuery(Subject.class);
			query.from(Subject.class);
		}

		List<Subject> result = em.createQuery(query).getResultList();

		if (result.size() > 0)
			return result;
		else
			return new ArrayList<Subject>();

//		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//
//		CriteriaQuery<Subject> cQuery = criteriaBuilder.createQuery(Subject.class);
//		cQuery.from(Subject.class);
//
//		Query query = em.createQuery(cQuery);
//		List<Subject> resultList = query.getResultList();
//
//		return resultList;

	}

	public Subject getSubject(String subnumber) {
		return em.find(Subject.class, subnumber);
	}
}
