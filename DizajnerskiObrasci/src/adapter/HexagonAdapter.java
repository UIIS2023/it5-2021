package adapter;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import geometry.Point;
import hexagon.Hexagon;

public class HexagonAdapter extends geometry.Shape implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon=new Hexagon(1,1,1);
	
	
	
	public HexagonAdapter()
	{
		
	}
   public HexagonAdapter(int x,int y,int r,boolean selected,Color borderColor, Color innerColor)
	
	{
		this.hexagon.setX(x);
		this.hexagon.setY(y);
		this.hexagon.setR(r);
		this.hexagon.setSelected(selected);
	 	this.hexagon.setAreaColor(innerColor);
	 	this.hexagon.setBorderColor(borderColor);
	
	}
   public HexagonAdapter(int x,int y,int r,Color borderColor, Color innerColor)
	
  	{
  		this.hexagon.setX(x);
  		this.hexagon.setY(y);
  		this.hexagon.setR(r);
  
  	 	this.hexagon.setAreaColor(innerColor);
  	 	this.hexagon.setBorderColor(borderColor);
  	
  	}
	
   public HexagonAdapter(Hexagon h)
	
	{
		this.hexagon=h;
	
	}
	public HexagonAdapter(Point p,int r)
	
	{
		this.hexagon=new Hexagon(p.getX(),p.getY(),r);
	
	}
 public HexagonAdapter(Point p,int r,boolean selected)
	
	{
	 	this.hexagon=new Hexagon(p.getX(),p.getY(),r);
	 	this.hexagon.setSelected(selected);
	
	}
  public HexagonAdapter(Point p,int r,boolean selected,Color Color, Color innerColor)
	
	{
	 	this.hexagon=new Hexagon(p.getX(),p.getY(),r);
	 	this.hexagon.setSelected(selected);
	 	this.hexagon.setAreaColor(innerColor);
	 	this.hexagon.setBorderColor(Color);
	
	}
	

	@Override
	public void moveBy(int x, int y) {
		
		
		
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter)
			return hexagon.getR() - ((HexagonAdapter) o).getRadius();
		return 0;
		
	}
	public HexagonAdapter clone()
	{
		HexagonAdapter hexagon=new HexagonAdapter();
		hexagon.setX(this.getX());
		hexagon.setY(this.getY());
		hexagon.setRadius(this.getRadius());
		hexagon.setColor(this.getBorderColor());
		hexagon.setInnerColor(this.getInnerColor());
		return hexagon;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
		
	}
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hex = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; border color=" + getColor().toString().substring(14).replace('=', '-') + 
				"; inner color=" + getInnerColor().toString().substring(14).replace('=', '-');
	}
	public Color getBorderColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		//super.setColor(color);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInnerColor(Color InnerColor) {
		hexagon.setAreaColor(InnerColor);
		//super.setColor(color);
	}
//	public Point  getCenter()
//	{
//		return 
//		
//	}
	
	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setRadius(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public void setY(int y) {
		 hexagon.setY(y);
	}
	public void setX(int x) {
		
		 hexagon.setX(x);
		 System.out.println("prosao set x heksagona");
	}
	public int getY() {
		return hexagon.getY();
	}
	public boolean isSelected()
	{
		return hexagon.isSelected();
	}
	public void setSelected(boolean selected)
	{
		hexagon.setSelected(selected);
	}
	

	
}