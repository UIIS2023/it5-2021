package mvc;

import java.awt.Color;

import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.Stack;

import geometry.*;
import observer.Observer;
import strategy.SaveDrawings;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SerializableFile;
import drawing.*;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

public class DrawingController {
	

	DrawingModel model;
	DrawingFrame frame;
	Observer observer;
	private Color innerColor = Color.WHITE;
	private Color borderColor = Color.BLACK;
	private Point startPoint;
	private Boolean secondPoint = false;
	private int numberOfSelectedShapes = 0;
	private Color generalInnerColor = Color.white;
	private Color generalBorderColor = Color.black;
	private ArrayList<Integer> selectedShapes = new ArrayList<Integer>();
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	private DefaultListModel<String> log;
	private SaveManager manager;

	
	public Stack<Command> getUndoStack() {
		return undoStack;
	}

	public Stack<Command> getRedoStack() {
		return redoStack;
	}


	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		log = frame.getList();

		observer=new Observer(frame);
	}

	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		model.addShape(p);

		frame.repaint();
	}

	public void drawingShape(Point mouseClicked) {

		if (frame.getTglbtnPoint().isSelected()) {
			secondPoint = false;
			DlgPoint dlg = new DlgPoint();
			dlg.setPoint(mouseClicked);
			dlg.setVisible(true);
			dlg.setColor(borderColor);
			if (dlg.getPoint() != null) {
				Command cmd = new AddShapeCmd(mouseClicked, model);
				cmd.execute();
				redoStack.clear();
				log.addElement(cmd.toLogText());
				undoStack.push(cmd);
				ObserveChange();

				return;
			}
		}

		else if (frame.getTglbtnLine().isSelected()) {
			if (secondPoint) {

				DlgLine dlg1 = new DlgLine();
				Line line = new Line(startPoint, mouseClicked);
				dlg1.setLine(line);
				dlg1.setColor(borderColor);
				dlg1.setVisible(true);

				if (dlg1.getLine() != null) {
					Command cmd = new AddShapeCmd(dlg1.getLine(), model);
					cmd.execute();
					redoStack.clear();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();

				}
				secondPoint = false;
				return;
			}
			startPoint = mouseClicked;
			secondPoint = true;
			return;

		}

		else if (frame.getTglbtnRectangle().isSelected()) {
			secondPoint = false;
			DlgRectangle dlg = new DlgRectangle();
			dlg.setUpperLeftPoint(mouseClicked);
			dlg.setBorderColor(borderColor);
			dlg.setInnerColor(innerColor);
			dlg.setVisible(true);
			if (dlg.getRectangle() != null) {
				Command cmd = new AddShapeCmd(dlg.getRectangle(), model);
				cmd.execute();
				redoStack.clear();
				log.addElement(cmd.toLogText());
				undoStack.push(cmd);
				ObserveChange();

			}

		}

		else if (frame.getTglbtnCircle().isSelected()) {
			secondPoint = false;
			DlgCircle dlg = new DlgCircle();
			dlg.setCenter(mouseClicked);
			dlg.setBorderColor(borderColor);
			dlg.setInnerColor(innerColor);
			dlg.setVisible(true);
			if (dlg.getCircle() != null) {
				Command cmd = new AddShapeCmd(dlg.getCircle(), model);
				cmd.execute();
				redoStack.clear();
				log.addElement(cmd.toLogText());
				
				undoStack.push(cmd);
				ObserveChange();

			}
		} else if (frame.getTglbtnHexagon().isSelected()) {
			secondPoint = false;
			DlgHexagon dlg = new DlgHexagon();
			dlg.setCenter(mouseClicked);
			dlg.setBorderColor(borderColor);
			dlg.setInnerColor(innerColor);
			dlg.setVisible(true);
			if (dlg.getHexagon() != null) {
				Command cmd = new AddShapeCmd(dlg.getHexagon(), model);
				cmd.execute();
				redoStack.clear();
				log.addElement(cmd.toLogText());
				undoStack.push(cmd);
				ObserveChange();

			}
		}

		else if (frame.getTglbtnDonut().isSelected()) {
			secondPoint = false;
			DlgDonut dlg = new DlgDonut();
			dlg.setCenter(mouseClicked);
			dlg.setInnerColor(innerColor);
			dlg.setBorderColor(borderColor);
			dlg.setVisible(true);
			if (dlg.getDonut() != null) {
				Command cmd = new AddShapeCmd(dlg.getDonut(), model);
				cmd.execute();
				redoStack.clear();
				log.addElement(cmd.toLogText());
				undoStack.push(cmd);
				ObserveChange();

				return;
			}

		}

	}

	public void SelectionControle(Point mouseClicked) {
	
		observer.proba("selekcija");
		

		for (int i = 0; i < model.getShapes().size(); i++) {
			System.out.println("Da li je uslo");

			if (model.getShapes().get(i).contains(mouseClicked.getX(), mouseClicked.getY())) {

				if (!model.getShapes().get(i).isSelected()) {
					System.out.println("Da li je uslo2");
					selectedShapes.add(i);
					numberOfSelectedShapes++;
					Shape shape = model.getShapes().get(i);
					Command cmd = new SelectShapeCmd(shape, true);
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();

					frame.getView().repaint();
					System.out.println(model.getShapes().get(i).isSelected());
					return;

				}
				if (model.getShapes().get(i).isSelected()) {
					System.out.println("Provera");
					// proveri da se brise taj i a na na tom i
					for (int a = 0; a < selectedShapes.size(); a++) {
						if (selectedShapes.get(a) == i) {
							selectedShapes.remove(a);

						}

					}

					numberOfSelectedShapes--;
					System.out.println("Sad ga unselectuje");
					Shape shape = model.getShapes().get(i);
					Command cmd = new SelectShapeCmd(shape, false);
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();

					frame.getView().repaint();
					
					
				}


			}

		}

	}
	public void ObserveChange()
	{
		if(undoStack.size()!=0)
		{
			observer.proba("Undo not null");
			
		}
		if(undoStack.size()==0)
		{
			observer.proba("Undo is null");
			
		}
		if(redoStack.size()!=0)
		{
			observer.proba("Redo not null");
			
		}
		if(redoStack.size()==0)
		{
			observer.proba("Redo is null");
			
		}
		

		if(numberOfSelectedShapes==0)
		{
			observer.proba("Zero selected");
			
		}
		if(numberOfSelectedShapes==1)
		{
			observer.proba("One selected");
			
		}
		if(numberOfSelectedShapes>1)
		{
			observer.proba("Multiply selected");
			
		}
		
		
	
	frame.getView().repaint();
	}

