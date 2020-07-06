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


public class stock extends JFrame implements ActionListener{
	JButton button1,button2;
	stock()
	{
			setTitle("库存");
			setLayout(null);
			setSize(250, 150);
			button1=new JButton("购入商品");
			button1.addActionListener(this);
			button1.setBounds(30, 40, 90, 30);
			button2=new JButton("淘汰商品");
			button2.addActionListener(this);
			button2.setBounds(140, 40, 90, 30);
			add(button1);
			add(button2);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
			
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("购入商品"))
	{
		this.dispose();
		new buy();
	}
	if(e.getActionCommand().equals("淘汰商品"))
	{
		this.dispose();
		new eli();
	}
	}
}


