package com.zhangyifa.DrawingBoard.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;
import com.zhangyifa.DrawingBoard.meta.Circle;
import com.zhangyifa.DrawingBoard.meta.Line;
import com.zhangyifa.DrawingBoard.meta.Rectangle;
import com.zhangyifa.DrawingBoard.meta.Shape;
import com.zhangyifa.DrawingBoard.meta.Title;
import com.zhangyifa.DrawingBoard.ui.ShapesPanel;

public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {
	public Graphics2D g;
	public int x1, y1, x2, y2, ox, oy, x3, y3;
	public String title;
	public Color color;
	public ArrayList<Shape> list;
	public boolean flag = true;
	ShapesPanel shapesPanel;
	private static String command = "直线";
	
	Shape selectShape;
	
	public static final Stroke s1 = new BasicStroke(1);
	public static final Stroke s2 = new BasicStroke(10);
	public static final Stroke s3 = new BasicStroke(15);

	public Random r = new Random();

	public DrawListener(){
	}
	
	public DrawListener(Graphics g2, ShapesPanel sPanel, ArrayList list) {
		g = (Graphics2D) g2;
		shapesPanel = sPanel;
		this.list = list;
	}

	// 鼠标按下事件监听
	public void mousePressed(MouseEvent e) {
		// 获取鼠标按下点的坐标
		x1 = e.getX();
		y1 = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		// 获取鼠标释放的坐标
		x2 = e.getX();
		y2 = e.getY();

		// 直线
		if ("直线".equals(command)) {
			Shape line = new Line(x1, y1, x2, y2, g.getColor(), 1);
			line.draw(g);
			list.add(line);
		} // 矩形
		else if ("矩形".equals(command)) {
			Shape rect = new Rectangle(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2),
					g.getColor(), 1);
			rect.draw(g);
			list.add(rect);
		} // 椭圆
		else if ("圆".equals(command)) {
			Shape oval = new Circle(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1),g.getColor(), 1);
			oval.draw(g);
			list.add(oval);
		} // 文本
		else if ("文本".equals(command)) {
			Shape roundrect = new Title(x1, y1,title, g.getColor(), 1);
			roundrect.draw(g);
			list.add(roundrect);
		} 
	}

	
	public void mouseEntered(MouseEvent e) {
		g.setColor(color);
		g.setStroke(s1);
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		
		for (Shape shape : list) {
			if (shape.isSelected(x, y)) {
				selectShape = shape;
				break;
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("")) {
			JButton but = (JButton) e.getSource();
			color = but.getBackground();
			g.setColor(color);
		} else {
			command = e.getActionCommand();
			if ("文本".equals(command)) {
				title = JOptionPane.showInputDialog("请输入:");
			}else if ("打开".equals(command)) {
				
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
		        jfc.showOpenDialog(new JLabel());  
		        File file=jfc.getSelectedFile();  
		        
		        
				String string = jfc.getSelectedFile().getPath();
				ArrayList<Shape> aList = readObjectFromFile(string);
				list = aList;
				shapesPanel.setListShape(aList);
				shapesPanel.repaint();
				
			} else if ("保存".equals(command)) {
				 //弹出文件选择框  
			    JFileChooser chooser = new JFileChooser();  
			      
			    //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】  
			    int option = chooser.showSaveDialog(null);  
			    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
			        File file = chooser.getSelectedFile();  
			        String path = chooser.getSelectedFile().getPath();
					
					writeObjectToFile(list, path);  
			    }  
				
			}
		}
	}
	 public static <T> void writeObjectToFile(ArrayList<Shape> list, String str) {
	        
	        File file = new File(str);
//			// 判断目标文件所在的目录是否存在
			if (!file.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建父目录
				System.out.println("目标文件所在目录不存在，准备创建它！");
				if (!file.getParentFile().mkdirs()) {
					System.out.println("创建目标文件所在目录失败！");
					return;
				}
			}
			try {
				file.createNewFile();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			
	        
	        FileOutputStream out;
	        try {
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut = new ObjectOutputStream(out);
	            objOut.writeObject(list);
	            objOut.flush();
	            objOut.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static  ArrayList readObjectFromFile(String str) {
	        File file = new File(str);
	        FileInputStream in;
	        ArrayList object = null;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn = new ObjectInputStream(in);
	            object = (ArrayList<Shape>) objIn.readObject();
	            objIn.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return object;
	    }
	
}
