package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Circle extends Shape  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2634956091954232627L;
	private int x;
	private int y;
	private int radius;

	public Circle(int x, int y, int radius,Color color,int width)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
        this.color = color;  
        this.width = width;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
		
		shape = new GeneralPath(new Ellipse2D.Double(x, y, radius, radius));
		shape.closePath();
	}

	@Override
	public boolean isSelected(int x, int y) {
		return Path2D.contains(shape.getPathIterator(null), new Point2D.Float(x, y));
	}
	
	
}
