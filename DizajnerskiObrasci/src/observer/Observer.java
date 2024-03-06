package observer;



import javax.swing.JButton;
import javax.swing.JToggleButton;

import mvc.DrawingFrame;

public class Observer  {

	DrawingFrame frame;

	public Observer(DrawingFrame frame) {
		this.frame = frame;
	}

	public void addListener(JButton button) {
		if (!button.isEnabled()) {
			button.setEnabled(true);
			
		}
	}

	public void removeListener(JButton button) {
		if (button.isEnabled()) {
			
			button.setEnabled(false);
		}
	}
	public void addListenerT(JToggleButton button) {
		if (!button.isEnabled()) {
			button.setEnabled(true);
			
		}
	}

	public void removeListenerT(JToggleButton button) {
		if (button.isEnabled()) {
		
			button.setEnabled(false);
		}
	}
	
	
	public void proba(String s)
	{
		System.out.println("SELECIJA USLO U PROBU");
		if(s=="selekcija")
		{
			removeListenerT(frame.getTglbtnCircle());
			removeListenerT(frame.getTglbtnLine());
			removeListenerT(frame.getTglbtnDonut());
			removeListenerT(frame.getTglbtnPoint());
			removeListenerT(frame.getTglbtnHexagon());
			removeListenerT(frame.getTglbtnRectangle());
			return;
		}
		else if(s=="Zero selected")
		{
			removeListener(frame.getBtnDelete());
			removeListener(frame.getBtnModify());
			removeListener(frame.getBtnBringToBack());
			removeListener(frame.getBtnBringToFront());
			removeListener(frame.getBtnToBack());
			removeListener(frame.getBtnToFront());
		}
		else if(s=="One selected")
		{
			addListener(frame.getBtnDelete());
			addListener(frame.getBtnModify());
			addListener(frame.getBtnBringToBack());
			addListener(frame.getBtnBringToFront());
			addListener(frame.getBtnToBack());
			addListener(frame.getBtnToFront());
		}
		else if(s=="Multiply selected")
		{
			addListener(frame.getBtnDelete());
			removeListener(frame.getBtnModify());
			removeListener(frame.getBtnBringToBack());
			removeListener(frame.getBtnBringToFront());
			removeListener(frame.getBtnToBack());
			removeListener(frame.getBtnToFront());
		}
		else if(s=="Undo not null")
		{
			addListener(frame.getBtnUndo());
			
		}
		else if(s=="Undo is null")
		{
			removeListener(frame.getBtnUndo());
			
			
		}
		else if(s=="Redo not null")
		{
			addListener(frame.getBtnRedo());
			
		}
		else if(s=="Redo is null")
		{
			removeListener(frame.getBtnRedo());
			
			
		}
		
		
		
		
		
		
		

		

	

	
	}

}