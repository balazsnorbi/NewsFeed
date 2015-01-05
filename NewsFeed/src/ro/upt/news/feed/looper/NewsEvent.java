package ro.upt.news.feed.looper;

import ro.upt.news.feed.news.News;

public class NewsEvent {
	private final News news;
	private final EventType type;
	
	
	public enum EventType {
		POST, UPDATE, DELETE;
	}
	
	public NewsEvent(News news, EventType type) {
		this.news = news;
		this.type = type;
	}
	
	public News getNews() {
		return news;
	}
	
	public EventType getType() {
		return type;
	}
}
