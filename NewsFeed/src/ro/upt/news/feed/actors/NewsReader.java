package ro.upt.news.feed.actors;

import java.util.logging.Logger;

import ro.upt.news.feed.news.News;

public class NewsReader {
	private final static Logger LOG = Logger.getLogger(NewsReader.class.getSimpleName());
	
	public void read(News news) {
		LOG.info("I've read the following news: " + news.getBody());
	}
}
