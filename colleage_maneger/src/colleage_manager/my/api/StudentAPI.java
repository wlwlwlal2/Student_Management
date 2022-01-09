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
			user.setRole("ÇÐ»ý");

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
	
//	public boolean Delete(String number) {
//		try {
//			Student student = em.find(Student.class, number);
//			
//			EntityTransaction transaction = em.getTransaction();
//			transaction.begin();
//
//			em.remove(student);
//			
//			transaction.commit();
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//	
	
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