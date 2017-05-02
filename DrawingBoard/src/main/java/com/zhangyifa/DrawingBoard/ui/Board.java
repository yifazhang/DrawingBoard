package com.zhangyifa.DrawingBoard.ui;

import java.awt.Container;

import javax.swing.JFrame;
public class Board extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int width = 400;
	private int height = 322;
	private ShapesPanel shapesPanel;
	private FunctionPanel functionPanel;
	
	public Board() {
		super();
		Container conPane = getContentPane();
		conPane.setLayout(null);
		shapesPanel = new ShapesPanel();
		shapesPanel.setBounds(0, 0, 300, 300);
		shapesPanel.setLayout(null);
		conPane.add(shapesPanel);
		
		functionPanel = new FunctionPanel();
		functionPanel.setBounds(300, 0, 100, 300);
		functionPanel.setLayout(null);
		functionPanel.setUI();
		functionPanel.addObserver(shapesPanel);
		conPane.add(functionPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void draw() {
		
		setSize(width, height);
		setTitle("DrawingBoard");
		setResizable(false);
		setVisible(true);
	}
	
}
