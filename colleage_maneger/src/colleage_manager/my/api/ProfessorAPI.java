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

public class ProfessorAPI extends CommonAPI {
	public boolean Register(String role, String number, String password) {
		try {
			Common user = new Common();
			user.setNumber(number);
			user.setPassword(password);
			user.setRole("±³¼ö");

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
	public Common Object(String number) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Common> cQuery = criteriaBuilder.createQuery(Common.class);
		CriteriaQuery<Student> sQuery = criteriaBuilder.createQuery(Student.class);
		Root<Common> from = cQuery.from(Common.class);
		Predicate where1 = criteriaBuilder.equal(from.get("number"), number);
		
		cQuery.where(where1);
		
		Query query = em.createQuery(sQuery);
		
		List<Common> resultList = query.getResultList();
		
		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
		
	}
	
}

