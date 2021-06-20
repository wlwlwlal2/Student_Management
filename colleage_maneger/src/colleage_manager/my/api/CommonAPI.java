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
import colleage_manager.my.model.Student;
import colleage_manager.my.model.StudentID;

public class CommonAPI extends BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "h2";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static EntityManager em = factory.createEntityManager();

	private static CommonAPI instance;

	public static CommonAPI getInstance() {
		if (instance == null) {
			instance = new CommonAPI();
		}
		return instance;
	}

	public boolean Register(String role, String number, String password) {
		try {
			Common common = new Common();

			common.setNumber(number);
			common.setPassword(password);
			common.setRole(role);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			if (common.getRole() == "학생") {
				Student student = new Student();

				student.setNumber(number);

				//em.persist(student); // 여기가 문제임
			}
			em.persist(common);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Common login(String number, String password) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Common> cQuery = criteriaBuilder.createQuery(Common.class);
		Root<Common> from = cQuery.from(Common.class);
		Predicate where1 = criteriaBuilder.equal(from.get("number"), number);
		Predicate where2 = criteriaBuilder.equal(from.get("password"), password);

		Predicate whereFinal = criteriaBuilder.and(where1, where2);
		cQuery.where(whereFinal);

		Query query = em.createQuery(cQuery);
		List<Common> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;

	}

	public boolean InfoUpdate(String number, String classNumber, String name, String birth, String phoneNumber,
			String email, String address) {

		try {

			Common common = em.find(Common.class, number);

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			common.setClassNumber(classNumber);
			common.setName(name);
			common.setBirth(birth);
			common.setPhoneNumber(phoneNumber);
			common.setEmail(email);
			common.setAddress(address);
			// common.setFamily(family);
			String role = "student";
			common.setRole(role);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Common Read(String number) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Common> cQuery = criteriaBuilder.createQuery(Common.class);
		Root<Common> from = cQuery.from(Common.class);
		Predicate where1 = criteriaBuilder.equal(from.get("number"), number);

		Predicate whereFinal = criteriaBuilder.and(where1);
		cQuery.where(whereFinal);

		Query query = em.createQuery(cQuery);
		List<Common> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}

	public String Test(String number) {
		Common a = em.find(Common.class, number);
		return a.getNumber();
	}

	public String[] join() {
		List<Common> list = readAll();
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).getNumber();
		}
		return result;
	}

	public List<Common> readAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Common> cQuery = criteriaBuilder.createQuery(Common.class);
		Root<Common> from = cQuery.from(Common.class);

		Query query = em.createQuery(cQuery);
		List<Common> resultList = query.getResultList();

//			for(int i = 0; i < resultList.size(); i++ ) {
//				
//			}

		return resultList;

	}

	public Common getCommon(String number) {
		return em.find(Common.class, number);
	}
}