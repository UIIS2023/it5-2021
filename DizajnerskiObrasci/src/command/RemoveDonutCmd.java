package command;

import geometry.Donut;
import mvc.DrawingModel;

public class RemoveDonutCmd implements Command {
	private Donut donut;
	private DrawingModel model;
	

	public RemoveDonutCmd(Donut donut,DrawingModel model) {
		this.donut=donut;
		this.model=model;

		
	}

	@Override
	public void execute() {
		model.remove(donut);
		
	}

	@Override
	public void unexecute() {
		model.addShape(donut);
	

	}
	public String toLogText() {
		return "Deleted->"+donut.toString();	 
	}

}
