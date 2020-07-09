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
        String options[]={"���ֶһ�","��150�һ�"};

        int value=JOptionPane.showOptionDialog(null, "ѡ��һ��������",

                "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,

                options, "���ֶһ�");
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

        setTitle("��Ʒ���͡�������Ʒ");
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


        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("����");   //��Ӱ�ť
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
            tableModel.addRow(rowValues);  //���һ��
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

        setTitle("��Ʒ���͡����ֶһ�");
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

        panel1.add(new JLabel("���öһ�����: "));
        text1 = new JTextField("",15);
        panel1.add(text1);

        final JPanel panel2 = new JPanel();
        getContentPane().add(panel2,BorderLayout.SOUTH);

        final JButton del = new JButton("ȷ��");   //��Ӱ�ť
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
            tableModel.addRow(rowValues);  //���һ��
        }catch (ClassNotFoundException e1) {
            System.out.println("ERROR:" + e1);
        }catch (SQLException e2Exception) {
            e2Exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("ȷ��")) {
            String key;
            if (text1.getText().equals("") == false) {
                int input = Integer.parseInt(text1.getText());
                if (input <= 0) {
                    JOptionPane.showMessageDialog(null, "������д����", "��д����",
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
                    JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "���������", "��д����",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getActionCommand().equals("����"))
        {
            PreparedStatement ps = null;
            Connection ct = null;
            ResultSet rs = null;
            String sql = "select * from product";
            if(JOptionPane.showConfirmDialog(null, "��ȷ���Ƿ񽫴���Ʒ����Ϊ��Ʒ", "ȷ��",
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
                    JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ",
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
        if(e.getActionCommand().equals("����"))
        {
            this.dispose();
            new gift();
        }

    }
}
