package com.gr.scraper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public interface IWebScraper
{

	//method that returns the Json Object
	public Gson getJson();

	// Default implementation for writing Json object to a json file
	
	 public void  writeJson2File(File file);
	
	

}
