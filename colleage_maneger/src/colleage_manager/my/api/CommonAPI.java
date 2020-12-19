package colleage_manager.my.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import colleage_manager.my.model.Common;

public class CommonAPI {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); 
    private EntityManager em = factory.createEntityManager();

    public int login(String number, String password) {
    	
    	try {
    		Common common = new Common();
    		common.setNumber("202020202");
    	} catch (Exception e) {
    	
    	}
    	return 0;
    }
}
