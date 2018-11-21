package com.offcn.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.senqi.util.HttpDownUtil;

public class Text {
	
	public static void main(String[] args) throws Exception {
		
		List<String> picUrls = new ArrayList<String>();
		
		
		String[] urls = {"https://www.168.com/gallery-1149--1-0-1--grid-g.html", 
				"https://www.168.com/gallery-1149--1-0-2--grid-g.html", 
				"https://www.168.com/gallery-1149--1-0-3--grid-g.html", 
				"https://www.168.com/gallery-1149--1-0-4--grid-g.html"};
		
		for (String url : urls) {
			Document doc = Jsoup.connect(url).get();
			
			Elements eles = doc.select("[class=items-gallery  itemsList]").select("img");
			
			for (Element e : eles) {
				picUrls.add(e.attr("lazyload"));
			}
		}
		
		
		// Æô¶¯ÏÂÔØ³ÌÐò
		HttpDownUtil.down(picUrls);
		
	}

}
