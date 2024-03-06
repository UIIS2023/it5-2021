package strategy;

import java.io.File;

public class SaveManager implements Save {
	private Save saveFile;

	public SaveManager(Save saveFile) {
		this.saveFile=saveFile;
	}
	

	@Override
	public void Save(File file) {
		saveFile.Save(file);

	}
	public void Open(File file) {
		saveFile.Open(file);
	}


}