package com.zhangyifa.DrawingBoard.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import com.zhangyifa.DrawingBoard.meta.Shape;
public class Board extends JFrame {

	private static final long serialVersionUID = 1L;
	private int width = 800;
	private int height = 500;
	ShapesPanel shapesPanel;
	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	

	public void add(Shape s)
	{
		listShape.add(s);
	}

	public Board()
	{
		shapesPanel = new ShapesPanel();
		shapesPanel.setBackground(Color.WHITE);
		shapesPanel.setPreferredSize(new Dimension(width, height));
		shapesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(shapesPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void draw()
	{
		setLocationRelativeTo(null);
		setSize(width, height);
		setTitle("DrawingBoard");
		FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
		setLayout(f1);
		setVisible(true);
		shapesPanel.setListener();
	}
}
