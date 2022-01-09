package com.lec.my.ui.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import colleage_manager.my.api.CommonAPI;
//
//public class InformationPanel extends JPanel {
//	private CommonAPI api = new CommonAPI();
//	
//	public InformationPanel() {
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		JTextField tf1 = new JTextField("���̵�");
//		JTextField tf2 = new JTextField("�̸�");
//		JTextField tf3 = new JTextField("�а�");
//		JTextField tf4 = new JTextField("�������");
//		JTextField tf5 = new JTextField("��ȭ��ȣ");
//		JTextField tf6 = new JTextField("�̸���");
//		JTextField tf7 = new JTextField("���ּ�");
//		JTextField tf8 = new JTextField("��������");
//		
//		JButton j1 = (new JButton("���� ��ȸ"));
//		
//		this.add(tf1);
//		this.add(tf2);
//		this.add(tf3);
//		this.add(tf4);
//		this.add(tf5);
//		this.add(tf6);
//		this.add(tf7);
//		this.add(tf8);
//		
//		
//		add(j1);
//	}
//}
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.persistence.EntityTransaction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import colleage_manager.my.api.CommonAPI;
import colleage_manager.my.api.UserAuth;
import colleage_manager.my.model.Common;
import colleage_manager.my.swing.SwingMain;

@SuppressWarnings("serial")
public class StudentInformationPanel extends JPanel {

	protected static final String LoginTab = null;
	private BorderLayout layout = new BorderLayout();
	private HashMap<String, JTextField> infoMap = new HashMap<String, JTextField>();
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton check;
	private JButton logout;
	private static Image image2;
	JLabel img;

	private JFileChooser jfc = new JFileChooser();
	private JButton jfc_btn1 = new JButton("�̹��� ����");
	private JButton jfc_btn2 = new JButton("�̹��� ����");
	private CommonAPI api = CommonAPI.getInstance();
	private SwingMain frame;
	static int sizeX = 100;
	static int sizeY = 100;
	static int[][] inImage;
	static int[][] outImage;
	static String path = System.getProperty("user.dir") + "/image";

	public StudentInformationPanel(SwingMain frame) {
		this.frame = frame;
		
		img = new JLabel();
		add(img, layout.NORTH);
		jfc_btn1.setPreferredSize(new Dimension(120, 30));
		jfc_btn1.addActionListener(Jfc_open);
		jfc.setFileFilter(new FileNameExtensionFilter("png", "png", "jpg"));
		jfc.setMultiSelectionEnabled(false);// ���� ���� �Ұ�
		
		jfc_btn2.setPreferredSize(new Dimension(120, 30));
		jfc_btn2.addActionListener(Jfc_save);
		
		
		JPanel imagePanelblack = new JPanel();
		imagePanelblack.setPreferredSize(new Dimension(200, 100));
		add(imagePanelblack,layout.NORTH);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(150, 100));
		imagePanel.add(jfc_btn1);
		imagePanel.add(jfc_btn2);
		add(imagePanel,layout.NORTH);
	
		genInfoPair("id", "ID(�й�)");
		genInfoPair("classNumber", "�а��ڵ�");
		genInfoPair("name", "�̸�");
		genInfoPair("phone", "��ȭ��ȣ");
		genInfoPair("email", "�̸���");
		genInfoPair("birth", "�������");
		genInfoPair("pwd", "��й�ȣ");
		genInfoPair("address", "�ּ�");
		System.out.println("Working Directory = " + path);

