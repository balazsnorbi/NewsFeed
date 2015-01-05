package ro.upt.news.feed.threads;

import ro.upt.news.feed.looper.NewsDispatcher;

public class DispatcherThread extends Thread{
	private final NewsDispatcher dispatcher;
	
	public DispatcherThread(NewsDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			dispatcher.handle();
		}
	}
}
