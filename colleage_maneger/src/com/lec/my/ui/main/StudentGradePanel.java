package com.lec.my.ui.main;

import java.awt.CardLayout;
import java.awt.Container;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import colleage_manager.my.api.CommonAPI;
//
//public class InformationPanel extends JPanel {
//	private CommonAPI api = new CommonAPI();
//	
//	public InformationPanel() {
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		JTextField tf1 = new JTextField("아이디");
//		JTextField tf2 = new JTextField("이름");
//		JTextField tf3 = new JTextField("학과");
//		JTextField tf4 = new JTextField("생년월일");
//		JTextField tf5 = new JTextField("전화번호");
//		JTextField tf6 = new JTextField("이메일");
//		JTextField tf7 = new JTextField("집주소");
//		JTextField tf8 = new JTextField("가족관계");
//		
//		JButton j1 = (new JButton("정보 조회"));
//		
//		this.add(tf1);
//		this.add(tf2);
//		this.add(tf3);
//		this.add(tf4);
//		this.add(tf5);
//		this.add(tf6);
//		this.add(tf7);
//		this.add(tf8);
//		
//		
//		add(j1);
//	}
//}
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class StudentGradePanel extends JPanel {
	
	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton loadBtn;
	private JButton logout;
	private CommonAPI api = new CommonAPI();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	
	
	UserAuth auth = UserAuth.getInstance();
	Common user = auth.getUser();
	public StudentGradePanel(SwingMain frame) {	
		this.frame = frame;
		
		
		genInfoPair("id", "ID(학번)");
		genInfoPair("name", "이름");
		genInfoPair("class1", "과목1");
		genInfoPair("class2", "과목2");
		genInfoPair("class3", "과목3");
		genInfoPair("class4", "과목4");
		genInfoPair("class5", "과목5");
		genInfoPair("class6", "과목6");
		
		loadBtn = (new JButton("과목 정보 불러오기"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(loadListener);
		add(loadBtn);
		logout = (new JButton("로그아웃"));
		logout.setPreferredSize(new Dimension(200, 30));
		logout.addActionListener(Logout);
		add(logout);
		
	}
	
	private void genInfoPair(String id, String name) {
		JLabel label = new JLabel(name);
		label.setPreferredSize(new Dimension(200, 30));
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(200, 30));
		add(label);
		add(field);
		
		infoMap.put(id, field);
	}
	
	private ActionListener loadListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if (auth.isLogin()) {
				Common user = auth.getUser();
				infoMap.get("id").setText(user.getNumber());
				infoMap.get("name").setText(user.getName());
				infoMap.get("class1").setText(user.getName());
			}
		}
	};
	public ActionListener Logout = new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			auth.logout();
			SwingMain ins = new SwingMain();
			ins.setVisible(true);
			frame.dispose();
		//layout.show(this.getContentPane(), MainTab);
			
	
		}
		
	
		
		
		
		
	};
}