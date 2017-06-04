package com.zhangyifa.DrawingBoard.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.zhangyifa.DrawingBoard.meta.Shape;
import com.zhangyifa.DrawingBoard.service.DrawListener;


public class ShapesPanel extends JPanel {

	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	private ArrayList<JButton> colorList = new ArrayList<JButton>();
	private ArrayList<JButton> titleList = new ArrayList<JButton>();
	
	String shapeArray[] = {"直线","矩形","文本","圆","打开","保存"};
	Color colorArray[] = {Color.BLACK,Color.BLUE,Color.GRAY,Color.DARK_GRAY,
						Color.LIGHT_GRAY,Color.GREEN,Color.CYAN,Color.MAGENTA,
						Color.PINK,Color.RED,Color.WHITE,Color.ORANGE};
	 DrawListener drawl;
	 
	 
	 
	JPanel centerPanel = new JPanel() {
		public void paint(Graphics g1) {  
            Graphics2D  g=(Graphics2D)g1;  
            super.paint(g);  
            for (int i = 0; i <listShape.size(); i++) {  
                Shape shape =(Shape)listShape.get(i);  
                shape.draw(g);
            }  
        }  
	};
	JPanel btnPanel = new JPanel();
	JPanel colorPanel = new JPanel();
	
	
	public String[] getShapeArray() {
		return shapeArray;
	}

	public void setShapeArray(String[] shapeArray) {
		this.shapeArray = shapeArray;
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


	public ShapesPanel() {
		super();
		setBackground(Color.WHITE);
		
		setLayout(null);
		setUI();
		
	}
	
	public void setUI() {
	
		centerPanel.setPreferredSize(new Dimension(780, 380));
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnPanel.setPreferredSize(new Dimension(300, 75));
		btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		for(int i = 0; i < shapeArray.length; i ++) {
			titleList.add(creatButton(shapeArray[i], i));
		}
		
		
		colorPanel.setPreferredSize(new Dimension(300, 75));
		colorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (int i = 0; i < colorArray.length; i++) {
			colorList.add(creatButton(colorArray[i]));
		}
		
		add(btnPanel);
		add(colorPanel);
		add(centerPanel);
		
		 
	}
	
	public void setListener() {
		
		//画笔必须在setVisible后才能拿  
        Graphics g = centerPanel.getGraphics();  
      
        //传递画笔，按钮组管理对象，以及this对象  
        drawl =new DrawListener(g,this,listShape);  
          
        //添加普通鼠标监听器  
        centerPanel.addMouseListener(drawl);  
          
        //添加鼠标拖动监听器  
        centerPanel.addMouseMotionListener(drawl);  
        
        for (JButton button : colorList) {
			button.addActionListener(drawl);
		}
        for (JButton button : titleList) {
        	button.addActionListener(drawl);
		}
	}
	

	private JButton creatButton(Color color) {
		JButton button = new JButton();
		button.setBackground(color);
		button.setPreferredSize(new Dimension(30, 30));
		button.setOpaque(true);
        button.setBorderPainted(false);
		colorPanel.add(button);
		return button;
	}
	
	private JButton creatButton(String title, int index) {
		JButton button = new JButton(title);
		button.setPreferredSize(new Dimension(80, 30));
		btnPanel.add(button);
		return button;
	}


	
	


}
