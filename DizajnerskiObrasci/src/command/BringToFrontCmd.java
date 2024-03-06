package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	
	private int index;

	public BringToFrontCmd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
		
	}

	@Override
	public void execute() {
		index =  model.getIndex(shape);
		System.out.println(model.getIndex(shape));
		
		
		if(index==model.getShapes().size()-1)
		{
			JOptionPane.showMessageDialog(null, "It is not possible to bring to Front!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
     
		
		
			for(int i=index;i<model.getShapes().size()-1;i++)
			{
				Collections.swap(model.getShapes(), i, i+1);
				
				
				}
	}

	@Override
	public void unexecute() {
		for(int i=model.getShapes().size()-1;i>index;i--)
		{
			Collections.swap(model.getShapes(), i, i-1);
			
			
			}
	}
	
	public String toLogText() {
		return "Bringed to front->"+shape.toString();
	}

}