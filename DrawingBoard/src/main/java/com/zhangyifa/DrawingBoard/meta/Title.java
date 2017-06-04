package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Title extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3936621771537750763L;
	private int x1;
	private int y1;
	private String str;
	
	public Title(int x1, int y1,String str, Color color, int width) {
		this.x1 = x1;
		this.y1 = y1;
		this.str = str;
		this.color = color;
		this.width = width;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawString(str, x1, y1);
		
	}

	@Override
	public boolean isSelected(int x, int y) {
		return false;
	}

}