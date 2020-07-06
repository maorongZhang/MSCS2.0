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
	DefaultTableModel tableModel;   //���ģ�Ͷ���
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
		setTitle("��Աɾ��");
		//setLayout(null);
		setSize(400, 500);
		setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] columnNames = {"��Ա��","����","������","����","�绰��"};   //����
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

		panel1.add(new JLabel("��Ա��: "));
		text1 = new JTextField("",15);
		panel1.add(text1);

		final JButton add = new JButton("��ѯ");   //��Ӱ�ť
		add.addActionListener(this);
		panel1.add(add);

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

	if(e.getActionCommand().equals("��ѯ")) {
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
					tableModel.addRow(rowValues);  //���һ��
				}
			}
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	if(e.getActionCommand().equals("ȷ��"))
	{
		Object[] options ={ "ȷ��", "ȡ��" };  
		int m = JOptionPane.showOptionDialog(null, "ȷ��ɾ���û�Ա", "��Ա",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
		if (m==0){
			Object[] options1 ={ "ȷ��", "ȡ��" };
			int n = JOptionPane.showOptionDialog(null, "�ٴ�ȷ��ɾ���û�Ա", "��Ա",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				Delete_member del = new Delete_member(text1.getText());
				this.dispose();
				new vip();
			}
		}
	}
	if(e.getActionCommand().equals("����"))
	{
		this.dispose();
		new vip();
	}
	}
}
