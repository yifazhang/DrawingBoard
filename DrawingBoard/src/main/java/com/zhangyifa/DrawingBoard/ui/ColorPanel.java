package com.zhangyifa.DrawingBoard.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ColorPanel extends Panel implements ActionListener{

	private ArrayList<Color> listColor = new ArrayList<Color>();
	
	private FunctionPanel functionPanel;

	public ColorPanel(FunctionPanel functionPanel) {
		super();
		this.functionPanel = functionPanel;
		
		setLayout(new GridLayout(4, 3));
		initColor();
		
		for (int i = 0; i < listColor.size(); i++) {
			creatButton(listColor.get(i));
		}
	}

	
	private void initColor() {
		listColor.add(Color.BLACK);
		listColor.add(Color.BLUE);
		listColor.add(Color.GRAY);
		
		listColor.add(Color.DARK_GRAY);
		listColor.add(Color.LIGHT_GRAY);
		listColor.add(Color.GREEN);

		listColor.add(Color.CYAN);
		listColor.add(Color.MAGENTA);
		listColor.add(Color.PINK);

		listColor.add(Color.RED);
		listColor.add(Color.WHITE);
		listColor.add(Color.ORANGE);
	}
	
	private void creatButton(Color color) {
		JButton button = new JButton();
		button.setBackground(color);
		button.setOpaque(true);
        button.setBorderPainted(false);
		button.addActionListener(this);
		add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		functionPanel.setChanged();
		functionPanel.notifyObservers(e.getSource());
	}
	
	
	
}
