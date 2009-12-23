package org.jdamico.tamandare.urlreader;

import java.util.ArrayList;



public class LiveMemoryManager {
	
	private static ArrayList<String> urlArray = new ArrayList<String>();
	
	private static LiveMemoryManager INSTANCE = null;
	public static LiveMemoryManager getInstance(){
		if(INSTANCE == null) INSTANCE = new LiveMemoryManager();
		return INSTANCE;
	}
	public static ArrayList<String> getUrlArray() {
		return urlArray;
	}
	public static void setUrlArray(ArrayList<String> urlArray) {
		LiveMemoryManager.urlArray.addAll(urlArray);
	}
	
	
}
