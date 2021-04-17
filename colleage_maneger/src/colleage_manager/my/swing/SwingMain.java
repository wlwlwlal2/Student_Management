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
import colleage_manager.my.api.SubjectAPI;;


public class SwingMain extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static SwingMain instance = new SwingMain();
	private CardLayout layout = new CardLayout();
	private SubjectAPI api = new SubjectAPI();
	
	private JTabbedPane mainTab;
	private JTabbedPane InfoTab;
	private JTabbedPane studentTab;
	private JTabbedPane professorTab;

	private final static String MainTab = "MAIN";
	private final static String StudentTab = "STUDENT";
	private final static String ProfessorTab = "PROFESSOR";
	
	@SuppressWarnings("unused")
	public SwingMain() {
		setLocation(200, 400);
		setPreferredSize(new Dimension(500, 600));
		
		mainTab = new MainTab(this);
		studentTab = new InfoTab(this);
		professorTab = new InfoTab2(this);
		
		Container pan = getContentPane();
		pan.setLayout(layout);
		pan.add(MainTab, mainTab);
		pan.add(StudentTab, studentTab);
		pan.add(ProfessorTab, professorTab);
		
		
		LoginPanel loginFrame = new LoginPanel(this);
		SignUpPanel signUpFrame = new SignUpPanel();
			
		
		
		this.pack();

		mainTab = new MainTab(this);
		
		
		
		this.setVisible(true);
		
		api.Register("001","과목1", "공ㅇㅇ","전필","50");
		api.Register("002","과목2", "공ㅇㅇ","전필","50");
		api.Register("003","과목3", "공ㅇㅇ","전필","50");
		api.Register("004","과목4", "공ㅇㅇ","전필","50");
		api.Register("005","과목5", "공ㅇㅇ","전필","50");
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
	
}