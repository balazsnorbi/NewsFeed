package ro.upt.news.feed.threads;

import ro.upt.news.feed.looper.NewsDispatcher;

public class ReadersThread {
	private final NewsDispatcher dispatcher;

	public ReadersThread(NewsDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			
			
		}
	}
}
