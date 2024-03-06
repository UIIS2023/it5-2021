package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;
	

	public Rectangle() {
	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {

		this(upperLeftPoint, width, height);
		setSelected(selected);
	}
	public Rectangle(Point upperLeftPoint, int height, int width, Color borderColor, Color innerColor) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
		setColor(borderColor);
		setInnerColor(innerColor);

	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean b, Color borderColor, Color innerColor) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
		setColor(borderColor);
		setInnerColor(innerColor);

	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return upperLeftPoint.getX() <= x && upperLeftPoint.getX() + width >= x && upperLeftPoint.getY() <= y
				&& upperLeftPoint.getY() + width >= y;

	}

	public boolean contains(Point clickPoint) {
		return clickPoint.getX() <= clickPoint.getX() && clickPoint.getX() + width >= clickPoint.getX()
				&& clickPoint.getY() <= clickPoint.getY() && clickPoint.getY() + width >= clickPoint.getY();
	}
	public Rectangle clone()
	{
		Rectangle rectangle=new Rectangle(new Point(1,1),3,5);
		
		try {
			rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
			rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
			rectangle.setHeight(this.getHeight());
			rectangle.setWidth(this.getWidth());
			rectangle.setColor(this.getColor());
			rectangle.setInnerColor(this.getInnerColor());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rectangle;
	}

	public int area() {
		return width * height;
	}

	public int circumference() {
		return 2 * width + 2 * height;

	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.width - 1, this.height - 1);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);

		}
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangleToCompare = (Rectangle) obj;
			return (int) (this.area() - rectangleToCompare.area());
		}
		return 0;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;

	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) throws Exception {
		if (width < 0)
			throw new Exception("Width can't be negative!");

		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) throws Exception {
		if (height < 0)
			throw new Exception("Height can't be negative!");

		this.height = height;
	}

	

	public String toString() {
		return "Rectangle: x=" + upperLeftPoint.getX() + "; y=" + upperLeftPoint.getY() + "; height=" + height + "; width=" + width + "; border color=" + getColor().toString().substring(14).replace('=', '-') + 
				"; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}
}