package mew;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;


import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.sql.*;


public class login extends JFrame implements ActionListener{
	JLabel label1,label2,label3;
	JTextField text1,text2;
	JRadioButton jrb1, jrb2;
	JButton button1,button2;
	Connection dbConn;
	String sql;
	//PreparedStatement pstmt = ;
	ResultSet rs;
	String sname;
	String spwd;
	String sposition;
	login()
	{
		setTitle("登录");
		setLayout(null);
		setSize(250, 250);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		ImageIcon a=new ImageIcon("login.jpg");//设置窗体的背景图片，首先把照片加入到标签里再把标签添加到窗体内
		label1=new JLabel(a);
		label1.setBounds(0, 0, a.getIconWidth(), a.getIconHeight());//设置标签高度和长度与图片的高度和长度一致
		label2=new JLabel("用 户 名:");//提醒用户此处应输入相应的用户名
		label2.setBounds(10,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(70, 40, 150, 20);//提醒用户此处输入相应的密码信息
		label3=new JLabel("密       码:");
		label3.setBounds(10,80, 70, 40);
		text2=new JPasswordField();
		text2.setBounds(70, 90, 150, 20);
		button1=new JButton("登录");
		button1.addActionListener(this);
		button1.setBounds(60, 140, 60, 30);
		button2=new JButton("修改密码");
		button2.addActionListener(this);
		button2.setBounds(140, 140, 90, 30);
		add(label2);
		add(text1);
		add(label3);
		add(text2);
		add(button1);
		add(button2);
		add(label1,new Integer(Integer.MIN_VALUE));
		add(label1);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
	
		String driverName="com.mysql.jdbc.Driver";
		String dbURL="jdbc:mysql://localhost:3306/shop?useSSL=false";
		String userName="root";
		String userPwd="123456";
		try
		{
			Class.forName(driverName);
			System.out.println("加载驱动成功！");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("加载驱动失败！");
		}
		try{
			Connection dbConn= DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("连接数据库成功！");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}
		if(e.getActionCommand().equals("修改密码")){
			this.dispose();
			new pwd();
		}
		if(e.getActionCommand().equals("登录")) {
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from shop.user";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			int m1=0,m2=0;
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				sname = rs.getString("username");
				spwd = rs.getString("password");
				if (sname.equals(text1.getText())) {
					sposition = rs.getString("position");
					m1=1;
					if(spwd.equals(text2.getText())){
						m2=1;
					}
				}
			}
			if(m1==1){
				if(m2==1){
					if (sposition.equals("salesperson")) {
						this.dispose();
						new salesperson();
					} else if (sposition.equals("stock")) {
						this.dispose();
						new stock();
					} else if (sposition.equals("manager")) {
						this.dispose();
						new manager();
					}
					this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "密码填写错误！", "错误", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "账号填写错误！", "错误", 
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (ClassNotFoundException e1) {
			System.out.println("ERROR:" + e1);
		} catch (SQLException e2Exception) {
			//JOptionPane.showMessageDialog(this,"信息输入的格式有误！");
			e2Exception.printStackTrace();
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
				e2.printStackTrace();
			}
		}
	}


	}
}


