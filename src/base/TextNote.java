package base;

public class TextNote extends Note{
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String Content) {
		super(title);
		content = Content;
	}
	
	
	public String getContent() {
		return content;
	}

}
