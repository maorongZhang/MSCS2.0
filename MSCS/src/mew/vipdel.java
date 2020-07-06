package mew;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class vipdel extends JFrame implements ActionListener{
	DefaultTableModel tableModel;   //表格模型对象
	JTable table;
	JLabel label1;
	JTextField text1;
	JButton button1,button2,button3;
	selectmessage sel;
	JTable bg1;
	JScrollPane gdt;
	String x;
	vipdel()
	{
		super();
		setTitle("会员删除");
		//setLayout(null);
		setSize(400, 500);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] columnNames = {"会员号","积分","订单数","名称","电话号"};   //列名
		String [][]tableVales={}; //数据
		tableModel = new DefaultTableModel(tableVales,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();//数据居中
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
		table.addMouseListener(new MouseAdapter(){    //鼠标事件
			public void mouseClicked(MouseEvent e){
				int selectedRow = table.getSelectedRow(); //获得选中行索引
			}
		});
		scrollPane.setViewportView(table);

		final JPanel panel1 = new JPanel();
		getContentPane().add(panel1,BorderLayout.NORTH);

		panel1.add(new JLabel("会员号: "));
		text1 = new JTextField("",15);
		panel1.add(text1);

		final JButton add = new JButton("查询");   //添加按钮
		add.addActionListener(this);
		panel1.add(add);

		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2,BorderLayout.SOUTH);

		final JButton con = new JButton("确认");   //添加按钮
		con.addActionListener(this);
		panel2.add(con);
		final JButton back = new JButton("返回");
		back.addActionListener(this);
		panel2.add(back);

		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

	if(e.getActionCommand().equals("查询")) {
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
					String []rowValues = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
					tableModel.addRow(rowValues);  //添加一行
				}
			}
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	if(e.getActionCommand().equals("确认"))
	{
		Object[] options ={ "确认", "取消" };  
		int m = JOptionPane.showOptionDialog(null, "确认删除该会员", "会员",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			Object[] options1 ={ "确认", "取消" };
			int n = JOptionPane.showOptionDialog(null, "再次确认删除该会员", "会员",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				JOptionPane.showMessageDialog(null, "删除成功");
				Delete_member del = new Delete_member(text1.getText());
				this.dispose();
				new vip();
			}
		}
	}
	if(e.getActionCommand().equals("返回"))
	{
		this.dispose();
		new vip();
	}
	}
}
