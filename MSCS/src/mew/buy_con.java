package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class buy_con extends JFrame implements ActionListener{
	JLabel label1,label2,label3,label4,label5,label6;
	JTextField text1,text2,text3,text4,text5,text6;
	JButton button1,button2;
	String ID;
	buy_con(String x)
	{
		ID = x;
		setTitle("������Ʒ");
		setLayout(null);
		setSize(400,500);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		label1=new JLabel("�� Ʒ ��:");//�����û��˴�Ӧ������Ӧ���û���
		label1.setBounds(60,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(130, 40, 150, 20);//�����û��˴�������Ӧ��������Ϣ
	
		label2=new JLabel("�� Ʒ �� ��:");
		label2.setBounds(60,80, 70, 40);
		text2=new JPasswordField();
		text2.setBounds(130, 90, 150, 20);
		
		label3=new JLabel("�� Ʒ �� ��:");
		label3.setBounds(60,130, 70, 40);
		text3=new JPasswordField();
		text3.setBounds(130, 140, 150, 20);
		
		label4=new JLabel("�� Ʒ �� ��:");
		label4.setBounds(60,180, 70, 40);
		text4=new JPasswordField();
		text4.setBounds(130, 190, 150, 20);
	
		label5=new JLabel("�� �� �� ��:");
		label5.setBounds(60,230, 70, 40);
		text5=new JPasswordField();
		text5.setBounds(130, 240, 150, 20);
		
		label6=new JLabel("�� �� �� :");
		label6.setBounds(60,280, 70, 40);
		text6=new JPasswordField();
		text6.setBounds(130, 290, 150, 20);
	
		button1=new JButton("ȷ��");
		button1.addActionListener(this);
		button1.setBounds(120, 360, 60, 30);
		button2=new JButton("����");
		button2.addActionListener(this);
		button2.setBounds(220, 360, 60, 30);
		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(label4);
		add(text4);
		add(label5);
		add(text5);
		add(label6);
		add(text6);

		add(button1);
		add(button2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	if(e.getActionCommand().equals("ȷ��")) {
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement("Update shop.product set name=?,number=?,discount=?,price=?,date=?,keep=? where ID = ?");
			ps.setString(1, text1.getText());
			ps.setString(2, text2.getText());
			ps.setString(3, text4.getText());
			ps.setString(4, text3.getText());
			ps.setString(5, text5.getText());
			ps.setString(6, text6.getText());
			ps.setString(7, ID);
			
			//System.out.println(text1.getText())
			ps.executeUpdate();
			Object[] options = {"ȷ��", "ȡ��"};
			int m = JOptionPane.showOptionDialog(null, "ȷ�Ϲ�����Ʒ", "���", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (m == 0) {
				JOptionPane.showMessageDialog(null, "����ɹ�");
				this.dispose();
				new stock();
			}
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR:" + e2);
			}
		}
	}
		if(e.getActionCommand().equals("����"))
	{
		this.dispose();
		new buy();
	}
	}

}
