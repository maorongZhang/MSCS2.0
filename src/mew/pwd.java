package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class pwd extends JFrame implements ActionListener{
	JLabel label1,label2,label3,label4;
	JTextField text1;
	JPasswordField text2,text3,text4;
	JButton button1,button2,button3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	double price=1;
	double discount=1;
	double mark=1;
	
	pwd()
	{
		setTitle("密码修改");
		setLayout(null);
		setSize(500, 350);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		
		label1=new JLabel("账 号:");
		label1.setBounds(100,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(185, 40, 150, 20);
		label2=new JLabel("原 密 码:");
		label2.setBounds(100,80, 70, 40);
		text2=new JPasswordField();
		text2.setBounds(185, 90, 150, 20);
		label3=new JLabel("新 密 码 :");
		label3.setBounds(100,130, 70, 40);
		text3=new JPasswordField();
		text3.setBounds(185, 140, 150, 20);
		label4=new JLabel("再 次 输 入 :");
		label4.setBounds(100,180, 70, 40);
		text4=new JPasswordField();
		text4.setBounds(185, 190, 150, 20);
	
		button1=new JButton("完成");
		button1.addActionListener(this);
		button1.setBounds(160, 240, 60, 30);
		button2=new JButton("返回");
		button2.addActionListener(this);
		button2.setBounds(250, 240, 60, 30);

		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(label4);
		add(text4);
	
		add(button1);
		add(button2);
	
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("完成"))
	{
		Object[] options ={ "确认", "取消" };  
		int m = JOptionPane.showOptionDialog(null, "确认会员信息填写正确", "会员信息",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			  try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					int k = 0;
					int p = 0;
					String su,sp;
					System.out.print(text1.getText());
					
					ct = DriverManager.getConnection(url, Name, Pwd);
					ps = ct.prepareStatement("select * from user");
					rs = ps.executeQuery();
					
					while (rs.next()) {
						su = rs.getString("username");
						System.out.print("哈"+su);
						if(su.equals(text1.getText())){
							k = 1;
							sp = rs.getString("password");
							if(sp.equals( text2.getText())){
								p = 1;
							}
						}
					}
					
					if(k == 0){
						JOptionPane.showMessageDialog(null, "用户不存在！", "错误", 
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(p == 0){
							JOptionPane.showMessageDialog(null, "密码填写错误！", "错误", 
									JOptionPane.ERROR_MESSAGE);
						}
						else{
							if(text3.getText().equals(text4.getText())){
								
								ps = ct.prepareStatement("Update user set password = ? where username = ?");
								ps.setString(1,text3.getText());
								ps.setString(2,text1.getText());
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "修改成功!.", "登录",JOptionPane.PLAIN_MESSAGE); 
								this.dispose();
								new login();
							}
							else{
								JOptionPane.showMessageDialog(null, "两次密码填写不一致！", "错误", 
										JOptionPane.ERROR_MESSAGE);
							}
						}				
					}					
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}           
		}
		
	}
	if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new login();
	}
	}
}

