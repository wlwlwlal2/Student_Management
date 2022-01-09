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
	private CommonAPI api = CommonAPI.getInstance();
	private SwingMain frame;
	private String role;
	private UserAuth auth;

	public LoginPanel(SwingMain frame) {
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField tf1 = new JTextField("���̵�");
		JTextField tf2 = new JTextField("��й�ȣ");
		JButton j2 = (new JButton("�α���"));
		this.add(tf1);
		this.add(tf2);
		add(j2);
		auth = UserAuth.getInstance();

		j2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = tf1.getText();
				String password = tf2.getText();
				Common result = api.login(number, password);

				if (number == "" || password == "")
					return;

				JOptionPane op1 = new JOptionPane();
				if (result != null) {
					auth.login(result);
					role = result.getRole();

					if (role.equals("�л�"))
						frame.changeStudentTab();
					else if (role.equals("����"))
						frame.changeProfessorTab();
					else if (role.equals("������"))
						frame.changeManagerTab();

					op1.showMessageDialog(null, number + " �α��� ����");
				} else {
					op1.showMessageDialog(null, number + " �α��� ����");
				}
			}
		});
	}
}