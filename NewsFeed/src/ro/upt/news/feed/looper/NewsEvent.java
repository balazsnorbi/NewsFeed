package ro.upt.news.feed.looper;

public class NewsEvent {
	private final News oneNews;
	private final EventType type;
	
	
	public enum EventType {
		POST, UPDATE, DELETE;
	}
	
	public NewsEvent(News oneNews, EventType type) {
		this.oneNews = oneNews;
		this.type = type;
	}
	
	public News getNews() {
		return oneNews;
	}
	
	public EventType getType() {
		return type;
	}
}
