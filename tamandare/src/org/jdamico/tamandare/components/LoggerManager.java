package org.jdamico.tamandare.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdamico.tamandare.dataobjects.LogData;
import org.jdamico.tamandare.utils.Constants;

public class LoggerManager {
	
		
		private static final Logger logger = Logger.getLogger("LoggerManager");
		
		private static LoggerManager INSTANCE;
		public static LoggerManager getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new LoggerManager();
			}
			return INSTANCE;
		}
		
		public void logAtDebugTime(String className, String logLine){
			log(className, logLine, false);
		}
		
		public void logAtExceptionTime(String className, String logLine) {
			log(className, logLine, true);
		}
		
		/** 
		 * Check if log direction is SystemOut or a separate file
		 * Check if a log file exist
		 * If there is no file create, on the contrary, use the file
		 * @throws IOException 
		 *
		 */
		private void log(String className, String logLine, boolean logLevel) {
			String formatedLog = " ("+className+") "+logLine;
			
			
				String fileName = Constants.LOG_NAME;
				
				fileName = Constants.TAMANDARE_LOG_FOLDER + fileName;
				
				
				String stime = getCurrentDateTimeFormated();
				if(logLevel){
					formatedLog = stime+Constants.SEVERE_LOGLEVEL+formatedLog+"\n";
				}else{
					formatedLog = stime+Constants.NORMAL_LOGLEVEL+formatedLog+"\n";
				}
				
				int limit = Constants.FIXED_LOGLIMIT;
				
				
				
				File listenerLog = null;
				FileWriter fw = null;
				BufferedWriter bwr = null;
				try{
									
					listenerLog = new File(fileName);
					if(!listenerLog.exists()){
						listenerLog.createNewFile();
					} else if(listenerLog.length() > limit){
						/* 
						 * check if file is too big
						 */
						listenerLog.delete();
						listenerLog.createNewFile();
						
					}
					
					fw = new FileWriter(fileName, true);
					bwr = new BufferedWriter(fw);
					bwr.write(formatedLog);
					bwr.close();
					fw.close();
				}catch(IOException ioe){
					logger.log(Level.SEVERE, "File listenerLog = new File("+fileName+") "+ioe.getMessage());
				}finally{
					listenerLog = null;
					fw = null;
					bwr = null;
				}
				
				
				
			
		}
		
		public ArrayList getLogsData(){
			/* 
			 * Scan logs folder
			 */
			ArrayList logsArray = new ArrayList();
			LogData logData = null;
			
			File foldersLog = new File(Constants.TAMANDARE_FOLDER);
			
		    String[] logsList = foldersLog.list();
		    
		    if (logsList == null) {
		        logAtExceptionTime(this.getClass().getName(), "No log files found! Check system configuration.");
		    } else {
		        for (int i=0; i< logsList.length; i++) {
		        	logData = new LogData();
		        	String logName = logsList[i];
		            logData.setLogName(logName);
		            logData.setLogPrefix(logName.replaceAll("_"+Constants.LOG_NAME, ""));
		            logData.setLogSize(getLogSize(logName));
		            logData.setLogLines(getLogLines(logName));
		            logsArray.add(logData); 
		        }
		    }
		    return logsArray;
		}

		private int getLogLines(String fileName) {
			int lines = 0;
		    try {
		    	FileReader fr =  new FileReader(Constants.TAMANDARE_FOLDER+fileName);
		        BufferedReader fileInput = new BufferedReader(fr);
		        while ((fileInput.readLine()) != null) {
		            lines++;
		        }
		        fileInput.close();
		        fr.close();
		    } catch (IOException e) {
		    	logAtExceptionTime(this.getClass().getName(), "getLogLines(String fileName) "+e.getMessage());
		    }
			return lines;
		}

		private long getLogSize(String fileName) {
			File listenerLog = new File(Constants.TAMANDARE_FOLDER+fileName);
			return listenerLog.length();
		}
		
		public String getCurrentDateTimeFormated(){
			Date date = new Date();
			Format formatter = new SimpleDateFormat("yyyyMMMdd_HH:mm:ss");
			String stime = formatter.format(date);
			return stime;
		}
	}


