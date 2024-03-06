package command;

import geometry.Point;
import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	private Rectangle rectangle=new Rectangle(new Point(1,1),3,5);;
	private Rectangle newState;
	private Rectangle temp=new Rectangle(new Point(1,1),3,5);

	public UpdateRectangleCmd(Rectangle rectangle,Rectangle newState) {
		this.rectangle=rectangle;
		this.newState=newState;
		
	}

	@Override
	public void execute() {
		
		try {
//			temp.setUpperLeftPoint(rectangle.getUpperLeftPoint());
//			temp.setHeight(rectangle.getHeight());
//			temp.setWidth(rectangle.getWidth());
//			temp.setInnerColor(rectangle.getInnerColor());
//			temp.setColor(rectangle.getColor());
			temp=rectangle.clone();
			
			rectangle.setUpperLeftPoint(newState.getUpperLeftPoint());
			rectangle.setHeight(newState.getHeight());
			rectangle.setWidth(newState.getWidth());
			rectangle.setInnerColor(newState.getInnerColor());
			rectangle.setColor(newState.getColor());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		try {
			rectangle.setUpperLeftPoint(temp.getUpperLeftPoint());
			rectangle.setHeight(temp.getHeight());
			rectangle.setWidth(temp.getWidth());
			rectangle.setInnerColor(temp.getInnerColor());
			rectangle.setColor(temp.getColor());
			
		}
		catch(Exception e)
		{
			
		}

	}
	public String toLogText() {
		return "Updated->" + rectangle.toString() + "->" + newState.toString();
	}


}