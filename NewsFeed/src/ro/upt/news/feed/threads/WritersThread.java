package ro.upt.news.feed.threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ro.upt.news.feed.actors.NewsEditor;
import ro.upt.news.feed.looper.NewsDispatcher;
import ro.upt.news.feed.news.News;
import ro.upt.news.feed.subscription.EditorSubscription;

public class WritersThread extends Thread {

	private final NewsDispatcher dispatcher;
	private final DateFormat formatter = new SimpleDateFormat("mm-DD hh:MM:ss");

	public WritersThread(NewsDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {

			News sportsNews = new News(
					"Real Madrid stops at 22 victories tonight, "
							+ formatter
									.format(Calendar.getInstance().getTime()),
					"Sports", "Football", null);
			
			NewsEditor sportsEditor = new NewsEditor();
			sportsEditor.postNews(sportsNews, dispatcher);
			
			//register a listener to see if news were readen
			dispatcher.subscribeEditor(sportsEditor, new EditorSubscription("Sports", "Football", null));
			
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
