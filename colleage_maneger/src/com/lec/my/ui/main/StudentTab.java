package com.lec.my.ui.main;

import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class StudentTab extends JTabbedPane {
	
	private InformationPanel informationPanel = new InformationPanel();
	
		public StudentTab(Frame frame) {
			addTab("³» Á¤º¸", informationPanel);
		
		}
	}
