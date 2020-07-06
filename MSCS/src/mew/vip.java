package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class vip extends JFrame implements ActionListener{
	JButton button1,button2,button3,button4;
	vip()
	{
			setTitle("会员管理");
			setLayout(null);
			setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
			setSize(300, 300);
			button1=new JButton("注册会员");
			button1.addActionListener(this);
			button1.setBounds(100, 20, 90, 30);
			button2=new JButton("删除会员");
			button2.addActionListener(this);
			button2.setBounds(100, 80, 90, 30);
			button3=new JButton("更新会员信息");
			button3.addActionListener(this);
			button3.setBounds(90, 140, 120, 30);
			button4=new JButton("返回");
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
	if(e.getActionCommand().equals("注册会员"))
	{
		this.dispose();
		new vipreg();
	}
	if(e.getActionCommand().equals("删除会员"))
	{
		this.dispose();
		new vipdel();
	}
	if(e.getActionCommand().equals("更新会员信息"))
	{	
		this.dispose();
		new vipupd();
	}
	if(e.getActionCommand().equals("返回"))
	{	
		this.dispose();
		new manager();
	}
	}
}
