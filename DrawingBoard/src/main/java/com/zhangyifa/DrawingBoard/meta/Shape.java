package com.zhangyifa.DrawingBoard.meta;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5092772309049766863L;
	public Color color;//画笔颜色  
    public int width;//画笔粗细  
    public GeneralPath shape;  //线的构造路径
	public abstract void draw(Graphics2D g);
	public abstract boolean isSelected(int x, int y);
}
