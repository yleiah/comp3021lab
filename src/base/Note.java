package base;

import java.util.Date;

public class Note {
    private Date date;
    private String title;
    
    public Note(String title) {
    	this.title = title;
    	this.date = new Date();
    }
    
    public String getTiltle() {
    	return this.title;
    }
    
    public Date getDate() {
    	return this.date;
    }
    
    public boolean equals(Note note) {
    	return this.title.equals(note.getTiltle());
    }
}

