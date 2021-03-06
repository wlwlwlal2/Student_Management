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
public class ProfessorInformationPanel extends JPanel {
	
	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton check;
	private JButton logout;
	private CommonAPI api = new CommonAPI();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	
	public ProfessorInformationPanel(SwingMain frame) {	
		this.frame = frame;
		genInfoPair("id", "ID(학번)");
		genInfoPair("name", "이름");
		genInfoPair("phone", "전화번호");
		genInfoPair("email", "이메일");
		genInfoPair("birth", "생년월일");
		genInfoPair("pwd", "비밀번호");
		genInfoPair("address", "주소");
		
		
		
		saveBtn = (new JButton("내 정보 저장하기"));
		saveBtn.setPreferredSize(new Dimension(200, 30));
		saveBtn.addActionListener(saveListener);
		add(saveBtn);
		loadBtn = (new JButton("내 정보 불러오기"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(loadListener);
		add(loadBtn);
		logout = (new JButton("로그아웃"));
		logout.setPreferredSize(new Dimension(200, 30));
		logout.addActionListener(Logout);
		add(logout);
		check = (new JButton("확인"));
		check.setPreferredSize(new Dimension(200, 30));
		//loadBtn.addActionListener(loadListener);
		add(check);
		
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
	
	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				
				String number = infoMap.get("id").getText();
				String name = infoMap.get("name").getText();
				String birth = infoMap.get("birth").getText();
				String phoneNumber = infoMap.get("phone").getText();
				String email = infoMap.get("email").getText();
				String birth2 = infoMap.get("birth").getText();
				String address = infoMap.get("address").getText();
				
				
				//boolean result1 = api.InfoUpdate(number,name, birth2, phoneNumber, email, address);
				Common result2 = api.Read(number);
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
                	op1.showMessageDialog(null, number + " 정보 저장 성공");
                	
    			} else {
    				op1.showMessageDialog(null, number + " 정보 저장 실패");   
    			}
				
			}
		}
	};
	private ActionListener loadListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				Common user = auth.getUser();
				infoMap.get("id").setText(user.getNumber());
				infoMap.get("name").setText(user.getName());
				infoMap.get("phone").setText(user.getPhoneNumber());
				infoMap.get("email").setText(user.getEmail());
				infoMap.get("birth").setText(user.getBirth());
				infoMap.get("pwd").setText(user.getPassword());
				infoMap.get("address").setText(user.getAddress());
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