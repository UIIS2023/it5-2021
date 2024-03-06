package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	private int a;

	public RemoveShapeCmd(DrawingModel model,Shape shape,int a) {
		this.model=model;
		this.shape=shape;
		this.a=a;
		
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.addAtIndex(a, shape);
		//shape.setSelected(true);
		
	}
	public String toLogText() {
		return "Deleted->"+shape.toString();	 
	}

}
