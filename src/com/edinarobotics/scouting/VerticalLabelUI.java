package com.edinarobotics.scouting;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class VerticalLabelUI extends BasicLabelUI {

	private boolean clockwise = false;

	Rectangle verticalViewR = new Rectangle();
	Rectangle verticalIconR = new Rectangle();
	Rectangle verticalTextR = new Rectangle();

	protected static VerticalLabelUI verticalLabelUI = new VerticalLabelUI();

	private static final VerticalLabelUI SAFE_VERTICAL_LABEL_UI = new VerticalLabelUI();

	public VerticalLabelUI() {
	}

	public VerticalLabelUI(boolean clockwise) {
		this.clockwise = clockwise;
	}

	public static ComponentUI createUI(JComponent c) {
		if (System.getSecurityManager() != null) {
			return SAFE_VERTICAL_LABEL_UI;
		}
		return verticalLabelUI;
	}

	public int getBaseline(JComponent c, int width, int height) {
		super.getBaseline(c, width, height);
		return -1;
	}

	public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		super.getBaselineResizeBehavior(c);
		return Component.BaselineResizeBehavior.OTHER;
	}

	protected String layoutCL(JLabel label, FontMetrics fontMetrics, String text, Icon icon, Rectangle viewR,
			Rectangle iconR, Rectangle textR) {
		this.verticalViewR = transposeRectangle(viewR, this.verticalViewR);
		this.verticalIconR = transposeRectangle(iconR, this.verticalIconR);
		this.verticalTextR = transposeRectangle(textR, this.verticalTextR);

		text = super.layoutCL(label, fontMetrics, text, icon, this.verticalViewR, this.verticalIconR,
				this.verticalTextR);

		viewR = copyRectangle(this.verticalViewR, viewR);
		iconR = copyRectangle(this.verticalIconR, iconR);
		textR = copyRectangle(this.verticalTextR, textR);
		return text;
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D) g.create();
		if (this.clockwise)
			g2.rotate(1.570796326794897D, c.getSize().width / 2, c.getSize().width / 2);
		else {
			g2.rotate(-1.570796326794897D, c.getSize().height / 2, c.getSize().height / 2);
		}
		super.paint(g2, c);
	}

	public Dimension getPreferredSize(JComponent c) {
		return transposeDimension(super.getPreferredSize(c));
	}

	public Dimension getMaximumSize(JComponent c) {
		return transposeDimension(super.getMaximumSize(c));
	}

	public Dimension getMinimumSize(JComponent c) {
		return transposeDimension(super.getMinimumSize(c));
	}

	private Dimension transposeDimension(Dimension from) {
		return new Dimension(from.height, from.width + 2);
	}

	private Rectangle transposeRectangle(Rectangle from, Rectangle to) {
		if (to == null) {
			to = new Rectangle();
		}
		to.x = from.y;
		to.y = from.x;
		to.width = from.height;
		to.height = from.width;
		return to;
	}

	private Rectangle copyRectangle(Rectangle from, Rectangle to) {
		if (to == null) {
			to = new Rectangle();
		}
		to.x = from.x;
		to.y = from.y;
		to.width = from.width;
		to.height = from.height;
		return to;
	}

}
