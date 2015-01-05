package ro.upt.news.feed.news;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Attributes {
	private final DateFormat formatter = new SimpleDateFormat("mm-DD hh:MM:ss");
	
	private final String creationDate;
	private String lastModifiedDate;
	private String infoSource;
	private String author;
	
	public Attributes(String infoSource, String author) {
		this.infoSource = infoSource;
		this.author = author;
		
		creationDate = formatter.format(Calendar.getInstance().getTime()); 
		lastModifiedDate = creationDate;
	}
	
	public String getCreationDate() {
		return this.creationDate;
	}
	
	public String getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	
	public String getInfoSource() {
		return this.infoSource;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Attributes)) {
			return false;
		}
		
		Attributes cmp = (Attributes) obj;
		
		if (! creationDate.equals(cmp.getCreationDate())) {
			return false;
		}
		if (! lastModifiedDate.equals(cmp.getLastModifiedDate())) {
			return false;
		}
		if (! infoSource.equals(cmp.getInfoSource())) {
			return false;
		}
		if (! author.equals(cmp.getAuthor())) {
			return false;
		}
		
		return true;
	}

}
