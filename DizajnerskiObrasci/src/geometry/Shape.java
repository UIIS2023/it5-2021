package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable<Object>,Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// protected boolean selected;
	private boolean selected;

	private Color color=Color.black;
	private Color InnerColor=Color.white;

	public Shape() {

	}

	public Shape(boolean selected) {
		this.selected = selected;

	}

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}
	public Color getInnerColor() {
		return InnerColor;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setInnerColor(Color InnerColor) {
		this.InnerColor = InnerColor;
	}

}