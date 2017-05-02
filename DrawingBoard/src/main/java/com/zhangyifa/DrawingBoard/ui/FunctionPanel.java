package com.zhangyifa.DrawingBoard.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.zhangyifa.DrawingBoard.service.Observer;

public class FunctionPanel extends Panel implements ActionListener{

	// 这是一个改变标识，来标记该被观察者有没有改变
	private boolean changed = false;
	// 持有一个观察者列表
	private Vector obs = new Vector();
		
	private ArrayList<String> titleArray = new ArrayList<String>();
	private ColorPanel colorPanel;
	
	public FunctionPanel() {
		super();
//		setBackground(Color.YELLOW);
		
		titleArray.add("线条");
		titleArray.add("矩形");
		titleArray.add("文本");
		titleArray.add("圆");
		titleArray.add("疑问");
		
	}
	
	public void setUI() {
		for(int i = 0; i < titleArray.size(); i ++) {
			creatButton(titleArray.get(i), i);
		}
		
		colorPanel = new ColorPanel(this);
		colorPanel.setBounds(0, getHeight() / 6 * 5, getWidth(), getHeight() / 6);
		add(colorPanel);
	}

	private void creatButton(String title, int index) {
		JButton button = new JButton(title);
		button.setBounds(0, getHeight() / 6 * index, getWidth(), getHeight() / 6);
		button.addActionListener(this);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(e.getSource());
	}
	
	// 添加观察者
	public synchronized void addObserver(Observer o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (!obs.contains(o)) {
			obs.addElement(o);
		}
	}

	// 删除观察者
	public synchronized void deleteObserver(Observer o) {
		obs.remove(o);
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	// 通知所有观察者，被观察者改变了，执行你需要的方法
	public void notifyObservers(Object arg) {
		// 一个临时的数组，用于并发访问被观察者时，留住观察者列表的当前状态，这种处理方式其实也算是一种设计模式，即备忘录模式。
		Object[] arrLocal;
		// 注意这个同步块，它表示在获取观察者列表时，该对象是被锁定的
		// 也就是说，在我获取到观察者列表之前，不允许其他线程改变观察者列表
		synchronized (this) {
			// 如果没变化直接返回
			if (!changed) {
				return;
			}
			// 这里将当前的观察者列表放入临时数组
			arrLocal = obs.toArray();
			// 将改变标识重新置回未改变
			clearChanged();
		}
		// 注意这个for循环没有在同步块，此时已经释放了被观察者的锁，其他线程可以改变观察者列表
		// 但是这并不影响我们当前进行的操作，因为我们已经将观察者列表复制到临时数组
		// 在通知时我们只通知数组中的观察者，当前删除和添加观察者，都不会影响我们通知的对象
		for (int i = arrLocal.length - 1; i >= 0; i--) {
			try {
				((Observer) arrLocal[i]).update(this, arg);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	// 删除所有观察者
	public synchronized void deleteObservers() {
		obs.removeAllElements();
	}

	// 标识被观察者被改变过了
	protected synchronized void setChanged() {
		changed = true;
	}

	// 标识被观察者没改变
	protected synchronized void clearChanged() {
		changed = false;
	}

	// 返回被观察者是否改变
	public synchronized boolean hasChanged() {
		return changed;
	}

	// 返回观察者数量
	public synchronized int countObservers() {
		return obs.size();
	}
}
