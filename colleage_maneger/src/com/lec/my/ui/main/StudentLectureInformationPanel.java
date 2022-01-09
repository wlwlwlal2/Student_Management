package com.lec.my.ui.main;

import java.awt.CardLayout;
import java.awt.Color;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import colleage_manager.my.api.LectureHistoryAPI;
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;
import colleage_manager.my.model.LectureHistory;
import colleage_manager.my.model.LectureHistoryID;
import colleage_manager.my.model.Subject;
import colleage_manager.my.swing.SwingMain;
import java.util.Arrays;


@SuppressWarnings("serial")
public class StudentLectureInformationPanel extends JPanel {
	
	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private HashMap<String, String> infoMap2 = new HashMap<String, String>();
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton check;
	private JButton logout;
	private JCheckBox cb1, cb2, cb3, cb4, cb5;
	private CommonAPI commonapi = CommonAPI.getInstance();;
	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private StudentAPI studentapi = StudentAPI.getInstance();
	private LectureHistoryAPI studentidapi = LectureHistoryAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	String[] dataDefault = { "안바뀌면 이거나옴" };

	public JComboBox<Subject> strCombo = new JComboBox(dataDefault);
	private int selectedIdx = 0;

	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);

	public StudentLectureInformationPanel(SwingMain frame) {
		this.frame = frame;
		genInfoPair("lecturenumber", "강의코드");
		genInfoPair("subnumber", "과목코드");
		// genInfoPair("number", "교수ID");
		JLabel label = new JLabel("과목명");
		label.setPreferredSize(new Dimension(200, 30));
		add(label);
		
		strCombo.setPreferredSize(new Dimension(200, 30));
		add(strCombo);
		//strCombo.setEditable(false);
		strCombo.setEnabled(false);
		//strCombo.setBackground(Color.LIGHT_GRAY);
		
		infoMap2.put("subname", (String) strCombo.getSelectedItem());

		genInfoPair("lecturename", "강의명");
		genInfoPair("name", "교수 이름");
		genInfoPair("grade", "점수");
		genInfoPair("day", "요구 출석일");
		genInfoPair("starttime", "수업 시작시간");
		genInfoPair("endtime", "수업 종료시간");
		
		cb1 = new JCheckBox("월");
		cb1.setPreferredSize(new Dimension(80, 20));
		cb2 = new JCheckBox("화");
		cb2.setPreferredSize(new Dimension(80, 20));
		cb3 = new JCheckBox("수");
		cb3.setPreferredSize(new Dimension(80, 20));
		cb4 = new JCheckBox("목");
		cb4.setPreferredSize(new Dimension(80, 20));
		cb5 = new JCheckBox("금");
		cb5.setPreferredSize(new Dimension(80, 20));
		add(cb1); add(cb2); add(cb3); add(cb4); add(cb5);
		cb1.setEnabled(false);
		cb2.setEnabled(false);
		cb3.setEnabled(false);
		cb4.setEnabled(false);
		cb5.setEnabled(false);
		
		saveBtn = (new JButton("강의 등록"));
		saveBtn.setPreferredSize(new Dimension(200, 30));
		saveBtn.addActionListener(addLecture);
		add(saveBtn);
		loadBtn = (new JButton("강의 취소"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(cancleLecture);
		add(loadBtn);
		logout = (new JButton("버튼 1"));
		logout.setPreferredSize(new Dimension(200, 30));
		//logout.addActionListener(saveListener);
		//add(logout);
		check = (new JButton("버튼 1"));
		check.setPreferredSize(new Dimension(200, 30));
		// loadBtn.addActionListener(loadListener);
		//add(check);
		ml.addListSelectionListener(change);
		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 170));
		add(ml);

		setComboBox();
		strCombo.addItemListener(subJComboListener);
	}

	private void setComboBox() {
		List<Subject> sublist = subjectapi.readAll();

		strCombo.removeAllItems();
		
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
		ArrayList<String> stringlist = new ArrayList<String>();
		
		dlm.clear();
		stringlist.clear();
		for (int i = 0; i < mylist.size(); i++) {
			Lecture lecture = mylist.get(i);
			String data = lecture.getLectureNumber();
			stringlist.add(data);
			Collections.sort(stringlist);
			
		}
		
		for (int i = 0; i < mylist.size(); i++) {
			Lecture lecture = lectureapi.getLecture(stringlist.get(i));
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

				System.out.println(val1);

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
						infoMap.get("starttime").setText(user.getStartTime());
						infoMap.get("endtime").setText(user.getEndTime());
						
						boolean[] chk = user.getStudyingDay();
						
						if(chk[0] == true)
							cb1.setSelected(true);
						else
							cb1.setSelected(false);
						
						if(chk[1] == true)
							cb2.setSelected(true);
						else
							cb2.setSelected(false);
						
						if(chk[2] == true)
							cb3.setSelected(true);
						else
							cb3.setSelected(false);
						
						if(chk[3] == true)
							cb4.setSelected(true);
						else
							cb4.setSelected(false);
						
						if(chk[4] == true)
							cb5.setSelected(true);
						else
							cb5.setSelected(false);
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
		field.setEditable(false);
		field.setBackground(Color.LIGHT_GRAY);
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

	private ActionListener addLecture = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				// String number,String subnumber,String name, String grade, String day
				String lecturenumber = infoMap.get("lecturenumber").getText();
				Common user = auth.getUser();
				LectureHistory lech = new LectureHistory();
				String studentnumber = user.getNumber();
				JOptionPane op1 = new JOptionPane();
				
				int result1 = lectureapi.listCheck(lecturenumber, studentnumber);
				if(result1 == 0) {
				lectureapi.AddListener(lecturenumber,studentnumber);
				
				int result2 = lectureapi.listCheck(lecturenumber, studentnumber);

				
				if (result2 == 1) {
                	op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 등록 성공");
                	studentidapi.Register(lecturenumber, studentnumber);
                	
					//ListUpdate();

				} else 
					op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 등록 실패");
				}
				else
					op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 가 이미 존재함");
			}
		}
				
	};

	private ActionListener cancleLecture = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				// String number,String subnumber,String name, String grade, String day
				String lecturenumber = infoMap.get("lecturenumber").getText();
				Common user = auth.getUser();
				LectureHistory lech = new LectureHistory();
				String studentnumber = user.getNumber();
				JOptionPane op1 = new JOptionPane();
				
				int result1 = lectureapi.listCheck(lecturenumber, studentnumber);
				if(result1 == 1) {
				lectureapi.DeleteListener(lecturenumber,studentnumber);
				
				int result2 = lectureapi.listCheck(lecturenumber, studentnumber);

				
				if (result2 == 0) {
                	op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 취소 성공");
                	studentidapi.Delete(lecturenumber, studentnumber);
                	
					//ListUpdate();

				} else 
					op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 취소 실패");
				}
				else
					op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 가 존재하지 않음");
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
				String starttime = infoMap.get("starttime").getText();
				String endtime = infoMap.get("endtime").getText();
				
				boolean chk1 = cb1.isSelected();
				boolean chk2 = cb2.isSelected();
				boolean chk3 = cb3.isSelected();
				boolean chk4 = cb4.isSelected();
				boolean chk5 = cb5.isSelected();
				
				boolean[] chk = {chk1, chk2, chk3, chk4, chk5};
				
				boolean result1 = lectureapi.InfoUpdate(lecturenumber, subnumber, subname, lecturename, name, grade, day, starttime, endtime, chk);
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
}	
	


// 안되는거 수정하기