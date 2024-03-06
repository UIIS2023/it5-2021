package command;

import geometry.Donut;
import mvc.DrawingModel;

public class AddDonutCmd implements Command {
	private Donut donut;
	private DrawingModel model;
	

	public AddDonutCmd(Donut donut,DrawingModel model) {
		this.donut=donut;
		this.model=model;

		
	}

	@Override
	public void execute() {
		model.addShape(donut);
		
	}

	@Override
	public void unexecute() {
		model.remove(donut);

	}
	public String toLogText() {
		return "Added->" + donut.toString();
	}

}
