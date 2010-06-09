package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.RequestBuilder;
import org.jdamico.tamandare.exceptions.TamandareException;

public class SendIntersectionTagsThread implements Runnable {
	private String remotePeer;
	private String back_tag;
	public SendIntersectionTagsThread(String remotePeer, String back_tag) {
		this.remotePeer = remotePeer;
		this.back_tag = back_tag;
	}

	@Override
	public void run() {
			try {
				RequestBuilder.getInstance().sendPost("back_tag", back_tag, remotePeer);
			} catch (TamandareException e) {
				e.printStackTrace();
			}
		
	}

}
