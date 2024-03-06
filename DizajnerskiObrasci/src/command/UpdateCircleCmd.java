package command;

import geometry.Circle;
import geometry.Point;

public class UpdateCircleCmd implements Command {
	private Circle circle;
	private Circle newState;
	private Circle temp=new Circle(new Point(1,1),5);
	

	public UpdateCircleCmd(Circle circle,Circle newState) {
		this.circle=circle;
		this.newState=newState;
		
	}


	@Override
	public void execute() {
	 
	 try {
//	    temp.setCenter(circle.getCenter());
//		temp.setRadius(circle.getRadius());
//		temp.setInnerColor(circle.getInnerColor());
//	    temp.setColor(circle.getColor());
	    
		 temp=circle.clone();
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		circle.setRadius(newState.getRadius());
		circle.setInnerColor(newState.getInnerColor());
	    circle.setColor(newState.getColor());
	    
	    
				
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	 
	}


	@Override
	public void unexecute() {
		
		try {
			circle.setCenter(temp.getCenter());
			circle.setRadius(temp.getRadius());
			circle.setInnerColor(temp.getInnerColor());
		    circle.setColor(temp.getColor());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	    
		
	}
	public String toLogText() {
		return "Updated->" + circle.toString() + "->" + newState.toString();
	}

	

}