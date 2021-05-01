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
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.Lecture;
import colleage_manager.my.model.Subject;
import colleage_manager.my.swing.SwingMain;



@SuppressWarnings("serial")
public class LectureInformationPanel extends JPanel {
	
	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private HashMap<String, String> infoMap2 = new HashMap<String, String>();
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton check;
	private JButton logout;
	private CommonAPI commonapi = CommonAPI.getInstance();;
	private SubjectAPI subjectapi = SubjectAPI.getInstance();
	private LectureAPI lectureapi = LectureAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	String[] dataDefault = {"안바뀌면 이거나옴"};
	
	public JComboBox<String> strCombo = new JComboBox(dataDefault);
	private String selectedComboObj = "과목을 선택하세요.";
	
	
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);
	
	
	public LectureInformationPanel(SwingMain frame) {	
		this.frame = frame;
		genInfoPair("subnumber", "강의코드");
		//genInfoPair("number", "교수ID");
		JLabel label = new JLabel("과목명");
		label.setPreferredSize(new Dimension(200, 30));
		add(label);
		strCombo.setPreferredSize(new Dimension(200, 30));
		add(strCombo);
		infoMap2.put("subname", (String) strCombo.getSelectedItem());
		
		genInfoPair("lecturename", "강의명");
		genInfoPair("name", "교수 이름");
		genInfoPair("grade", "점수");
		//genInfoPair("gradeint", "이메일");
		//genInfoPair("realgrade", "생년월일");
		genInfoPair("day", "요구 출석일");
		//genInfoPair("late", "지각");
		//genInfoPair("absent", "결석");
		
		
		saveBtn = (new JButton("강의 개설"));
		saveBtn.setPreferredSize(new Dimension(200, 30));
		saveBtn.addActionListener(addListener);
		add(saveBtn);
		loadBtn = (new JButton("강의 제거"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(deleteListener);
		add(loadBtn);
		logout = (new JButton("버튼 3"));
		logout.setPreferredSize(new Dimension(200, 30));
		//logout.addActionListener(Logout);
		add(logout);
		check = (new JButton("버튼 4"));
		check.setPreferredSize(new Dimension(200, 30));
		//loadBtn.addActionListener(loadListener);
		add(check);
		ml.addListSelectionListener(change);
		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 300));
		add(ml);
		
		setComboBox();
		strCombo.setSelectedItem("과목을 선택하세요.");
		strCombo.addActionListener(subJComboListener);
	}
	
	private void setComboBox() {
		List<Subject> sublist = subjectapi.readAll();

		strCombo.removeAllItems();
		strCombo.addItem("과목을 선택하세요.");
		for(int i = 0; i < sublist.size(); i++ ) {
			Subject subject = sublist.get(i);
			String subname = subject.getSubName();
			strCombo.addItem(subname);
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<Lecture> mylist = lectureapi.readAll();
		

		dlm.clear();
		for(int i = 0; i < mylist.size(); i++) {
			Lecture lecture = mylist.get(i);
			String data = lecture.getSubNumber();
		
	       
			 dlm.addElement(data);
			 
		}
//		List<Subject> sublist = subjectapi.readAll();
//		
//		String[] subnameArray = new String[sublist.size()];
		
		
//		if(strCombo.getSelectedItem() == "안바뀌면 이거나옴") {
//			strCombo.removeAllItems();
//			for(int i = 0; i < sublist.size(); i++ ) {
//				Subject subject = sublist.get(i);
//				String subname = subject.getSubName();
//				subnameArray[i] = subname;
//				strCombo.addItem(subname);
//			}
//		}
		
		
		System.out.println("call on pain()");
	}
	public ListSelectionListener change = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				int row = ml.getSelectedIndex();
				String val1 = (String) ml.getSelectedValue();
				
				if(val1 != null) {
				String val2 = val1;
				
				if (row != -1) {
					Lecture user = lectureapi.getLecture(val2);
					infoMap.get("subnumber").setText(user.getSubNumber());
					
					strCombo.setSelectedItem(user.getName());
					
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
	
	private ActionListener subJComboListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!strCombo.getSelectedItem().equals("과목을 선택하세요.")) {
				selectedComboObj = (String) strCombo.getSelectedItem();
				
				setComboBox();
				strCombo.setSelectedItem(selectedComboObj);
				repaint();
			}
		}
	};
	
	private ActionListener addListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				//String number,String subnumber,String name, String grade, String day
				String subnumber = infoMap.get("subnumber").getText();	
				String subname = infoMap2.get("subname");
				String lecturename = infoMap.get("lecturename").getText();
				String name = infoMap.get("name").getText();
				String grade = infoMap.get("grade").getText();
				String day = infoMap.get("day").getText();
			
				
				lectureapi.Register(subnumber,subname, lecturename,name,grade,day);
				Lecture result2 = lectureapi.Read(subnumber);
				
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
                	op1.showMessageDialog(null, lecturename + " 강의 추가 성공");
                	
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
                	
    			} else {
    				op1.showMessageDialog(null, subnumber + " 과목 추가 실패");   
    			}
				
			}
		}
	};
	

	};
