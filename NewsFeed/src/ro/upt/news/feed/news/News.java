package ro.upt.news.feed.news;

public class News {
	private NewsInfo info;
	private String body;
	
	public News (String body, String domain, String subdomain, Attributes attr) {
		this.body = body;
		info = new NewsInfo(domain, subdomain, attr);
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
	
	public String getBody() {
		return this.body;
	}
}
