package mew;

import com.sun.deploy.net.MessageHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class pointGift extends JFrame implements ActionListener {
    String ID;
    DefaultTableModel tableModel;
    JTextField text1;
    JTable table;
    String gName, gNum, gDis, gPrice, gDate, gKeep, gSum,gGiftPoint;

    pointGift(){
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

        panel1.add(new JLabel("会员编号: "));
        text1 = new JTextField("",15);
        panel1.add(text1);

        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("确定");   //添加按钮
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
                key = rs.getString("giftPoint");
                if(key.equals("")==false) {
                    ID = rs.getString("ID");
                    gName = rs.getString("name");
                    gNum = rs.getString("number");
                    gDis = rs.getString("discount");
                    gPrice = rs.getString("price");
                    gDate = rs.getString("date");
                    gKeep = rs.getString("keep");
                    gGiftPoint = rs.getString("giftPoint");
                    String[] rowValues = {ID, gName, gNum, gDis, gPrice, gDate, gKeep,gGiftPoint};
                    tableModel.addRow(rowValues);  //添加一行
                }
            }

            // num = Integer.parseInt(eNum);
        }catch (ClassNotFoundException e1) {
            System.out.println("ERROR:" + e1);
        }catch (SQLException e2Exception) {
            e2Exception.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("确定")) {
            boolean isMember = false;
            boolean ep = false;
            PreparedStatement ps = null;
            String eNum="";
            Connection ct = null;
            ResultSet rs = null;
            String key,mPoint;
            int selectedRow = table.getSelectedRow();//获得选中行的索引
            String sql = "select * from member";

            if(text1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "请输入会员号", "错误！",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                if (selectedRow != -1)  //存在选中行
                {

                    Object oa = tableModel.getValueAt(selectedRow, 0);
                    key = oa.toString();
                    Object a = table.getValueAt(selectedRow, 7);
                    int num = Integer.parseInt(a.toString());
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
                        String Name = "root";
                        String Pwd = "123456";
                        ct = DriverManager.getConnection(url, Name, Pwd);
                        ps = ct.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            String key1 = rs.getString("ID");
                            if (key1.equals(text1.getText())) {

                                mPoint = rs.getString("point");
                                int mp = Integer.parseInt(mPoint);

                                if(mp-num<0)
                                {
                                    JOptionPane.showMessageDialog(null, "积分不足", "错误！",
                                            JOptionPane.ERROR_MESSAGE);
                                }else {
                                    ps = ct.prepareStatement("update member set point=point-" + num + " where ID = ?");
                                    ps.setString(1, key1);
                                    ps.executeUpdate();
                                    ep=true;
                                    // ps = ct.prepareStatement(sql);
                                    //rs = ps.executeQuery();

                                    // while (rs.next()) {
                                    //  if (key.equals(ID)) {
                                    //      mPoint = rs.getString("point");
                                    //   }
                                    //}
                                    // tableModel.setValueAt(gGiftPoint, 0, 7);
                                }
                                isMember = true;
                            }
                        }


                        // num = Integer.parseInt(eNum);
                    } catch (ClassNotFoundException e1) {
                        System.out.println("ERROR:" + e1);
                    } catch (SQLException e2Exception) {
                        e2Exception.printStackTrace();
                    }
                    if(isMember==false){
                        JOptionPane.showMessageDialog(null, "会员号未查询到", "错误！",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(ep==true){
                        sql = "select * from product";
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
                            String Name = "root";
                            String Pwd = "123456";
                            ct = DriverManager.getConnection(url, Name, Pwd);
                            ps = ct.prepareStatement("update product set number=number-1 where ID = ?");
                            ps.setString(1, key);
                            ps.executeUpdate();

                            ps = ct.prepareStatement(sql);
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                String key2 = rs.getString("ID");
                                if (key2.equals(key)) {
                                    eNum = rs.getString("number");
                                }
                            }

                            if(eNum.equals("0")){
                                ps = ct.prepareStatement("delete from product where ID = ?");
                                ps.setString(1, key);
                                ps.executeUpdate();
                                tableModel.setRowCount(0);
                            }
                            else{
                                tableModel.setValueAt(eNum, selectedRow, 2);
                            }
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "赠送成功", "提示",
                                JOptionPane.INFORMATION_MESSAGE);



                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选中赠送商品", "错误！",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getActionCommand().equals("返回"))
        {
            this.dispose();
            new salesperson();
        }
    }
}
