package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class SerializableFile implements Save {

	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private DrawingModel model;

	public SerializableFile(DrawingModel model) {
		this.model = model;
	}

	//https://howtodoinjava.com/java/serialization/custom-serialization-readobject-writeobject/
	@Override
	public void Save(File file) {
		try {
			fileOutputStream = new FileOutputStream(file + ".bin");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(model.getShapes());
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void Open(File file) {
		try {
			System.out.println("Serializable");
			fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//			while(objectInputStream.readObject()!=null)
//			{
//				model.addShape((Shape) objectInputStream.readObject());
//			}
			ArrayList<Shape> temp=(ArrayList<Shape>) objectInputStream.readObject();
		
	        model.addMultiple(temp );
	        
	        System.out.println("Serializable1");
	        objectInputStream.close();
	        fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}