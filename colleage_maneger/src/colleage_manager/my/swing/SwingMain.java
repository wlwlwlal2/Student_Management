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
import javax.swing.JTextField;

public class SwingMain extends JFrame {
	SwingMain()
	{
		setTitle("테스트");
		setSize(1200,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		
		
		JTextField tf1 = new JTextField("아이디 입력");
		JTextField tf2 = new JTextField("비밀번호 입력");
		
		
		JButton j1 = (new JButton("1"));
		JButton j2 = (new JButton("2"));
		JButton j3 = (new JButton("3"));
		JButton j4 = (new JButton("4"));
		JButton j5 = (new JButton("5"));
		
		c.add(tf1);
		c.add(tf2);
		
		c.add(Box.createHorizontalStrut(10));
		add(j1);
		
		c.add(Box.createHorizontalStrut(10));
		add(j2);
			
		c.add(Box.createHorizontalStrut(10));
		add(j3);
		
		c.add(Box.createHorizontalStrut(10));
		add(j4);
		
		c.add(Box.createHorizontalStrut(10));
		add(j5);
		
		setVisible(true);
		
		j1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String idInput = tf1.getText();
                System.out.println(idInput);             
            }
        });
		j2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String passwordInput = tf2.getText();
                System.out.println(passwordInput);             
            }
        });
		j3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String idInput = tf1.getText();
            	String passwordInput = tf2.getText();
            	JOptionPane op1 = new JOptionPane();
            	op1.showMessageDialog(null,"Id : " + idInput + " , Passowrd : " + passwordInput);      
            }
        });
		
	}

	public static void main(String[] args) {
		new SwingMain();
	}
}