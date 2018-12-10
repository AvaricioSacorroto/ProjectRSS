package com.rssproject.api.modelo;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	private String title;
	private String languaje;
	private String link;
	private String imageTitle;
	private String imageUrl;
	private String url;
	List<Entries> entries = new ArrayList<>();

	public Feed() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguaje() {
		return languaje;
	}

	public void setLanguaje(String languaje) {
		this.languaje = languaje;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Entries> getEntries() {
		return entries;
	}

	public void setEntries(List<Entries> entries) {
		this.entries = entries;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
