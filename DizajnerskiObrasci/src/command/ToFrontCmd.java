package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	
	private int index;
	
	public ToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	
	@Override
	public void execute() {
		index =  model.getIndex(shape);
		System.out.println(model.getIndex(shape));
		
		
		if(index==model.getShapes().size()-1)
		{
			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		model.getShapes().set(index, model.getShape(index+1));
		model.getShapes().set(index+1,shape);
		
		
		
//		model.addAtIndex(index+1, shape);
	//	shape2=model.getShape(index+1)
	//	model.removeAtIndex(index);
	//	model.removeAtIndex(index+1);
	//	model.addAtIndex(index+1, shape);
		
		
		
		
	
	}

	//Vraca index oblika, uklanja ga sa prethodne pozicije i vraca na staru
	@Override
	public void unexecute() {
		index =  model.getIndex(shape);
		if(index!=0)
		{
			model.getShapes().set(index, model.getShape(index-1));
			model.getShapes().set(index-1,shape);
		}
		
	}
	public String toLogText() {
		return "To front->"+shape.toString();
	}


}