package com.nt.jdbc3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ScrollableGUI extends JFrame implements ActionListener{
	public ScrollableGUI() {
		setTitle("GUIFrontEnd-Scroll");
		System.out.println("GUIFrontEnd-Scroll Frame- 0 param constructor (Start)");
		setSize(300,300);
		setLayout(new FlowLayout());
		
		JLabel lsno = new JLabel("sno");
		add(lsno);
		JTextField tsno = new JTextField(10);
		add(tsno);
		
		JLabel lsname = new JLabel("sname");
		add(lsname);
		JTextField tsname = new JTextField(10);
		add(tsname);
		
		JLabel ladd = new JLabel("sadd");
		add(ladd);
		JTextField tadd = new JTextField(10);
		add(tadd);
		
		JLabel lavg = new JLabel("average");
		add(lavg);
		JTextField tavg = new JTextField(10);
		add(tavg);
		
		JButton bt1 = new JButton("First");
		JButton bt2 = new JButton("next");
		JButton bt3 = new JButton("previous");
		JButton bt4 = new JButton("last");
		add(bt1);add(bt2);add(bt3);add(bt4);
		
		//register and actiovate actionlistener for all 4 buttons
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing of rame window will terminates
		                                               //application flow
		System.out.println("End");
	}
	public static void main(String[] args) {
		new ScrollableGUI();//anonymous object
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("GUIScrollFrame Action performed");
	}
}
