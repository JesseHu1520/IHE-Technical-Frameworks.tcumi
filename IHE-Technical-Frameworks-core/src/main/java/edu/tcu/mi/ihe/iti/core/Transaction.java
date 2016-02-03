package edu.tcu.mi.ihe.iti.core;

import java.sql.Timestamp;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;

import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;


@PropertySource(value="classpath:ihexds.properties")
public abstract class Transaction {
	public static Logger logger = Logger.getLogger(Transaction.class);

	protected MessageBuilder messageBuilder;

	/**
	 * To implement the Transaction iti-20 RecordAuditEvent
	 */

	public abstract void auditLog();

	public abstract String webservice(MessageBuilder builder);
	public abstract String webservice(OMElement request, String endpoint, NonBlockCallBack callback);
	
	public String transaction(MessageBuilder builder){
		this.messageBuilder = builder;
		String response = webservice(builder);
		auditLog();
		return response;
	}
	
	public String transaction(MessageBuilder builder, NonBlockCallBack callback){
		this.messageBuilder = builder;
		return transaction(messageBuilder.getMessageFromXML(), messageBuilder.getEndpoint(), callback);
	}
	
	public String transaction(OMElement request, String endpoint, NonBlockCallBack callback){
		try {
			String response = webservice(request, endpoint, callback);
//			auditLog();
			return response;
		} catch (java.lang.NullPointerException e){
			e.printStackTrace();
		}
		return "";
	}
	
	protected void gc() {
		Runtime r = Runtime.getRuntime();
		long memory = r.freeMemory();
		logger.debug("Free Memory : " + memory);
		r.gc();
	}
}

