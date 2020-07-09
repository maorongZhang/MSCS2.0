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



public class sale  extends JFrame implements  ActionListener{

	sale_ordernumber ord = new sale_ordernumber();
	DefaultTableModel tableModel;   //���ģ�Ͷ���
	JTable table;
	JTextField Text1;
	JTextField Text2;
	String sID,sname,sprice,sdis,ssum,snum;
	String oid=ord.GetRandom();
	int o=0;
	public sale(int i)
	{
		this.dispose();
		new sale();
	}
	public sale()
	{
		super();
		setTitle("����");
		setSize(500, 600);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] columnNames = {"������","���","��Ʒ��","����","�ۿ�","�ּ�","����"};   //����
		String [][]tableVales={}; //����
		tableModel = new DefaultTableModel(tableVales,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);   //֧�ֹ���
		getContentPane().add(scrollPane,BorderLayout.CENTER);

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

		panel1.add(new JLabel("��Ʒ���: "));
		Text1 = new JTextField("",15);
		panel1.add(Text1);
		panel1.add(new JLabel("����: "));
		Text2 = new JTextField("",5);
		panel1.add(Text2);
		final JButton add = new JButton("���");   //��Ӱ�ť
		add.addActionListener(this);
		panel1.add(add);
		final JButton del = new JButton("ɾ��");
		del.addActionListener(this);
		panel1.add(del);

		final JPanel panel2 = new JPanel();
		getContentPane().add(panel2,BorderLayout.SOUTH);

		final JButton con = new JButton("ȷ��");   //��Ӱ�ť
		con.addActionListener(this);
		panel2.add(con);
		final JButton back = new JButton("����");
		back.addActionListener(this);
		panel2.add(back);

		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("���"))
		{
			String key;
			sID = Text1.getText();
			snum = Text2.getText();
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			String sql = "select * from product";

			if(sID.equals("") || snum.equals(""))
			{
				JOptionPane.showMessageDialog(null, "��д����", "����",
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
						key = rs.getString("ID");
						if (key.equals(sID)) {
							sname = rs.getString("name");
							sprice = rs.getString("price");
							sdis = rs.getString("discount");
							o=1;
						}
					}
					if(o==1){
						
						Double price=Double.parseDouble(sprice);
						Double dis=Double.parseDouble(sdis);
						Double num=Double.parseDouble(snum);
						Double sum=price*dis*num;
						ssum = String.format("%.2f", sum);

						String []rowValues = {oid,sID,sname,sprice,sdis,ssum,snum};
						tableModel.addRow(rowValues);  //���һ��
					}
					else{
						JOptionPane.showMessageDialog(null, "��Ʒ�����ڣ�", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				}catch (ClassNotFoundException e1) {
					System.out.println("ERROR:" + e1);
				}catch (SQLException e2Exception) {
					e2Exception.printStackTrace();
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					ct = DriverManager.getConnection(url, Name, Pwd);
					ps = ct.prepareStatement("insert into ord (OID,ID,name,price,discount,num,sum) values(?,?,?,?,?,?,?)");
					ps.setString(1,oid);
					ps.setString(2,sID);
					ps.setString(3,sname);
					ps.setString(4,sprice);
					ps.setString(5,sdis);
					ps.setString(6,snum);
					ps.setString(7,ssum);
					ps.executeUpdate();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

			}
		}

		if(e.getActionCommand().equals("ɾ��"))
		{
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			String key;
			int selectedRow = table.getSelectedRow();//���ѡ���е�����
			Object oa = tableModel.getValueAt(selectedRow, 0);


			if(selectedRow!=-1)  //����ѡ����
			{
				tableModel.removeRow(selectedRow);  //ɾ����
				key=oa.toString();
				System.out.println(key);

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
					String Name = "root";
					String Pwd = "123456";
					ct = DriverManager.getConnection(url, Name, Pwd);
					ps = ct.prepareStatement("delete from ord where OID = ?");
					ps.setString(1, key);
					ps.executeUpdate();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}

		if(e.getActionCommand().equals("ȷ��"))
		{
			this.dispose();
			new sale_con(oid);

		}

		if(e.getActionCommand().equals("����"))
		{
			this.dispose();
			new salesperson();
		}
	}
}