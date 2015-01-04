package ro.upt.news.feed.looper;

public class ReaderSubscription {
	private final String domain;
	private final String subdomain;
	private final Attributes attributes;
	
	public boolean interestInPost = true;
	public boolean interestInUpdate = true;
	public boolean interestInDelete = true;
	
	public ReaderSubscription (String domain, String subdomain, Attributes attributes) {
		this.domain = domain;
		this.subdomain = subdomain;
		this.attributes = attributes;
	}
	
	public String getDomain() {
		return this.domain;
	}
	
	public String getSubdomain() {
		return this.subdomain;
	}
	
	public Attributes getAttributes() {
		return this.attributes;
	}
	
	
}
