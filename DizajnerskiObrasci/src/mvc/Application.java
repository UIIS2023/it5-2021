package mvc;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		System.out.println("Dobro dosli na vezbe iz predmeta Dizajnerski obrasci.");

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		//frame
		frame.setSize(900,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
	}
	

}