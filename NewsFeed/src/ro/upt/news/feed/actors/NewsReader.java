package ro.upt.news.feed.actors;

import java.util.logging.Logger;

import ro.upt.news.feed.news.News;

public class NewsReader {
	private final static Logger LOG = Logger.getLogger(NewsReader.class.getSimpleName());
	
	private String name;
	
	public NewsReader(String name) {
		this.name = name;
	}
	
	public void read(String news) {
		LOG.info(name + " - I've been informed with the following news: " + news);
	}
}
