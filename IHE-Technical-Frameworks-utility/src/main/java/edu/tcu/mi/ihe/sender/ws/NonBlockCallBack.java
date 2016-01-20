package edu.tcu.mi.ihe.sender.ws;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

public class NonBlockCallBack implements AxisCallback {
	public static Logger logger = Logger.getLogger(NonBlockCallBack.class);

	@Getter @Setter
	private SOAPEnvelope envelope;
	@Getter @Setter
	private MessageContext context;

	public void onMessage(MessageContext msgContext) {
		setContext(msgContext);
		envelope = msgContext.getEnvelope();
		logger.trace(envelope);
		setEnvelope(envelope);
	}

	public void onFault(MessageContext msgContext) {
		QName errorCode = new QName("faultcode");
		QName reason = new QName("faultstring");
		OMElement fault = msgContext.getEnvelope().getBody().getFault();
		logger.error("ErrorCode[" + fault.getFirstChildWithName(errorCode).getText() + "] caused by: " + fault.getFirstChildWithName(reason).getText());
		synchronized (this) {
			try {
				this.notify();
			} catch (Exception e) {
			}
		}
	}

	public void onError(Exception e) {
		synchronized (this) {
			try {
				this.notify();
			} catch (Exception ex) {
			}
		}
		e.printStackTrace();
	}

	public void onComplete() {
		synchronized (this) {
			try {
				this.notify();
			} catch (Exception e) {
			}
		}
	}
}
