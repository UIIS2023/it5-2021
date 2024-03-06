package command;

import geometry.Rectangle;
import mvc.DrawingModel;

public class AddRectangleCmd implements Command {
	private Rectangle rectangle;
	private DrawingModel model;
	
	public AddRectangleCmd(Rectangle rectangle,DrawingModel model)
	{
		this.rectangle=rectangle;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.addShape(rectangle);
	}

	@Override
	public void unexecute() {
		model.remove(rectangle);

	}

	public String toLogText() {
		return "Added->" + rectangle.toString();
	}
}