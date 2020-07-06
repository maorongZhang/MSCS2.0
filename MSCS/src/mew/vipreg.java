package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class vipreg extends JFrame implements ActionListener{
	JLabel label1,label2,label3,label4,label5,label6;
	JTextField text1,text2,text3;
	JButton button1,button2,button3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	double price=1;
	double discount=1;
	double mark=1;
	String x;
	vipreg()
	{
		setTitle("注册会员");
		setLayout(null);
		setSize(500, 300);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		
		label1=new JLabel("会 员 号:");
		label1.setBounds(100,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(185, 40, 150, 20);
		label2=new JLabel("姓 名:");
		label2.setBounds(100,80, 70, 40);
		text2=new JTextField();
		text2.setBounds(185, 90, 150, 20);
		label3=new JLabel("手 机 号 :");
		label3.setBounds(100,130, 70, 40);
		text3=new JTextField();
		text3.setBounds(185, 140, 150, 20);
	
		button1=new JButton("查重");
		button1.addActionListener(this);
		button1.setBounds(380,35, 70, 30);
		button2=new JButton("完成");
		button2.addActionListener(this);
		button2.setBounds(160, 200, 60, 30);
		button3=new JButton("返回");
		button3.addActionListener(this);
		button3.setBounds(250, 200, 60, 30);

		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
	
		add(button1);
		add(button2);
		add(button3);
	
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			//System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
	return true;
	}
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("查重")) {
		if(isNumeric(text1.getText()) == false){
			JOptionPane.showMessageDialog(null, "ID输入格式有误！");
			return;
		}
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from shop.member";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				x = rs.getString("ID");
				if (x.equals(text1.getText())) {
					JOptionPane.showMessageDialog(null, "该会员号已存在");
					this.dispose();
					new vipreg();
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "该会员号可以使用");
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
		if(e.getActionCommand().equals("完成"))
	{
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String userName="root";
			String userPwd="123456";
			ct = DriverManager.getConnection(url,userName,userPwd);
			ps=ct.prepareStatement("insert into shop.member ( `ID`, `name`, `tel` ) values(?,?,?)");
			ps.setString(1,text1.getText());
			ps.setString(2,text2.getText());
			ps.setString(3,text3.getText());

			ps.executeUpdate();

			this.dispose();

		}catch(ClassNotFoundException  e1){
			System.out.println("ERROR:"+e1);
		}catch(SQLException e2Exception){
			JOptionPane.showMessageDialog(this,"信息输入的格式有误！");

			e2Exception.printStackTrace();
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(ct!=null){
					ct.close();
				}
			}
			catch(SQLException e2){

			}
		}
		Object[] options ={ "确认", "取消" };
		int m = JOptionPane.showOptionDialog(null, "确认会员信息填写正确", "会员信息",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			JOptionPane.showMessageDialog(null, "注册成功");  
			this.dispose();
			new vip();
		}
		
	}
	if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new vip();
	}
	}
}

