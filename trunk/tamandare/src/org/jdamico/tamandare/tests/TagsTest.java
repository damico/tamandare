package org.jdamico.tamandare.tests;

import java.util.ArrayList;

import org.jdamico.tamandare.components.URLManager;

import junit.framework.TestCase;

public class TagsTest extends TestCase {
	public void testListTags(){
		ArrayList<String> tags = URLManager.getInstance().getTags();
		for(int i = 0; i < tags.size(); i++){
			String ret = tags.get(i).replaceAll("<tags>", "");
			ret = ret.replaceAll("</tags>", " ");
			System.out.println(ret);
		}
	}
}
