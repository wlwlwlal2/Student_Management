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
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.model.Student;
import colleage_manager.my.model.LectureHistoryID;

public class CommonAPI extends BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "h2";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static EntityManager em = factory.createEntityManager();

	private static CommonAPI instance;
	EntityTransaction transaction;
	public static CommonAPI getInstance() {
		if (instance == null) {
			instance = new CommonAPI();
		}
		return instance;
	}

	
	public boolean Register(String role, String number, String password) {
		
		try {
			Common common = new Common();
			transaction = em.getTransaction();
			common.setNumber(number);
			common.setPassword(password);
			common.setRole(role);

			transaction.begin();
			
			em.persist(common);
			
			if (common.getRole().equals("학생")) {
				Student student = new Student();
				student.setCommon(common);
				student.setNumber(number);
				
				em.persist(student);
				
			}

			transaction.commit();
			
		} catch (EntityExistsException e1) {
			transaction.rollback();
			throw e1;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} 

		return true;
	}

	public boolean Delete(String number) {
		try {
			LectureHistoryAPI lecturehistoryapi = LectureHistoryAPI.getInstance();
			LectureAPI lectureapi = LectureAPI.getInstance();
			
			Common common = em.find(Common.class, number);
			Student student = em.find(Student.class,number);
			
			if(common.getRole().equals("학생")) {
				System.out.println(number==null + ", " +  student.equals(null) );
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
			
				lecturehistoryapi.DeleteAll(student.getNumber());
				em.remove(student);
				em.remove(common);
				
				transaction.commit();
				
			}else if(common.getRole().equals("교수")) {
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
			
				//lecturehistoryapi.DeleteAll(student.getNumber());
				//em.remove(student);
				em.remove(common);
				
				transaction.commit();
				
			}else if(common.getRole().equals("관리자")) {
				JOptionPane op = new JOptionPane();
			
				op.showMessageDialog(null,"관리자 계정은 삭제할 수 없음");
			}
			
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
			
			if(classNumber.contentEquals("001")) {
			common.setClassName("001학과");
			System.out.println("001학과");
			}
			else
			common.setClassName("미설정");
			
			transaction.commit();
			System.out.println(common.getClassNumber());
			System.out.println(common.getClassName());
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