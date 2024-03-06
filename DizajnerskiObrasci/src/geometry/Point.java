package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected) {
		this(x, y); // odnosi se na konstruktor Point
		setSelected(selected);
	}
	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
	
		setColor(color);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this.x = x;
		this.y = y;
		setSelected(selected);
		setColor(color);
	}

	public double distance(int xPoint2, int yPoint2) {
		double dx = this.x - xPoint2;
		double dy = this.y - yPoint2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;

	}
	public Point clone() {
		Point point=new Point();
		try {
			point.setX(this.getX());
			point.setY(this.getY());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return point;
    }

	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;// DOWNCAST PRETVARANJE
			if (this.x == pomocna.x && this.y == pomocna.y)
				return true;
			else
				return false;
		} else
			return false;
	}// obj konvertujem u point zato sto obj ne vidi point
		// REDEFINISALI OVU METODU IZ OBJECT KLASE
		// metoda instance;poziva se nad nekim i odnosi se na taj x y

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
		}

	}

	@Override
	public void moveBy(int x, int y) {

		try {
			setX(this.x + x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.y += y;
	}

	public void moveTo(int x, int y) {
		/* this. */try {
			setX(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.y = y;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Point) {
			Point pointToCompare = (Point) obj;
			return (int) (this.distance(0, 0) - pointToCompare.distance(0, 0));
		}
		return 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) throws Exception {

		if (x < 0)
			throw new Exception("Insert positive value");
		this.x = x;

	}

	public int getY() {
		return y;
	}

	public void setY(int y) throws Exception {
		if (y < 0)
			throw new Exception("Insert positive value");

		this.y = y;
	}

	public String toString() {
		return "Point: x=" + x + "; y=" + y + "; color=" + getColor().toString().substring(14).replace('=', '-');
		// nije ispravno
		// return x.toString();

		// ispravno, ali necemo samo x koordinatu
		// return String.valueOf(x);

	}// postoji u object ovde redefinisana

}