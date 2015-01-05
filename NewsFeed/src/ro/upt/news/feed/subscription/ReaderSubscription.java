package ro.upt.news.feed.subscription;

import ro.upt.news.feed.news.Attributes;

public class ReaderSubscription extends AbstractSubscription {
	
	public boolean interestInPost = true;
	public boolean interestInUpdate = true;
	public boolean interestInDelete = true;
	
	public ReaderSubscription (String domain, String subdomain, Attributes attributes) {
		super (domain, subdomain, attributes);
	}
}
