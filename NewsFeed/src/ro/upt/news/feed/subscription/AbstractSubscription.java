package ro.upt.news.feed.subscription;

import ro.upt.news.feed.news.Attributes;
import ro.upt.news.feed.news.NewsInfo;

public class AbstractSubscription {
	private final NewsInfo info;
	
	protected AbstractSubscription(String domain, String subdomain, Attributes attributes) {
		info = new NewsInfo(domain, subdomain, attributes);
	}
	
	public String getDomain() {
		return this.info.getDomain();
	}
	
	public String getSubdomain() {
		return this.info.getSubdomain();
	}
	
	public Attributes getAttributes() {
		return this.info.getAttributes();
	}
}
