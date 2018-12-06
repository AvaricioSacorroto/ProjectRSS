package com.rssproject.web.forms;

public class UrlForm {

	private String url;
	private Long id;

	public UrlForm() {

	}

	public UrlForm(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
