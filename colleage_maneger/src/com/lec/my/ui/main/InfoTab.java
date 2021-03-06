package com.lec.my.ui.main;

import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

public class InfoTab extends JTabbedPane {
	
	public InfoTab(SwingMain frame) {
		
		CommonAPI api = new CommonAPI();
		String role = null;
		Common user = null;
		UserAuth auth = UserAuth.getInstance();
		
		if (auth.isLogin()) {
			user = auth.getUser();
			role = user.getRole();
		}
		
		if(role == "학생")
		addTab("학생 정보", new StudentInformationPanel(frame));
		else if(role == "교수") 
		addTab("교수 정보", new ProfessorInformationPanel(frame));
		else if(role == null) // 테스트
		addTab("학생 정보", new StudentInformationPanel(frame));
	}
	
}

