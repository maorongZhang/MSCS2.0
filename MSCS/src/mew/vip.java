package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class vip extends JFrame implements ActionListener{
	JButton button1,button2,button3,button4;
	vip()
	{
			setTitle("��Ա����");
			setLayout(null);
			setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
			setSize(300, 300);
			button1=new JButton("ע���Ա");
			button1.addActionListener(this);
			button1.setBounds(100, 20, 90, 30);
			button2=new JButton("ɾ����Ա");
			button2.addActionListener(this);
			button2.setBounds(100, 80, 90, 30);
			button3=new JButton("���»�Ա��Ϣ");
			button3.addActionListener(this);
			button3.setBounds(90, 140, 120, 30);
			button4=new JButton("����");
			button4.addActionListener(this);
			button4.setBounds(100, 200, 90, 30);
			
			add(button1);
			add(button2);
			add(button3);
			add(button4);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
			
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("ע���Ա"))
	{
		this.dispose();
		new vipreg();
	}
	if(e.getActionCommand().equals("ɾ����Ա"))
	{
		this.dispose();
		new vipdel();
	}
	if(e.getActionCommand().equals("���»�Ա��Ϣ"))
	{	
		this.dispose();
		new vipupd();
	}
	if(e.getActionCommand().equals("����"))
	{	
		this.dispose();
		new manager();
	}
	}
}
