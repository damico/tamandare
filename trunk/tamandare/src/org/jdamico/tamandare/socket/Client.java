package org.jdamico.tamandare.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.exceptions.TamandareException;
import org.jdamico.tamandare.utils.Constants;
import org.jdamico.tamandare.utils.TamandareHelper;

public class Client {
	public String initClient(String msg, String host) throws IOException, TamandareException{

		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "initClient(String msg, String host)");
		
		Socket jtopSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = Constants.SOCKET_SERVER_PORT;
		try {
			jtopSocket = new Socket(host, port);
			out = new PrintWriter(jtopSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(jtopSocket.getInputStream()));
		} catch (UnknownHostException e) {
			throw new TamandareException("Don't know about host: "+host+".");
		} catch (IOException e) {
			throw new TamandareException("Couldn't get I/O for the connection to: "+host+".");
		}


		String fromServer;
		String fromUser = msg;

		int count = 0;
		
		while ((fromServer = in.readLine()) != null) {
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "from["+jtopSocket.getLocalSocketAddress().toString()+"]  "+fromServer + " | to["+jtopSocket.getRemoteSocketAddress().toString()+"] "+fromUser + " | msg "+msg);
			if (fromServer.equals(":")){
				out.println(fromUser);
			}else{
				break;
			}
			count++;
			
			if(count==2) break;
		}

		out.close();
		in.close();

		jtopSocket.close();
		
		return fromServer;
 
	}

	public ComplexPacket areU(ComplexPacket cp, String entityName) throws TamandareException, IOException {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "areU(ComplexPacket cp, String entityName)");
		
		Socket jtopSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = Constants.SOCKET_SERVER_PORT;
		try {
			jtopSocket = new Socket(cp.getAddr(), port);
			out = new PrintWriter(jtopSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(jtopSocket.getInputStream()));
		} catch (UnknownHostException e) {
			throw new TamandareException("Don't know about host: "+cp.getAddr()+".");
		} catch (IOException e) {
			throw new TamandareException("Couldn't get I/O for the connection to: "+cp.getAddr()+".");
		}
		
		
		String fromServer;
		String fromUser = cp.getSComplexPacket();

		int count = 0;
		
		while ((fromServer = in.readLine()) != null) {

			if (fromServer.equals(":")){
				out.println(fromUser);
			}else{
				break;
			}
			count++;
			if(count==2) break;
		} 

		out.close();
		in.close();

		jtopSocket.close();
		
		
		cp.setSComplexPacket(fromServer);
		
		return cp;
	}

	public ComplexPacket sendSignature(ComplexPacket cp, String entityName) throws TamandareException, IOException {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSignature(ComplexPacket cp, String entityName)");
		
		Socket jtopSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = Constants.SOCKET_SERVER_PORT;
		try {
			jtopSocket = new Socket(cp.getAddr(), port);
			out = new PrintWriter(jtopSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(jtopSocket.getInputStream()));
		} catch (UnknownHostException e) {
			throw new TamandareException("Don't know about host: "+cp.getAddr()+".");
		} catch (IOException e) {
			throw new TamandareException("Couldn't get I/O for the connection to: "+cp.getAddr()+".");
		}
		
		
		String fromServer;
		String fromUser = cp.getSComplexPacket();

		int count = 0;
		
		while ((fromServer = in.readLine()) != null) {

			if (fromServer.equals(":")){
				out.println(fromUser);
			}else{
				break;
			}
			count++;
			if(count==2) break;
		} 

		out.close();
		in.close();

		jtopSocket.close();
		cp.setSComplexPacket(fromServer);
		
		return cp;
	}

	public ComplexPacket sendSessionAcceptance(ComplexPacket cp) throws TamandareException, IOException {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSessionAcceptance(ComplexPacket cp): "+cp.getAddr());
		
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = Constants.SOCKET_SERVER_PORT;
		try {
			clientSocket = new Socket(cp.getAddr(), port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "handling session: "+cp.getAddr());
			
		} catch (UnknownHostException e) {
			throw new TamandareException("Don't know about host: "+cp.getAddr()+".");
		} catch (IOException e) {
			throw new TamandareException("Couldn't get I/O for the connection to: "+cp.getAddr()+".");
		}
		
		
		
		
		
		String fromServer;
		
		
		
		if(Boolean.valueOf(cp.getValue())){
			cp.setType("sendSessionAcceptance");
		}else{
			cp.setType("sendSessionDenied");
		}
		
		String clientAddr = clientSocket.getLocalAddress().toString(); 
		
		cp.setValue(clientAddr.replaceAll("/", ""));
		
		String fromUser = TamandareHelper.getInstance().complexPacket2String(cp.getAddr(), cp.getType(), cp.getValue());

		int count = 0;
		
		while ((fromServer = in.readLine()) != null) {

			if (fromServer.equals(":")){
				out.println(fromUser);
			}else{
				break;
			}
			count++;
			if(count==2) break;
		} 

		out.close();
		in.close();

		clientSocket.close();
		
		
		cp.setSComplexPacket(fromServer);
		
		return cp;
	}
 
	
	public ComplexPacket sendMyTags(ComplexPacket cp) throws TamandareException, IOException {
		
		LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "sendSessionAcceptance(ComplexPacket cp)");
		
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = Constants.SOCKET_SERVER_PORT;
		try {
			clientSocket = new Socket(cp.getAddr(), port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			throw new TamandareException("Don't know about host: "+cp.getAddr()+".");
		} catch (IOException e) {
			throw new TamandareException("Couldn't get I/O for the connection to: "+cp.getAddr()+".");
		}
		
		
		
		
		
		String fromServer;
		String fromUser = TamandareHelper.getInstance().complexPacket2String(cp.getAddr(), cp.getType(), cp.getValue());

		int count = 0;
		
		while ((fromServer = in.readLine()) != null) {

			if (fromServer.equals(":")){
				out.println(fromUser);
			}else{
				break;
			}
			count++;
			if(count==2) break;
		} 

		out.close();
		in.close();

		clientSocket.close();
		
		
		cp.setSComplexPacket(fromServer);
		
		return cp;
	}
	
}
