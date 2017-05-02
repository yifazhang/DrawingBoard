package com.zhangyifa.DrawingBoard.meta;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

class MyLine {
	int a, b, c, d;// shape后直线的两个端点
	boolean isSelected;
	boolean isSelectP0; // 选择线段的一个端点
	boolean isSelectP1;// 选择线段的另一个端点
	public static float lineWidth = 0.5f; // 线宽
	static float len = 10.0f; // 线宽的改变主要靠该参数
	GeneralPath shape;
	double distance;
	int x0, y0, x1, y1;

	public void constructShape() {
		int m = Math.round(len / 2);
		a = x0 + m;
		b = y0 + m;
		c = x1 + m;
		d = y1 + m;

		double[] px = new double[4];
		double[] py = new double[4];

		distance = Math.hypot(c - a, d - b);
		if (c == a) {
			px[0] = a + len;
			py[0] = b;
			px[1] = c + len;
			py[1] = d;
			px[2] = c - len;
			py[2] = d;
			px[3] = a - len;
			py[3] = b;
		} else if (d == b) {
			px[0] = a;
			py[0] = b - len;
			px[1] = c;
			py[1] = d - len;
			px[2] = c;
			py[2] = d + len;
			px[3] = a;
			py[3] = b + len;
		} else {
			double sin = (c - a) / distance;
			double cos = (d - b) / distance;
			px[0] = a + len * cos;
			py[0] = b - len * sin;
			px[1] = c + len * cos;
			py[1] = d - len * sin;
			px[2] = c - len * cos;
			py[2] = d + len * sin;
			px[3] = a - len * cos;
			py[3] = b + len * sin;
		}

		shape = new GeneralPath(Path2D.WIND_NON_ZERO);
		shape.moveTo(px[0], py[0]);
		shape.lineTo(px[1], py[1]);
		shape.lineTo(px[2], py[2]);
		shape.lineTo(px[3], py[3]);
		shape.closePath();
	}

	public boolean contains(int x, int y) {
		return Path2D.contains(shape.getPathIterator(null), new Point2D.Float(x, y));
	}
}

/**
 * 在面板上画线，可以选择直线，可以移动、拉伸、删除直线。
 * 
 * @author Limit
 * @date 2011.12.30
 */
public class TestLine extends JPanel {
	class MyMouseListener implements MouseInputListener {
		Point oldPoint;
		boolean isDragge;

		private void constructXY0(MyLine ln, int x, int y) {
			if (x > getWidth())
				ln.x0 = getWidth();
			else if (x < 0)
				ln.x0 = 0;
			else
				ln.x0 = x;
			if (y > getHeight())
				ln.y0 = getHeight();
			else if (y < 0)
				ln.y0 = 0;
			else
				ln.y0 = y;
		}

