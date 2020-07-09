package mew;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import javax.swing.JComboBox;



public class analyse  extends JFrame implements  ActionListener{
	
	sale_ordernumber ord = new sale_ordernumber();
	DefaultTableModel tableModel;   //���ģ�Ͷ���
	JTable table;
	JComboBox com;
	String sID,sname,sprice,sdis,ssum,snum;

    public analyse()
    {
        super();
        setTitle("������");
        setSize(500, 600);
    	setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"���","��Ʒ��","����","�ۿ�","ʣ�ౣ����","���"};   //����
        String [][]tableVales={}; //����
        tableModel = new DefaultTableModel(tableVales,columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);   //֧�ֹ���
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();//���ݾ���
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);
        
        
        final JPanel panel1 = new JPanel();
        getContentPane().add(panel1,BorderLayout.NORTH);
      
        panel1.add(new JLabel("������ʽ: "));
        com= new JComboBox();
        com.addItem("ʣ�ౣ����");
        com.addItem("�������");
        panel1.add(com);

        final JButton add = new JButton("ȷ��");   //��Ӱ�ť
        add.addActionListener(this);
        panel1.add(add);  
        
        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);
   
        final JButton con = new JButton("����");   //��Ӱ�ť
        con.addActionListener(this);
        panel2.add(con);  
        
        this.setVisible(true);
    }
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ȷ��"))
		{	
			String sID,sname,sprice,sdis,sdate,skeep,snum,rkeep;
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			int k=1;
			String key=com.getSelectedItem().toString();
			int rowCount = table.getRowCount();
			
			if(rowCount == 0){
				if(key.equals("ʣ�ౣ����")){
					k=0;
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					dateCal cal= new dateCal();
					ct = DriverManager.getConnection(url, Name, Pwd);
					if(k == 0){
						ps = ct.prepareStatement("select * from product ");
						rs = ps.executeQuery();
						while (rs.next()) {
							sID = rs.getString("ID");
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							snum = rs.getString("number");
							skeep = rs.getString("keep");
							sdate = rs.getString("date");
							rkeep = Long.toString(cal.reda(sdate, skeep));
							int r = Integer.parseInt(rkeep);
							if(r<15){
								String []rowValues = {sID,sname,sprice,sdis,rkeep,snum};
								tableModel.addRow(rowValues);  //���һ��
							}
						}
					}
					else{
						ps = ct.prepareStatement("select * from product where cast(number as decimal(18,2)) < '50'");
						rs = ps.executeQuery();
						while (rs.next()) {
							sID = rs.getString("ID");
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							snum = rs.getString("number");
							skeep = rs.getString("keep");
							sdate = rs.getString("date");
							rkeep = Long.toString(cal.reda(sdate, skeep));
							String []rowValues = {sID,sname,sprice,sdis,rkeep,snum};
							tableModel.addRow(rowValues);  //���һ��
						}
					
					}
				
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				while(rowCount>0){
					rowCount--;
					tableModel.removeRow(rowCount);
				}
				if(key.equals("ʣ�ౣ����")){
					k=0;
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					dateCal cal= new dateCal();
					ct = DriverManager.getConnection(url, Name, Pwd);
					if(k == 0){
						ps = ct.prepareStatement("select * from product ");
						rs = ps.executeQuery();
						while (rs.next()) {
							sID = rs.getString("ID");
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							snum = rs.getString("number");
							skeep = rs.getString("keep");
							sdate = rs.getString("date");
							rkeep = Long.toString(cal.reda(sdate, skeep));
							int r = Integer.parseInt(rkeep);
							if(r<15){
								String []rowValues = {sID,sname,sprice,sdis,rkeep,snum};
								tableModel.addRow(rowValues);  //���һ��
							}
						}
					}
					else{
						ps = ct.prepareStatement("select * from product where cast(number as decimal(18,2)) < '50'");
						rs = ps.executeQuery();
						while (rs.next()) {
							sID = rs.getString("ID");
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							snum = rs.getString("number");
							skeep = rs.getString("keep");
							sdate = rs.getString("date");
							rkeep = Long.toString(cal.reda(sdate, skeep));
							String []rowValues = {sID,sname,sprice,sdis,rkeep,snum};
							tableModel.addRow(rowValues);  //���һ��
						}
					
					}
				
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}
			
		if(e.getActionCommand().equals("����"))
		{
			this.dispose();
			new manager();
		}
	}
}