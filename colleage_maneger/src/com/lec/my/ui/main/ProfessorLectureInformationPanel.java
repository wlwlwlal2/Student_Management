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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JButton deleteBtn;
	private JButton check;
	private JButton logout;
	private JCheckBox cb1, cb2, cb3, cb4, cb5;
	private CommonAPI commonapi = CommonAPI.getInstance();;
	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private LectureHistoryAPI studentidapi = LectureHistoryAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	JScrollPane sc;
	JTable table;
	String[] dataDefault = { "안바뀌면 이거나옴" };

	public JComboBox<Subject> strCombo = new JComboBox(dataDefault);
	private int selectedIdx = 0;

	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);

	public ProfessorLectureInformationPanel(SwingMain frame) {
		this.frame = frame;
		genInfoPair("lecturenumber", "강의코드");
		genInfoPair("subnumber", "과목코드");
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
		ml.setPreferredSize(new Dimension(400, 150));
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
				
				if (val1 != null) {
					String val2 = val1;
					
					if (row != -1) {
						Lecture user = lectureapi.getLecture(val2);
						Subject sub = user.getSubject();

						infoMap.get("lecturenumber").setText(user.getLectureNumber());
						infoMap.get("subnumber").setText(sub.getSubNumber());

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
				
				lectureapi.Register(lecturenumber, subjectapi.getSubject(subnumber), subname, lecturename, name, grade,
						day,starttime,endtime,chk);
				Lecture result2 = lectureapi.Read(subnumber);

				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
					// op1.showMessageDialog(null, lecturename + " 강의 추가 성공");
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
				String starttime = infoMap.get("starttime").getText();
				String endtime = infoMap.get("endtime").getText();
				
				boolean chk1 = cb1.isSelected();
				boolean chk2 = cb2.isSelected();
				boolean chk3 = cb3.isSelected();
				boolean chk4 = cb4.isSelected();
				boolean chk5 = cb5.isSelected();

				boolean[] chk = {chk1, chk2, chk3, chk4, chk5};
				boolean result1 = lectureapi.InfoUpdate(lecturenumber, subnumber, subname, lecturename, name, grade,
						day, starttime, endtime, chk);
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
			setSize(500, 600);
			setResizable(false);
			setVisible(true);

			String lecturenumber = infoMap.get("lecturenumber").getText();
			List<String> mylist = lectureapi.GetListener(lecturenumber);
			String[][] arr = new String[mylist.size()][7];
			
			for (int i = 0; i < mylist.size(); i++) {
				Common user = commonapi.getCommon(mylist.get(i));
				LectureHistory student = studentidapi.Read(lecturenumber, mylist.get(i));
				arr[i][0] = user.getName();
				arr[i][1] = user.getNumber();
				arr[i][2] = student.getGrade();
				arr[i][3] = student.getRealGrade();
				arr[i][4] = student.getMaxAttendance();
				arr[i][5] = student.getAttendance();
				arr[i][6] = student.getAbsence();
			}
			
			String[] label = { "이름", "아이디", "점수", "반영점수", "출석", "지각", "결석" };
			DefaultTableModel model = new DefaultTableModel(arr, label);
		
			table = new JTable(model);
			sc = new JScrollPane(table);
			add(sc);

			ActionListener gradejoinListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < mylist.size(); i++) {
						Common user = commonapi.getCommon(mylist.get(i));
						LectureHistory student = studentidapi.Read(lecturenumber, user.getNumber());
						arr[i][0] = user.getName();
						arr[i][1] = user.getNumber();
						arr[i][2] = student.getGrade();
						arr[i][3] = student.getRealGrade();
						arr[i][4] = student.getMaxAttendance();
						arr[i][5] = student.getAttendance();
						arr[i][6] = student.getAbsence();
						table.setValueAt(arr[i][0], i, 0);
						table.setValueAt(arr[i][1], i, 1);
						table.setValueAt(arr[i][2], i, 2);
						table.setValueAt(arr[i][3], i, 3);
						table.setValueAt(arr[i][4], i, 4);
						table.setValueAt(arr[i][5], i, 5);
						table.setValueAt(arr[i][6], i, 6);
					}
				}
			};

			ActionListener gradeupdateListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < mylist.size(); i++) {
						String lecturenumber = infoMap.get("lecturenumber").getText();
						Common user = commonapi.getCommon(mylist.get(i));
						LectureHistory student = studentidapi.Read(lecturenumber, user.getNumber());

						sc.repaint();
						arr[i][2] = (String) table.getValueAt(i, 2);
						arr[i][3] = (String) table.getValueAt(i, 3);
						arr[i][4] = (String) table.getValueAt(i, 4);
						arr[i][5] = (String) table.getValueAt(i, 5);
						arr[i][6] = (String) table.getValueAt(i, 6);
						System.out.println(student.getLecture().getLectureNumber());
						System.out.println(arr[i][2]);
						studentidapi.Update(lecturenumber,user.getNumber(),arr[i][2],arr[i][3],arr[i][4],arr[i][5],arr[i][6]);
						System.out.println(student.getGrade());

					}
				}
			};

			ActionListener lecturecancleListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						String lecturenumber = infoMap.get("lecturenumber").getText();
						int i = table.getSelectedRow();
						Common user = commonapi.getCommon(mylist.get(i));
						JOptionPane op1 = new JOptionPane();
						LectureHistory student = studentidapi.Read(lecturenumber, user.getNumber());
						String studentnumber = student.getStudent().getNumber();
						
					
						sc.repaint();
						String number = (String) table.getValueAt(i, 2);
						
						int result1 = lectureapi.listCheck(lecturenumber, studentnumber);
						if(result1 == 1) {
						lectureapi.DeleteListener(lecturenumber,studentnumber);
						int result2 = lectureapi.listCheck(lecturenumber, studentnumber);

						if (result2 == 0) {
		                	op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 취소 성공");
		                	studentidapi.Delete(lecturenumber, studentnumber);

						} else 
							op1.showMessageDialog(null, studentnumber + " " + lecturenumber + " 강의 취소 실패");
						}
					}
			};
			
			joinBtn = (new JButton("성적 조회"));
			joinBtn.setPreferredSize(new Dimension(150, 30));
			joinBtn.addActionListener(gradejoinListener);
			add(joinBtn);

			updateBtn = (new JButton("성적 수정"));
			updateBtn.setPreferredSize(new Dimension(150, 30));
			updateBtn.addActionListener(gradeupdateListener);
			add(updateBtn);
			
			deleteBtn = (new JButton("학생 수강 취소"));
			deleteBtn.setPreferredSize(new Dimension(150, 30));
			deleteBtn.addActionListener(lecturecancleListener);
			add(deleteBtn);
		}
	}
}
