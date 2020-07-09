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


public class re_con extends JFrame implements  ActionListener{
	
	DefaultTableModel tableModel;   //表格模型对象
	JTable table;
	JLabel po;
	JTextField Text1;
	String sID,sname,sprice,sdis,ssum,snum,s="0",afte=" ";
	String mnum;
	int k;

	int p=0;
	static String point;
    public re_con(String oid)
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
        
        JLabel la=new JLabel("积分:");
        panel1.add(la);

        JButton sea = new JButton("查询");   //添加按钮
        panel1.add(sea);  
        
        JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);
        
    
        JButton del = new JButton("删除");   //添加按钮
        del.addActionListener(this);
        panel2.add(del);  
        JButton back = new JButton("返回");
        back.addActionListener(this);


        panel2.add(back);

        sea.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		mnum = Text1.getText();
        		Double cou = Double.parseDouble(s);
        		k = vip(mnum);
        		if(k == 1){
        			JOptionPane.showMessageDialog(null, "您是会员！", "收银",JOptionPane.PLAIN_MESSAGE);

        			po=new JLabel(point);
        			panel1.remove(Text1);
        			panel1.remove(sea);
        			panel1.remove(la);
        			panel1.add(new JLabel(Text1.getText()));
        			panel1.add(la);
        			panel1.add(po);
        	        panel1.setLayout(new GridLayout(1,2,2,30));
        	      
        		}
        		else{
        			JOptionPane.showMessageDialog(null, "该会员不存在！", "错误", 
        					JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        
        this.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getActionCommand().equals("删除"))
    	{	
    		PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			String key;
			int selectedRow = table.getSelectedRow();//获得选中行的索引
			
            if(selectedRow!=-1)  //存在选中行
            {
            	Object oa = tableModel.getValueAt(selectedRow, 0);
    			Object ob = tableModel.getValueAt(selectedRow, 1);
    			Object oc = tableModel.getValueAt(selectedRow, 6);
    			Object od = tableModel.getValueAt(selectedRow, 5);
    			key=oa.toString();
                tableModel.removeRow(selectedRow);  //删除行                
                
                try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					String num = "0";
					
					ct = DriverManager.getConnection(url, Name, Pwd);
					ps = ct.prepareStatement("delete from ord where OID = ? AND ID = ? AND num = ? AND sum = ?");
					ps.setString(1, oa.toString());
					ps.setString(2, ob.toString());
					ps.setString(3, oc.toString());
					ps.setString(4, od.toString());
					ps.executeUpdate();	
					
					ps = ct.prepareStatement("select * from product where ID = ?");
					ps.setString(1,ob.toString() );
					rs = ps.executeQuery();
					while (rs.next()) {
						num = rs.getString("number");
					}

					ps = ct.prepareStatement("Update product set number = ? where ID = ?");
					num=String.valueOf(Integer.parseUnsignedInt(num)+Integer.parseUnsignedInt(oc.toString()));
					ps.setString(1,num );
					ps.setString(2,ob.toString());
					ps.executeUpdate();
		
					if(k == 1){
						ps = ct.prepareStatement("Update member set point = ? where ID = ?");
						point = String.valueOf(Double.parseDouble(point)-Double.parseDouble(od.toString()));
						ps.setString(1,point);
						ps.setString(2,Text1.getText());
						ps.executeUpdate();
					}	
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}             
            }       
            else{
            	JOptionPane.showMessageDialog(null, "未选择商品！", "确认", 
    					JOptionPane.ERROR_MESSAGE);
            }
    	}
    	if(e.getActionCommand().equals("返回"))
    	{	
    		this.dispose();
    		new re();
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