/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentUniqueIdType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.MimeTypeType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RepositoryUniqueIdType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorListType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryErrorType;
import edu.tcu.mi.ihe.iti.ebxml.rs.RegistryResponseType;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class Response_ITI_43 {
	public static Logger logger = Logger.getLogger(Response_ITI_43.class);
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
    private List<RetrieveResult> retrieveResultlist;

    /**
     * @param msgContext
     */
    public void parser(MessageContext msgContext) {
        if (msgContext != null) {
            parser(msgContext.getEnvelope());
            
            synchronized (this) {
                this.notify();
            }
        }else {
            this.setCodeContext("NO MessageContext");
        }
    }
    
    public void parser(SOAPEnvelope envelope) {
    	try{
    		response = envelope.getBody().getFirstElement().getFirstElement();
    	}catch(java.lang.NullPointerException e){
    		logger.error(e.toString());
    	}
        if (response != null) {

            AxiomUtil axiom = AxiomUtil.getInstance();
            RetrieveDocumentSetResponseType retrieveDocumentSetResponse = axiom.fromXML(response, RetrieveDocumentSetResponseType.class);
            RegistryResponseType registryResponse = retrieveDocumentSetResponse.getRegistryResponseType();
            status = registryResponse.getStatus();
            if (status.equals(Namespace.FAILURE.getNamespace())) {
                RegistryErrorListType errorList = registryResponse.getRegistryErrorList();
                List<RegistryErrorType> errors = errorList.getRegistryErrors();
                for(RegistryErrorType error : errors){
                    this.setCodeContext(error.getCodeContext());
                    this.setErrorCode(error.getErrorCode());
                    this.setLocation(error.getLocation());
                    this.setSeverity(error.getSeverity());
                }
            }
            if (status.equals(Namespace.SUCCESS.getNamespace())) { 
                List<DocumentResponseType> documentResponses = retrieveDocumentSetResponse.getDocumentResponses();
                RetrieveResult rr = new RetrieveResult();
                for(DocumentResponseType documentResponse : documentResponses){
                	RepositoryUniqueIdType repositoryUniqueId = documentResponse.getRepositoryUniqueId();
                    rr.setRepositoryUniqueId(repositoryUniqueId.getValue());
                    DocumentUniqueIdType documentUniqueId = documentResponse.getDocumentUniqueId();
                    rr.setDocumentUniqueId(documentUniqueId.getValue());
                    MimeTypeType mimeType = documentResponse.getMimeType();
                    rr.setMimeType(mimeType.getValue());
                	DocumentType document = documentResponse.getDocument();
                    rr.setDocument(document.getValue());
                }
                retrieveResultlist.add(rr);
            }
        }else {
            this.setCodeContext("NO SOAP Body");
        }
    }

    public String toString() {
        return new Gson().toJson(this);
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
