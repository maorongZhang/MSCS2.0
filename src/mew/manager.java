package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class manager  extends JFrame implements ActionListener{
	JButton button1,button2,button3,button4,button5,button6;
	manager()
	{
			setTitle("����");
			setLayout(null);
			setLocationRelativeTo(null);//���ô����λ��Ĭ��Ϊ�������ֵ�����
			setSize(250, 250);//*
			button1=new JButton("������");
			button1.addActionListener(this);
			button1.setBounds(30, 40, 90, 30);
			button2=new JButton("�ƶ��ƻ�");
			button2.addActionListener(this);
			button2.setBounds(140, 40, 90, 30);
			button3=new JButton("��Ա����");
			button3.addActionListener(this);
			button3.setBounds(30, 100, 90, 30);
			button4=new JButton("Ա������");
			button4.addActionListener(this);
			button4.setBounds(140, 100, 90, 30);
			button5=new JButton("��Ʒ����");//*
			button5.addActionListener(this);//*
			button5.setBounds(30, 160, 90, 30);//*
			button6=new JButton("�����ƻ�");//*
			button6.addActionListener(this);//*
			button6.setBounds(140, 160, 90, 30);//*
			add(button1);
			add(button2);
			add(button3);
			add(button4);
			add(button5);//*
			add(button6);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
			
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("������"))
	{
		new analyse();
		this.dispose();
	}
	if(e.getActionCommand().equals("��Ʒ����"))
	{
		new plan();
		this.dispose();
	}
	if(e.getActionCommand().equals("��Ա����"))
	{
		new vip();
		this.dispose();
	}
	if(e.getActionCommand().equals("Ա������"))
	{
		new employee();
		this.dispose();
	}
	if(e.getActionCommand().equals("��Ʒ����"))//*
	{//*
		this.dispose();//*
		new gift();
	}

	if(e.getActionCommand().equals("�����ƻ�"))
	{
		this.dispose();
		new discount();
	}
	}
}