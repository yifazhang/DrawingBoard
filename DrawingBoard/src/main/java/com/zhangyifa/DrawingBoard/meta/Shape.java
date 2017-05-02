package com.zhangyifa.DrawingBoard.meta;

import java.awt.Graphics;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public abstract class Shape {
	/**
	 * 边框宽度
	 */
	 int bordWidth = 1;
	/**
	 * 所有路径
	 */
	GeneralPath shape;
	/**
	 * 是否被选中
	 */
	boolean isSelected;
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getBordWidth() {
		return bordWidth;
	}
	
	public void setBordWidth(int bordWidth) {
		this.bordWidth = bordWidth;
	}

	public boolean contains(int x, int y) {
		return Path2D.contains(shape.getPathIterator(null), new Point2D.Float(x, y));
	}
	
	public abstract void constructShape() ;
	
	
	public abstract void draw(Graphics g);
	
	public abstract void constructXY1(int x, int y);
}
