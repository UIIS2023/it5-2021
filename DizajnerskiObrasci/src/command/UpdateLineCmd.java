package command;

import geometry.Line;
import geometry.Point;

public class UpdateLineCmd implements Command {
	private Line line;
	private Line newState;
	private Line temp=new Line(new Point(1,1),new Point(2,3));
	
	
	public UpdateLineCmd(Line line,Line newState) {
	this.line=line;
	this.newState=newState;
	
	}

	@Override
	public void execute() {
		temp=line.clone();
		
//		temp.setStartPoint(line.getStartPoint());
//		temp.setEndPoint(line.getEndPoint());
//		temp.setColor(line.getColor());
//		
		line.setStartPoint(newState.getStartPoint());
		line.setEndPoint(newState.getEndPoint());
		line.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		line.setStartPoint(temp.getStartPoint());
		line.setEndPoint(temp.getEndPoint());
		line.setColor(temp.getColor());
		

	}
	public String toLogText() {
		return "Updated->" + line.toString() + "->" + newState.toString();
	}


}