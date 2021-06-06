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
	private static ProfessorAPI instance;

	public static ProfessorAPI getInstance() {
		if (instance == null) {
			instance = new ProfessorAPI();
		}
		return instance;
	}
	
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
	
	
}

