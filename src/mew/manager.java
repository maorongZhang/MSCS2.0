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
			setTitle("管理");
			setLayout(null);
			setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
			setSize(250, 250);//*
			button1=new JButton("库存分析");
			button1.addActionListener(this);
			button1.setBounds(30, 40, 90, 30);
			button2=new JButton("制定计划");
			button2.addActionListener(this);
			button2.setBounds(140, 40, 90, 30);
			button3=new JButton("会员管理");
			button3.addActionListener(this);
			button3.setBounds(30, 100, 90, 30);
			button4=new JButton("员工管理");
			button4.addActionListener(this);
			button4.setBounds(140, 100, 90, 30);
			button5=new JButton("礼品赠送");//*
			button5.addActionListener(this);//*
			button5.setBounds(30, 160, 90, 30);//*
			button6=new JButton("促销计划");//*
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
	if(e.getActionCommand().equals("库存分析"))
	{
		new analyse();
		this.dispose();
	}
	if(e.getActionCommand().equals("商品赠送"))
	{
		new plan();
		this.dispose();
	}
	if(e.getActionCommand().equals("会员管理"))
	{
		new vip();
		this.dispose();
	}
	if(e.getActionCommand().equals("员工管理"))
	{
		new employee();
		this.dispose();
	}
	if(e.getActionCommand().equals("礼品赠送"))//*
	{//*
		this.dispose();//*
		new gift();
	}

	if(e.getActionCommand().equals("促销计划"))
	{
		this.dispose();
		new discount();
	}
	}
}