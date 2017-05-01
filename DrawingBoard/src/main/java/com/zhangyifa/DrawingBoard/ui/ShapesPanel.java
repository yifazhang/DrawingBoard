package com.zhangyifa.DrawingBoard.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.zhangyifa.DrawingBoard.meta.Line;
import com.zhangyifa.DrawingBoard.meta.Shape;

public class ShapesPanel extends JPanel {


	Graphics2D graphices2d;
	
	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	

	public ArrayList<Shape> getListShape() {
		return listShape;
	}


	public void setListShape(ArrayList<Shape> listShape) {
		this.listShape = listShape;
	}

	public ShapesPanel() {
		super();
		setBackground(Color.GREEN);
		listShape.add(new Line(0,205,400,205));
		
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : listShape) {
			s.draw(g);
		}
	}
	
}
