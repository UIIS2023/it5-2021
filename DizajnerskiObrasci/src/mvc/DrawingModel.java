package mvc;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private boolean selected=false;
	
	public void addShape(Shape shape) {
		System.out.println("Dodao ga u model");
		shapes.add(shape);
		
	}
	public void addAtIndex(Integer i,Shape shape) {
		shapes.add(i, shape);
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
		
		

	}
	public void removeAtIndex(Integer i) {
		shapes.remove(i);
	}
//	public void select(Point point) {
//		for (int i = shapes.size() - 1; i >= 0; i--) {
////		\
//				shapes.get(i).setSelected(true);
//		
////				if(shapes.get(i).isSelected() && selected)
////				{
////					shapes.get(i).setSelected(false);
////					//repaint();
////					selected=false;
////					
////				}
////				selected=true;
////				return;
////				}
////			
////			
////			
////			selected=false;	
////			
////		} 
////		//selected=false;
//		
//
//	}
//		
//	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
	public int getSelected() {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}
	public void remove() {
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				shapes.remove(i);
			}
			
		}
		

	}
	
	public void unselectAllShapes() {
		for (int i = 0; i < shapes.size(); i++) {
			shapes.get(i).setSelected(false);
		}
		
	}
	public void unselectShape(int i) {
		
			shapes.get(i).setSelected(false);
		
		
	}
	public void selectShape(int i) {
		System.out.println("Da li je uslo3");
		shapes.get(i).setSelected(true);
	
	
}
	public int getIndex(Shape shape) {
		return shapes.indexOf(shape);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void addMultiple(ArrayList<Shape> object) {
		this.shapes.addAll(object);
		
	}
	
	
	
	
	
}