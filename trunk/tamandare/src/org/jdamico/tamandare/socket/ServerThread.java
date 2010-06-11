package org.jdamico.tamandare.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import org.jdamico.tamandare.components.LoggerManager;
import org.jdamico.tamandare.exceptions.TamandareException;



public class ServerThread extends Thread {
	private Socket socket = null;

	public ServerThread(Socket socket) {
		super("ServerThread");
		this.socket = socket;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));

			String inputLine, outputLine;
			SocketProtocol sp = new SocketProtocol();
			outputLine = sp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				LoggerManager.getInstance().logAtDebugTime(this.getClass().getName(), "Message *raw* from Client: "+inputLine);
				outputLine = sp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (TamandareException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
	}
}
