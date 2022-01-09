package com.lec.my.ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityExistsException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.model.Common;

public class SignUpPanel extends JPanel {

	private CommonAPI api = CommonAPI.getInstance();

	private String[] role = { "�л�", "����" };

	public SignUpPanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField tf1 = new JTextField("���̵�");
		JTextField tf2 = new JTextField("��й�ȣ");
		JComboBox cb1 = new JComboBox(role);
		JButton j1 = (new JButton("ȸ������"));

		this.add(cb1);
		this.add(tf1);
		this.add(tf2);
		add(j1);

		j1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = cb1.getSelectedItem().toString();
				String number = tf1.getText();
				String password = tf2.getText();

				if (role == "" || number == "" || password == "")
					return;

				JOptionPane op1 = new JOptionPane();
				try {

					boolean result = api.Register(role, number, password);
					if (result == true) {
						op1.showMessageDialog(null, number + " " + role + " ȸ������ ����");
					}

				} catch (EntityExistsException e1) {
					op1.showMessageDialog(null, number + " ȸ������ ����, �̹� �����ϴ� ���̵�");
				}

			}
		});
	}
}
