
package colleage_manager.my;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import colleage_manager.my.model.Common;

public class Main {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
    private static EntityManagerFactory factory;

    @SuppressWarnings("unchecked")

    
	public static void main(String[] args) {
    	
    	
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        // DB �ν��Ͻ� ����
        EntityManager em = factory.createEntityManager();
        
        // ����� ������ ����
        Common[] userArr = new Common[5];
        for(int i=0; i<userArr.length; i++) {
        	userArr[i] = new Common();
        	userArr[i].setNumber(i + "");
        	userArr[i].setName("student-" + i);
        	
        }
        
        
        // Ʈ����� ���� & DB�� Ŀ��
        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); 
        for(int i=0; i<userArr.length; i++) {
        	em.persist(userArr[i]);
        }
        transaction.commit(); 
        
        // DB���� ����� �б�
        Query query = em.createQuery("select t from Common t");
        List<Common> resultList = query.getResultList();
        
        for (Common result : resultList) {
            System.out.println(result.toString());
        }
        System.out.println("Size: " + resultList.size());
        
        // DB ����
        em.close();
    }
}