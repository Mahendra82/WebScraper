/**
 * 
 */
package com.gr.scraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

/**
 * The core public access point to the WebScraper functionality.
 * 
 * @author MJ
 *
 */

public class WebScraper implements IWebScraper
{

	private String		name;
	private String		url;
	private Gson		allNodes	= new Gson();
	static final Logger	log		= Logger.getLogger(WebScraper.class);

	@Override
	public Gson getJson()
	{
		url = getPropertyValue("url");
		Document doc;
		Elements links;
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			doc = Jsoup.connect(url).get();
			name = doc.getElementsByTag("title").get(0).ownText();
			links = doc.select("a[href]");

			log.info("Links: " + links.size());

			// Use streams to populate the nodes map
			links.stream().forEach(i -> map.put("node" + map.size(), i.attr("abs:href")));
			
			String str = allNodes.toJson(new Web(url, name, map));
			log.info( str);

		}

		catch (IOException e)
		{
			// oops something's NOT right
			log.error("Error accessing url");
			e.printStackTrace();
		}

		return allNodes;

	}

	public String getPropertyValue(String prop)
	{
		String result = "";

		try
		{

			InputStream inputStream;
			Properties props = new Properties();
			String propFileName = "webscraper.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null)
			{
				props.load(inputStream);
			} else
			{
				throw new FileNotFoundException(
						"property file '" + propFileName + "' not found in the classpath");
			}

			result = props.getProperty("url");
			inputStream.close(); // Not the best way to close the
					     // input stream!
		} 
		
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}

		return result;
	}

	@Override
	public void writeJson2File(File file)
	{
		// could be extended to write object to file
		
	}
	

	

}
