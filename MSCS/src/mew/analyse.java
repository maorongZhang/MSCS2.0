package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class analyse extends JFrame implements ActionListener {
	JLabel label1;
	JButton button1;
	selectmessage sel;
	JTable bg1;
	JScrollPane gdt;
	int x, y;

	analyse() {
		setTitle("������");
		setLayout(null);
		setSize(400, 500);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		label1 = new JLabel("�� �� �� ʱ �� �� �� �� Ʒ �� ��:");//�����û��˴�Ӧ������Ӧ���û���
		label1.setBounds(60, 30, 200, 40);

		button1 = new JButton("����");
		button1.addActionListener(this);
		button1.setBounds(160, 380, 60, 30);

		add(label1);
		add(button1);


		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from shop.product";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				x = rs.getInt("keep");
				y = rs.getInt("number");
				if (x <= 30) {
					String sql1 = "select * from shop.product where keep='" + x + "'";
					sel = new selectmessage(sql1);
					bg1.setModel(sel);
				} else if (y < 100) {
					String sql1 = "select * from shop.product where number='" + y + "'";
					sel = new selectmessage(sql1);
					bg1.setModel(sel);
				}
			}
			this.dispose();
			new manager();
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}