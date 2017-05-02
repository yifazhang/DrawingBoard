package com.zhangyifa.DrawingBoard.service;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;

import com.zhangyifa.DrawingBoard.meta.DrawInfo;
import com.zhangyifa.DrawingBoard.meta.Line;
import com.zhangyifa.DrawingBoard.meta.Shape;
import com.zhangyifa.DrawingBoard.ui.ShapesPanel;

public class CustomMouseListener extends MouseInputAdapter {

	/*
	 * // 3种状态：0无任何状态 1选中直线 2开始画线 int pressIndex = 0;
	 * 
	 * Point oldPoint; Shape selectShape; boolean isDragge;
	 * 
	 * private ShapesPanel shapesPanel;
	 * 
	 * public CustomMouseListener(ShapesPanel shapesPanel) { super();
	 * this.shapesPanel = shapesPanel; }
	 * 
	 * @Override public void mouseDragged(MouseEvent e) {
	 * System.out.println("鼠标拖拽" + e.getPoint());
	 * 
	 * switch (pressIndex) { case 0: break; case 1: Point point = new
	 * Point(e.getX(), e.getY()); int lenx = point.x - oldPoint.x; int leny =
	 * point.y - oldPoint.y; if (selectShape instanceof Line) { Line line =
	 * (Line) selectShape; line.setX1(line.getX1() + lenx);
	 * line.setY1(line.getY1() + leny); line.setX2(line.getX2() + lenx);
	 * line.setY2(line.getY2() + leny); } else {
	 * 
	 * }
	 * 
	 * selectShape.constructShape(); isDragge = true; break; case 2:
	 * selectShape.constructXY1(e.getX(), e.getY());
	 * selectShape.constructShape(); break; default: break;
	 * 
	 * }
	 * 
	 * this.shapesPanel.repaint(); }
	 * 
	 * @Override public void mouseWheelMoved(MouseWheelEvent e) {
	 * System.out.println("鼠标滚轮旋转" + e.getPoint()); }
	 * 
	 * @Override public void mouseMoved(MouseEvent e) {
	 * System.out.println("鼠标移动" + e.getPoint());
	 * 
	 * if (pressIndex != 2) { pressIndex = 0; }
	 * 
	 * // 先清空 for (Shape shape : shapesPanel.getListShape()) {
	 * shape.setSelected(false); } // 正在画 if (pressIndex == 2) { return; } //
	 * 判断是否选中直线 for (Shape shape : shapesPanel.getListShape()) { if
	 * (shape.contains(e.getX(), e.getY())) { shape.setSelected(true); ;
	 * pressIndex = 1; break; } } }
	 * 
	 * @Override public void mouseClicked(MouseEvent e) {
	 * System.out.println("鼠标点击" + e.getPoint()); }
	 * 
	 * @Override public void mousePressed(MouseEvent e) {
	 * System.out.println("鼠标点下去" + e.getPoint()); }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) {
	 * System.out.println("鼠标松开" + e.getPoint());
	 * 
	 * switch (pressIndex) { case 0: break; case 1: Point point = new
	 * Point(e.getX(), e.getY()); int lenx = point.x - oldPoint.x; int leny =
	 * point.y - oldPoint.y; if (selectShape instanceof Line) { Line line =
	 * (Line) selectShape; line.setX1(line.getX1() + lenx);
	 * line.setY1(line.getY1() + leny); line.setX2(line.getX2() + lenx);
	 * line.setY2(line.getY2() + leny); } else {
	 * 
	 * }
	 * 
	 * selectShape.constructShape(); break; case 2:
	 * selectShape.constructXY1(e.getX(), e.getY());
	 * selectShape.constructShape(); shapesPanel.addShape(selectShape);
	 * selectShape.setSelected(true); pressIndex = 0; break; default: break;
	 * 
	 * } // 统一在鼠标释放的时候重绘 shapesPanel.repaint(); }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) {
	 * System.out.println("鼠标进入" + e.getPoint()); }
	 * 
	 * @Override public void mouseExited(MouseEvent e) {
	 * System.out.println("鼠标退出" + e.getPoint()); }
	 */

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private DrawInfo drawInfo;
	private Graphics graphics;// 绘图区域的图形上下文
	private ShapesPanel shapesPanel;
	public CustomMouseListener(ShapesPanel shapesPanel, Graphics graphics) {
		this.shapesPanel = shapesPanel;
		this.drawInfo = shapesPanel.getDrawInfo();
		this.graphics = graphics;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("当鼠标单击事件源的时候响应的方法");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("当鼠标进入事件源的时候执行的方法");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("当鼠标离开事件源的时候执行的方法");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		System.out.println(e.getX() + " " + e.getY());
//		
//		x2 = e.getX();
//		y2 = e.getY();
//		String shape = drawInfo.getShape();
//
//		if (shape.equals("线条")) {
//			graphics.drawLine(x1, y1, x2, y2);
//		} else if (shape.equals("矩形")) {
//			graphics.drawRect(x1, y1, x2 - x1, y2 - y1);
//		} else if (shape.equals("圆")) {
//			graphics.fillRect(x1, y1, x2 - x1, y2 - y1); 
//		} else if (shape.equals("文本")) {
//			graphics.drawString(drawInfo.getText(), x1, y1);
//		}
//	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		String shape = drawInfo.getShape();
		if(drawInfo.getColor() != null) {
			graphics.setColor(drawInfo.getColor());
		}
		if (shape.equals("线条")) {
			graphics.drawLine(x1, y1, x2, y2);
		} else if (shape.equals("矩形")) {
			graphics.drawRect(x1, y1, x2 - x1, y2 - y1);
		} else if (shape.equals("圆")) {
		    graphics.drawOval(x1, y1, x2 - x1, y2 - y1); 
		} else if (shape.equals("文本")) {
			graphics.drawString(drawInfo.getText(), x1, y1);
		}
	}

}
