package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
      private ArrayList<Folder> folders;
      
      public NoteBook() {
    	  this.folders = new ArrayList<Folder>();
      }
      
      public ArrayList<Folder> getFolders(){
    	  return folders;
      }
      
      public boolean insertNote(String folderName ,Note note) {
    	  Folder f = null;
    	  for(Folder folder:folders) {
    		  if(folderName.equals(folder.getName())) {
    			  f = folder;
    		  }
    	  }
    	  if(f == null) {
    		  f=new Folder(folderName);
    		  f.addNote(note);
    		  folders.add(f);
    		  return true;
    	  }
    	  for(Note note1:f.getNotes()) {
    		  if(note1.equals(note)) {
    			  System.out.println("Creating note "+note.getTiltle()+" under folder "+folderName+" failded");
    			  return false;
    		  }
    	  }
    	  f.addNote(note);
    	  return true;
      }
      
      public boolean createTextNote (String folderName , String title) {
    	  TextNote note = new TextNote(title);
    	  return insertNote(folderName,note);
      }
      
      public boolean createTextNote (String folderName , String title, String content) {
    	  TextNote note = new TextNote(title,content);
    	  return insertNote(folderName,note);
      }
      
      public boolean createImageNote (String folderName , String title) {
    	  ImageNote note = new ImageNote(title);
    	  return insertNote(folderName,note);
      }
      
      public void sortFolders() {
  		Collections.sort(folders);
  	  }
      
      public List<Note> searchNotes(String keywords){
    	  ArrayList<Note> out = new ArrayList<Note>();
    	  for(Folder f : this.folders) {
    		  List<Note> l = f.searchNotes(keywords);
    		  out.addAll(0, l);
    	  }
    	  return out;
      }
}
