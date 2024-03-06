package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private Point startPoint;
	private Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		super(selected);
		this.startPoint = startPoint;
		this.endPoint = endPoint;

	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setSelected(selected);
		setColor(color);
	}
	public Line(Point startPoint, Point endPoint, Color color) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setColor(color);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return this.startPoint.distance(x, y) + this.endPoint.distance(x, y) - length() <= 2;
	}

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX() - 3, startPoint.getY() - 3, 6, 6);
			g.drawRect(endPoint.getX() - 3, endPoint.getY() - 3, 6, 6);
		}

	}

	@Override
	public void moveTo(int x, int y) {
		startPoint.moveTo(x, y);
		endPoint.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line) {
			Line lineToCompare = (Line) obj;
			return (int) (this.length() - lineToCompare.length());
		}
		return 0;
	}

	public Line clone() {
		Line line = new Line(new Point(1,1),new Point(2,2));

		try {
			line.getStartPoint().setX(this.getStartPoint().getX());
			line.getStartPoint().setY(this.getStartPoint().getY());
			line.getEndPoint().setX(this.getEndPoint().getX());
			line.getEndPoint().setY(this.getEndPoint().getY());
			line.setColor(this.getColor());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		return line;
    }
	public void setStartPoint(Point StartPoint) {
		this.startPoint = StartPoint;

	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line: start point x=" + startPoint.getX() + "; start point y=" +startPoint .getY() + "; end point x=" + endPoint.getX() + "; end point y=" + endPoint.getY() + "; color=" + getColor().toString().substring(14).replace('=', '-');
	}

}