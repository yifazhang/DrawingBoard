package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.awt.Color;

public class Rectangle extends Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6010979763354726924L;
	private int x;
	private int y;
	private int w;
	private int h;

	public Rectangle(int x, int y, int w, int h, Color color, int width) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		this.width = width;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawRect(x, y, w, h);
		shape = new GeneralPath(new Rectangle2D.Double(x, y, w, h));
		shape.closePath();
	}

	@Override
	public boolean isSelected(int x, int y) {
		return Path2D.contains(shape.getPathIterator(null), new Point2D.Float(x, y));
	}

}
