package colleage_manager.my.api;

import colleage_manager.my.model.Common;


public class UserAuth {
	private static UserAuth instance;
	private Common loginUser;
	private CommonAPI loginAPI;
	// private Student StudentUser;
	
	
	// Singleton Pattern
	public static UserAuth getInstance() {
		if(instance == null) {
			instance = new UserAuth();
		}
		return instance;
	}
	
	public void login(Common common) {
		this.loginUser = common;
		
		
//		switch(common.getRole()) {
//		case "학생": 
//			loginAPI = StudentAPI.getInstance(); break;
//		case "교수": 
//			loginAPI = ProfessorAPI.getInstance(); break;
////		case "admin": 
////			CommonAPI = new AdminAPI(); break;
////		case "employee": 
////			CommonAPI = new EmployeeAPI(); break;
//		default: break;
//		}
	}
	
	public boolean isLogin() {
		return (loginUser != null) ? true : false;
	}
	
	public String getLoginName() {
		return loginUser.getName();
	}
	
	public Common getUser() {
		return loginUser;
	}
	
	public CommonAPI getUserAPI() {
		return loginAPI;
	}
	
	
	public void logout() {
		loginUser = null;
		
	}
}
