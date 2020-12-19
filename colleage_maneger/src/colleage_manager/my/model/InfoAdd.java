package colleage_manager.my.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import colleage_manager.my.model.Common; 

class InfoAdd {
	private static final String PERSISTENCE_UNIT_NAME = "h2";
    private static EntityManagerFactory factory;
    
//   @SuppressWarnings("unchecked")
    
public static void main(String[] args) {
    	
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // em이라는 새로운 목록 생성
        // 사용자 데이터 생성
        
        Common[] userArr = new Common[11];
        for(int i=0; i<userArr.length; i++) {
        	userArr[i] = new Common();
        	userArr[i].setNumber(i + "");
        	userArr[i].setName("student-" + i);
        	userArr[i].setPassword("password: " + i);
        	userArr[i].setClassNumber(i);
        	userArr[i].setBirth(i);
        	userArr[i].setPhoneNumber(i);
        	userArr[i].setEmail("password: " + i);
        	userArr[i].setAddress("password: " + i);
        	userArr[i].setFamily("password: " + i);
        	userArr[i].setLectureName("password: " + i);
        //	userArr[i].setLectureLocate("password: " + i);
        }
}
}
