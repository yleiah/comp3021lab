package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addNote(Note newNote) {
		notes.add(newNote);
	}
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}
	
	public boolean equals(Folder folder) {
		return this.name.equals(folder.getName());
	}
	
	@Override
	public String toString(){
		int nText = 0;
		int nImage = 0;
		for (Note note:notes) {
            if(note instanceof TextNote) {
            	nText++;
            }
            else if(note instanceof ImageNote) {
            	nImage++;
            }
        }
		return name+":"+nText+":"+nImage;
	}
}
