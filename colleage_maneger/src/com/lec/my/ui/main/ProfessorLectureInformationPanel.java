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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.LectureAPI;
import colleage_manager.my.api.StudentAPI;
import colleage_manager.my.api.LectureHistoryAPI;
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.model.Student;
import colleage_manager.my.model.LectureHistoryID;
import colleage_manager.my.model.Subject;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class ProfessorLectureInformationPanel extends JPanel {

	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private HashMap<String, String> infoMap2 = new HashMap<String, String>();
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton joinBtn;
	private JButton updateBtn;
	private JButton check;
	private JButton logout;
	private CommonAPI commonapi = CommonAPI.getInstance();;
	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private LectureHistoryAPI studentidapi = LectureHistoryAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	String[] dataDefault = { "안바뀌면 이거나옴" };

	public JComboBox<Subject> strCombo = new JComboBox(dataDefault);
	private int selectedIdx = 0;

	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);

	public ProfessorLectureInformationPanel(SwingMain frame) {
		this.frame = frame;
		genInfoPair("lecturenumber", "강의코드");
		genInfoPair("subnumber", "과목코드");
		// genInfoPair("number", "교수ID");
		JLabel label = new JLabel("과목명");
		label.setPreferredSize(new Dimension(200, 30));
		add(label);
		strCombo.setPreferredSize(new Dimension(200, 30));
		add(strCombo);
		infoMap2.put("subname", (String) strCombo.getSelectedItem());

		genInfoPair("lecturename", "강의명");
		genInfoPair("name", "교수 이름");
		genInfoPair("grade", "점수");
		genInfoPair("day", "요구 출석일");

		saveBtn = (new JButton("강의 개설"));
		saveBtn.setPreferredSize(new Dimension(200, 30));
		saveBtn.addActionListener(addListener);
		add(saveBtn);
		loadBtn = (new JButton("강의 제거"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(deleteListener);
		add(loadBtn);
		logout = (new JButton("정보 수정"));
		logout.setPreferredSize(new Dimension(200, 30));
		logout.addActionListener(saveListener);
		add(logout);
		check = (new JButton("수강자 확인"));
		check.setPreferredSize(new Dimension(200, 30));
		check.addActionListener(CheckListener);
		add(check);
		ml.addListSelectionListener(change);
		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 300));
		add(ml);

		setComboBox();
		strCombo.addItemListener(subJComboListener);
	}

	private void setComboBox() {
		List<Subject> sublist = subjectapi.readAll();

		strCombo.removeAllItems(); // 이게 문제같음

		Subject defaultSub = new Subject();
		defaultSub.setSubName("과목을 선택하세요.");
		strCombo.addItem(defaultSub);
		for (int i = 0; i < sublist.size(); i++) {
			Subject subject = sublist.get(i);
			strCombo.addItem(subject);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ListUpdate();
	}

	public void tabChanged() {

		setComboBox();
	}

	private void ListUpdate() {
		List<Lecture> mylist = lectureapi.readAll();
		dlm.clear();
		for (int i = 0; i < mylist.size(); i++) {
			Lecture lecture = mylist.get(i);
			String data = lecture.getLectureNumber();
			dlm.addElement(data);

		}
	}

	public ListSelectionListener change = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {

			if (!e.getValueIsAdjusting()) {

				int row = ml.getSelectedIndex();
				String val1 = (String) ml.getSelectedValue();

				// String val3 = val1.substring(val1.lastIndexOf("(") +
				// 1,val1.lastIndexOf(")"));



				if (val1 != null) {
					String val2 = val1;

					if (row != -1) {
						Lecture user = lectureapi.getLecture(val2);
						Subject sub = user.getSubject();
						
						infoMap.get("lecturenumber").setText(user.getLectureNumber());
						infoMap.get("subnumber").setText(sub.getSubNumber());

						// infoMap2.put("subname", user.getSubname());
					//	strCombo.setSelectedItem(sub.getSubName());
						strCombo.setSelectedItem(user.getSubject());
						System.out.println(user);
						infoMap.get("lecturename").setText(user.getLectureName());
						infoMap.get("name").setText(user.getName());
						infoMap.get("grade").setText(user.getGrade());
						infoMap.get("day").setText(user.getDay());
					}

					else {

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

//	private ActionListener subJComboListener = new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			
//			if(strCombo.getSelectedIndex() != 0) {
//				Subject selectedComboObj = (Subject) strCombo.getSelectedItem();
//				if (selectedComboObj == null) 
//					return; // 여기 원인 찾기
//				
//				
//				
//				infoMap.get("subnumber").setText(selectedComboObj.getSubNumber());
//				
//				repaint();
//			} 
//		}
//	};
	private ItemListener subJComboListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == 1) {
				if (strCombo.getSelectedIndex() != 0) {
					Subject selectedComboObj = (Subject) strCombo.getSelectedItem();
					infoMap.get("subnumber").setText(selectedComboObj.getSubNumber());

					repaint();
				}
			}

		}

	};

	private ActionListener addListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				// String number,String subnumber,String name, String grade, String day
				String lecturenumber = infoMap.get("lecturenumber").getText();
				String subnumber = infoMap.get("subnumber").getText();

				Subject subnameObj = (Subject) strCombo.getSelectedItem();
				String subname = subnameObj.getSubName();
				String lecturename = infoMap.get("lecturename").getText();
				String name = infoMap.get("name").getText();
				String grade = infoMap.get("grade").getText();
				String day = infoMap.get("day").getText();

				lectureapi.Register(lecturenumber,subjectapi.getSubject(subnumber), subname, lecturename, name, grade, day);
				Lecture result2 = lectureapi.Read(subnumber);

				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
                	op1.showMessageDialog(null, lecturename + " 강의 추가 성공");
					ListUpdate();

				} else {
					op1.showMessageDialog(null, lecturename + " 강의 추가 실패");
				}

			}
		}
	};

	private ActionListener deleteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {

				String subnumber = infoMap.get("subnumber").getText();

				lectureapi.Delete(subnumber);
				Lecture result2 = lectureapi.Read(subnumber);
				JOptionPane op1 = new JOptionPane();
				if (result2 == null) {
					op1.showMessageDialog(null, subnumber + " 과목 삭제 성공");
					ListUpdate();
				} else {
					op1.showMessageDialog(null, subnumber + " 과목 추가 실패");
				}

			}
		}
	};

	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {

				String lecturenumber = infoMap.get("lecturenumber").getText();
				String subnumber = infoMap.get("subnumber").getText();
				Subject subnameObj = (Subject) strCombo.getSelectedItem();
				String subname = subnameObj.getSubName();
				
				String lecturename = infoMap.get("lecturename").getText();
				String name = infoMap.get("name").getText();
				String grade = infoMap.get("grade").getText();
				String day = infoMap.get("day").getText();

				boolean result1 = lectureapi.InfoUpdate(lecturenumber, subnumber, subname, lecturename, name, grade, day);
				Lecture result2 = lectureapi.Read(lecturenumber);
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
					op1.showMessageDialog(null, lecturenumber + " 정보 수정 성공");
					ListUpdate();
				} else {
					op1.showMessageDialog(null, lecturenumber + " 정보 수정 실패");
					
				}

			}
		}
	};
	private ActionListener CheckListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				// String number,String subnumber,String name, String grade, String day
				new studentcheckWindow();
				

			}
		}
	};
	class studentcheckWindow extends JFrame {
		studentcheckWindow() {
	        setTitle("수강 학생 확인");
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        
	        setSize(500,600);
	        setResizable(false);
	        setVisible(true);
	        
	        String lecturenumber = infoMap.get("lecturenumber").getText();
	        List<String> mylist = lectureapi.GetListener(lecturenumber);
	        ;
			String[][] arr = new String[mylist.size()][7];
			String[] label = {"이름","아이디","점수","반영점수","출석","지각","결석"};
			for(int i = 0; i <mylist.size(); i++) {
				Common user = commonapi.getCommon(mylist.get(i));
				LectureHistory student = studentidapi.Read(lecturenumber, user.getNumber());
					arr[i][0] = user.getName();
					arr[i][1] = user.getNumber();
					arr[i][2] = user.getNumber();
						
			}
			
			
			DefaultTableModel model = new DefaultTableModel(arr,label);
			JTable table = new JTable(model);
			JScrollPane sc = new JScrollPane(table);
			add(sc);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			
			joinBtn = (new JButton("성적 조회"));
			joinBtn.setPreferredSize(new Dimension(150, 30));
			//saveBtn.addActionListener(addListener);
			add(joinBtn);
			
			updateBtn = (new JButton("성적 수정"));
			updateBtn.setPreferredSize(new Dimension(150, 30));
			//saveBtn.addActionListener(addListener);
			add(updateBtn);
			
	    }
	}

	
}


// 성적 기입 코드 바꾸기