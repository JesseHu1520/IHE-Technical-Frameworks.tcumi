/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorType;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class Response_ITI_18 extends Response {
	@Getter @Setter
	private OMElement response;
	@Getter @Setter
	private String status;
	@Getter @Setter
	private String codeContext;
	@Getter @Setter
	private String errorCode;
	@Getter @Setter
	private String severity;
	@Getter @Setter
	private String location;
	@Getter @Setter
	private Set<String> list;

	/**
	 * @param msgContext
	 */
	public void parser(MessageContext msgContext) {
		setList(new TreeSet<String>());
		if (msgContext != null) {
			parser(msgContext.getEnvelope());
		} else {
			this.setCodeContext("NO MessageContext");
		}
	}

	public void parser(SOAPEnvelope envelope) {
		if (envelope != null)  parser(envelope.getBody());			
	}
	
	public void parser(SOAPBody body){
		parser(body.getFirstElement());
	}
	
	public void parser (OMElement response){
		setList(new TreeSet<String>());
//		logger.info(response);
		if (response != null) {
			AxiomUtil axiom = AxiomUtil.getInstance();
			AdhocQueryResponseType adhocQueryResponse = axiom.fromXML(response, AdhocQueryResponseType.class);
			
			status = adhocQueryResponse.getStatus();

			if (status.equals(Namespace.SUCCESS.getNamespace())) {
				RegistryObjectListType objectList = adhocQueryResponse.getRegistryObjectList();
				List<ExtrinsicObjectType> docEntries = objectList.getExtrinsicObjects();
				for(ExtrinsicObjectType docEntry : docEntries){
					this.list.add(docEntry.getId());
				}
				
			} else if (status.equals(Namespace.FAILURE.getNamespace())) {
				RegistryErrorListType errorList = adhocQueryResponse.getRegistryErrorList();
                List<RegistryErrorType> errors = errorList.getRegistryErrors();
                for(RegistryErrorType error : errors){
                    this.setCodeContext(error.getCodeContext());
                    this.setErrorCode(error.getErrorCode());
                    this.setLocation(error.getLocation());
                    this.setSeverity(error.getSeverity());
                }
			}
		}
		synchronized (this) {
			this.notify();
		}
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	public void addItemToList(String str) {
		this.list.add(str);
	}

	public boolean clean() {
    	errorCode = "";
    	codeContext = "";
    	status = "";
    	severity = "";
    	location = "";
    	return true;
	}
}
