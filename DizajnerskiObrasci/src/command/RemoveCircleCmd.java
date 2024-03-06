package command;

import geometry.Circle;
import mvc.DrawingModel;

public class RemoveCircleCmd implements Command {

	private Circle circle;
	private DrawingModel model;
	
	
	public RemoveCircleCmd(Circle circle,DrawingModel model) {
		this.circle=circle;
		this.model=model;
	
	}

	@Override
	public void execute() {
		model.addShape(circle);

	}

	@Override
	public void unexecute() {
		model.remove(circle);
	}
	public String toLogText() {
		return "Deleted->"+circle.toString();	 
	}

}
