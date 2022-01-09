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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;
import colleage_manager.my.model.Subject;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class ManagerPanel extends JPanel {

	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton logout;
	private CommonAPI api = CommonAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	private String[] cmbDefault = {"학생","교수","관리자"};
	private JComboBox cmb = new JComboBox(cmbDefault);
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);

	public ManagerPanel(SwingMain frame) {
		this.frame = frame;
		genInfoPair("id", "ID(학번)");
		genInfoPair("password", "비밀번호");
		genInfoPair("classNumber", "학과코드");
		genInfoPair("name", "이름");
		
		JLabel label = new JLabel("계정분류");
		label.setPreferredSize(new Dimension(200, 30));
		add(label);
		cmb.setPreferredSize(new Dimension(200, 30));
		add(cmb);

		ml.addListSelectionListener(change);
		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 250));
		add(ml);
		
		addBtn = (new JButton("계정 추가"));
		addBtn.setPreferredSize(new Dimension(200, 30));
		addBtn.addActionListener(addListener);
		add(addBtn);
		
		deleteBtn = (new JButton("계정 제거"));
		deleteBtn.setPreferredSize(new Dimension(200, 30));
		deleteBtn.addActionListener(deleteListener);
		add(deleteBtn);

		logout = (new JButton("로그아웃"));
		logout.setPreferredSize(new Dimension(405, 30));
		logout.addActionListener(Logout);
		add(logout);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ListUpdate();
	}
	
	private void ListUpdate() {
		List<Common> mylist = api.readAll();
		ArrayList<String> stringlist = new ArrayList<String>();

		dlm.clear();
		stringlist.clear();
		for (int i = 0; i < mylist.size(); i++) {
			Common common = mylist.get(i);
			String data = common.getNumber();
			stringlist.add(data);
			Collections.sort(stringlist);	
		}
		
		for (int i = 0; i < mylist.size(); i++) {
			Common common = api.getCommon(stringlist.get(i));
			String data = common.getNumber();
			dlm.addElement(data);
		}
	}

	public ListSelectionListener change = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {

			if (!e.getValueIsAdjusting()) {

				int row = ml.getSelectedIndex();
				String val1 = (String) ml.getSelectedValue();

				if (val1 != null) {
					String val2 = val1;

					if (row != -1) {
						Common user = api.getCommon(val2);		
						infoMap.get("id").setText(user.getNumber());
						infoMap.get("password").setText(user.getPassword());
						infoMap.get("classNumber").setText(user.getClassNumber());
						infoMap.get("name").setText(user.getName());
						cmb.setSelectedItem(user.getRole());

					}
				}
			}
		}
	};
	
	private void genInfoPair(String id, String name) {
		JLabel label = new JLabel(name);
		label.setPreferredSize(new Dimension(200, 30));
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(200, 30));
		add(label);
		add(field);

		infoMap.put(id, field);
	}
	private ActionListener addListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				
				String number = infoMap.get("id").getText();
				String role = (String) cmb.getSelectedItem();
				String password = infoMap.get("password").getText();
				String classNumber = infoMap.get("classNumber").getText();
				String name = infoMap.get("name").getText();
				api.Register(role, number, password); 
				api.InfoUpdate(number, classNumber, name, "", "", "", "");
				Common result = api.Read(number);
				JOptionPane op1 = new JOptionPane();

				if (result != null) {
					op1.showMessageDialog(null, number + " 계정 생성 성공");
					ListUpdate();
				} else {
					op1.showMessageDialog(null, number + " 계정 생성 오류");
				}

			}
		}
	};
	
	private ActionListener deleteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {

				String number = infoMap.get("id").getText();
				api.Delete(number);
				JOptionPane op1 = new JOptionPane();
				Common result = api.Read(number);
				
				if (result == null) {
					op1.showMessageDialog(null, number + " 계정 삭제 성공");
					ListUpdate();
				} else {
					op1.showMessageDialog(null, number + " 계정 삭제 실패");
				}
			}
		}
	};
	
	public ActionListener Logout = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			auth.logout();
			frame.changeMainTab();
		}
	};
}