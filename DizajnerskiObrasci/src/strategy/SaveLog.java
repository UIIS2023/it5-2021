package strategy;

import java.awt.Color;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgParser;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;


public class SaveLog implements Save{
	private BufferedWriter writer;
	private BufferedReader reader;
	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	private DlgParser parser;
	private int index;


	public SaveLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.model = model; 
		this.controller = controller;
	}

	@Override
	public void Save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> list = frame.getList();
			for (int i = 0; i < frame.getList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void Open(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			parser = new DlgParser();
			parser.setFileLog(this);
			parser.addCommand(reader.readLine());
			parser.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void readLine(String command) {
		try {
			String[] commands1 = command.split("->");
		
			switch(commands1[0]) {
				
				case "Added":
					Shape shape = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd=new AddShapeCmd(shape, model);
					cmd.execute();
					controller.getUndoStack().push(cmd);
					frame.getView().repaint();
					frame.getList().addElement("Added->" + shape.toString());
					break;
				case "Updated":
					System.out.println("Uslo u updated");
					Shape oldShape = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					
					int index = model.getIndex(oldShape);
					System.out.println("Index starog oblika je " + index);
					if (oldShape instanceof Point) {
						System.out.println(oldShape.toString());
						Point newPoint = parsePoint(commands1[2].split(":")[1]);
						System.out.println(newPoint.toString());
						Command cmd1=new UpdatePointCmd((Point) model.getShape(index), newPoint);
						cmd1.execute();
						controller.getUndoStack().push(cmd1);
						frame.getView().repaint();
						
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newPoint.toString());
					}
					else if (oldShape instanceof Line) {
						Line newLine = parseLine(commands1[2].split(":")[1]);
						Command cmd2=new UpdateLineCmd((Line) model.getShape(index), newLine);
						cmd2.execute();
						controller.getUndoStack().push(cmd2);
						frame.getView().repaint();
						
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newLine.toString());
					}
					else if (oldShape instanceof Rectangle) {
						Rectangle newRectangle = parseRectangle(commands1[2].split(":")[1]);
						Command cmd3=new UpdateRectangleCmd((Rectangle) model.getShape(index), newRectangle);
						cmd3.execute();
						controller.getUndoStack().push(cmd3);
						frame.getView().repaint();
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newRectangle.toString());
					}
					else if (oldShape instanceof Donut) {
						Donut newDonut = parseDonut(commands1[2].split(":")[1]);
						Command cmd4=new UpdateDonutCmd((Donut) model.getShape(index), newDonut);
						cmd4.execute();
						controller.getUndoStack().push(cmd4);
						frame.getView().repaint();
						
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newDonut.toString());
					}
					else if (oldShape instanceof Circle) {
						Circle newCircle = parseCircle(commands1[2].split(":")[1]);
						Command cmd5=new UpdateCircleCmd((Circle) model.getShape(index), newCircle);
						cmd5.execute();
						controller.getUndoStack().push(cmd5);
						frame.getView().repaint();
						
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newCircle.toString());
					}
					else if (oldShape instanceof HexagonAdapter) {
						System.out.println("USLO U HEKSAGONPARSSER U SHAPE");
						HexagonAdapter newHexagon = parseHexagon(commands1[2].split(":")[1]);
						System.out.println(newHexagon.toString());
						Command cmd6=new UpdateHexagonCmd((HexagonAdapter) model.getShape(index), newHexagon);
						cmd6.execute();
						controller.getUndoStack().push(cmd6);
						frame.getView().repaint();
						
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newHexagon.toString());
					}
					break;
				case "Deleted":
					
					
					Shape deleted = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd99=new RemoveShapeCmd(model, deleted, model.getIndex(deleted));
					cmd99.execute();
					controller.getUndoStack().push(cmd99);
					frame.getView().repaint();
					
					frame.getList().addElement("Deleted->" + deleted.toString());
					break;
				case "To front":
					System.out.println("To front provera");
					Shape shapeMovedToFront = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd7=new ToFrontCmd(model, shapeMovedToFront);
					cmd7.execute();
					controller.getUndoStack().push(cmd7);
					frame.getView().repaint();
					
					frame.getList().addElement("To front->" + shapeMovedToFront.toString());
					break;
				case "To back":
					System.out.println("To back provera");
					Shape shapeMovedToBack = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd88=new ToBackCmd(model, shapeMovedToBack);
					cmd88.execute();
					controller.getUndoStack().push(cmd88);
					frame.getView().repaint();
					
					frame.getList().addElement("To back->" + shapeMovedToBack.toString());
					break;
				case "Bringed to front":
					Shape shapeBringedToFront = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd8=new BringToFrontCmd(model, shapeBringedToFront);
					cmd8.execute();
					controller.getUndoStack().push(cmd8);
					frame.getView().repaint();
					frame.getList().addElement("Bringed to front->" + shapeBringedToFront.toString());
					break;
				case "Bringed to back":
					Shape shapeBringedToBack = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					Command cmd9=new BringToBackCmd(model, shapeBringedToBack);
					cmd9.execute();
					controller.getUndoStack().push(cmd9);
					frame.getView().repaint();
				
					frame.getList().addElement("Bringed to back->" + shapeBringedToBack.toString());
					break;
				case "Undo":
					controller.Undo();
					break;
				case "Redo":
					controller.Redo();
					break;
				case "Selected":
					System.out.println("Uslo u selected provera");
					Shape selectedShape = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
				//index = model.getIndex(selectedShape);
					int index1 = model.getIndex(selectedShape);
					
					Command cmd10=new SelectShapeCmd(model.getShape(index1),true);
					cmd10.execute();
					controller.getUndoStack().push(cmd10);
					frame.getView().repaint();
					
					frame.getList().addElement("Selected->" + selectedShape.toString());
					
					break;
				case "Unselected":
					Shape unselectedShape = ShapeParser(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index2 = model.getIndex(unselectedShape);
					
					Command cmd11=new SelectShapeCmd(model.getShape(index2),false);
					
					cmd11.execute();
					controller.getUndoStack().push(cmd11);
					frame.getView().repaint();
					
					frame.getList().addElement("Unselected->" + unselectedShape.toString());
					
					break;
			}

			String line = reader.readLine();
			if (line != null) parser.addCommand(line);
			else {
				parser.closeDialog();
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Shape ShapeParser(String shape, String shapeParameters) {
		System.out.println("Uslo u shapeparser");
		if (shape.equals("Point")) 
		{
			System.out.println("Uslo u point shape parser");
			String [] pointParts = shapeParameters.split(";"); 		
			int x=Integer.parseInt(pointParts[0].split("=")[1]);
			int y=Integer.parseInt(pointParts[1].split("=")[1])	;
			String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
			String [] colors = s.split(",");
			Color c= new Color (Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1]));
			
			
			return new Point(x, y,c);
			
		}
		
		else if (shape.equals("Line")) {
			String [] lineParts = shapeParameters.split(";"); 	
			int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
			int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
			int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
			int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
			String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
			String [] edgeColors = s.split(",");
			Color c=new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1]));
			Point startP=new Point(xStart,yStart);
			Point endP=new Point(xEnd,yEnd);
			
			return new Line(startP,endP,c);
			
			
		}
		
		else if (shape.equals("Rectangle"))
			{
			String [] rectangleParts = shapeParameters.split(";"); 	
			int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
			int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
			int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
			int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
			String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
			String [] borderColors = s.split(",");
			String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
			String [] innerColors = s1.split(",");
			Point leftP=new Point(x,y);
			Color borderC=new Color(Integer.parseInt(borderColors[0].split("-")[1]),Integer.parseInt(borderColors[1].split("-")[1]), Integer.parseInt(borderColors[2].split("-")[1]));
			Color innerC =new Color(Integer.parseInt(innerColors[0].split("-")[1]), Integer.parseInt(innerColors[1].split("-")[1]), Integer.parseInt(innerColors[2].split("-")[1]));
			return new Rectangle(leftP, width, height,borderC,innerC);
			}
		else if(shape.equals("Hexagon")) 
		{
			System.out.println("menjanje hesakgona");
			String [] hexagonParts = shapeParameters.split(";"); 	
			int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
			int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
			int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
			String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
			String [] borderColors = s.split(",");
			String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
			String [] innerColors = s1.split(",");
			
			Color borderC=new Color(Integer.parseInt(borderColors[0].split("-")[1]), Integer.parseInt(borderColors[1].split("-")[1]), Integer.parseInt(borderColors[2].split("-")[1]));
			Color innerC=new Color(Integer.parseInt(innerColors[0].split("-")[1]), Integer.parseInt(innerColors[1].split("-")[1]), Integer.parseInt(innerColors[2].split("-")[1]));
			
			return new HexagonAdapter(x,y,radius,borderC,innerC);
		}
		else if (shape.equals("Donut"))
			{
			String [] donutParts = shapeParameters.split(";"); 	
			int radius = Integer.parseInt(donutParts[0].split("=")[1]);
			int x = Integer.parseInt(donutParts[1].split("=")[1]);
			int y = Integer.parseInt(donutParts[2].split("=")[1]);
			String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
			String [] borderColors = s.split(",");
			String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
			String [] innerColors = s1.split(",");
			int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
			Color borderC=new Color(Integer.parseInt(borderColors[0].split("-")[1]), Integer.parseInt(borderColors[1].split("-")[1]), Integer.parseInt(borderColors[2].split("-")[1]));
			Color innerC=new Color(Integer.parseInt(innerColors[0].split("-")[1]), Integer.parseInt(innerColors[1].split("-")[1]), Integer.parseInt(innerColors[2].split("-")[1]));
			Point centerP=new Point(x,y);
			return new Donut(centerP, radius, innerRadius,borderC,innerC);
			
			}
		else
		{
			String [] circleParts = shapeParameters.split(";"); 	
			int radius = Integer.parseInt(circleParts[0].split("=")[1]);
			int x = Integer.parseInt(circleParts[1].split("=")[1]);
			int y = Integer.parseInt(circleParts[2].split("=")[1]);
			String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
			String [] borderColors = s.split(",");
			String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
			String [] innerColors = s1.split(",");
			Color borderC=new Color(Integer.parseInt(borderColors[0].split("-")[1]), Integer.parseInt(borderColors[1].split("-")[1]), Integer.parseInt(borderColors[2].split("-")[1]));
			Color innerC=new Color(Integer.parseInt(innerColors[0].split("-")[1]), Integer.parseInt(innerColors[1].split("-")[1]), Integer.parseInt(innerColors[2].split("-")[1]));
			Point centerP=new Point(x,y);
			return new Circle(centerP,radius,borderC,innerC);
		
		}
		
		
	}


	private Point parsePoint(String string) {
		System.out.println("Uslo u point parser");
		String [] pointParts = string.split(";"); 
		System.out.println(pointParts);
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		System.out.println(pointParts[2]);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
	}


	private Circle parseCircle(String string) {
		String [] circleParts = string.split(";"); 	
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Donut parseDonut(String string) {
		String [] donutParts = string.split(";"); 	
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
		return new Donut(new Point(x, y), radius, innerRadius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}


	private Rectangle parseRectangle(String string) {
		String [] rectangleParts = string.split(";"); 	
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Line parseLine(String string) {
		String [] lineParts = string.split(";"); 	
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
	}


	private HexagonAdapter parseHexagon(String string) {
		String [] hexagonParts = string.split(";"); 	
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexagonAdapter(h);
	}

}