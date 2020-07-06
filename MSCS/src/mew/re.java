package mew;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;



public class re  extends JFrame implements  ActionListener{
	
	sale_ordernumber ord = new sale_ordernumber();
	DefaultTableModel tableModel;   //表格模型对象
	JTable table;
	JTextField Text1;
	JTextField Text2;
	String oID,sID,sname,sprice,sdis,ssum,snum;

    public re()
    {
        super();
        setTitle("退货");
        setSize(500, 600);
    	setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"订单号","编号","商品名","单价","折扣","总价","件数"};   //列名
        String [][]tableVales={}; //数据
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        
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
      
        panel1.add(new JLabel("订单号: "));
        Text1 = new JTextField("",15);
        panel1.add(Text1);
        
        final JButton add = new JButton("查找");   //添加按钮
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
		if(e.getActionCommand().equals("查找"))
		{	
			String key;
			oID = Text1.getText();
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			String sql = "select * from ord";
			
			if(oID.equals(""))
			{
				JOptionPane.showMessageDialog(null, "填写错误！", "错误", 
				JOptionPane.ERROR_MESSAGE);
			}
			else{	
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					ct = DriverManager.getConnection(url, Name, Pwd);
					ps = ct.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						key = rs.getString("OID");
						if (key.equals(oID)) {
							sID = rs.getString("ID");
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							ssum = rs.getString("sum");
							snum = rs.getString("num");
							String []rowValues = {oID,sID,sname,sprice,sdis,ssum,snum};
							tableModel.addRow(rowValues);  //添加一行
						}
					}
					}catch (ClassNotFoundException e1) {
					System.out.println("ERROR:" + e1);
					}catch (SQLException e2Exception) {
						e2Exception.printStackTrace();
					}
		
			}
		}
			
		
		if(e.getActionCommand().equals("确认"))
		{
			if(Text1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "填写错误！", "错误", 
				JOptionPane.ERROR_MESSAGE);
			}
			else{
				this.dispose();
				new re_con(oID);
			}
		}
		
		if(e.getActionCommand().equals("返回"))
		{
			this.dispose();
			new salesperson();
		}
	}
}