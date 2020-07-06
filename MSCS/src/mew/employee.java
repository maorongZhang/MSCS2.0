package mew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class employee extends JFrame implements ActionListener{
	JButton button1,button2,button3,button4;
	employee()
	{
			setTitle("员工管理");
			setLayout(null);
			setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
			setSize(300, 300);
			button1=new JButton("登记员工");
			button1.addActionListener(this);
			button1.setBounds(100, 20, 90, 30);
			button2=new JButton("删除员工");
			button2.addActionListener(this);
			button2.setBounds(100, 80, 90, 30);
			button3=new JButton("更新员工信息");
			button3.addActionListener(this);
			button3.setBounds(90, 140, 120, 30);
			button4=new JButton("返 回");
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
	if(e.getActionCommand().equals("登记员工"))
	{
		this.dispose();
		new emreg();
	}
	if(e.getActionCommand().equals("删除员工"))
	{
		this.dispose();
		new emdel();
	}
	if(e.getActionCommand().equals("更新员工信息"))
	{	
		this.dispose();
		new emupd();
	}
	if(e.getActionCommand().equals("返回"))
	{	
		this.dispose();
		new manager();
	}
	}
}
