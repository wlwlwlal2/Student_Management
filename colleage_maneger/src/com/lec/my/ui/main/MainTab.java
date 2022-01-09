package com.lec.my.ui.main;

import javax.swing.JTabbedPane;

import colleage_manager.my.swing.SwingMain;

public class MainTab extends JTabbedPane {
		public MainTab(SwingMain loginFrame) {
			addTab("로그인", new LoginPanel(loginFrame));
			addTab("회원가입", new SignUpPanel());
		}
}
