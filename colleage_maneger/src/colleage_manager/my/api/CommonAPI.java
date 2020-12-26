package colleage_manager.my.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import antlr.collections.List;
import colleage_manager.my.model.Common;


public class CommonAPI {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); 
    private EntityManager em = factory.createEntityManager();
    
    
    public int Resister(String number, String password) {
    	
    	try {
    		
    		Common common = new Common();
    		common.setNumber("202020202");
    		common.setPassword("202020202");
    		
    		EntityTransaction transaction = em.getTransaction();
    	    transaction.begin(); 
    	    em.persist(common);
    	    transaction.commit(); 
    		
    	    Query query = em.createQuery("select t from Common t");
    	    List result = (List) query.getResultList();
            
            System.out.println(result.toString());
            
    	    em.close();
    	} catch (Exception e) {
    	
    	}
    	return 0;
    }
    
    public int login(String number, String password) {
    	
    	
    	try {
    		
    		Common common = new Common();
    		common.setNumber("202020202");
    		common.setPassword("202020202");
    		
    		EntityTransaction transaction = em.getTransaction();
    	    transaction.begin(); 
    	    em.persist(common);
    	    transaction.commit(); 
    		
    	    Query query = em.createQuery("select t from Common t where");
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


    public int InfoUpdate(int birth, int phoneNumber, String email, String adress, String family) {
	
	try {
		
		Common common = new Common();
		common.setBirth(2);
    	common.setPhoneNumber(2);
    	common.setEmail("2");
    	common.setAddress("2");
    	common.setFamily("2");
		
		EntityTransaction transaction = em.getTransaction();
	    transaction.begin(); 
	    em.persist(common);
	    transaction.commit(); 
		
	    Query query = em.createQuery("select t from Common t");
	    List result = (List) query.getResultList();
      
	    em.close();
	} catch (Exception e) {
	
	}
	return 0;
}
}
