package org.jdamico.tamandare.threads;

import org.jdamico.tamandare.components.RequestBuilder;
import org.jdamico.tamandare.components.URLManager;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.XmlUtils;

public class SendMyTagsThread implements Runnable {
	private String remotePeer;
	public SendMyTagsThread(String remotePeer) {
		this.remotePeer = remotePeer;
	}

	@Override
	public void run() {
		
		/* Get mytags */
		String tagsXml = XmlUtils.getInstance().buildTagsXml(URLManager.getInstance().getTags());
		try {
			RequestBuilder.getInstance().sendPost(tagsXml, tagsXml, remotePeer);
		} catch (TamandareException e) {
			e.printStackTrace();
		}

	}

}
