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

public class consGift extends JFrame implements ActionListener {
    String ID;
    DefaultTableModel tableModel;
    JTextField text1;
    JTable table;
    String gName, gNum, gDis, gPrice, gDate, gKeep, gSum,gGiftPoint;

    consGift(){
        String key;
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        String sql = "select * from product";

        setTitle("��Ʒ���͡���������");
        setSize(500, 600);
        setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"��Ʒ���","��Ʒ��","����","�ۿ�","���ۣ�Ԫ��","��������","�����ڣ��죩","�һ�����"};   //����
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

       //panel1.add(new JLabel("��Ա���: "));
       // text1 = new JTextField("",15);
       // panel1.add(text1);

        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("ȷ��");   //���Ӱ�ť
        del.addActionListener(this);
        panel2.add(del);
        final JButton back = new JButton("����");
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
                key = rs.getString("isGift");
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
                    tableModel.addRow(rowValues);  //����һ��
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

        if(e.getActionCommand().equals("ȷ��")) {
            PreparedStatement ps = null;
            String eNum="";
            Connection ct = null;
            ResultSet rs = null;
            String key,mPoint;
            int selectedRow = table.getSelectedRow();//���ѡ���е�����
            String sql = "select * from product";


            if (selectedRow != -1)  //����ѡ����
                {

                    Object oa = tableModel.getValueAt(selectedRow, 0);
                    key = oa.toString();
                    //Object a = table.getValueAt(selectedRow, 7);
                   // int num = Integer.parseInt(a.toString());
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


                        // num = Integer.parseInt(eNum);
                    } catch (ClassNotFoundException e1) {
                        System.out.println("ERROR:" + e1);
                    } catch (SQLException e2Exception) {
                        e2Exception.printStackTrace();
                    }

                        JOptionPane.showMessageDialog(null, "���ͳɹ�", "��ʾ",
                                JOptionPane.INFORMATION_MESSAGE);

                    this.dispose();
                    new salesperson();
                } else {
                    JOptionPane.showMessageDialog(null, "��ѡ��������Ʒ", "����",
                            JOptionPane.ERROR_MESSAGE);
                }

        }

        if(e.getActionCommand().equals("����"))
        {
            this.dispose();
            new salesperson();
        }
    }
}