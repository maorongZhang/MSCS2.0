package mew;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class gift_con extends JFrame implements ActionListener {

    String ID;
    JTextField text1;
    JTextField text2;
    DefaultTableModel tableModel;
    JTable table;
    String gID, gName, gNum, gDis, gPrice, gDate, gKeep, gSum,gGiftPoint;

    gift_con(String x) {
        super();
        ID=x;
        String options[]={"积分兑换","满150兑换"};

        int value=JOptionPane.showOptionDialog(null, "选择一个方案：",

                "方案", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,

                options, "积分兑换");
       // System.out.println(value);
        if(value==0){
            pointGift(ID);
        }
        if(value==1){
            consGift(ID);
        }
        if(value==-1){
            new gift();
        }
    }

    private void consGift(String x) {
        String key;
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        String sql = "select * from product";

        setTitle("礼品赠送—消费礼品");
        setSize(500, 600);
        setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"商品编号","商品名","件数","折扣","单价（元）","生产日期","保质期（天）","兑换积分"};   //列名
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


        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("设置");   //添加按钮
        del.addActionListener(this);
        panel2.add(del);
        final JButton back = new JButton("返回");
        back.addActionListener(this);
        panel2.add(back);

        this.setVisible(true);



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
                if (key.equals(ID)) {
                    gName = rs.getString("name");
                    gNum = rs.getString("number");
                    gDis = rs.getString("discount");
                    gPrice = rs.getString("price");
                    gDate = rs.getString("date");
                    gKeep = rs.getString("keep");
                    gGiftPoint = rs.getString("giftPoint");
                }
            }

            // num = Integer.parseInt(eNum);
            String[] rowValues = {ID, gName, gNum, gDis, gPrice, gDate, gKeep,gGiftPoint};
            tableModel.addRow(rowValues);  //添加一行
        }catch (ClassNotFoundException e1) {
            System.out.println("ERROR:" + e1);
        }catch (SQLException e2Exception) {
            e2Exception.printStackTrace();
        }

    }
    private void pointGift(String x) {
        String key;
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        String sql = "select * from product";

        setTitle("礼品赠送—积分兑换");
        setSize(500, 600);
        setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"商品编号","商品名","件数","折扣","单价（元）","生产日期","保质期（天）","兑换积分"};   //列名
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

        panel1.add(new JLabel("设置兑换积分: "));
        text1 = new JTextField("",15);
        panel1.add(text1);

        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("确认");   //添加按钮
        del.addActionListener(this);
        panel2.add(del);
        final JButton back = new JButton("返回");
        back.addActionListener(this);
        panel2.add(back);

        this.setVisible(true);



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
                if (key.equals(ID)) {
                    gName = rs.getString("name");
                    gNum = rs.getString("number");
                    gDis = rs.getString("discount");
                    gPrice = rs.getString("price");
                    gDate = rs.getString("date");
                    gKeep = rs.getString("keep");
                    gGiftPoint = rs.getString("giftPoint");
                }
            }

           // num = Integer.parseInt(eNum);
            String[] rowValues = {ID, gName, gNum, gDis, gPrice, gDate, gKeep,gGiftPoint};
            tableModel.addRow(rowValues);  //添加一行
        }catch (ClassNotFoundException e1) {
            System.out.println("ERROR:" + e1);
        }catch (SQLException e2Exception) {
            e2Exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("确认")) {
            String key;
            if (text1.getText().equals("") == false) {
                int input = Integer.parseInt(text1.getText());
                if (input <= 0) {
                    JOptionPane.showMessageDialog(null, "积分填写错误", "填写错误！",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    PreparedStatement ps = null;
                    Connection ct = null;
                    ResultSet rs = null;
                    String sql = "select * from product";
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
                        String Name = "root";
                        String Pwd = "123456";
                        ct = DriverManager.getConnection(url, Name, Pwd);
                        ps = ct.prepareStatement("update product set giftPoint=" + text1.getText() + " where ID = ?");
                        ps.setString(1, ID);
                        ps.executeUpdate();

                        ps = ct.prepareStatement(sql);
                        rs = ps.executeQuery();

                        ps = ct.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            key = rs.getString("ID");
                            if (key.equals(ID)) {
                                gGiftPoint = rs.getString("giftPoint");
                            }
                        }
                        tableModel.setValueAt(gGiftPoint, 0, 7);

                    } catch (ClassNotFoundException e1) {
                        System.out.println("ERROR:" + e1);
                    } catch (SQLException e2Exception) {
                        e2Exception.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "操作成功", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "请输入积分", "填写错误！",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getActionCommand().equals("设置"))
        {
            PreparedStatement ps = null;
            Connection ct = null;
            ResultSet rs = null;
            String sql = "select * from product";
            if(JOptionPane.showConfirmDialog(null, "请确认是否将此商品设置为礼品", "确认",
                    JOptionPane.YES_NO_OPTION)==0){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
                    String Name = "root";
                    String Pwd = "123456";
                    ct = DriverManager.getConnection(url, Name, Pwd);
                    ps = ct.prepareStatement("update product set isGift=1  where ID = ?");
                    ps.setString(1, ID);
                    ps.executeUpdate();

                    ps = ct.prepareStatement(sql);
                    rs = ps.executeQuery();
                    JOptionPane.showMessageDialog(null, "操作成功", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new gift();

                } catch (ClassNotFoundException e1) {
                    System.out.println("ERROR:" + e1);
                } catch (SQLException e2Exception) {
                    e2Exception.printStackTrace();
                }
            }else{

            }
        }
        if(e.getActionCommand().equals("返回"))
        {
            this.dispose();
            new gift();
        }

    }
}
