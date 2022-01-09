package com.lec.my.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

public class InfoTab extends JTabbedPane {
	
	public InfoTab(SwingMain frame) {
		
		CommonAPI api = CommonAPI.getInstance();
		String role = null;
		Common user = null;
		UserAuth auth = UserAuth.getInstance();
		
		if (auth.isLogin()) {
			user = auth.getUser();
			role = user.getRole();
		}
		
		StudentLectureInformationPanel lecturePanel = new StudentLectureInformationPanel(frame); 
		TestPanel testPanel = new TestPanel(frame); 
		addTab("학생 정보", new StudentInformationPanel(frame));
		addTab("성적", new StudentGradePanel(frame));
		addTab("강의", lecturePanel);
		addTab("테스트", testPanel);
	
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lecturePanel.tabChanged();
			}
	    });
	}
	
	
	
}

