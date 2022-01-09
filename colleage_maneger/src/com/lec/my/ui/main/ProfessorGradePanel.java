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
//		JTextField tf1 = new JTextField("���̵�");
//		JTextField tf2 = new JTextField("�̸�");
//		JTextField tf3 = new JTextField("�а�");
//		JTextField tf4 = new JTextField("�������");
//		JTextField tf5 = new JTextField("��ȭ��ȣ");
//		JTextField tf6 = new JTextField("�̸���");
//		JTextField tf7 = new JTextField("���ּ�");
//		JTextField tf8 = new JTextField("��������");
//		
//		JButton j1 = (new JButton("���� ��ȸ"));
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.LectureAPI;
import colleage_manager.my.api.StudentAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class ProfessorGradePanel extends JPanel {

	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton loadBtn;
	private JButton logout;
	private JButton update;
	private CommonAPI api = CommonAPI.getInstance();
	private StudentAPI sapi = StudentAPI.getInstance();
	private LectureAPI lapi = LectureAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);

	public ProfessorGradePanel(SwingMain frame) {
		this.frame = frame;
		genInfoPair("id", "ID(�й�)");
		genInfoPair("name", "�̸�");
		genInfoPair("class1", "����1");
		genInfoPair("class2", "����2");
		genInfoPair("class3", "����3");
		genInfoPair("class4", "����4");
		genInfoPair("class5", "����5");
		genInfoPair("class6", "����6");

		loadBtn = (new JButton("���� ���� �����ϱ�"));
		loadBtn.setPreferredSize(new Dimension(150, 30));
		loadBtn.addActionListener(loadListener);
		add(loadBtn);
		logout = (new JButton("�α׾ƿ�"));
		logout.setPreferredSize(new Dimension(150, 30));
		logout.addActionListener(Logout);
		add(logout);
		update = (new JButton("����"));
		update.setPreferredSize(new Dimension(150, 30));
		update.addActionListener(Update);
		add(logout);
		ml.addListSelectionListener(change);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<Common> mylist = api.readAll();

		dlm.clear();
		for (int i = 0; i < mylist.size(); i++) {
			Common common = mylist.get(i);
			String data = common.getNumber();
			dlm.addElement(data);
		}

		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 300));
		add(ml);

	}

	private void genInfoPair(String id, String name) {
		JTextField label = new JTextField(name);
		label.setPreferredSize(new Dimension(200, 30));
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(200, 30));

		add(label);
		add(field);
		infoMap.put(id, field);
	}

	private ActionListener loadListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				Common user = auth.getUser();
				infoMap.get("id").setText(user.getNumber());
				infoMap.get("name").setText(user.getName());
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

		}

	};
	public ListSelectionListener change = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				int row = ml.getSelectedIndex();
				String val = (String) ml.getSelectedValue();
				if (row != -1) {
					Common user = api.getCommon(val);
					infoMap.get("id").setText(user.getNumber());
					infoMap.get("name").setText(user.getName());
				}
			}
		}
	};
	public ActionListener Update = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			api.join();
		}
	};
}