		saveBtn = (new JButton("�� ���� �����ϱ�"));
		saveBtn.setPreferredSize(new Dimension(200, 30));
		saveBtn.addActionListener(saveListener);
		add(saveBtn);
		loadBtn = (new JButton("�� ���� �ҷ�����"));
		loadBtn.setPreferredSize(new Dimension(200, 30));
		loadBtn.addActionListener(loadListener);
		add(loadBtn);
		logout = (new JButton("�α׾ƿ�"));
		logout.setPreferredSize(new Dimension(200, 30));
		logout.addActionListener(Logout);
		add(logout);
		check = (new JButton("Ȯ��"));
		check.setPreferredSize(new Dimension(200, 30));
		add(check);
	}
		
	

	void loadImage(String path) throws Exception {
		// �̹���
		File inFile;
		inFile = new File(path);

		// ���� ��Ʈ��
		FileInputStream inFileStream;
		inFileStream = new FileInputStream(inFile.getPath());

		// �о�� �̹��� �迭�� ����
		inImage = new int[sizeX][sizeY];
		outImage = new int[sizeX][sizeY];

		for (int i = 0; i < sizeX; i++) {
			for (int k = 0; k < sizeY; k++) {
				inImage[i][k] = inFileStream.read();
				outImage[i][k] = inImage[i][k];
			}
		}
		inFileStream.close();
	}

	private void genInfoPair(String id, String name) {
		JLabel label = new JLabel(name);
		label.setPreferredSize(new Dimension(200, 30));
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(200, 30));
		add(label);
		add(field);

		infoMap.put(id, field);
	}

	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				Common user = auth.getUser();

				String number = infoMap.get("id").getText();
				String classNumber = infoMap.get("classNumber").getText();
				String name = infoMap.get("name").getText();
				String birth = infoMap.get("birth").getText();
				String phoneNumber = infoMap.get("phone").getText();
				String email = infoMap.get("email").getText();
				String birth2 = infoMap.get("birth").getText();
				String address = infoMap.get("address").getText();
				Image pro = image2;

				boolean result1 = api.InfoUpdate(number, classNumber, name, birth, phoneNumber, email, address);
				Common result2 = api.Read(number);
				JOptionPane op1 = new JOptionPane();
				if (result2 != null) {
					op1.showMessageDialog(null, number + " ���� ���� ����");
				} else {
					op1.showMessageDialog(null, number + " ���� ���� ����");
				}
			}
		}
	};
	private ActionListener loadListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			if (auth.isLogin()) {
				Common user = auth.getUser();
				infoMap.get("id").setText(user.getNumber());
				infoMap.get("classNumber").setText(user.getClassNumber());
				infoMap.get("name").setText(user.getName());
				infoMap.get("phone").setText(user.getPhoneNumber());
				infoMap.get("email").setText(user.getEmail());
				infoMap.get("birth").setText(user.getBirth());
				infoMap.get("pwd").setText(user.getPassword());
				infoMap.get("address").setText(user.getAddress());

				Image image1;
				try {
					if(new File(path + "/" + user.getNumber() + ".png").canRead() == true) {
					File file = new File(path + "/" + user.getNumber() + ".png");
					
					image1 = ImageIO.read(file);
					
					System.out.println(image1.toString());
					image2 = image1.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
					ImageIcon imageicon2 = new ImageIcon(image2);
					loadImage(path + "/" + user.getNumber() + ".png");
					img.setIcon(imageicon2);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		}
	};
	public ActionListener Jfc_open = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

				try {
					Image image1 = ImageIO.read(new File(jfc.getSelectedFile().toString()));
					image2 = image1.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
					ImageIcon imageicon2 = new ImageIcon(image2);
					loadImage(jfc.getSelectedFile().toString());
					img.setIcon(imageicon2);
					//add(jfc_btn2);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};

	public ActionListener Jfc_save = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			try {
				saveImage();
			} catch (Exception f) {
				// TODO Auto-generated catch block
				f.printStackTrace();
			}
		}
	};

	static void saveImage() throws Exception {
		UserAuth auth = UserAuth.getInstance();
		if (auth.isLogin()) {
			Common user = auth.getUser();
			// ���� ��Ʈ��

			BufferedImage bufferedimage = convertToBufferedImage(image2);
			ImageIO.write(bufferedimage, "png", new File(path + "/" + user.getNumber() + ".png"));

			JOptionPane.showMessageDialog(null, "���� ���� ����", "���� ����", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public ActionListener Logout = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			UserAuth auth = UserAuth.getInstance();
			auth.logout();
			frame.changeMainTab();
		}
	};

	public static BufferedImage convertToBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		// Create a buffered image with transparency
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics2D = bi.createGraphics();
		graphics2D.drawImage(img, 0, 0, null);
		graphics2D.dispose();

		return bi;
	}

}
