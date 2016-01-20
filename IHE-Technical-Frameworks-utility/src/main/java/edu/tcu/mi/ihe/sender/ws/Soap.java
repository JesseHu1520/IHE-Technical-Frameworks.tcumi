package edu.tcu.mi.ihe.sender.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.WSDL2Constants;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.commons.io.IOUtils;

import edu.tcu.mi.ihe.sender.MessageSender;
import lombok.Getter;
import lombok.Setter;

public class Soap extends MessageSender {

	@Getter @Setter
	protected String action = "";
	@Getter @Setter
	protected ServiceClient sender = null;
	@Getter @Setter
	protected String endpoint = "";

	@Getter @Setter
	protected OMElement data = null;
	@Getter @Setter
	protected boolean mtomXop;
	
	@Getter @Setter
	private Options option;
	@Getter @Setter
	private MessageContext request;
	@Getter @Setter
	private MessageContext response;
	@Getter @Setter
	private OperationClient client;
	@Getter @Setter
	private boolean swa;
	@Getter @Setter
	private boolean canSend = true;

	public Soap(String endpoint, String action) {
		this.endpoint = endpoint;
		this.action = action;
		this.request = new MessageContext();
		try {
			this.sender = new ServiceClient();
		} catch (AxisFault e) {
			logger.error("Soap : " + e.toString());
			setCanSend(false);
		}

	}

	public OMElement send(InputStream is) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "utf-8");
			String str = writer.toString();
			OMElement element = AXIOMUtil.stringToOM(str);
			return send(element);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public OMElement send(String data){
		try {
			OMElement element = AXIOMUtil.stringToOM(data);
			return send(element);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public OMElement send(OMElement data) {
		if (!isCanSend()) {
			logger.error("Can not Send");
			return null;
		}
		try {
			request.setEnvelope(createEnvelope(data));
			request.setDoingMTOM(true);
			request.setDoingSwA(true);
			
			sender.setOptions(getOptions());
			
			client = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			client.addMessageContext(request);
			
			client.execute(true);
			response = client.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			if (response == null) {
				logger.error("No MessageContext");
				return null;
			}
			//-------------- debug ------------ 
			SOAPEnvelope envelope01 = request.getEnvelope();
			logger.info("request\t" + envelope01);
			SOAPEnvelope envelope02 = response.getEnvelope();
			logger.info("response\t" + envelope02.toString());
			//-------------- debug ------------ 
		} catch (org.apache.axiom.om.OMException e) {
			logger.error("send : \t" + e.toString());
		} catch (java.lang.NullPointerException e) {
			logger.error("send : \t" + e.toString());
		} catch (AxisFault e) {
			logger.error("send : \t" + e.toString());
		}
		

		SOAPEnvelope envelope = (response != null) ? response.getEnvelope() : null;
		SOAPBody body = (envelope != null) ? envelope.getBody() : null;
		
		return (body != null) ? body.getFirstElement() : null;
	}

	protected Options getOptions() {
		Options options = new Options();
		options.setTimeOutInMilliSeconds(1000 * 60 * 10);
		options.setAction(action);
		options.setProperty(WSDL2Constants.ATTRIBUTE_MUST_UNDERSTAND, "1");
		options.setTo(new EndpointReference(endpoint));
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setProperty(Constants.Configuration.ENABLE_SWA, swa);
		options.setProperty(Constants.Configuration.ENABLE_MTOM,  ((mtomXop) ? Constants.VALUE_TRUE : Constants.VALUE_FALSE));

		return options;
	}

	private SOAPEnvelope createEnvelope(OMElement data) {
		SOAPFactory fac = OMAbstractFactory.getSOAP12Factory();
		SOAPEnvelope envelope = fac.getDefaultEnvelope();
		SOAPHeader header = envelope.getHeader();
		OMNamespace wsa = fac.createOMNamespace("http://www.w3.org/2005/08/addressing", "wsa");
		OMElement to = fac.createOMElement("To", wsa);
		to.setText(endpoint);
		header.addChild(to);
		OMElement messageID = fac.createOMElement("MessageID", wsa);
		messageID.setText(UUID.randomUUID().toString());
		header.addChild(messageID);
		OMElement Action = fac.createOMElement("Action", wsa);
		Action.setText(action);
		header.addChild(Action);
		SOAPBody body = envelope.getBody();
		body.addChild(data);
		return envelope;
	}

	public String addAttachment(DataHandler handler) {
		String contentId = request.addAttachment(handler);
		logger.info("cid:" + contentId);
		return "cid:" + contentId;
	}
}
