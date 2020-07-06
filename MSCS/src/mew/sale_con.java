package mew;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.sql.*;


public class sale_con extends JFrame implements  ActionListener{
	
	sale_ordernumber ord = new sale_ordernumber();
	DefaultTableModel tableModel,tableModel1;   //表格模型对象
	JTable table,table1;
	JLabel po;
	JTextField Text1;
	String sID,sname,sprice,sdis,ssum,snum,s="0",afte=" ";
	String mnum;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	int p=0;
	static String point;
    public sale_con(String oid)
    {
        super();   
        setTitle("销售");
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
        
        get(oid);
        
        
        JPanel panel1 = new JPanel();
        getContentPane().add(panel1,BorderLayout.NORTH);
      
        panel1.add(new JLabel("会员: "));
        Text1 = new JTextField("",15);
        panel1.add(Text1);
        
        jrb1 = new JRadioButton("是");
        jrb2 = new JRadioButton("否");
        
        JLabel la=new JLabel("积分:");
        panel1.add(la);
        
        bg = new ButtonGroup();	
		bg.add(jrb1);
		bg.add(jrb2);
		
		jrb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	   			p=1;
	   		}
	   	});

        JButton sea = new JButton("查询");   //添加按钮
        panel1.add(sea);  
        
        JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);
        
        Double su = Double.parseDouble(s);//格式控制
        s = String.format("%.2f", su);
        JLabel sum = new JLabel(s);
        panel2.add(new JLabel("合计: "));
    
        panel2.add(sum);
        JLabel aft = new JLabel(afte);
        panel2.add(aft);
        
        JButton con = new JButton("确认");   //添加按钮
        panel2.add(con);  
        con.addActionListener(this);
 
        JButton back = new JButton("返回");
        back.addActionListener(this);


        panel2.add(back);

        sea.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		mnum = Text1.getText();
        		Double cou = Double.parseDouble(s);
        		int k = vip(mnum);
        		if(k == 1){
        			JOptionPane.showMessageDialog(null, "您是会员！", "收银",JOptionPane.PLAIN_MESSAGE);  

        			po=new JLabel(point);
        			panel1.remove(Text1);
        			panel1.remove(sea);
        			panel1.remove(la);
        			panel1.add(new JLabel(Text1.getText()));
        			panel1.add(la);
        			panel1.add(po);
        	        panel1.add(new JLabel("积分抵扣: "));
        	        panel1.add(jrb1);
        	        panel1.add(jrb2);
        	        panel1.setLayout(new GridLayout(1,2,2,30));
        	      
        	        if(p==1){
        	        	cou = Double.parseDouble(s)-(Double.parseDouble(point)/100);
        	        }
        	        cou = cou * 0.95;
        	      	s = String.format("%.2f", cou);
        	   
        	        afte = "折后:"+s;
        	        panel2.remove(con);
        	        panel2.remove(back);
        	        aft.setText(afte);
        	        panel2.add(aft);
        	        panel2.add(con);
        	        panel2.add(back);
        	        panel1.updateUI();
        	        panel2.updateUI();
        		}
        		else{
        			int n = JOptionPane.showConfirmDialog(null, "您不是会员，是否注册？", "收银",JOptionPane.YES_NO_OPTION);
        			if(n==0)
        			{
        				new vipreg();
        			}
        		}
        	}
        });
        
        this.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("确认")) {
    		PreparedStatement ps = null;
    		Connection ct = null;
    		ResultSet rs = null;
    		String mid = Text1.getText();
    		
    		Object[] options = {"确认", "取消"};   		
			int m = JOptionPane.showOptionDialog(null, "确认订单", "订单", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (m == 0) {
				if(p==1){
					if(Double.parseDouble(s)-(Double.parseDouble(point)/100)>=0){
						s = String.valueOf(Double.parseDouble(s)-(Double.parseDouble(point)/100));
						point = "0"+String.valueOf(Double.parseDouble(s));
						System.out.println(point);
					}
					else
					{
						point = String.valueOf(Double.parseDouble(point)-Double.parseDouble(s)*100+Double.parseDouble(s));
						s = "0";
						System.out.println(point);
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
						String Name = "root";
						String Pwd = "123456";
						ct = DriverManager.getConnection(url, Name, Pwd);
						ps = ct.prepareStatement("Update member set point=? where ID = ?");
						ps.setString(1, point);
						ps.setString(2, mid);
						ps.executeUpdate();
						}catch (ClassNotFoundException e1) {
						System.out.println("ERROR:" + e1);
						}catch (SQLException e2Exception) {
							e2Exception.printStackTrace();
						}
    	        }
				int rowCount = table.getRowCount();
				int i=0;
				String num = "0";
				while(i < rowCount){
					Object a = table.getValueAt(i, 1);
					Object b = table.getValueAt(i, 6);
					String pid = a.toString();
					String pnum = b.toString();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
						String Name = "root";
						String Pwd = "123456";
						
						ct = DriverManager.getConnection(url, Name, Pwd);
						ps = ct.prepareStatement("select * from product where ID = ?");
						ps.setString(1, pid);
						rs = ps.executeQuery();
						while (rs.next()) {
							num = rs.getString("number");
						}
					} catch (ClassNotFoundException e1) {
						System.out.println("ERROR:" + e1);
					} catch (SQLException e2Exception) {
						e2Exception.printStackTrace();
					}	
					
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
						String Name = "root";
						String Pwd = "123456";
						ct = DriverManager.getConnection(url, Name, Pwd);
						ps = ct.prepareStatement("Update product set number = ? where ID = ?");
						num = String.valueOf(Double.parseDouble(num)-Double.parseDouble(pnum));
						ps.setString(1, num);
						ps.setString(2, pid);
						ps.executeUpdate();
						}catch (ClassNotFoundException e1) {
						System.out.println("ERROR:" + e1);
						}catch (SQLException e2Exception) {
							e2Exception.printStackTrace();
						}
					i++;
				}
				
				JOptionPane.showMessageDialog(null, "下单成功，实付"+s+"元");
				this.dispose();
				new salesperson();
			}
    	}
    	
    	else if(e.getActionCommand().equals("返回"))
    	{	
    		delete();
    		this.dispose();
    		new sale();
    	}
    }
    
    public void get(String oid){
    	String key;
    	PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		String sql = "select * from ord";
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
				if (key.equals(oid)) {
					sID = rs.getString("ID");
					sname = rs.getString("name");
					sprice = rs.getString("price");
					sdis = rs.getString("discount");
					snum = rs.getString("num");
					ssum = rs.getString("sum");
					String []rowValues = {oid,sID,sname,sprice,sdis,ssum,snum};
					tableModel.addRow(rowValues);  //添加一行
					double p = Double.parseDouble(s) + Double.parseDouble(ssum);
					s=String.valueOf(p);
					}
			}
			}catch (ClassNotFoundException e1) {
			System.out.println("ERROR:" + e1);
			}catch (SQLException e2Exception) {
				e2Exception.printStackTrace();
			}
    }

    
    public void delete(){
    	String key;
    	PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		Object  value = table.getValueAt(0, 0);
		String oid = value.toString();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement("delete from ord where OID = ?");
			ps.setString(1, oid);
			ps.executeUpdate();
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
    }
    
    public static int vip(String mnum){
    	String key;
    	PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
			String Name = "root";
			String Pwd = "123456";

			String sql = "select * from member";
			ct = DriverManager.getConnection(url, Name, Pwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				key = rs.getString("ID");
				if (key.equals(mnum)){
					point = rs.getString("point");
					return 1;
				}
			}
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	    return 0;
    }
}