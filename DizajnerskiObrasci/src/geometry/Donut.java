package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Donut extends Circle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);// SUPER OD NASLEDJNE KLASE THIS OD OVE
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		/*
		 * this.center= Center;//moze jer je protected this.setRadius(radius);// ne moze
		 * kao iznad jer nije protected ali mozemo da namestimo;ali trudimo se da svi
		 * budu private this.setSelected(selected); this.setInnerRadius(innerRadius);
		 * drugi nacin
		 */
		super(center, radius, selected);// SUPER OD NASLEDJNE KLASE THIS OD OVE
		this.innerRadius = innerRadius;

	}

	/*
	 * public boolean equals(Object obj) { if (obj instanceof Donut) { Donut pomocna
	 * = (Donut) obj; // DOWNCAST PRETVARANJE /* if
	 * (this.center.equals(pomocna.center) && this.getRadius()==pomocni.getR//
	 * return true; else return false; } else
	 */
	// return radius >3;
	// }

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color borderColor, Color innerColor) {
		// TODO Auto-generated constructor stub
		super(center, radius, selected);// SUPER OD NASLEDJNE KLASE THIS OD OVE
		this.innerRadius = innerRadius;
		setColor(borderColor);
		setInnerColor(innerColor);
	

	}
	public Donut(Point center, int radius, int innerRadius, Color borderColor, Color innerColor) {
		// TODO Auto-generated constructor stub
		super(center, radius);// SUPER OD NASLEDJNE KLASE THIS OD OVE
		this.innerRadius = innerRadius;
		setColor(borderColor);
		setInnerColor(innerColor);
	

	}

	// }
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			/*
			 * if (this.center.equals(pomocni.center) && this.getRadius() ==
			 * pomocni.getRadius() && this.innerRadius == pomocni.innerRadius) { return
			 * true; } else { return false; }
			 */
			if (super.equals(pomocni) && this.innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y) && center.distance(x, y) >= innerRadius;
	}

	
	public boolean contains(Point clickPoint) {
		return super.contains(clickPoint) && center.distance(clickPoint.getX(), clickPoint.getY()) >= innerRadius;
	}
	public Donut clone()
	{
		Donut donut=new Donut(new Point(1,1),2,3);
		try {
			donut.getCenter().setX(this.getCenter().getX());
			donut.getCenter().setY(this.getCenter().getY());
			donut.setRadius(this.getRadius());
			donut.setColor(this.getColor());
			donut.setInnerColor(this.getInnerColor());
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donut;
		
		
		
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	@Override
	public void moveTo(int x, int y) {

		super.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {

		super.moveBy(x, y);
	}

	@Override
	public void draw(Graphics g) {
		
		
		
		Graphics2D g2D=(Graphics2D)g.create();
		Ellipse2D outer=new Ellipse2D.Double(center.getX()-radius,center.getY()-radius,2*radius,2*radius);
		Ellipse2D inner=new Ellipse2D.Double(center.getX()-this.getInnerRadius(),center.getY()-this.getInnerRadius(),2*this.getInnerRadius(),2*this.getInnerRadius());
		Area circle=new Area(outer);
		circle.subtract(new Area(inner));
		g2D.setColor(getInnerColor());
		g2D.fill(circle);
		g2D.setColor(getColor());
		g2D.draw(circle);
//		super.draw(g);	
//		g.setColor(getColor());
//		g.drawOval(center.getX() - innerRadius, center.getY() - innerRadius, innerRadius * 2, innerRadius * 2);

		// this.fill(g);
		if (isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - this.innerRadius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + this.innerRadius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - this.innerRadius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + this.innerRadius - 3, 6, 6);

		}
	}

	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius,
				this.innerRadius * 2 - 2, this.innerRadius * 2 - 2);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut donutToCompare = (Donut) obj;
			return (int) (this.area() - donutToCompare.area());
		}
		return 0;
	}

	

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) throws Exception {
		if (innerRadius < 0)
			throw new Exception("Inner radius can't be negative");

		this.innerRadius = innerRadius;
	}
	public String toString() {
		return "Donut: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; border color=" + getColor().toString().substring(14).replace('=', '-') + "; inner color=" + getInnerColor().toString().substring(14).replace('=', '-') + "; inner radius=" + innerRadius;
	}

}