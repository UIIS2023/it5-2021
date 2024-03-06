package command;

import geometry.Donut;
import geometry.Point;

public class UpdateDonutCmd implements Command {
	private Donut donut;
	private Donut newState;
	private Donut temp=new Donut(new Point(1,1),3,5);
	

	public UpdateDonutCmd(Donut donut,Donut newState) {
		this.donut=donut;
		this.newState=newState;
	}

	@Override
	public void execute() {
		
		try {
//			temp.setCenter(donut.getCenter());
//			temp.setColor(donut.getColor());
//			temp.setInnerColor(donut.getInnerColor());
//			temp.setRadius(donut.getRadius());
//			temp.setInnerRadius(donut.getInnerRadius());
			
			temp=donut.clone();
			donut.getCenter().setX(newState.getCenter().getX());
			donut.getCenter().setY(newState.getCenter().getY());
		
			donut.setColor(newState.getColor());
			donut.setInnerColor(newState.getInnerColor());
			donut.setRadius(newState.getRadius());
			donut.setInnerRadius(newState.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

	@Override
	public void unexecute() {
		
		try {
			donut.setCenter(temp.getCenter());
			donut.setColor(temp.getColor());
			donut.setInnerColor(temp.getInnerColor());
			donut.setRadius(temp.getRadius());
			donut.setInnerRadius(temp.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		



	}
	public String toLogText() {
		return "Updated->" + donut.toString() + "->" + newState.toString();
	}


}