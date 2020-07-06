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

public class emupd extends JFrame implements ActionListener{
	DefaultTableModel tableModel;   //���ģ�Ͷ���
	JLabel label1;
	JTextField text1;
	JButton button1,button2;
	selectmessage sel;
	JTable table;
	JScrollPane gdt;
	String x;
	int u = 0;
	emupd()
	{super();
		setTitle("��Ա��Ϣ����");
		//setLayout(null);
		setSize(400, 500);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] columnNames = {"Ա����","����","�˻�","����"};   //����
		String [][]tableVales={}; //����
		tableModel = new DefaultTableModel(tableVales,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);   //֧�ֹ���
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();//���ݾ���
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //��ѡ
		table.addMouseListener(new MouseAdapter(){    //����¼�
			public void mouseClicked(MouseEvent e){
				int selectedRow = table.getSelectedRow(); //���ѡ��������
			}
		});
		scrollPane.setViewportView(table);

		final JPanel panel1 = new JPanel();
		getContentPane().add(panel1,BorderLayout.NORTH);

		panel1.add(new JLabel("Ա����: "));
		text1 = new JTextField("",15);
		panel1.add(text1);

		final JButton add = new JButton("��ѯ");   //��Ӱ�ť
		add.addActionListener(this);
		panel1.add(add);

		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2,BorderLayout.SOUTH);

		final JButton con = new JButton("����");   //��Ӱ�ť
		con.addActionListener(this);
		panel2.add(con);
		final JButton sort = new JButton("��������");   //��Ӱ�ť
		con.addActionListener(this);
		panel2.add(sort);
		final JButton back = new JButton("����");
		back.addActionListener(this);
		panel2.add(back);

		this.setVisible(true);

		/*setTitle("Ա����Ϣ����");
		setLayout(null);
		setSize(300, 180);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		label1=new JLabel("Ա �� ��:");
		label1.setBounds(30,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(100, 40, 150, 20);
		button1=new JButton("����");
		button1.addActionListener(this);
		button1.setBounds(70, 80, 60, 30);
		button2=new JButton("����");
		button2.addActionListener(this);
		button2.setBounds(160, 80, 60, 30);
		add(label1);
		add(text1);
		add(button1);
		add(button2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("��ѯ")) {
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
				for(int i=0;i<tableModel.getRowCount();i++) {//����е�����{
					for (int j = 0; j < tableModel.getColumnCount(); j++) {//����е�����
						tableModel.setValueAt(" ", i, j);
					}//������
				}
				u = 0;
				while (rs.next()) {
					x = rs.getString("ID");
					if (x.equals(text1.getText())) {
						String []rowValues = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
						tableModel.addRow(rowValues);  //���һ��
						u = 1;
					}
				}
				if (u == 0) JOptionPane.showMessageDialog(null, "��Ա���Ų�����");
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	if(e.getActionCommand().equals("����"))
	{	PreparedStatement ps = null;
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
				if (x.equals(text1.getText())) {
					new emupd_con(x);
					this.dispose();
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "��Ա���Ų�����");
			this.dispose();
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		//this.dispose();
		//new emupd_con();
	}

		if(e.getActionCommand().equals("��������")){
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
				for(int i=0;i<tableModel.getRowCount();i++) {//����е�����{
					for (int j = 0; j < tableModel.getColumnCount(); j++) {//����е�����
						tableModel.setValueAt(" ", i, j);
					}//������
				}
				while (rs.next()) {
					x = rs.getString("level");
					if (x.equals("A")) {
						String []rowValues = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
						tableModel.addRow(rowValues);  //���һ��
					}
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					x = rs.getString("level");
					if (x.equals("B")) {
						String []rowValues = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
						tableModel.addRow(rowValues);  //���һ��
					}
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					x = rs.getString("level");
					if (x.equals("C")) {
						String []rowValues = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
						tableModel.addRow(rowValues);  //���һ��
					}
				}
				//if (u == 0) JOptionPane.showMessageDialog(null, "��Ա���Ų�����");
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("����"))
	{
		this.dispose();
		new employee();
	}
	}
}
