package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point center;
	public int radius;
	

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	public Circle(Point center, int radius, Color borderColor, Color innerColor) {
		// TODO Auto-generated constructor stub
		this.center = center;
		this.radius = radius;
		setColor(borderColor);
		setInnerColor(innerColor);
	}

	public Circle(Point center, int radius, boolean selected, Color borderColor, Color innerColor) {
		// TODO Auto-generated constructor stub
		this.center = center;
		this.radius = radius;
		setSelected(selected);
		setColor(borderColor);
		setInnerColor(innerColor);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			if (this.center.equals(pomocni.center) && this.radius == pomocni.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}
	public Circle clone()
	{
		Circle circle =new Circle(new Point(1,1),2);
		try {
			circle.getCenter().setX(this.getCenter().getX());
			circle.getCenter().setY(this.getCenter().getY());
			circle.setRadius(this.getRadius());
			circle.setColor(this.getColor());
			circle.setInnerColor(this.getInnerColor());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return circle;
		
		
	}
	

	public boolean contains(Point clickPoint) {
		return center.distance(clickPoint.getX(), clickPoint.getY()) <= 2;
	}

	public double area() {
		return radius * radius * Math.PI;

	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2,
				this.radius * 2 - 2);

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		this.fill(g);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - this.radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + this.radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - this.radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + this.radius - 3, 6, 6);
		}

	}

	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Circle) {
			Circle circleToCompare = (Circle) obj;
			return (int) (this.area() - circleToCompare.area());
		}
		return 0;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius < 0)
			throw new Exception("Radius can't be negative");

		this.radius = radius;
	}

	

	public String toString() {
		return "Circle: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; border color=" + getColor().toString().substring(14).replace('=', '-') + 
				"; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}

}