//	public void selectShapes(Point mouseClicked) {
//		frame.getTglbtnPoint().setEnabled(false);
//		frame.getTglbtnLine().setEnabled(false);
//		frame.getTglbtnRectangle().setEnabled(false);
//		frame.getTglbtnCircle().setEnabled(false);
//		frame.getTglbtnDonut().setEnabled(false);
//		frame.getTglbtnHexagon().setEnabled(false);
//		frame.getBtnModify().setEnabled(true);
//		frame.getBtnDelete().setEnabled(true);
//
//		Command cmd = new SelectShapeCmd(mouseClicked, true);
//		cmd.execute();
//		log.addElement(cmd.toLogText());
//		undoStack.push(cmd);
//
//	}

	public void deleteShapes() {
		
		
		
		if (model.getSelected() == -1) {
	
			JOptionPane.showMessageDialog(null, "You need to select shape!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (numberOfSelectedShapes == 1) {


			if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Delete message!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				int a = model.getSelected();
				

				Command cmd = new RemoveShapeCmd(model, model.getShape(model.getSelected()), a);
				cmd.execute();
				log.addElement(cmd.toLogText());
				undoStack.push(cmd);
				numberOfSelectedShapes--;
				ObserveChange();
				frame.getView().repaint();
				
			}
			return;
		}
		if (numberOfSelectedShapes > 1) {
			

			if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Delete message!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
				for (int i = 0; i < numberOfSelectedShapes; i++) {
					Command cmd = new RemoveShapeCmd(model, model.getShape(model.getSelected()), (model.getSelected()));
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();

				}
			numberOfSelectedShapes = 0;

		}
		frame.getView().repaint();

	}

	public void ToFront() {
		if (numberOfSelectedShapes > 1) {

			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front multiple shapes!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			model.unselectAllShapes();
			numberOfSelectedShapes = 0;
			frame.getView().repaint();

			return;

		}
		if (numberOfSelectedShapes == 1) {
			System.out.println("Uslo");
			int index = model.getSelected();
			System.out.println(index);
			Shape shape = model.getShape(index);
			Command cmd = new ToFrontCmd(model, shape);
			cmd.execute();
			log.addElement(cmd.toLogText());
			undoStack.push(cmd);
			ObserveChange();
			System.out.println("Stiglo do kraja");
			frame.getView().repaint();

		}

	}

	public void ToBack() {
		if (numberOfSelectedShapes > 1) {

			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front multiple shapes!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			model.unselectAllShapes();
			numberOfSelectedShapes = 0;
			frame.getView().repaint();

			return;

		}
		if (numberOfSelectedShapes == 1) {
			System.out.println("Uslo");
			int index = model.getSelected();
			System.out.println(index);
			Shape shape = model.getShape(index);
			Command cmd = new ToBackCmd(model, shape);
			cmd.execute();
			log.addElement(cmd.toLogText());
			undoStack.push(cmd);
			ObserveChange();
			System.out.println("Stiglo do kraja");
			frame.getView().repaint();

		}

	}

	public void BringToBack() {
		if (numberOfSelectedShapes > 1) {

			JOptionPane.showMessageDialog(null, "It is not possible to bring to Back  multiple shapes!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			model.unselectAllShapes();
			numberOfSelectedShapes = 0;
			frame.getView().repaint();
			return;

		}
		if (numberOfSelectedShapes == 1) {
			System.out.println("Uslo");
			int index = model.getSelected();
			System.out.println(index);
			Shape shape = model.getShape(index);
			Command cmd = new BringToBackCmd(model, shape);
			cmd.execute();
			log.addElement(cmd.toLogText());
			undoStack.push(cmd);
			ObserveChange();
			System.out.println("Stiglo do kraja");
			frame.getView().repaint();
			

		}

	}

	public void BringToFront() {
		if (numberOfSelectedShapes > 1) {

			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front  multiple shapes!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			model.unselectAllShapes();
			numberOfSelectedShapes = 0;
			frame.getView().repaint();

			return;

		}
		if (numberOfSelectedShapes == 1) {
			System.out.println("Uslo");
			int index = model.getSelected();
			System.out.println(index);
			Shape shape = model.getShape(index);
			Command cmd = new BringToFrontCmd(model, shape);
			cmd.execute();
			undoStack.push(cmd);
			ObserveChange();
			log.addElement(cmd.toLogText());
			System.out.println("Stiglo do kraja");
			frame.getView().repaint();
			
		}

	}

	public void Undo() {
		if (undoStack.size() == 0) {

	
			JOptionPane.showMessageDialog(null, "It is not possible to undo!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;

		}
		log.addElement("Undo->" + undoStack.peek().toLogText());
		Command cmd = undoStack.pop();
		cmd.unexecute();
		redoStack.push(cmd);
		ObserveChange();
		frame.getView().repaint();
		

	}

	public void Redo() {
		if (redoStack.size() == 0) {
		
			JOptionPane.showMessageDialog(null, "It is not possible to redo!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;

		}
		log.addElement("Redo->" + redoStack.peek().toLogText());

		Command cmd = redoStack.pop();
		cmd.execute();

		
		undoStack.push(cmd);
		ObserveChange();
		frame.getView().repaint();

	}

	public Color InnerColor() {
		generalInnerColor = JColorChooser.showDialog(null, "Choose a color", innerColor);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().setInnerColor(generalInnerColor);
		}
		frame.getView().repaint();
		
		if (generalInnerColor != null) {
			innerColor = generalInnerColor;
			return innerColor;
		}
		return generalInnerColor;

		
	}

	public Color BorderColor() {

		generalBorderColor = JColorChooser.showDialog(null, "Choose a color", borderColor);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().setColor(generalBorderColor);
		}
		frame.getView().repaint();
		if (generalBorderColor != null) {
			borderColor = generalBorderColor;
			return borderColor;
		}
		return generalBorderColor;

		
	}

	public void SaveDrawing() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.enableInputMethods(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setDialogTitle("Save");
		chooser.setAcceptAllFileFilterUsed(false);
		if (!model.getShapes().isEmpty()) {
			chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "bin"));
			chooser.setFileFilter(new FileNameExtensionFilter("Picture", "jpeg"));
		}
		if (!undoStack.isEmpty())
			chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (chooser.getFileFilter().getDescription().equals("Serialized draw"))
				manager = new SaveManager(new SerializableFile(model));
			else if (chooser.getFileFilter().getDescription().equals("Commands log"))
				manager = new SaveManager(new SaveLog(frame, model, this));
			else
				manager = new SaveManager(new SaveDrawings(frame));
			manager.Save(chooser.getSelectedFile());
		}
		chooser.setVisible(false);
	}

	public void OpenDrawing() {
		JFileChooser chooser = new JFileChooser();
		chooser.enableInputMethods(true);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		chooser.setEnabled(true);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("Serialized draw", "bin"));
		chooser.setFileFilter(new FileNameExtensionFilter("Commands log", "log"));
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			model.getShapes().clear();
			log.removeAllElements();
			undoStack.clear();
			redoStack.clear();
			frame.getView().repaint();
			if (chooser.getFileFilter().getDescription().equals("Serialized draw")) {
				manager = new SaveManager(new SerializableFile(model));
			
				 
			} else if (chooser.getFileFilter().getDescription().equals("Commands log"))
				manager = new SaveManager(new SaveLog(frame, model, this));
			manager.Open(chooser.getSelectedFile());
		}
		frame.getView().repaint();
		chooser.setVisible(false);
	}

	public void modifyShapes() {

		if (numberOfSelectedShapes > 1) {
			System.out.println(numberOfSelectedShapes);


			JOptionPane.showMessageDialog(null, "It is not possible to modify multiple shapes!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			//model.unselectAllShapes();
			//numberOfSelectedShapes = 0;
			frame.getView().repaint();
			return;
		}
		if (numberOfSelectedShapes == 1) {
			if (model.isEmpty()) {

				return;
			}
			if (model.getSelected() == -1) {
				JOptionPane.showMessageDialog(null, "You have not selected anything!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int index = model.getSelected();

			Shape shape = model.getShape(index);

			if (shape instanceof Point) {
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.setPoint((Point) shape);
				dlgPoint.setVisible(true);
				System.out.println((Point) shape);

				if (dlgPoint.getPoint() != null) {
					System.out.println("Nov stanje tacka je " +dlgPoint.getPoint());
					System.out.println("Stara tacka je " +shape.toString());
					
					Command cmd = new UpdatePointCmd((Point) shape, dlgPoint.getPoint());
					cmd.execute();
					System.out.println("Nova tacka nakon update " +shape.toString());
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					
					ObserveChange();

				}
			} else if (shape instanceof Line) {
				DlgLine dlgLine = new DlgLine();
				dlgLine.setLine((Line) shape);
				dlgLine.setVisible(true);

				if (dlgLine.getLine() != null) {
					Command cmd = new UpdateLineCmd((Line) shape, dlgLine.getLine());
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();
					
				}
			} else if (shape instanceof Rectangle) {
				DlgRectangle dlgRectangle = new DlgRectangle();
				dlgRectangle.setRectangle((Rectangle) shape);
				dlgRectangle.setVisible(true);

				if (dlgRectangle.getRectangle() != null) {

					Command cmd = new UpdateRectangleCmd((Rectangle) shape, (Rectangle) dlgRectangle.getRectangle());
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();
					
				}

			} else if (shape instanceof Donut) {
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.setDonut((Donut) shape);
				dlgDonut.setVisible(true);

				if (dlgDonut.getDonut() != null) {
					Command cmd = new UpdateDonutCmd((Donut) shape, (Donut) dlgDonut.getDonut());
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();
					
				}
			} else if (shape instanceof Circle) {
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setCircle((Circle) shape);
				dlgCircle.setVisible(true);

				if (dlgCircle.getCircle() != null) {

					Command cmd = new UpdateCircleCmd((Circle) shape, (Circle) dlgCircle.getCircle());
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();
					
				}
			} else if (shape instanceof HexagonAdapter) {
				DlgHexagon dlgHexagon = new DlgHexagon();
				dlgHexagon.setHexagon((HexagonAdapter) shape);
				dlgHexagon.setVisible(true);

				if (dlgHexagon.getHexagon() != null) {
					Command cmd = new UpdateHexagonCmd((HexagonAdapter) shape,
							(HexagonAdapter) dlgHexagon.getHexagon());
					cmd.execute();
					log.addElement(cmd.toLogText());
					undoStack.push(cmd);
					ObserveChange();
					
				}
			}

			frame.getView().repaint();

		}
	}

}