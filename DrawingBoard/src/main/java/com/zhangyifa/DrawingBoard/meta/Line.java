package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Line extends Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4826392855993984382L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Line(int x1, int y1, int x2, int y2, Color color, int width) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.width = width;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawLine(x1, y1, x2, y2);
		shape = new GeneralPath(new Rectangle2D.Double(x1, y1, x2, y2));
		shape.closePath();
	}

	@Override
	public boolean isSelected(int x, int y) {
		return Path2D.contains(shape.getPathIterator(null), new Point2D.Float(x, y));
	}

}
