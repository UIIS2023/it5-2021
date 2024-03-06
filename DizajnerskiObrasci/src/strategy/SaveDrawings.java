package strategy;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.DrawingFrame;

public class SaveDrawings implements Save {
	private DrawingFrame frame;

	public SaveDrawings(DrawingFrame frame) {
		this.frame = frame;
	}
	//Ovim cuvamo sliku
	public void Save(File file)
	{
		BufferedImage imagebuffer = null;
	    try {
	        imagebuffer = new Robot().createScreenCapture(frame.getView().getBounds());
	        frame.getView().paint(imagebuffer.createGraphics());
	        ImageIO.write(imagebuffer,"jpeg", new File(file + ".jpeg"));
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}
	
	@Override
	public void Open(File file) {
		// TODO Auto-generated method stub
		
	}

}