package com.lec.my.ui.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

public class InfoTab3 extends JTabbedPane {

	CommonAPI api = CommonAPI.getInstance();

	public InfoTab3(SwingMain frame) {

		String role = null;
		Common user = null;
		UserAuth auth = UserAuth.getInstance();

		if (auth.isLogin()) {
			user = auth.getUser();
			role = user.getRole();
		}

		addTab("가입자 정보", new ManagerPanel(frame));
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
			}
		});
	}

}

//	saveBtn.setPreferredSize(new Dimension(200, 30));
//	saveBtn.addActionListener(saveListener);
