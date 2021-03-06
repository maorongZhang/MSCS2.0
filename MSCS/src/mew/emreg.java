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
import java.text.SimpleDateFormat;
import java.util.Date;

public class emreg extends JFrame implements ActionListener{
	JLabel label1,label2,label3,label4;
	JTextField text1,text2,text3,text4;
	JButton button1,button2,button3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	String x;
	double price=1;
	double discount=1;
	double mark=1;
	
	emreg()
	{
		setTitle("员工登记");
		setLayout(null);
		setSize(500, 400);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		
		//label1=new JLabel("员 工  号:");
		//label1.setBounds(100,30, 70, 40);
		//text1=new JTextField();
		//text1.setBounds(185, 40, 150, 20);
		label2=new JLabel("姓 名:");
		label2.setBounds(100,80, 70, 40);
		text2=new JTextField();
		text2.setBounds(185, 90, 150, 20);
		label3=new JLabel("账 号 :");
		label3.setBounds(100,130, 70, 40);
		text3=new JTextField();
		text3.setBounds(185, 140, 150, 20);
		label4=new JLabel("评 级 :");
		label4.setBounds(100,180, 70, 40);
		text4=new JTextField();
		text4.setBounds(185, 190, 150, 20);
	
		//button1=new JButton("查重");
		//button1.addActionListener(this);
		//button1.setBounds(380,35, 70, 30);
		button2=new JButton("完成");
		button2.addActionListener(this);
		button2.setBounds(160, 250, 60, 30);
		button3=new JButton("返回");
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

	public static String getDate(String sformat) {
      Date currentTime = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat(sformat);
      String dateString = formatter.format(currentTime);
      return dateString;
   }

	 public static String getRandomNum(int num){
	     String numStr = "";
	     for(int i = 0; i < num; i++){
	             numStr += (int)(10*(Math.random()));
	         }
	     return numStr;
	 }




	 public static long getGeneratID(){
	     String sformat = "MMddhhmmssSSS";
	     int num = 3;
	     String idStr = getDate(sformat) + getRandomNum(num);
	     Long id = Long.valueOf(idStr);
	     return id;
	 }

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			//System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	public void actionPerformed(ActionEvent e) {
	/*if(e.getActionCommand().equals("查重"))
	{
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from shop.employee";
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
					JOptionPane.showMessageDialog(null, "该员工号已存在");
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "该员工号可以使用");
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}*/

	if(e.getActionCommand().equals("完成"))
	{
		if (isNumeric(text4.getText()) == false){
			JOptionPane.showMessageDialog(null, "评级输入格式有误！");
			return;
		}
		Object[] options ={ "确认", "取消" };  
		int m = JOptionPane.showOptionDialog(null, "确认员工信息填写正确", "员工信息",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
				String userName="root";
				String userPwd="123456";
				ct = DriverManager.getConnection(url,userName,userPwd);
				ps=ct.prepareStatement("insert into shop.employee ( `ID`, `name`, `account`,`level`) values(?,?,?,?)");
				ps.setString(1,String.valueOf(getGeneratID()));
				ps.setString(2,text2.getText());
				ps.setString(3,text3.getText());
				ps.setString(4,text4.getText());

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
			JOptionPane.showMessageDialog(null, "登记成功");  
			this.dispose();
			new employee();
		}
		
	}
	if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new employee();
	}
	}
	}
}
