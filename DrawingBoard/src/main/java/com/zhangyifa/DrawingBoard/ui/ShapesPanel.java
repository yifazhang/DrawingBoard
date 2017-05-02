package com.zhangyifa.DrawingBoard.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.zhangyifa.DrawingBoard.meta.Line;
import com.zhangyifa.DrawingBoard.meta.Shape;
import com.zhangyifa.DrawingBoard.service.Observer;
import com.zhangyifa.DrawingBoard.service.CustomMouseListener;

public class ShapesPanel extends JPanel implements Observer{

	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	
	public ArrayList<Shape> getListShape() {
		return listShape;
	}

	public void setListShape(ArrayList<Shape> listShape) {
		this.listShape = listShape;
	}

	public void addShape(Shape shape) {
		listShape.add(shape);
	}
	
	public void removeShape(Shape shape) {
		listShape.remove(shape);
	}
	
//	publis void setShape()
	
	public ShapesPanel() {
		super();
		setBackground(Color.GREEN);
		listShape.add(new Line(0,205,400,205));
		
//		CustomMouseListener listener = new CustomMouseListener(this);
//		addMouseMotionListener(listener);
//		addMouseListener(listener);
        
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : listShape) {
			s.draw(g);
		}
	}

	//监听到一些设置的改变
	@Override
	public void update(Object o, Object arg) {
		if (arg instanceof JButton) {
			JButton button = (JButton)arg;
			String text = button.getText();
			Color color = button.getBackground();
			if (text.isEmpty() || text == null) {
				System.out.println(color);
			} else {
				System.out.println(text);
			}		
		}
	}
	
}
