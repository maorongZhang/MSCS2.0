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
		setTitle("购入商品");
		setLayout(null);
		setSize(400,500);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		label1=new JLabel("商 品 名:");//提醒用户此处应输入相应的用户名
		label1.setBounds(60,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(130, 40, 150, 20);//提醒用户此处输入相应的密码信息
	
		label2=new JLabel("商 品 数 量:");
		label2.setBounds(60,80, 70, 40);
		text2=new JPasswordField();
		text2.setBounds(130, 90, 150, 20);
		
		label3=new JLabel("商 品 单 价:");
		label3.setBounds(60,130, 70, 40);
		text3=new JPasswordField();
		text3.setBounds(130, 140, 150, 20);
		
		label4=new JLabel("商 品 折 扣:");
		label4.setBounds(60,180, 70, 40);
		text4=new JPasswordField();
		text4.setBounds(130, 190, 150, 20);
	
		label5=new JLabel("生 产 日 期:");
		label5.setBounds(60,230, 70, 40);
		text5=new JPasswordField();
		text5.setBounds(130, 240, 150, 20);
		
		label6=new JLabel("保 质 期 :");
		label6.setBounds(60,280, 70, 40);
		text6=new JPasswordField();
		text6.setBounds(130, 290, 150, 20);
	
		button1=new JButton("确认");
		button1.addActionListener(this);
		button1.setBounds(120, 360, 60, 30);
		button2=new JButton("返回");
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
		
	if(e.getActionCommand().equals("确认")) {
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
			Object[] options = {"确认", "取消"};
			int m = JOptionPane.showOptionDialog(null, "确认购入商品", "库存", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (m == 0) {
				JOptionPane.showMessageDialog(null, "购入成功");
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
		if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new buy();
	}
	}

}
