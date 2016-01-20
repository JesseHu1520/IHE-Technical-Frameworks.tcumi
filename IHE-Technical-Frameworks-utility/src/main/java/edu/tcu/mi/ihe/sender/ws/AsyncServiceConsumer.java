package edu.tcu.mi.ihe.sender.ws;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.description.WSDL2Constants;

public class AsyncServiceConsumer extends Soap {
//	private NonBlockCallBack callback;

	private boolean async;

	public AsyncServiceConsumer(String endpoint, String action) {
		super(endpoint, action);
//		callback = new NonBlockCallBack();
	}

	public OMElement send(OMElement data) {
		OMElement response = null;
		try {
			sender.setOptions(getOptions(getAction(), isMtomXop(), getEndpoint()));
			sender.engageModule(Constants.MODULE_ADDRESSING);

			synchronized (data) {
				response = sender.sendReceive(data);
				logger.info(response);
			}

			if (async)
				sender.cleanupTransport();
			
		} catch (AxisFault e) {
			logger.error(e.toString());
		} finally {
			if (sender != null) {
				try {
					sender.cleanup();
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
		}
		return response;
	}

	protected Options getOptions(String action, boolean enableMTOM, String url) {
		Options options = new Options();
		options.setAction(action);
		options.setTimeOutInMilliSeconds(1000*60*10);
		options.setProperty(WSDL2Constants.ATTRIBUTE_MUST_UNDERSTAND, "1");
		options.setTo(new EndpointReference(url));
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setProperty(Constants.Configuration.ENABLE_MTOM,  ((enableMTOM) ? Constants.VALUE_TRUE : Constants.VALUE_FALSE));
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		
		if ( async && !options.isUseSeparateListener()){
			options.setUseSeparateListener(async);
		}
		
		return options;
	}
	
//	public NonBlockCallBack getCallback() {
//		return callback;
//	}
//
//	public void setCallback(NonBlockCallBack callback) {
//		this.callback = callback;
//	}

	public void setAsync(boolean async) {
		this.async = async;
	}
	
	

}