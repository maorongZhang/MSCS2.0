package mew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class gift extends JFrame implements ActionListener {

    JLabel label1;
    JTextField text1;
    JButton button1, button2;
    String x;
    int k=0;

    gift() {
        setTitle("��Ʒ����");
        setLayout(null);
        setSize(300, 180);
        setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
        label1 = new JLabel("��Ʒ���:");
        label1.setBounds(30, 30, 70, 40);
        text1 = new JTextField();
        text1.setBounds(100, 40, 150, 20);
        button1 = new JButton("ȷ��");
        button1.addActionListener(this);
        button1.setBounds(70, 80, 60, 30);
        button2 = new JButton("ȡ��");
        button2.addActionListener(this);
        button2.setBounds(160, 80, 60, 30);
        add(label1);
        add(text1);
        add(button1);
        add(button2);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ȷ��")) {
            PreparedStatement ps = null;
            Connection ct = null;
            ResultSet rs = null;
            String sql = "select * from shop.product";
            try {
                if(text1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "��������Ʒ���", "����",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
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
                            k = 1;
                        }
                    }
                    if (k == 1) {
                        //ps = ct.prepareStatement("insert into product (ID) values(?)");
                       // ps.setString(1, text1.getText());
                       // ps.executeUpdate();
                        this.dispose();
                        new gift_con(text1.getText());
                      //  k = 0;

                    } else {
                        JOptionPane.showMessageDialog((Component)null, "δ��ѯ������Ʒ", "����", 0);
                        this.dispose();
                        new gift();
                    }
                       // k = 0;
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (e.getActionCommand().equals("ȡ��")) {
            new manager();
            this.dispose();
        }
    }
}
