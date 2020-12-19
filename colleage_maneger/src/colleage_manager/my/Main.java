
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
    
    // EntityManager 인스턴트 관리
    // 이 클래스로 Entity

    @SuppressWarnings("unchecked")
    // 주의 문구 제거, unchecked : 미확인 오퍼레이션 관련 경고 제거
    
	public static void main(String[] args) {
    	
    	
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        // factory에 위에서 만든 h2에 대한 영속성 관리를 넣는다.
        // DB 인스턴스 생성
        
        EntityManager em = factory.createEntityManager();
        // em이라는 새로운 목록 생성
        // 사용자 데이터 생성
        
        Common[] userArr = new Common[5];
        for(int i=0; i<userArr.length; i++) {
        	userArr[i] = new Common();
        	userArr[i].setNumber(i + "");
        	userArr[i].setName("student-" + i);
        }
        // 사용자 데이터 Common을 5칸 만들고 for문을 사용하여 배열에 사용자 데이터를 넣는다.
        
        // 트랜잭션 시작 & DB로 커밋
        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); 
        for(int i=0; i<userArr.length; i++) {
        	em.persist(userArr[i]);
        }
        transaction.commit(); 
        
        // DB에서 사용자 읽기
        Query query = em.createQuery("select t from Common t");
        List<Common> resultList = query.getResultList();
        
        for (Common result : resultList) {
            System.out.println(result.toString());
        }
        System.out.println("Size: " + resultList.size());
        
        // DB 종료
        em.close();
    }
}