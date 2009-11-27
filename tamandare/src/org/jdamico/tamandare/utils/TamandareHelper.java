package org.jdamico.tamandare.utils;

import java.util.StringTokenizer;

import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.socket.ComplexPacket;


public class TamandareHelper {
	private static TamandareHelper INSTANCE = null;
	public static TamandareHelper getInstance(){
		if(INSTANCE == null) INSTANCE = new TamandareHelper();
		return INSTANCE;
	}
	
	public String[] tags2Array(String tags){
		
		tags = tags.replaceAll("\n", " ");
	
		StringTokenizer st = new StringTokenizer(tags, " ");
		String[] tagArray = new String[st.countTokens()]; 
		int count = 0;
		
		String element = null;
		
		while (st.hasMoreElements()) {
			element = st.nextToken();
			if(!element.equals("\n") && !element.equals(" ")){
				tagArray[count] = element;
				count++;	
			}
			
		}
		
		return tagArray;
	}
	
	
	public String tagsArray2String(String[] tagsArray, boolean xml){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < tagsArray.length; i++){
			if(xml) sb.append("<tags>"+tagsArray[i]+"</tags>\n");
			else sb.append(tagsArray[i]+" \n");
		}
		return sb.toString();
	}

	public String complexPacket2String(String toAddr, String type, String value, String fromAddr){
		return "{["+toAddr+"]:["+type+"]:["+value+"]:["+fromAddr+"]}";
	}
	
	public ComplexPacket string2ComplexPacket(String msg) throws TamandareException {
		msg = msg.substring(1,msg.length()-1);
		ComplexPacket cp = null;
		StringTokenizer st = new StringTokenizer(msg,":");
		String[] pcktSubG = new String[3];
		int count = 0;
		while(st.hasMoreElements()){
			String data =  st.nextToken();
			pcktSubG[count] = data.substring(1,data.length()-1);
			count++;
		}
		
		if(count==4) cp = new ComplexPacket(pcktSubG[0],pcktSubG[1],pcktSubG[2],pcktSubG[3]);
		else throw new TamandareException("Invalid protocol packet!");
		
		return cp;
	}
}
