package ro.upt.news.feed.looper;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Queue;
import java.util.logging.Logger;

import ro.upt.news.feed.actors.NewsEditor;
import ro.upt.news.feed.actors.NewsReader;
import ro.upt.news.feed.news.News;
import ro.upt.news.feed.subscription.AbstractSubscription;
import ro.upt.news.feed.subscription.EditorSubscription;
import ro.upt.news.feed.subscription.ReaderSubscription;

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
	public synchronized void postEvent(NewsEvent newsEvent) {
		eventQueue.add(newsEvent);
	}

	/**
	 * Add a reader with the specified subscription.
	 * 
	 * @param reader
	 * @param subscription
	 */
	public synchronized void subscribeReader(NewsReader reader, ReaderSubscription subscription) {
		LOGGER.info("Reader " + reader.toString() + " is subscribed for " + subscription.toString());
		
		readers.add(new SimpleEntry<NewsReader, ReaderSubscription>(reader,
				subscription));
	}
	
	/**
	 * Add a writer with the specified subscription.
	 * 
	 * @param editor
	 * @param subscription
	 */
	public synchronized void subscribeEditor(NewsEditor editor, EditorSubscription subscription) {
		LOGGER.info("Editor " + editor.toString() + " is subscribed for " + subscription.toString());
		
		editors.add(new SimpleEntry<NewsEditor, EditorSubscription>(editor,
				subscription));
	}

	public void handle() {
		if (!eventQueue.isEmpty()) {
			NewsEvent nextEvent = eventQueue.poll();
			News news = nextEvent.getNews();

			switch (nextEvent.getType()) {
			case POST:
				post(news);
				break;

			case UPDATE:
				update(news);
				break;

			case DELETE:
				delete(news);
				break;
			}
		}
	}

	private synchronized void post(News news) {
		boolean isReaden = false;
		
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInPost == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					reader.read("POST: " + news.getBody());
					
					isReaden = true;
				}
			}
		}
		
		for (Entry<NewsEditor, EditorSubscription> pair : editors) {
			EditorSubscription editorSubscription = pair.getValue();
			
			if (checkSubscription(news, editorSubscription) && isReaden) {
				// inform the editor about the published news
				pair.getKey().notice(news);
			}
		}
	}

	private synchronized void update(News news) {
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInUpdate == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					reader.read("UPDATE: " + news.getBody());
				}
			}
		}
	}

	private synchronized void delete(News news) {
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();

			if (readerSubscription.interestInDelete == true) {
				if (checkSubscription(news, readerSubscription)) {
					// inform the reader about the news
					NewsReader reader = pair.getKey();
					reader.read("DELETED: " + news.getBody());
				}
			}
		}
	}
	
	private boolean checkSubscription(News news, AbstractSubscription readerSubscription) {
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
	
	public int getReadersCount(EditorSubscription editorSubscription) {
		int readersCount = 0;
		
		for (Entry<NewsReader, ReaderSubscription> pair : readers) {
			ReaderSubscription readerSubscription = pair.getValue();
			
			if (readerSubscription.equals(editorSubscription)) {
				++readersCount;
			}
		}
		return readersCount;
	}
}
