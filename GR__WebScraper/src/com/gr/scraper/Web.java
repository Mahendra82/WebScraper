/**
 * 
 */
package com.gr.scraper;

import java.util.Map;

/**
 * @author MJ
 *
 */
public  class Web {
	private String url;
	private String title;
	private Map<String, String> nodes;
	
	public Web(String url,String title, Map<String, String> nodes)
	{
		
		this.url=url;
		this.title=title;
		this.nodes= nodes;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, String> getNodes() {
		return nodes;
	}
	public void setNodes(Map<String, String> nodes) {
		this.nodes = nodes;
	}

}
