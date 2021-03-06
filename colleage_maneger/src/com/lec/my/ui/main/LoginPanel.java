package com.lec.my.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

public class LoginPanel extends JPanel {
	private CommonAPI api = new CommonAPI();
	private SwingMain frame;
	
	
	
	private UserAuth auth;
	
	public LoginPanel(SwingMain frame) {
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField tf1 = new JTextField("아이디");
		JTextField tf2 = new JTextField("비밀번호");
		
		
		JButton j2 = (new JButton("로그인"));
		
		
		this.add(tf1);
		this.add(tf2);
		
		add(j2);
		
		auth = UserAuth.getInstance();
		
		
		j2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String number = tf1.getText();
            	
            	String password = tf2.getText();
            	
    			if (number == "" || password == "")
    				return;
    			
    			
    			Common result = api.login(number, password);
    			
    			JOptionPane op1 = new JOptionPane();
    			if (result != null) {
    				
    				auth.login(result);
    				frame.changeStudentTab();
    				
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
		
	}
}