package org.jdamico.tamandare.threads;

import java.util.ArrayList;
import java.util.Date;

import org.jdamico.tamandare.components.Converter2XMLFactory;
import org.jdamico.tamandare.components.RequestBuilder;
import org.jdamico.tamandare.dataobjects.TagsObject;
import org.jdamico.tamandare.dataobjects.TamandareReturn;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.transactions.Derbymanager;
import org.jdamico.tamandare.utils.Constants;

public class SendMyDocsThread implements Runnable {
	private String remotePeer;
	public SendMyDocsThread(String remotePeer, String doc) {
		this.remotePeer = remotePeer;
	}

	@Override
	public void run() {
		
		/* Get mytags */
		
		Derbymanager dm =  new Derbymanager(); /**/
		
		ArrayList<String> tags = dm.getTags();
		
		String[] tagsArray = new String[tags.size()];
		for(int i=0; i<tags.size(); i++){
			
			tagsArray[i]=tags.get(i);
		}
	
		TagsObject tagsObject = new TagsObject(tagsArray, new Date(), new TamandareReturn(0,"success"));
		try {
			String tagsXml = Converter2XMLFactory.getConverter(Constants.TAGS, tagsObject).exec();
			RequestBuilder.getInstance().sendPost("tags", tagsXml, remotePeer);
		} catch (TamandareException e) {
			e.printStackTrace();
		}

	}

}
