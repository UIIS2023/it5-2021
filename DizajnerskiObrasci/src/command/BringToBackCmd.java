package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	
	private int index;

	public BringToBackCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
		
	}
	
	@Override
	public void execute() {
		index =  model.getIndex(shape);
		
		if(index!=0)
		{
			for(int i=index;i>0;i--)
			{
				Collections.swap(model.getShapes(), i, i-1);
				
				
				}
			model.getShapes().set(0,shape);
			
			
//			model.removeAtIndex(index);
////			model.getShapes().set(index, model.getShape(0));
//			model.getShapes().set(0,shape);
			return;
		}
		JOptionPane.showMessageDialog(null, "It is not possible to bring to Back!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void unexecute() {
		index =  model.getIndex(shape);
		System.out.println(model.getIndex(shape));
		
		
		if(index==model.getShapes().size()-1)
		{
			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		
		
		
		
			for(int i=0;i<model.getShapes().size()-1;i++)
			{
				model.getShapes().set(i,model.getShape(i+1));
				//Collections.swap(model.getShapes(), i, i+1);
				
				
				}
			
			model.getShapes().set(model.getShapes().size()-1,shape);
		//	model.getShapes().set(index,shape);
			//model.getShapes().set(0,shape);
			
			
//			
		
		
	}
	public String toLogText() {
		return "Bringed to back->"+shape.toString();
	}

}