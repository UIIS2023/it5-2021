package command;

import geometry.Point;

public class UpdatePointCmd implements Command {
	private Point point;
	private Point newState;
	private Point temp=new Point(1,1);
	
	public UpdatePointCmd(Point point,Point newState) {
		this.point=point;
		this.newState=newState;
	}

	@Override
	public void execute() {
		temp = point.clone();
		point.moveTo(newState.getX(), newState.getY());
		point.setColor(newState.getColor());
		
	
//		try {
//			temp.setX(point.getX());
//			temp.setY(point.getY());
//			temp.setColor(point.getColor());
//
//			point.setX(newState.getX());
//			point.setY(newState.getY());
//			point.setColor(newState.getColor());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
	}

	@Override
	public void unexecute() {
		
		point.moveTo(temp.getX(), temp.getY());
		point.setColor(temp.getColor());
//		try {
//			point.setX(temp.getX());
//			point.setY(temp.getY());
//			point.setColor(temp.getColor());
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		

	}
	public String toLogText() {
		return "Updated->" + point.toString() + "->" + newState.toString();
	}


}