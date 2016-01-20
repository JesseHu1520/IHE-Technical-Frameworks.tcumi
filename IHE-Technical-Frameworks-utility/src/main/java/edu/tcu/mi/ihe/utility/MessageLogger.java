package edu.tcu.mi.ihe.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value="classpath:ihexds.properties")
public class MessageLogger {
	public static Logger logger = Logger.getLogger(MessageLogger.class);
	
	@Value("${log}")
	private boolean log;
	
	private String rootDir;
	public MessageLogger(){
		rootDir = System.getProperty("user.home");
		System.out.println("user.home : " + rootDir);
	}

	public void saveLog(String filename, String postfix, OMElement message) {
		if(!log) return;
		File file = new File(rootDir + "/Metadata/");
		if (!file.exists()) {
			file.mkdirs();
		}
		String log = null;
		log = message.toString();
		if (log != null) {
			String output = rootDir + "/Metadata/" + filename + postfix + "_" + Thread.currentThread().getName() + ".xml";
			System.out.println(output);
			try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output, false), "utf8"))){
				bw.write(log);	
			} catch(IOException e){
				logger.error(e.toString() + "\t" + e.getLocalizedMessage());
			} catch(java.lang.NullPointerException e){
				logger.error(e.toString() + "\t" + e.getLocalizedMessage());
			}
		}
	}

}
