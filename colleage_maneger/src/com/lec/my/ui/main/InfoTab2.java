package com.lec.my.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

public class InfoTab2 extends JTabbedPane {
	
	CommonAPI api = new CommonAPI();
	
	public InfoTab2(SwingMain frame) {
		
		
		
		String role = null;
		Common user = null;
		UserAuth auth = UserAuth.getInstance();
		
		if (auth.isLogin()) {
			user = auth.getUser();
			role = user.getRole();
		}
		
//		if(role == "학생")
//		addTab("학생 정보", new StudentInformationPanel(frame));
//		else if(role == "교수") 
//		addTab("교수 정보", new ProfessorInformationPanel(frame));
//		else if(role == null) // 테스트
//		addTab("학생 정보", new StudentInformationPanel(frame));
		
		
		
	
		 
	//	addTab("학생 정보", new StudentInformationPanel(frame));
		addTab("교수 정보", new ProfessorInformationPanel(frame));
		addTab("성적", new ProfessorGradePanel(frame));
		addTab("과목", new SubjectInformationPanel(frame));
		addTab("강의", new LectureInformationPanel(frame));
		
	}
	
	
	}
		
	
//	saveBtn.setPreferredSize(new Dimension(200, 30));
//	saveBtn.addActionListener(saveListener);
	


