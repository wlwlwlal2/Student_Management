package colleage_manager.my.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import com.lec.my.ui.main.*;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.LectureAPI;
import colleage_manager.my.api.LectureHistoryAPI;
import colleage_manager.my.api.SubjectAPI;
import colleage_manager.my.model.Subject;;


public class SwingMain extends JFrame {
	/**
	 * 
	 */
	
//	// Database API 코드 이동 필요
//	private CommonAPI capi = CommonAPI.getInstance();
//	private SubjectAPI api = SubjectAPI.getInstance();
//	private LectureAPI lapi = LectureAPI.getInstance();
	
	private static final long serialVersionUID = 1L;
	private static SwingMain instance = new SwingMain();
	private CardLayout layout = new CardLayout();
	private JTabbedPane mainTab;
	private JTabbedPane InfoTab;
	private JTabbedPane studentTab;
	private JTabbedPane professorTab;
	private JTabbedPane managerTab;
	
	private final static String MainTab = "MAIN";
	private final static String StudentTab = "STUDENT";
	private final static String ProfessorTab = "PROFESSOR";
	private final static String ManagerTab = "MANAGER";
	
	private void initDB() {
		// Database API 코드 이동 필요
		CommonAPI capi = CommonAPI.getInstance();
		SubjectAPI api = SubjectAPI.getInstance();
		LectureAPI lapi = LectureAPI.getInstance();
		LectureHistoryAPI lechi = LectureHistoryAPI.getInstance();
		
		
		capi.Register("학생" ,"11", "11");
		capi.Register("학생" ,"12", "11");
		capi.Register("학생" ,"13", "11");
		capi.Register("관리자" ,"00", "00");
		
		boolean[] boo1 = {true, false, false, false, false};
		boolean[] boo2 = {false, true, false, false, false};
		boolean[] boo3 = {false, false, true, false, false};
		
		api.Register("001","과목1", "공ㅇㅇ","전필","50");
		api.Register("002","과목2", "공ㅇㅇ","전필","50");
		api.Register("003","과목3", "공ㅇㅇ","전필","50");
		api.Register("004","과목4", "공ㅇㅇ","전필","50");
		api.Register("005","과목5", "공ㅇㅇ","전필","50");
		lapi.Register("001",api.Read("005"),"과목5","강의1","이ㅇㅇ","60","60","12:30","14:30", boo1);
		lapi.Register("002",api.Read("004"),"과목4","강의2","김ㅇㅇ","70","70","13:30","15:30", boo2);
		lapi.Register("003",api.Read("003"),"과목3","강의3","박ㅇㅇ","80","80","15:30","16:30", boo3);
		lapi.AddListener("001","11");
		lechi.Register("001", "11");
		lapi.AddListener("001","12");
		lechi.Register("001", "12");
		
		
		
	}
	
	@SuppressWarnings("unused")
	public SwingMain() {
		
		initDB();
//		List<Subject> sublist = api.readAll();
		this.setResizable(false);
		setLocation(200, 400);
		setPreferredSize(new Dimension(500, 600));
	
		mainTab = new MainTab(this);
		studentTab = new InfoTab(this);
		professorTab = new InfoTab2(this);
		managerTab = new InfoTab3(this);
		
		Container pan = getContentPane();
		pan.setLayout(layout);
		pan.add(MainTab, mainTab);
		pan.add(StudentTab, studentTab);
		pan.add(ProfessorTab, professorTab);
		pan.add(ManagerTab, managerTab);
		
		LoginPanel loginFrame = new LoginPanel(this);
		SignUpPanel signUpFrame = new SignUpPanel();
		
		this.pack();
//		mainTab = new MainTab(this);
		
		this.setVisible(true);
		
    }
		
	
	private void start() {
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		instance.start();
	}
	
	public void changeStudentTab() {;
		layout.show(this.getContentPane(), StudentTab);
	}
	public void changeProfessorTab() {;
		
		layout.show(this.getContentPane(), ProfessorTab);
	}
	public void changeManagerTab() {;

		layout.show(this.getContentPane(), ManagerTab);
	}
	
	public void changeMainTab() {;
	
	layout.show(this.getContentPane(), MainTab);
}
}