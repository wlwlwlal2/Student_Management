package com.lec.my.ui.main;

import javax.swing.JTabbedPane;

import colleage_manager.my.swing.SwingMain;

public class MainTab extends JTabbedPane {
		public MainTab(SwingMain loginFrame) {
			addTab("�α���", new LoginPanel(loginFrame));
			addTab("ȸ������", new SignUpPanel());
		}
}
