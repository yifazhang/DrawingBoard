package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

public class Line extends Shape {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Line(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public void constructShape() {
		int m = Math.round(bordWidth / 4);
		int a1 = x1 + m;
		
		double[] px = new double[4];
		double[] py = new double[4];

		int h = (bordWidth / 2) * (bordWidth / 2) - (a1 - x1) * (a1 - x1);
		
		if (h == 0) {
			px[0] = x1;
			py[0] = y1;
			px[1] = x1;
			py[1] = y1;
			px[2] = x2;
			py[2] = y2;
			px[3] = x2;
			py[3] = y2;
		} else {
			double sin = a1 / m;
			px[0] = a1;
			py[0] = y1 - h;
			px[1] = x2 + m;
			py[1] = y2 - h;
			px[2] = x1 - m;
			py[2] = y1 + h;
			px[3] = x2 - m;
			py[3] = y2 + h;
		}

		shape = new GeneralPath(Path2D.WIND_NON_ZERO);
		shape.moveTo(px[0], py[0]);
		shape.lineTo(px[1], py[1]);
		shape.lineTo(px[2], py[2]);
		shape.lineTo(px[3], py[3]);
		shape.closePath();
	}
	
	@Override
	public void constructXY1(int x, int y) {
		if (x > 300)
			x1 = 300;
		else if (x < 0)
			x1 = 0;
		else
			x1 = x;
		if (y > 300)
			y1 = 300;
		else if (y < 0)
			y1 = 0;
		else
			y1 = y;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(bordWidth));
		g2.drawLine(x1, y1, x2, y2);
	}

	
	
	

}