		private void constructXY1(MyLine ln, int x, int y) {
			if (x > getWidth())
				ln.x1 = getWidth();
			else if (x < 0)
				ln.x1 = 0;
			else
				ln.x1 = x;
			if (y > getHeight())
				ln.y1 = getHeight();
			else if (y < 0)
				ln.y1 = 0;
			else
				ln.y1 = y;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			/** 判断是否移动到某直线上或者直线的端点上 **/
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if (pressIndex != 3)
				pressIndex = 0;
			// 先清空
			for (MyLine line : lines) {
				line.isSelectP0 = false;
				line.isSelectP1 = false;
				line.isSelected = false;
			}
			if (pressIndex == 3) {
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				return;
			}

			// 判断是否选中直线，若是则将光标改变
			for (MyLine line : lines) {
				if (line.contains(e.getX(), e.getY())) {
					line.isSelected = true;
					pressIndex = 1;
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					break;
				}
			}
			// 如果选中了某条直线，再判断该直线的端点是否被选中
			if (selectedLine != null) {
				MyLine line = selectedLine;
				Point p1 = new Point(line.a, line.b); // 左端点
				Point p2 = new Point(line.c, line.d); // 右端点
				Point p = new Point(e.getX(), e.getY());
				if (isRange(p1, p)) {
					line.isSelectP0 = true;
					pressIndex = 2;
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
				if (isRange(p2, p)) {
					line.isSelectP1 = true;
					pressIndex = 2;
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			switch (pressIndex) {
			case 0:
				selectedLine = null;
				for (MyLine line : lines) {
					line.isSelectP0 = false;
					line.isSelectP1 = false;
					line.isSelected = false;
				}
				break;
			case 1:
				for (MyLine line : lines) {
					if (line.isSelected) {
						selectedLine = line;
						break;
					}
				}
				tmpSelectedLine = new MyLine();
				tmpSelectedLine.x0 = selectedLine.x0;
				tmpSelectedLine.x1 = selectedLine.x1;
				tmpSelectedLine.y0 = selectedLine.y0;
				tmpSelectedLine.y1 = selectedLine.y1;
				tmpSelectedLine.constructShape();
				oldPoint = new Point(e.getX(), e.getY());
				break;
			case 2:

				break;
			case 3:
				inLine = new MyLine();
				inLine.x0 = e.getX();
				inLine.y0 = e.getY();
				break;
			default:
				break;

			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			switch (pressIndex) {
			case 0:
				break;
			case 1:
				Point point = new Point(e.getX(), e.getY());
				int lenx = point.x - oldPoint.x;
				int leny = point.y - oldPoint.y;
				tmpSelectedLine.x0 = selectedLine.x0 + lenx;
				tmpSelectedLine.x1 = selectedLine.x1 + lenx;
				tmpSelectedLine.y0 = selectedLine.y0 + leny;
				tmpSelectedLine.y1 = selectedLine.y1 + leny;
				tmpSelectedLine.constructShape();
				isDragge = true;
				break;
			case 2:
				MyLine line = selectedLine;// lines.get(selectPointInWhichLine);
				if (line.isSelectP0) {
					constructXY0(line, e.getX(), e.getY());
				}
				if (line.isSelectP1) {
					constructXY1(line, e.getX(), e.getY());
				}
				line.constructShape();
				break;
			case 3:
				constructXY1(inLine, e.getX(), e.getY());
				inLine.constructShape();
				break;
			default:
				break;

			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			switch (pressIndex) {
			case 0:
				break;
			case 1:
				Point point = new Point(e.getX(), e.getY());
				int lenx = point.x - oldPoint.x;
				int leny = point.y - oldPoint.y;
				selectedLine.x0 += lenx;
				selectedLine.x1 += lenx;
				selectedLine.y0 += leny;
				selectedLine.y1 += leny;
				selectedLine.constructShape();
				tmpSelectedLine = null;
				if (!isDragge && e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
				isDragge = false;
				break;
			case 2:
				MyLine line = selectedLine;
				if (line.isSelectP0) {
					constructXY0(line, e.getX(), e.getY());
				}
				if (line.isSelectP1) {
					constructXY1(line, e.getX(), e.getY());
				}
				line.constructShape();
				break;
			case 3:
				constructXY1(inLine, e.getX(), e.getY());
				inLine.constructShape();
				lines.add(inLine);
				selectedLine = inLine;
				selectedLine.isSelected = true;
				inLine = null;
				pressIndex = 0;
				break;
			default:
				break;

			}
			// 统一在鼠标释放的时候重绘
			repaint();

		}

	}

	class PopupAction extends AbstractAction {

		/**
		*
		*/
		private static final long serialVersionUID = 1L;

		public PopupAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (lines.remove(selectedLine)) {
				selectedLine = null;
				pressIndex = 0;
				// selectWhichLine = -1;
				// selectPointInWhichLine = -1;
				JOptionPane.showMessageDialog(null, "remove success", "Teemo", JOptionPane.INFORMATION_MESSAGE);
			}
			repaint();
		}

	}

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				final JFrame frame = new JFrame();
				frame.setSize(400, 400);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				JButton jb = new JButton("add line");
				final TestLine tl = new TestLine();
				jb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// tl.isDrawingLine = true;
						tl.pressIndex = 3;
					}
				});
				frame.getContentPane().add(jb, BorderLayout.NORTH);
				frame.getContentPane().add(tl, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	private List<MyLine> lines = new ArrayList<MyLine>();
	private MyLine inLine;
	private float range = 4f;
	JPopupMenu popup;
	private MyLine selectedLine; // 选中的line
	MyLine tmpSelectedLine; // 移动时临时的线
	/**
	 * 4种状态：0无任何状态1选中直线2选中端点3开始画线
	 */
	int pressIndex = 0;

	public TestLine() {
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		addMouseMotionListener(ml);
		// addMouseListener(pl);
		constructPopup();
	}

	private void constructPopup() {
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem();
		menuItem.setAction(new PopupAction("remove line"));
		popup.add(menuItem);
	}

	/**
	 * 判断两个点是否离得很近 近的标准用range来横来
	 *
	 * @param a
	 *            第一个点坐标
	 * @param b
	 *            第二点坐标
	 * @return 离得近为真，否则为假
	 */
	private boolean isRange(Point a, Point b) {
		if (Math.hypot(a.x - b.x, a.y - b.y) < range)
			return true;
		return false;
	}

	// 画图的按钮的背景和标签
	protected void paintComponent(Graphics g2) {
		super.paintComponents(g2);
		g2.setColor(new Color(255, 255, 255));
		g2.fillRect(0, 0, getWidth(), getHeight());

		Graphics2D g = (Graphics2D) g2;
		Paint oldPaint = g.getPaint();
		Stroke s = g.getStroke();
		int i = 0;
		for (MyLine line : lines) {
			g.setStroke(new BasicStroke(MyLine.lineWidth));
			g.setPaint(Color.BLACK);
			GeneralPath shape = line.shape;
			g.drawString(i++ + "", line.x0, line.y0);
			int midx = Math.round((line.x0 + line.x1) / 2.0f);
			int midy = Math.round((line.y0 + line.y1) / 2.0f);
			g.drawString(String.format("%.2f", line.distance), midx, midy);
			g.fill(shape);

		}
		// 如果被选择，那么直线的两端应该会有两个黑点
		if (selectedLine != null) {
			MyLine line = selectedLine;
			g.setPaint(Color.BLACK);
			int l = 6;
			g.drawOval(line.a - l / 2, line.b - l / 2, l, l);
			g.drawOval(line.c - l / 2, line.d - l / 2, l, l);
			g.setPaint(Color.GREEN);
			g.fillOval(line.a - l / 2, line.b - l / 2, l, l);
			g.fillOval(line.c - l / 2, line.d - l / 2, l, l);
		}

		if (tmpSelectedLine != null) {
			g.setStroke(new BasicStroke(MyLine.lineWidth));
			g.setPaint(Color.BLACK);
			GeneralPath shape = tmpSelectedLine.shape;
			g.fill(shape);
		}

		// 画正在产生的直线
		if (inLine != null) {
			g.setStroke(new BasicStroke(MyLine.lineWidth));
			g.setPaint(Color.BLACK);
			GeneralPath shape = inLine.shape;
			if (shape != null)
				g.fill(shape);
		}
		g.setPaint(oldPaint);
		g.setStroke(s);
		g2.dispose();

	}
}