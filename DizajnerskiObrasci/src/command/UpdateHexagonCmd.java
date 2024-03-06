package command;

import java.awt.Color;

import adapter.HexagonAdapter;
import geometry.Point;

public class UpdateHexagonCmd implements Command {
	HexagonAdapter hexagon;
	HexagonAdapter newState;
	HexagonAdapter temp=new HexagonAdapter(new Point(0,0),0);
	
	

	public UpdateHexagonCmd(HexagonAdapter hexagon,HexagonAdapter newState) {
		this.hexagon=hexagon;
		this.newState=newState;
		
	}

	@Override
	public void execute() {
		System.out.println("Ulazi u execute update heksagona");
//		temp.setX(hexagon.getX());
//		temp.setY(hexagon.getY());
//		temp.setRadius(hexagon.getRadius());
//		temp.setColor(hexagon.getBorderColor());
//		temp.setInnerColor(hexagon.getInnerColor());
		temp=hexagon.clone();		
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		hexagon.setRadius(newState.getRadius());
		hexagon.setColor(newState.getBorderColor());
		hexagon.setInnerColor(newState.getInnerColor());
		
		 
		 
		
	}

	@Override
	public void unexecute() {
		hexagon.setX(temp.getX());
		hexagon.setY(temp.getY());
		hexagon.setRadius(temp.getRadius());
		hexagon.setColor(temp.getBorderColor());
		hexagon.setInnerColor(temp.getInnerColor());
		
		
	}
	public String toLogText() {
		return "Updated->" + hexagon.toString() + "->" + newState.toString();
	}


}