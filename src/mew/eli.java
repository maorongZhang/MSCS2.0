package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class eli extends JFrame implements ActionListener{
	JLabel label1;
	JTextField text1;
	JButton button1,button2,button3;
	selectmessage sel;
	JTable bg1;
	JScrollPane gdt;
	eli()
	{
		setTitle("淘汰商品");
		setLayout(null);
		setSize(400, 500);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		label1=new JLabel("商 品 编 号:");
		label1.setBounds(30,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(100, 40, 150, 20);
		button1=new JButton("删除");
		button1.addActionListener(this);
		button1.setBounds(120, 380, 60, 30);
		button2=new JButton("返回");
		button2.addActionListener(this);
		button2.setBounds(210, 380, 60, 30);
		button3=new JButton("查询");
		button3.addActionListener(this);
		button3.setBounds(300, 35, 60, 30);

		bg1 = new JTable();
		//sel = new selectmessage();
		//bg1 = new JTable(sel);
		//gdt = new JScrollPane(bg1);
		//add(bg1);
		add(label1);
		add(text1);
		add(button1);
		add(button2);
		add(button3);
		add(bg1);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("删除"))
	{
		/*int ii = this.bg1.getSelectedRow();
		System.out.println(ii);
		if (ii == -1) {
			JOptionPane.showMessageDialog(this, "请选中删除的行！");
			return;
		}
		String st = (String) sel.getValueAt(ii, 0);
		System.out.println(st);*/
		Delete delete = new Delete(text1.getText());
		//sel = new selectmessage();
		//bg1.setModel(sel);
		Object[] options ={ "确认", "取消" };  
		int m = JOptionPane.showOptionDialog(null, "确认淘汰商品", "库存",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			JOptionPane.showMessageDialog(null, "淘汰成功");  
			this.dispose();
			new stock();
		}
	}
	if(e.getActionCommand().equals("查询"))
	{
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			ct = DriverManager.getConnection(url, Name, Pwd);
			String sql1 = "select * from shop.product where ID='" + text1.getText() + "'";
			sel = new selectmessage(sql1);
			bg1.setModel(sel);
		} catch (ClassNotFoundException | SQLException classNotFoundException) {
			classNotFoundException.printStackTrace();
		}
	}
	if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new stock();
	}
	}
}