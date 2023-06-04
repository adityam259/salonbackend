package com.salon.app.dto;

public class SearchResults {

	private String id;
	private String title;
	private String source;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SearchResults [id=" + id + ", title=" + title + ", source=" + source + "]";
	}
	
	
	
}
