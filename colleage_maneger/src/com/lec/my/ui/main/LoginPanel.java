package com.lec.my.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colleage_manager.my.api.CommonAPI;

public class LoginPanel extends JPanel {
	private CommonAPI api = new CommonAPI();
	
	public LoginPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField tf1 = new JTextField("아이디");
		JTextField tf2 = new JTextField("비밀번호");
		
		JButton j1 = (new JButton("회원가입"));
		JButton j2 = (new JButton("로그인"));
		JButton j3 = (new JButton("3"));
		JButton j4 = (new JButton("4"));
		JButton j5 = (new JButton("5"));
		
		this.add(tf1);
		this.add(tf2);
		
		
		add(j1);
		add(j2);
		add(j3);
		add(j4);
		add(j5);
		
		
		
		j1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String number = tf1.getText();
            	
            	String password = tf2.getText();
            	
    			if (number == "" || password == "")
    				return;
    			int result = api.Register(number, password);
    			JOptionPane op1 = new JOptionPane();
    			if (result == 0) {
                	op1.showMessageDialog(null, number + " 회원가입 성공");   
    			} else {
    				op1.showMessageDialog(null, number + " 회원가입 실패");   
    			}
            	     
            }
        });
		
		j2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String number = tf1.getText();
            	
            	String password = tf2.getText();
            	
    			if (number == "" || password == "")
    				return;
    			int result = api.login(number, password);
    			JOptionPane op1 = new JOptionPane();
    			if (result == 0) {
                	op1.showMessageDialog(null, number + " 로그인 성공");   
    			} else {
    				op1.showMessageDialog(null, number + " 로그인 실패");   
    			}
            	     
            }
        });
		
//		j2.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//            	String passwordInput = tf2.getText();
//                System.out.println(passwordInput);             
//            }
//        });
		j3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String idInput = tf1.getText();
            	String passwordInput = tf2.getText();
            	JOptionPane op1 = new JOptionPane();
            	op1.showMessageDialog(null,"Id : " + idInput + " , Passowrd : " + passwordInput);      
            }
        });
	}
}