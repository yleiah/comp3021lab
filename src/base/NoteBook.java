package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements java.io.Serializable{
      private ArrayList<Folder> folders;
      private static final long serialVersionUID = 1L;
      
      public NoteBook() {
    	  this.folders = new ArrayList<Folder>();
      }
      //lab5
      public NoteBook(String file) {
    	  try {
    		  System.out.println(file);
    		  FileInputStream fis = new FileInputStream(file);
    		  ObjectInputStream in = new ObjectInputStream(fis);
    		  NoteBook n = (NoteBook)in.readObject();
    		  this.folders = n.getFolders();
    	  } catch(Exception e) {
    		  e.printStackTrace();
    	  }  	  
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
      
      //lab5
      public boolean save(String file) {
    	  FileOutputStream fos = null;
    	  ObjectOutputStream out = null;
    	  try {
    		  fos = new FileOutputStream(file);
    		  out = new ObjectOutputStream(fos);
    		  out.writeObject(this);
    		  out.close();
    	  }catch(Exception e) {
    		  e.printStackTrace();
    		  return false;
    	  }
    	  return true;
      }
      
      public boolean addFolder(String name) {
    	  for(Folder f:folders) {
    		  if(f.getName().equals(name)) {
    			  return false;
    		  }
    	  }
    	  
    	  Folder newFolder = new Folder(name);
    	  folders.add(newFolder);
    	  return true;
      }
}
