package ro.upt.news.feed.looper;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Queue;
import java.util.logging.Logger;

public class NewsDispatcher {
	private final static Logger LOGGER = Logger.getLogger(NewsDispatcher.class.getSimpleName());

	LinkedList<Entry<NewsReader, ReaderSubscription>> readers = new LinkedList<Entry<NewsReader, ReaderSubscription>>();
	LinkedList<Entry<NewsEditor, EditorSubscription>> editors = new LinkedList<Entry<NewsEditor, EditorSubscription>>();

	private Queue<NewsEvent> eventQueue = new LinkedList<NewsEvent>();

	/**
	 * Adds an event in the event queue.
	 * 
	 * @param newsEvent
	 */
	public void postEvent(NewsEvent newsEvent) {
		eventQueue.add(newsEvent);
	}

	/**
	 * Adds a reader with the specified request.
	 * 
	 * @param reader
	 * @param request
	 */
	public void addReader(NewsReader reader, ReaderSubscription request) {
		LOGGER.info("Reader " + reader.toString() + " is subscribed for " + request.toString());
		
		readers.add(new SimpleEntry<NewsReader, ReaderSubscription>(reader,
				request));
	}

	public void addEditor(NewsEditor editor, EditorSubscription request) {
		editors.add(new SimpleEntry<NewsEditor, EditorSubscription>(editor,
				request));
	}

	public void handle() {
		if (!eventQueue.isEmpty()) {
			NewsEvent nextEvent = eventQueue.poll();
			News oneNews = nextEvent.getNews();

			switch (nextEvent.getType()) {
			case POST:
				post(oneNews);
				break;

			case UPDATE:
				update(oneNews);
				break;

			case DELETE:
				delete(oneNews);
				break;
			}
		}
	}

	private void post(News news) {
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInPost == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					//reader.read(news);
				}
			}
		}
	}

	private void update(News news) {
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInUpdate == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					//reader.read(news);
				}
			}
		}
	}

	private void delete(News news) {
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInDelete == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					//reader.read(news);
				}
			}
		}
	}
	
	private boolean checkSubscription(News news, ReaderSubscription readerSubscription) {
		String newsDomain = news.getDomain();
		String newsSubdomain = news.getSubdomain();
		
		String subscriptionDomain = readerSubscription.getDomain();
		if (subscriptionDomain != null) {
			if (subscriptionDomain.isEmpty()) {
				return true;
			} else if (newsDomain.equals(subscriptionDomain)) {
				String subscriptionSubdomain = readerSubscription
						.getSubdomain();

				if (subscriptionSubdomain != null) {
					if (subscriptionSubdomain.isEmpty()) {
						return true;
					} else if (subscriptionSubdomain
							.equals(newsSubdomain)) {
						// TODO: check also the attributes
						return true;
					}
				}
			}
		}
		return false;
	}
}
