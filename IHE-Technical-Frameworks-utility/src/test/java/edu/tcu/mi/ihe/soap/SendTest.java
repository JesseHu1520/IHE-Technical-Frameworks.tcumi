package edu.tcu.mi.ihe.soap;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.context.MessageContext;
import org.junit.Test;

import edu.tcu.mi.ihe.sender.ws.ServiceConsumer;

public class SendTest {

	private String ACTION = "urn:ihe:iti:2007:RetrieveDocumentSet";
	@Test
	public void test() {
		
		String iti_43 = "<RetrieveDocumentSetRequest xmlns=\"urn:ihe:iti:xds-b:2007\"><DocumentRequest><RepositoryUniqueId>1.3.6.1.4.1.21367.2010.1.2.1125.103</RepositoryUniqueId><DocumentUniqueId>1.3.6.1.4.1.21367.2010.1.2.203.64.84.247.20140306201634.4</DocumentUniqueId></DocumentRequest></RetrieveDocumentSetRequest>";
		
		ServiceConsumer soap = new ServiceConsumer("http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl", ACTION);
		soap.setMtomXop(true);
		OMElement response = soap.send(iti_43);
	}

}
