package com.rssproject.web.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class ControllerRss {

	@RequestMapping(value = "/rrsView", method = RequestMethod.GET)
	public ModelAndView verRRS() {

		String url = "http://rss.cnn.com/rss/cnn_topstories.rss";
		SyndFeed feed = null;
		try {
			feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("FEED:title=" + feed.getTitle());
		System.out.println("FEED:languaje=" + feed.getLanguage());
		System.out.println("FEED:link=" + feed.getLink());
		System.out.println("FEED:imageTitle=" + feed.getImage().getTitle());
		System.out.println("FEED:imageUrl=" + feed.getImage().getUrl());
		List<SyndEntryImpl> entries = feed.getEntries();
		for (SyndEntryImpl fid : entries) {

			System.out.println("FiD:DescriptionValue=" + fid.getDescription().getValue());
			System.out.println("FiD:Title=" + fid.getTitle());
			System.out.println("FiD:Link=" + fid.getLink());
			System.out.println("FiD:Date=" + fid.getPublishedDate());
		}
		System.out.println("FEED:Date=" + feed.getPublishedDate());
		System.out.println("FEED:description=" + feed.getDescription());

		return new ModelAndView("rrsView","message",feed.getTitle());
}




















}
