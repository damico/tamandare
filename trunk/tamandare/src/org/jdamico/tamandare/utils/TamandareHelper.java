package org.jdamico.tamandare.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	public boolean isReady(Date now, Date last, int interval) {
		
		boolean ret = false;
		
		Calendar calNow = Calendar.getInstance();
	    calNow.setTime(now);
	    
	    Calendar calLast = Calendar.getInstance();
	    calLast.setTime(last);
	    
	    long millisecondsNow = calNow.getTimeInMillis();
	    long millisecondsLast = calLast.getTimeInMillis();
	    long diff = millisecondsNow - millisecondsLast;
	    long diffSeconds = diff / 1000;
	    
	    if(diffSeconds >= interval) ret = true;
	    
	    LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Last: "+date2String(last)+" | Now: "+date2String(now)+" | Diff: "+diffSeconds+"");
	    
	    System.out.println("Last: "+date2String(last)+" | Now: "+date2String(now)+" | Diff: "+diffSeconds+"");
	    
	    
		return ret;
	}
	
	public String date2String(Date date){
		Format formatter = new SimpleDateFormat("yyyyMMMdd_HH:mm:ss");
		String stime = formatter.format(date);
		return stime;
	}

	public String date2String(Date date, String format){
		Format formatter = new SimpleDateFormat(format);
		String stime = formatter.format(date);
		return stime;
	}
	
	public String getCurrentDateTimeFormated(){
		
		return date2String(new Date());
		
	}
	
	public String[] getMyIPsByStringArray() throws Exception{
		ArrayList<NetworkInterfaceObject> myIps = getMyIPs();
		String[] ips = new String[myIps.size()];
		for(int i=0; i<myIps.size(); i++){
			ips[i] = myIps.get(i).getIfaceIPv4();
		}
		return ips;
	}
	
}
