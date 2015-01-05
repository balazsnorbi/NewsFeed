package ro.upt.news.feed.news;

public class NewsInfo {
	
	private final String domain;
	private final String subdomain;
	private final Attributes attributes;
	
	public NewsInfo (String domain, String subdomain, Attributes attributes) {
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
