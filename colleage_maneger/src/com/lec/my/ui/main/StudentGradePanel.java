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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List; 

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.lec.my.ui.main.ProfessorLectureInformationPanel.studentcheckWindow;

import colleage_manager.my.excel.excel;
import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.LectureAPI;
import colleage_manager.my.api.LectureHistoryAPI;
import colleage_manager.my.api.StudentAPI;
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class StudentGradePanel extends JPanel {

	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton loadBtn;
	private JButton timeBtn;
	private JButton accelBtn;
	private CommonAPI api = CommonAPI.getInstance();
	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private StudentAPI studentapi = StudentAPI.getInstance();
	private LectureHistoryAPI studentidapi = LectureHistoryAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	private excel ex = excel.getInstance();
	List<LectureHistory> mylist;
	
	JScrollPane sc;
	JTable table;

	UserAuth auth = UserAuth.getInstance();
	Common user = auth.getUser();

	public StudentGradePanel(SwingMain frame) {
		this.frame = frame;

		loadBtn = (new JButton("���� Ȯ���ϱ�"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(loadListener);
		add(loadBtn);
		timeBtn = (new JButton("�ð�ǥ ����"));
		timeBtn.setPreferredSize(new Dimension(200, 30));
		timeBtn.addActionListener(time);
		add(timeBtn);
		accelBtn = (new JButton("�׼� ���� ����"));
		accelBtn.setPreferredSize(new Dimension(200, 30));
		accelBtn.addActionListener(createaccelListener);
		add(accelBtn);
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
				new gradecheckWindow();
			}
		}
	};

	private ActionListener time = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (auth.isLogin()) {
				new timecheckWindow();
			}
		}
	};

	class gradecheckWindow extends JFrame {
		gradecheckWindow() {
			setTitle("���� Ȯ��");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);
			setSize(500, 600);
			setResizable(false);
			setVisible(true);

			UserAuth auth = UserAuth.getInstance();
			Common user = auth.getUser();
			mylist = studentidapi.StudentListeningLectureRead(user.getNumber());
			String[][] arr = new String[mylist.size()][6];

			for (int i = 0; i < mylist.size(); i++) {
				LectureHistory student = mylist.get(i);
				arr[i][0] = student.getLecture().getLectureName();
				arr[i][1] = student.getGrade();
				arr[i][2] = student.getRealGrade();
				arr[i][3] = student.getMaxAttendance();
				arr[i][4] = student.getAttendance();
				arr[i][5] = student.getAbsence();
			}

			String[] label = { "���Ǹ�", "����", "�ݿ�����", "�⼮", "����", "�Ἦ" };
			DefaultTableModel model = new DefaultTableModel(arr, label) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};

			table = new JTable(model);
			sc = new JScrollPane(table);
			add(sc);		
		}
	}

	class timecheckWindow extends JFrame {
		timecheckWindow() {

			setTitle("�ð�ǥ");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);
			setSize(500, 300);
			setResizable(false);
			setVisible(true);
			UserAuth auth = UserAuth.getInstance();
			Common user = auth.getUser();

			List<LectureHistory> mylist = studentidapi.StudentListeningLectureRead(user.getNumber());
			String[][] arr = new String[mylist.size()][5];

			for (int i = 0; i < mylist.size(); i++) {
				String studyingtime = new String();
				LectureHistory student = mylist.get(i);
				arr[i][0] = student.getLecture().getLectureName();
				arr[i][1] = student.getLecture().getName();
				arr[i][2] = student.getLecture().getStartTime();
				arr[i][3] = student.getLecture().getEndTime();

				if (student.getLecture().getStudyingDay()[0] == true) {
					studyingtime = studyingtime.substring(0) + "�� ";
				}
				if (student.getLecture().getStudyingDay()[1] == true) {
					studyingtime = studyingtime.substring(0) + "ȭ ";
				}
				if (student.getLecture().getStudyingDay()[2] == true) {
					studyingtime = studyingtime.substring(0) + "�� ";
				}
				if (student.getLecture().getStudyingDay()[3] == true) {
					studyingtime = studyingtime.substring(0) + "�� ";
				}
				if (student.getLecture().getStudyingDay()[4] == true) {
					studyingtime = studyingtime.substring(0) + "��";
				}
				arr[i][4] = studyingtime;
			}

			String[] label = { "���Ǹ�", "������", "���� ����", "���� ����", "���� ����" };
			DefaultTableModel model = new DefaultTableModel(arr, label) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};

			table = new JTable(model);
			sc = new JScrollPane(table);
			add(sc);
		}
	}

	ActionListener createaccelListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Common user = auth.getUser();
			mylist = studentidapi.StudentListeningLectureRead(user.getNumber());
			
			ex.excel(user.getNumber(),mylist, 0);              
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
}