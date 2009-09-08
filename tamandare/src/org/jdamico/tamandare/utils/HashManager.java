package org.jdamico.tamandare.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashManager {
	private String byte2Hex(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		   for (int i = 0; i < bytes.length; i++) {
		       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		       int parteBaixa = bytes[i] & 0xf;
		       if (parteAlta == 0) s.append('0');
		       s.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return s.toString();
    }

	private static byte[] genHash(String message, String hashType) {
		  try {
		    MessageDigest md = MessageDigest.getInstance(hashType);
		    md.update(message.getBytes());
		    return md.digest();
		  } catch (NoSuchAlgorithmException e) {
		    return null;
		  }
	}
	

	public String getHash(String message){
		return byte2Hex(genHash(message, Constants.DEFAULT_HASH_TYPE));
	}

}
