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
import com.lec.my.ui.main.*;;


public class SwingMain extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static SwingMain instance = new SwingMain();
	private CardLayout layout = new CardLayout();
	
	private JTabbedPane mainTab;
	private JTabbedPane InfoTab;
	private JTabbedPane studentTab;

	private final static String MainTab = "MAIN";
	private final static String StudentTab = "STUDENT";
	
	@SuppressWarnings("unused")
	public SwingMain() {
		setLocation(200, 400);
		setPreferredSize(new Dimension(400, 600));
		
		mainTab = new MainTab(this);
		studentTab = new StudentTab(this);
		
		Container pan = getContentPane();
		pan.setLayout(layout);
		pan.add(MainTab, mainTab);
		pan.add(StudentTab, studentTab);
		
		LoginPanel loginFrame = new LoginPanel(this);
		SignUpPanel signUpFrame = new SignUpPanel();
		// this.add(loginFrame, BorderLayout.CENTER);	
		
		
		this.pack();

		mainTab = new MainTab(this);
		
		
		
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
}