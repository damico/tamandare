package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.RequestBuilder;
import org.jdamico.tamandare.exceptions.TamandareException;

public class SendMyDocsThread implements Runnable {
	private String remotePeer;
	private String doc;
	public SendMyDocsThread(String remotePeer, String doc) {
		this.remotePeer = remotePeer;
		this.doc = doc;
	}

	@Override
	public void run() {
			try {
				RequestBuilder.getInstance().sendPost("doc", doc, remotePeer);
			} catch (TamandareException e) {
				e.printStackTrace();
			}
		
	}

}
