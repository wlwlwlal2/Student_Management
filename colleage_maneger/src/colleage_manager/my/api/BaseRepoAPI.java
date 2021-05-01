package colleage_manager.my.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseRepoAPI {
//	private static final String PERSISTENCE_UNIT_NAME = "mysql";
//	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//	protected static final EntityManager em = factory.createEntityManager();
	
	private static final String PERSISTENCE_UNIT_NAME = "h2";
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	protected static EntityManager em = factory.createEntityManager();
}
