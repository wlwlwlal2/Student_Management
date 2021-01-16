package colleage_manager.my.swing;

import java.awt.BorderLayout;
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
import com.lec.my.ui.main.LoginPanel
public class SwingMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SwingMain instance = new SwingMain();

	@SuppressWarnings("unused")
	public SwingMain() {
		setLocation(200, 400);
		setPreferredSize(new Dimension(400, 500));
		
		LoginPanel loginFrame = new LoginPanel();
		this.add(loginFrame, BorderLayout.CENTER);	
		this.pack();
		this.setVisible(true);
	}
	
	private void start() {
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		instance.start();
	}
}