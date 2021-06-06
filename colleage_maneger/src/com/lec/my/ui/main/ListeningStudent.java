//package com.lec.my.ui.main;
//
//import java.awt.Dimension;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import colleage_manager.my.model.Common;
//import colleage_manager.my.model.Student;
//
//public class ListeningStudent extends JFrame{
//	
//	ListeningStudent() {
//	setTitle("수강 학생 확인");
//    
//    JPanel NewWindowContainer = new JPanel();
//    setContentPane(NewWindowContainer);
//    
//    setSize(500,600);
//    setResizable(false);
//    setVisible(true);
//    
//    String lecturenumber = infoMap.get("lecturenumber").getText();
//    List<String> mylist = lectureapi.GetListener(lecturenumber);
//    
//	String title[]={"상품명","수량","단가","총금액"};
//	String[][] arr = new String[mylist.size()][7];
//	String[] label = {"이름","아이디","점수","반영점수","출석","지각","결석"};
//	
//	DefaultTableModel model = new DefaultTableModel(arr,label);
//	JTable table = new JTable(model);
//	JScrollPane sc = new JScrollPane(table);
//	add(sc);
//	DefaultTableModel m = (DefaultTableModel)table.getModel();
//	
//	joinBtn = (new JButton("성적 조회"));
//	joinBtn.setPreferredSize(new Dimension(150, 30));
//	//saveBtn.addActionListener(addListener);
//	add(joinBtn);
//	
//	updateBtn = (new JButton("성적 수정"));
//	updateBtn.setPreferredSize(new Dimension(150, 30));
//	//saveBtn.addActionListener(addListener);
//	add(updateBtn);
//	
//}
//
//void tableupdate() {
//	for (int i = 0; i < mylist.size(); i++) {
//		String studentNumber = mylist.get(i);
//		Common com = commonapi.Read(studentNumber);
//		Student stud = studentapi.Read(studentNumber);
//		
//		String[] data = {com.getName(), stud.getNumber(), stud.getGrade(), stud.getRealGrade(), stud.getAttendancee(), stud.getLateness(), stud.getAbsence()};
//		m.insertRow(i, data);
//		table.updateUI();
//	}
//}
//}
