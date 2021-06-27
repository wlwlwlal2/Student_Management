package colleage_manager.my.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import colleage_manager.my.model.Common;

public class ComplexKeyAPI {
	private static final String PERSISTENCE_UNIT_NAME = "mysql";
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	protected static final EntityManager em = factory.createEntityManager();
	
	public static void main(String[] args) {
		try {
			
}
		
		private static void register(Common common) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.createNativeQuery("INSERT INTO common (id, name) VALUES (?,?)").setParameter(1, user.getId())
					.setParameter(2, user.getName()).executeUpdate();
			transaction.commit();
		}
		
}