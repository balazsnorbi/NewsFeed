package ro.upt.news.feed.looper;

import java.util.logging.Logger;

import ro.upt.news.feed.actors.NewsReader;
import ro.upt.news.feed.subscription.EditorSubscription;
import ro.upt.news.feed.subscription.ReaderSubscription;
import ro.upt.news.feed.threads.DispatcherThread;
import ro.upt.news.feed.threads.WritersThread;

public class Playground {
	private final static Logger LOG = Logger.getLogger(Playground.class.getSimpleName());
	
	public static void main(String args[]) {
		NewsDispatcher dispatcher = new NewsDispatcher();
		
		// subscribe for football
		NewsReader sportsReader1 = new NewsReader();
		ReaderSubscription sportsSubscription = new ReaderSubscription(
				"Sports", "Football", null);
		dispatcher.subscribeReader(sportsReader1, sportsSubscription);
		
		// same subscription
		NewsReader sportsReader2 = new NewsReader();
		dispatcher.subscribeReader(sportsReader2, sportsSubscription);
		
		// let's see how many subscribers do we have for sports-football
		LOG.info("Nb. of subscribed readers: " + dispatcher.getReadersCount(new EditorSubscription("Sports", "Football", null)));
		
		new DispatcherThread(dispatcher).start();
		new WritersThread(dispatcher).start();
	}

}
