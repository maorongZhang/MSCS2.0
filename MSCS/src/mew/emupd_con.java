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

public class emupd_con extends JFrame implements ActionListener{
	JLabel label1,label2,label3,label4;
	JTextField text1,text2,text3,text4;
	JButton button1,button2,button3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	double price=1;
	double discount=1;
	double mark=1;
	String x;
	String k;
	emupd_con(String m)
	{
		k = m;
		setTitle("Ա����Ϣ�޸�");
		setLayout(null);
		setSize(500, 400);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		
		//label1=new JLabel("Ա ��  ��:");
		//label1.setBounds(100,30, 70, 40);
		//text1=new JTextField();
		//text1.setBounds(185, 40, 150, 20);
		label2=new JLabel("�� ��:");
		label2.setBounds(100,80, 70, 40);
		text2=new JTextField();
		text2.setBounds(185, 90, 150, 20);
		label3=new JLabel("�� �� :");
		label3.setBounds(100,130, 70, 40);
		text3=new JTextField();
		text3.setBounds(185, 140, 150, 20);
		label4=new JLabel("�� �� :");
		label4.setBounds(100,180, 70, 40);
		text4=new JTextField();
		text4.setBounds(185, 190, 150, 20);
	
		//button1=new JButton("����");
		//button1.addActionListener(this);
		//button1.setBounds(380,35, 70, 30);
		button2=new JButton("���");
		button2.addActionListener(this);
		button2.setBounds(160, 250, 60, 30);
		button3=new JButton("����");
		button3.addActionListener(this);
		button3.setBounds(250, 250, 60, 30);

		//add(label1);
		//add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(label4);
		add(text4);
	
		//add(button1);
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

	
	if(e.getActionCommand().equals("���"))
	{
		if(isNumeric(text2.getText()) == false){
			JOptionPane.showMessageDialog(null, "�ֻ��������ʽ����");
			return;
		}
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from shop.employee";
		Object[] options ={ "ȷ��", "ȡ��" };  
		int m = JOptionPane.showOptionDialog(null, "ȷ��Ա����Ϣ��д��ȷ", "Ա����Ϣ",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
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
					if (x.equals(rs.getString("ID"))) {
						ps = ct.prepareStatement("update shop.employee set name=?,account=?,level=? where ID='" + k + "'");
						ps.setString(1, text2.getText());
						ps.setString(2, text3.getText());
						ps.setString(3, text4.getText());
						ps.executeUpdate();
					}
				}
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}finally {
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
					System.out.println("ERROR:"+e2);
				}
			}
			JOptionPane.showMessageDialog(null, "���ĳɹ�");
			this.dispose();
			new vipupd();
		}
		
	}
	if(e.getActionCommand().equals("����"))
	{
		this.dispose();
		new employee();
	}
	}
}

