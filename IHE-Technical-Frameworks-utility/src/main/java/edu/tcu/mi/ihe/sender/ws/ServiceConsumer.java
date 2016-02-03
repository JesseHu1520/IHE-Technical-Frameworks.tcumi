package edu.tcu.mi.ihe.sender.ws;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.WSDL2Constants;

import lombok.Getter;
import lombok.Setter;

public class ServiceConsumer extends Soap {
	@Getter @Setter
	private NonBlockCallBack callback;

	public ServiceConsumer(String endpoint, String action) {
		super(endpoint, action);
		callback = new NonBlockCallBack();
	}
	
	public ServiceConsumer(String endpoint, String action, NonBlockCallBack callback) {
		super(endpoint, action);
		this.callback = callback;
	}

	@Override
	public OMElement send(String data){
		try {
			synchronized (data) {
				OMElement element = AXIOMUtil.stringToOM(data);
				return send(element);
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public OMElement send(OMElement data) {
		if(data == null) return null;
		try {
			sender.setOptions(getOptions(getAction(), isMtomXop(), getEndpoint()));
			sender.engageModule(Constants.MODULE_ADDRESSING);
			synchronized (data) {
				sender.sendReceiveNonBlocking(data, callback);
			}
			synchronized (callback) {
				try {
					callback.wait();
				} catch (InterruptedException e) {
					logger.error(e.toString());
				}
			}
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
		MessageContext response = callback.getContext();
		SOAPEnvelope envelope = (response != null) ? response.getEnvelope() : null;
		SOAPBody body = (envelope != null) ? envelope.getBody() : null;
		
		return (body != null) ? body.getFirstElement() : null;
	}

	protected Options getOptions(String action, boolean enableMTOM, String url) {
		Options options = new Options();
		options.setAction(action);
		options.setProperty(WSDL2Constants.ATTRIBUTE_MUST_UNDERSTAND, "1");
		options.setTo(new EndpointReference(url));
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setProperty(Constants.Configuration.ENABLE_MTOM,  ((enableMTOM) ? Constants.VALUE_TRUE : Constants.VALUE_FALSE));
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setTimeOutInMilliSeconds(1000*60*10);
		return options;
	}

}