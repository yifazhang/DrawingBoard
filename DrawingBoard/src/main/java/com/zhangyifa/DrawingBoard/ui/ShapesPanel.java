package com.zhangyifa.DrawingBoard.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zhangyifa.DrawingBoard.meta.DrawInfo;
import com.zhangyifa.DrawingBoard.meta.Line;
import com.zhangyifa.DrawingBoard.meta.Shape;
import com.zhangyifa.DrawingBoard.service.Observer;
import com.zhangyifa.DrawingBoard.service.CustomMouseListener;

public class ShapesPanel extends JPanel implements Observer{

	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	CustomMouseListener listener;
	private DrawInfo drawInfo = new DrawInfo();
	
	public DrawInfo getDrawInfo() {
		return drawInfo;
	}

	public void setDrawInfo(DrawInfo drawInfo) {
		this.drawInfo = drawInfo;
	}

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
			Color color = Color.BLACK;
			if(text.isEmpty()) {
				color = button.getBackground();
			}
			if (color != null) {
				drawInfo.setColor(color);
				System.out.println(color);
			} 
			if(!text.isEmpty() && text != null) {
				drawInfo.setShape(text);
				System.out.println(text);
				
				if(text.equals("文本")) {
					String s = JOptionPane.showInputDialog("请输入:");
					System.out.println("输入的内容是：" + s);
					drawInfo.setText(s);
					listenerAtion();
				}else{
					listenerAtion();
				}
			}
			System.out.println(drawInfo);
			
		}
	}
	
	private void listenerAtion() {
		removeMouseListener(listener);
		listener = new CustomMouseListener(this, getGraphics());
		addMouseMotionListener(listener);
		addMouseListener(listener);
	}
}
