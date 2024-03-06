package command;

import geometry.Line;
import mvc.DrawingModel;

public class AddLineCmd implements Command {
	
	private Line line;
	private DrawingModel model;
	
	public AddLineCmd(Line line,DrawingModel model) {
		this.line=line;
		this.model=model;
	}

	@Override
	public void execute() {
		model.addShape(line);
		
	}

	@Override
	public void unexecute() {
		model.remove(line);
		
	}
	public String toLogText() {
		return "Added->" + line.toString();
	}

}
