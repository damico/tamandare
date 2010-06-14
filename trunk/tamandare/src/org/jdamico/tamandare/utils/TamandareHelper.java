package org.jdamico.tamandare.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.dataobjects.NetworkInterfaceObject;
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
			String e = tagsArray[i];
			e = e.replaceAll("\n", "");
			if(xml){
				if(!e.equals("") && !e.equals(" ") && e!=null) sb.append("<tags>"+e+"</tags>\n");
			}else{
				if(!e.equals("<tags></tags>")) sb.append(e);
			}
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
		String[] pcktSubG = new String[4];
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

	public ArrayList<String> stripTags(ArrayList<String> tagsArray){
		String delimit = ";";
		StringBuffer rawTags = new StringBuffer();
		for(int i=0; i<tagsArray.size(); i++){

			String e = tagsArray.get(i).replaceAll("<tags>", delimit);
			rawTags.append(e.replaceAll("</tags>", ""));

		}

		tagsArray.clear();
		StringTokenizer st = new StringTokenizer(rawTags.toString(), delimit);
		while(st.hasMoreElements()){
			String element = (String) st.nextElement();
			element = element.trim();
			if(!tagsArray.contains(element)){
				tagsArray.add(element);
			}else{
				System.err.println("888888888888888888888888888888888888888888 "+element);
			}
		}

		return tagsArray;
	}


	public ArrayList<String> putTags(ArrayList<String> tagsArray){
		ArrayList<String> ret = new ArrayList<String>();
		for(int i=0; i<tagsArray.size(); i++){
			ret.add("<tags>"+tagsArray.get(i)+"</tags>");
		}
		return ret;
	}
	
	public ArrayList<NetworkInterfaceObject> getMyIPs()	throws Exception {
		
		ArrayList<NetworkInterfaceObject> ifacesArray = new ArrayList<NetworkInterfaceObject>();
		
		String ifaceName = null;
		String ifaceIPv6 = null;
		String ifaceIPv4 = null;
		
		Enumeration<NetworkInterface> enumNetworkInterface = (Enumeration<NetworkInterface>)NetworkInterface.getNetworkInterfaces();
		while(enumNetworkInterface.hasMoreElements()) {
			
			
			NetworkInterface ni = enumNetworkInterface.nextElement();
			ifaceName = ni.getName();
			
			Enumeration<InetAddress> enumInetAddress = ni.getInetAddresses();
			while(enumInetAddress.hasMoreElements()) {
				try{
					InetAddress ia = enumInetAddress.nextElement();
					ifaceIPv6 = ia.toString().replaceAll("/", "");
					ia = enumInetAddress.nextElement();
					ifaceIPv4 = ia.toString().replaceAll("/", "");
				}catch(NoSuchElementException e){
					ifaceIPv4 = ifaceIPv6;
					ifaceIPv6 = null;
				}
				
			}
			
			ifacesArray.add(new NetworkInterfaceObject(ifaceName, ifaceIPv6, ifaceIPv4));
			
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), ifaceName+" : ["+ifaceIPv6+","+ifaceIPv4+"]");
		}
		
		return ifacesArray;
	}

}
