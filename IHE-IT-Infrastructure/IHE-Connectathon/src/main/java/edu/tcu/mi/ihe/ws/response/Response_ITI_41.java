/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Gaduo
 */
public class Response_ITI_41 extends Response {
	@Getter @Setter
    private OMElement response;
	@Getter @Setter
	private String status;
	@Getter @Setter
    private String codeContext;
	@Getter @Setter
    private String errorCode = "NO ErrorList";
	@Getter @Setter
    private String severity;
	@Getter @Setter
    private String location;

    /**
     * @param msgContext
     */
    public void parser(MessageContext msgContext) {
        if (msgContext != null) {
            parser(msgContext.getEnvelope());
        }else {
            this.setCodeContext("NO MessageContext");
        }
    }
    
    public void parser(SOAPEnvelope envelope){
    	clean();
        if (envelope != null) {
            SOAPBody body = envelope.getBody();
            response = body.getFirstElement();
            
            if (response != null) {
                AxiomUtil axiom = AxiomUtil.getInstance();
                RegistryResponseType registryResponse = axiom.fromXML(response, RegistryResponseType.class);
                status = registryResponse.getStatus();
                RegistryErrorListType errorList = registryResponse.getRegistryErrorList();
                if(errorList != null){
                    List<RegistryErrorType> errors = errorList.getRegistryErrors();
                    for(RegistryErrorType error : errors){
                        this.setCodeContext(error.getCodeContext());
                        this.setErrorCode(error.getErrorCode());
                        this.setLocation(error.getLocation());
                        this.setSeverity(error.getSeverity());
                    }
                }
            } else 
                this.setCodeContext("NO SOAP Body");
        } else 
            this.setCodeContext("NO SOAP Envelope");
        
    }
    
    public String toString() {
        return new Gson().toJson(this);
    }

    public boolean clean(){
    	errorCode = "";
    	codeContext = "";
    	status = "";
    	severity = "";
    	location = "";
    	return true;
    }

}
