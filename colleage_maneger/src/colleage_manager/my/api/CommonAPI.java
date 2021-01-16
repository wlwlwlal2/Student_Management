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


public class CommonAPI {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); 
    private EntityManager em = factory.createEntityManager();
    
    	
    public int Register(String number, String password) {
    	
    	try {
    		
    		Common common = new Common();
    		common.setNumber(number);
    		common.setPassword(password);
    		
    		EntityTransaction transaction = em.getTransaction();
    	    transaction.begin(); 
    	    em.persist(common);
    	    transaction.commit(); 
    		
    	    em.close();
    	} catch (Exception e) {
    	
    	} 
    	return 0;
    }
    
    public int login(String number, String password) {
    	
    	
    	try {
    		
    	    Query query = em.createQuery("select t from Common t" + "where t = " + number);
    	    String resultList = (String) query.getSingleResult();
            em.close();
    	    
            if(resultList.equals(number) && resultList.equals(password)) 
            	return 0;
            else
            	return -1;
            
            	
    	} catch (Exception e) {
    	
    	}
    	return 0;
    }


    public int InfoUpdate(String number, int birth, int phoneNumber, String email, String address, String family) {
	
	try {
		
		Common common = new Common();
		
		common.setBirth(birth);
    	common.setPhoneNumber(phoneNumber);
    	common.setEmail(email);
    	common.setAddress(address);
    	common.setFamily(family);
		
		EntityTransaction transaction = em.getTransaction();
	    transaction.begin(); 
	    em.persist(common);
	    transaction.commit(); 
		
	    Query query = em.createQuery("update t from Common t" + "where t = " + number);
	    List result = (List) query.getResultList();
      
	    em.close();
	} catch (Exception e) {
	
	}
	return 0;
}
    
    public Common Read(String number) {
    	
CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Common> cQuery = criteriaBuilder.createQuery(Common.class);
		Root<Common> from = cQuery.from(Common.class);
		Predicate where = criteriaBuilder.equal(from.get("number"), number);
		cQuery.where(where);
		
		Query query = em.createQuery(cQuery);
		List<Common> resultList = query.getResultList();

		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}
    
    public List<Common> readAll() {
		return null;
}
}