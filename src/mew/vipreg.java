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
		setTitle("ע���Ա");
		setLayout(null);
		setSize(500, 300);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		
		label1=new JLabel("�� Ա ��:");
		label1.setBounds(100,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(185, 40, 150, 20);
		label2=new JLabel("�� ��:");
		label2.setBounds(100,80, 70, 40);
		text2=new JTextField();
		text2.setBounds(185, 90, 150, 20);
		label3=new JLabel("�� �� �� :");
		label3.setBounds(100,130, 70, 40);
		text3=new JTextField();
		text3.setBounds(185, 140, 150, 20);
	
		button1=new JButton("����");
		button1.addActionListener(this);
		button1.setBounds(380,35, 70, 30);
		button2=new JButton("���");
		button2.addActionListener(this);
		button2.setBounds(160, 200, 60, 30);
		button3=new JButton("����");
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
	if(e.getActionCommand().equals("����")) {
		if(isNumeric(text1.getText()) == false){
			JOptionPane.showMessageDialog(null, "ID�����ʽ����");
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
					JOptionPane.showMessageDialog(null, "�û�Ա���Ѵ���");
					this.dispose();
					new vipreg();
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "�û�Ա�ſ���ʹ��");
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
		if(e.getActionCommand().equals("���"))
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
			JOptionPane.showMessageDialog(this,"��Ϣ����ĸ�ʽ����");

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
		Object[] options ={ "ȷ��", "ȡ��" };
		int m = JOptionPane.showOptionDialog(null, "ȷ�ϻ�Ա��Ϣ��д��ȷ", "��Ա��Ϣ",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			JOptionPane.showMessageDialog(null, "ע��ɹ�");  
			this.dispose();
			new vip();
		}
		
	}
	if(e.getActionCommand().equals("����"))
	{
		this.dispose();
		new vip();
	}
	}
}

