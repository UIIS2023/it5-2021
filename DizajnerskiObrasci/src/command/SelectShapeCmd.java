package command;

import geometry.Shape;

public class SelectShapeCmd implements Command {
	private Shape shape;
	private boolean selected;

	public SelectShapeCmd(Shape shape,boolean selected) {
		this.selected=selected;
		this.shape=shape;
	}

	@Override
	public void execute() {
		shape.setSelected(selected);

	}

	@Override
	public void unexecute() {
		shape.setSelected(!selected);
		

	}
	public String toLogText() {
		if(shape.isSelected()) {
			return "Selected->" + shape.toString();
		}else {
			return "Unselected->" + shape.toString();
		}
	}

}
