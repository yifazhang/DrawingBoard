package com.zhangyifa.DrawingBoard.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class FunctionPanel extends Panel implements ActionListener{

	private ArrayList<String> titleArray = new ArrayList<String>();
	private ColorPanel colorPanel;
	
	public FunctionPanel() {
		super();
//		setBackground(Color.YELLOW);
		
		titleArray.add("线条");
		titleArray.add("矩形");
		titleArray.add("文本");
		titleArray.add("圆");
		titleArray.add("疑问");
		
	}
	
	public void setUI() {
		for(int i = 0; i < titleArray.size(); i ++) {
			creatButton(titleArray.get(i), i);
		}
		
		colorPanel = new ColorPanel();
		colorPanel.setBounds(0, getHeight() / 6 * 5, getWidth(), getHeight() / 6);
		add(colorPanel);
	}

	private void creatButton(String title, int index) {
		JButton button = new JButton(title);
		button.setBounds(0, getHeight() / 6 * index, getWidth(), getHeight() / 6);
		button.addActionListener(this);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("线条")) {
			System.out.println("点击了线条");
		}else if(e.getActionCommand().equals("矩形")) {
			System.out.println("点击了线条");
		}else if(e.getActionCommand().equals("文本")) {
			System.out.println("点击了文本");
		}else if(e.getActionCommand().equals("圆")) {
			System.out.println("点击了圆");
		}else if(e.getActionCommand().equals("疑问")) {
			System.out.println("点击了疑问");
		}
	}
	
}
