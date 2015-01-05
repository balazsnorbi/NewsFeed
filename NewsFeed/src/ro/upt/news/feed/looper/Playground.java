package ro.upt.news.feed.looper;

import ro.upt.news.feed.actors.NewsReader;
import ro.upt.news.feed.subscription.ReaderSubscription;
import ro.upt.news.feed.threads.DispatcherThread;
import ro.upt.news.feed.threads.WritersThread;

public class Playground {
	
	public static void main(String args[]) {
		NewsDispatcher dispatcher = new NewsDispatcher();
		
		NewsReader sportsReader = new NewsReader();
		ReaderSubscription sportsSubscription = new ReaderSubscription(
				"Sports", "Football", null);

		dispatcher.subscribeReader(sportsReader, sportsSubscription);
		
		new DispatcherThread(dispatcher).start();
		new WritersThread(dispatcher).start();
	}

}
