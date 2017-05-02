package com.zhangyifa.DrawingBoard.meta;

import java.awt.Color;

public class DrawInfo {
	//图像
	private String shape;
	//颜色
	private Color color;
	//文本
	private String text;
	
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "DrawInfo [shape=" + shape + ", color=" + color + ", text=" + text + "]";
	}
	
}
