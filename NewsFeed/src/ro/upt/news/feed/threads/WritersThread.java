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
			try {
				News sportsNews = new News(
						"Real Madrid stops at 22 victories tonight, "
								+ formatter.format(Calendar.getInstance()
										.getTime()), "Sports", "Football", null);
				NewsEditor sportsEditor = new NewsEditor("SportsEditor1");
				sportsEditor.postNews(sportsNews, dispatcher);

				// register a listener to see if news were readen
				// dispatcher.subscribeEditor(sportsEditor, new
				// EditorSubscription("Sports", "Football", null));
				sleep(2000);
				
				News socialNews = new News(
						"Facebook, blah blah "
								+ formatter.format(Calendar.getInstance()
										.getTime()), "Social", "", null);
				NewsEditor socialEditor = new NewsEditor("SocialEditor1");
				socialEditor.postNews(socialNews, dispatcher);
				
				sleep(4000);
				
				sportsEditor.updateNews(sportsNews, dispatcher);
				
				sleep(3000);
				
				sportsEditor.deleteNews(socialNews, dispatcher);
				
				sleep(2000);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
