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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.model.Subject;
import colleage_manager.my.swing.SwingMain;



@SuppressWarnings("serial")
public class SubjectInformationPanel extends JPanel {
	
	protected static final String LoginTab = null;
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton updateBtn;
	private JButton check;
	private JButton addBtn;
	private JButton deleteBtn;
	private SubjectAPI api = SubjectAPI.getInstance();
	private CardLayout layout = new CardLayout();
	private SwingMain frame;
	
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	final JList ml = new JList<>(dlm);
	
	
	
	public SubjectInformationPanel(SwingMain frame) {	
		this.frame = frame;
		genInfoPair("subnumber", "과목코드");
		genInfoPair("subname", "과목명");
		genInfoPair("professor", "담당 교수");
		genInfoPair("lecturetype", "과목 유형");
		genInfoPair("needday", "요구 출석일");
		
		addBtn = (new JButton("과목 추가하기"));
		addBtn.setPreferredSize(new Dimension(200, 30));
		addBtn.addActionListener(addListener);
		add(addBtn);
		
		deleteBtn = (new JButton("과목 삭제하기"));
		deleteBtn.setPreferredSize(new Dimension(200, 30));
		deleteBtn.addActionListener(deleteListener);
		add(deleteBtn);
		
		updateBtn = (new JButton("과목 정보 수정하기"));
		updateBtn.setPreferredSize(new Dimension(200, 30));
		updateBtn.addActionListener(saveListener);
		add(updateBtn);
		
		check = (new JButton("확인"));
		check.setPreferredSize(new Dimension(200, 30));
		//loadBtn.addActionListener(loadListener);
		add(check);
		ml.addListSelectionListener(change);
		ml.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ml.setPreferredSize(new Dimension(400, 300));
		add(ml);
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
			 ListUpdate();
		}
		
		private void ListUpdate() {
			
			List<Subject> mylist = api.readAll();
			
			dlm.clear();
			for(int i = 0; i < mylist.size(); i++) {
				Subject subject = mylist.get(i);
				String data = subject.getSubName() + "(" + subject.getSubNumber() + ")";
				 
				 dlm.addElement(data);
			}
		
			
	//	((ListSelectionModel) list).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
	}
	public ListSelectionListener change = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				int row = ml.getSelectedIndex();
				String val1 = (String) ml.getSelectedValue();
				
				if(val1 != null) {
				String val2 = val1.substring(val1.indexOf("(") + 1 ,val1.indexOf(")"));
				
				if (row != -1) {
					Subject user = api.getSubject(val2);
					infoMap.get("subnumber").setText(user.getSubNumber());
					infoMap.get("subname").setText(user.getSubName());
					infoMap.get("professor").setText(user.getProfessor());
					infoMap.get("lecturetype").setText(user.getLectureType());
					infoMap.get("needday").setText(user.getNeedDay());
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
	
	private ActionListener addListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				
				String subnumber = infoMap.get("subnumber").getText();
				String subname = infoMap.get("subname").getText();
				String professor = infoMap.get("professor").getText();
				String lecturetyoe = infoMap.get("lecturetype").getText();
				String needday = infoMap.get("needday").getText();
				
				api.Register(subnumber,subname, professor,lecturetyoe,needday);
				Subject result2 = api.Read(subnumber);
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
                //	op1.showMessageDialog(null, subnumber + " 과목 추가 성공");
                	 ListUpdate();
                	 
                	
    			} else {
    				op1.showMessageDialog(null, subnumber + " 과목 추가 실패");   
    			}
				
			}
		}
	};
	private ActionListener deleteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				
				String subnumber = infoMap.get("subnumber").getText();
				
				api.Delete(subnumber);
				Subject result2 = api.Read(subnumber);
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
				
				String subnumber = infoMap.get("subnumber").getText();
				String subname = infoMap.get("subname").getText();
				String professor = infoMap.get("professor").getText();
				String lecturetype = infoMap.get("lecturetype").getText();
				String needday = infoMap.get("needday").getText();
				
				
				
				boolean result1 = api.InfoUpdate(subnumber,subname, professor, lecturetype, needday);
				Subject result2 = api.Read(subnumber);
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
                	op1.showMessageDialog(null, subnumber + " 정보 수정 성공");
                	ListUpdate();
    			} else {
    				op1.showMessageDialog(null, subnumber + " 정보 수정 실패");   
    			}
				
			}
		}
	};
	
//	public ActionListener Logout = new ActionListener() {
//	
//		public void actionPerformed(ActionEvent e) {
//			UserAuth auth = UserAuth.getInstance();
//			auth.logout();
//			SwingMain ins = new SwingMain();
//			ins.setVisible(true);
//			frame.dispose();
//		//layout.show(this.getContentPane(), MainTab);
//			
//	
//		}
		
		
		
		
		
	};
