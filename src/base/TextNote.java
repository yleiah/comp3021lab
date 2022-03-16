package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class TextNote extends Note{
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	//lab5
	public TextNote(File f) {
   	    super(f.getName());
   	    this.content = getTextFromFile(f.getAbsolutePath());
    }
	
	public TextNote(String title, String Content) {
		super(title);
		content = Content;
	}
	
	public String getContent() {
		return content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
		    FileInputStream fis = new FileInputStream(absolutePath);
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    InputStreamReader isr = new InputStreamReader(ois);
		    BufferedReader br = new BufferedReader(isr);
		    String temp = null;
		    while ((temp = br.readLine()) != null) {
		    	result = result + temp;
            }
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "") {
			pathFolder = ".";
		}
		File file = new File( pathFolder + File.separator +this.getTiltle().replace(" ", "_") +".txt");
		try {
		    FileWriter fw = new FileWriter(file);
		    fw.write(this.toString());
		    fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
