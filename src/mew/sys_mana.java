package mew;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class sys_mana extends JFrame implements ActionListener{
	JButton button1,button2,button3;
	sys_mana()
	{
			setTitle("ϵͳ����");
			setLayout(null);
			setSize(250, 200);
			setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
			button1=new JButton("��Ա����");
			button1.addActionListener(this);
			button1.setBounds(30, 40, 90, 30);
			button2=new JButton("Ա������");
			button2.addActionListener(this);
			button2.setBounds(140, 40, 90, 30);
			button3=new JButton("���ص�¼");
			button3.addActionListener(this);
			button3.setBounds(80, 100, 90, 30);
			add(button1);
			add(button2);
			add(button3);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
			
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("��Ա����"))
	{
		this.dispose();
		new vip();
	}
	if(e.getActionCommand().equals("Ա������"))
	{
		this.dispose();
		new employee();
	}
	if(e.getActionCommand().equals("���ص�¼"))
	{
		this.dispose();
		new login();
	}
	}
}


