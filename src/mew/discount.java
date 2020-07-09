package mew;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class discount extends JFrame implements ActionListener{
    DefaultTableModel tableModel;   //表格模型对象
    JLabel label1;
    JTextField text1;
    JButton button1,button2;
    selectmessage sel;
    JTable table;
    JScrollPane gdt;
    int x, y, rkeep;
    double k;
    String h;
    int u = 0;
    discount()
    {
        super();
        setTitle("促销计划");
        //setLayout(null);
        setSize(400, 500);
        setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"商品ID","商品姓名","原价","折扣","剩余保质期","剩余库存"};   //列名
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

        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton con = new JButton("生成");   //添加按钮
        con.addActionListener(this);
        panel2.add(con);
        final JButton back = new JButton("返回");
        back.addActionListener(this);
        panel2.add(back);

        this.setVisible(true);

		/*setTitle("会员信息更改");
		setLayout(null);
		setSize(300, 180);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		label1=new JLabel("会 员 号:");
		label1.setBounds(30,30, 70, 40);
		text1=new JTextField();
		text1.setBounds(100, 40, 150, 20);
		button1=new JButton("更改");
		button1.addActionListener(this);
		button1.setBounds(70, 80, 60, 30);
		button2=new JButton("返回");
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

    public static Double sub(Double v1,Double v2){

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.subtract(b2).doubleValue();

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("生成")) {
            PreparedStatement ps = null;
            Connection ct = null;
            ResultSet rs = null;
            String sql = "select * from shop.product";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
                String Name = "root";
                String Pwd = "123456";
                ct = DriverManager.getConnection(url, Name, Pwd);
                ps = ct.prepareStatement(sql);
                rs = ps.executeQuery();
                for(int i=0;i<tableModel.getRowCount();i++) {//表格中的行数{
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {//表格中的列数
                        tableModel.setValueAt(" ", i, j);
                    }//逐个清空
                }
                u = 0;
                while (rs.next()) {
                    x = Integer.parseInt(rs.getString("number"));
                    h = rs.getString("name");
                    k = Double.parseDouble(rs.getString("discount"));
                    if (x>300 && x<500) {
                        Object[] options = {"确认", "取消"};
                        int m = JOptionPane.showOptionDialog(null, "检测到商品：" + h + "库存大于300，是否对其做降1折处理？", "促销建议", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (m == 0) {
                            //k = Double.parseDouble(rs.getString("discount"));
                            //k = rs.getDouble("discount");
                            k = sub(k,0.1);
                            //JOptionPane.showMessageDialog(null,k);
                           // ct = DriverManager.getConnection(url, Name, Pwd);
                            //ps = ct.prepareStatement("update shop.product set discount=? where name='" + h + "'");
                            //JOptionPane.showMessageDialog(null,String.valueOf(k));
                            //ps.setString(1, String.valueOf(k));
                            //ps.executeUpdate();
                            //ps.setDouble(1, k);
                        }
                    }
                    if (x>500) {
                        Object[] options = {"确认", "取消"};
                        int m = JOptionPane.showOptionDialog(null, "检测到商品：" + h + "库存大于500，是否对其做降2折处理？", "促销建议", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (m == 0) {
                            //k = Double.parseDouble(rs.getString("discount"));
                            //k = rs.getFloat("discount");
                            k = sub(k,0.2);
                            //ct = DriverManager.getConnection(url, Name, Pwd);
                            //ps = ct.prepareStatement("update shop.product set discount=? where name='" + h + "'");
                            //ps.setString(1, String.valueOf(k));
                            //ps.executeUpdate();
                            //ps.setDouble(1, k);
                        }
                    }
                    dateCal cal= new dateCal();
                    rkeep = Integer.parseInt(Long.toString(cal.reda(rs.getString("date"), rs.getString("keep"))));
                    if (rkeep<30&&rkeep>=10){
                        Object[] options = {"确认", "取消"};
                        int m = JOptionPane.showOptionDialog(null, "检测到商品：" + h + "剩余保质期小于30天，是否对其做降1折处理？", "促销建议", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (m == 0) {
                            //k = Double.parseDouble(rs.getString("discount"));
                            //k = rs.getDouble("discount");
                            k = sub(k,0.1);
                            //ct = DriverManager.getConnection(url, Name, Pwd);
                            //ps = ct.prepareStatement("update shop.product set discount=? where name='" + h + "'");
                            //ps.setString(1, String.valueOf(k));
                            //ps.executeUpdate();
                            //ps.setDouble(1, k);
                        }
                    }
                    if (rkeep<10) {
                        Object[] options = {"确认", "取消"};
                        int m = JOptionPane.showOptionDialog(null, "检测到商品：" + h + "剩余保质期小于10天，是否对其做降2折处理？", "促销建议", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (m == 0) {
                            //k = Double.parseDouble(rs.getString("discount"));
                            //k = rs.getDouble("discount");
                            k = sub(k,0.2);
                            //ct = DriverManager.getConnection(url, Name, Pwd);
                            //ps = ct.prepareStatement("update shop.product set discount=? where name='" + h + "'");
                            //ps.setString(1, String.valueOf(k));
                            //ps.executeUpdate();
                            //ps.setDouble(1,k);
                        }
                    }
                    ps = ct.prepareStatement("update shop.product set discount=? where name='" + h + "'");
                    ps.setString(1, String.valueOf(k));
                    ps.executeUpdate();
                }
                ps = ct.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String[] rowValues = {rs.getString("ID"), rs.getString("name"), rs.getString("price"), rs.getString("discount"), String.valueOf(rkeep), rs.getString("number")};
                    tableModel.addRow(rowValues);  //添加一行
                }


            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        }
        
        if(e.getActionCommand().equals("返回"))
        {
            this.dispose();
            new manager();
        }
    }
}