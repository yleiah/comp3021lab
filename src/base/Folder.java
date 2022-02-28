package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
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
	
	@Override
	public int compareTo(Folder f) {
		return name.compareTo(f.getName());
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		
		String[] keywordList = keywords.split(" ");
		ArrayList<ArrayList<String>> keyWordsCache = new ArrayList<ArrayList<String>>();
		ArrayList<Note> out = new ArrayList<Note>();
		
		for(int i = 0; i < keywordList.length; i++) {
			ArrayList<String> tempList = new ArrayList<String>();
			if(i == keywordList.length-1  || !keywordList[i+1].equalsIgnoreCase("or")) {
				tempList.add(keywordList[i]);
			}
			else{
				tempList.add(keywordList[i]);
				tempList.add(keywordList[i+2]);
				i+=2;
			}
			keyWordsCache.add(tempList);
		}
		
		for (Note note : this.getNotes()) {
			boolean b1 = true;
			for (ArrayList<String> kws : keyWordsCache ) {
				boolean b2 = false;
				for (String s : kws) {
					if(note.getTiltle().toLowerCase().contains(s.toLowerCase())) {
						b2 = true;
					}
					if((note instanceof TextNote && ((TextNote) note).getContent().toLowerCase().contains(s.toLowerCase()))) {
						b2 = true;
					}
				}
				if (!b2) {
					b1 = false;
					break;
				}
				
			}
			if (b1) {
				out.add(note);
			}
		}
		return out;
	}
}
