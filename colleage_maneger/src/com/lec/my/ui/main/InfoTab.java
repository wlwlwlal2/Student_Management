package com.lec.my.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
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
		 
		addTab("학생 정보", new StudentInformationPanel(frame));
		addTab("성적", new StudentGradePanel(frame));
	
	}
	
	
	
}

