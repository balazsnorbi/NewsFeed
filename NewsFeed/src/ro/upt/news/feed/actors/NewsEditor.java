package ro.upt.news.feed.actors;

import java.util.logging.Logger;

import ro.upt.news.feed.looper.NewsDispatcher;
import ro.upt.news.feed.looper.NewsEvent;
import ro.upt.news.feed.looper.NewsEvent.EventType;
import ro.upt.news.feed.news.News;

public class NewsEditor {
	private final static Logger LOG = Logger.getLogger(NewsEditor.class.getSimpleName());
	
	public void notice(News news) {
		LOG.info("I've been noticed that the following news were readen: " + news.getDomain() + " - " + news.getBody());
	}
	
	public void postNews(News news, NewsDispatcher dispatcher) {
		NewsEvent event = new NewsEvent(news, EventType.POST);
		
		dispatcher.postEvent(event);
	}
}
