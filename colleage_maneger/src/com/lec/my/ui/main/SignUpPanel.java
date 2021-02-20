package com.lec.my.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.model.Common;

public class SignUpPanel extends JPanel {
private CommonAPI api = new CommonAPI();
	
	public SignUpPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField tf1 = new JTextField("아이디");
		JTextField tf2 = new JTextField("비밀번호");
		
		JButton j1 = (new JButton("회원가입"));
		
		
		this.add(tf1);
		this.add(tf2);

		
		
		add(j1);
		
		
		
		
		j1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String number = tf1.getText();
            	
            	String password = tf2.getText();
            	
    			if (number == "" || password == "" )
    				return;
    			boolean result = api.Register(number, password);
    			JOptionPane op1 = new JOptionPane();
    			if (result == true) {
                	op1.showMessageDialog(null, number + " 회원가입 성공");
                	
    			} else {
    				op1.showMessageDialog(null, number + " 회원가입 실패");   
    			}
            	     
            }
        });
		
		
		
        
	}
}

