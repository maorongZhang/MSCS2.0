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


public class salesperson extends JFrame implements ActionListener{
	JButton button1,button2,button3,button4;
	salesperson()
	{
		setTitle("销售");
		setLayout(null);
		setSize(250, 250);
		setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央
		button1=new JButton("销售");
		button1.addActionListener(this);
		button1.setBounds(50, 40, 60, 30);
		button2=new JButton("退货");
		button2.addActionListener(this);
		button2.setBounds(140, 40, 60, 30);
		button4=new JButton("积分兑换");
		button4.addActionListener(this);
		button4.setBounds(80, 100, 90, 30);
		button3=new JButton("返回登录");
		button3.addActionListener(this);
		button3.setBounds(80, 140, 90, 30);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("销售"))
		{
			this.dispose();
			new sale();
		}
		if(e.getActionCommand().equals("退货"))
		{
			this.dispose();
			new re();
		}
		if(e.getActionCommand().equals("返回登录"))
		{
			this.dispose();
			new login();
		}
		if(e.getActionCommand().equals("积分兑换"))
		{
			this.dispose();
			new pointGift();
		}
	}
}


