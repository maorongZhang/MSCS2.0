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
	JButton button1,button2;
	salesperson()
	{
			setTitle("����");
			setLayout(null);
			setSize(250, 150);
			button1=new JButton("����");
			button1.addActionListener(this);
			button1.setBounds(50, 40, 60, 30);
			button2=new JButton("�˻�");
			button2.addActionListener(this);
			button2.setBounds(140, 40, 60, 30);
			add(button1);
			add(button2);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
			
	public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("����"))
	{		
		this.dispose();
		new sale();
	}
	if(e.getActionCommand().equals("�˻�"))
	{
		this.dispose();
		new re();
	}
	}
}


