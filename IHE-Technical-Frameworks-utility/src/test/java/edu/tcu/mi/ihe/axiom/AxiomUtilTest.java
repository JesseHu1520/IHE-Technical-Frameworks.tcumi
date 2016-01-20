package edu.tcu.mi.ihe.axiom;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.junit.Test;

import edu.tcu.mi.ihe.utility.AxiomUtil;

public class AxiomUtilTest {

	@Test
	public void test() {
		String resource = "<?xml version='1.0' encoding='UTF-8'?><soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:Action>urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-bResponse</wsa:Action><wsa:RelatesTo>urn:uuid:512d5ceb-03ec-4e8b-a44e-d8b9723257e9</wsa:RelatesTo></soapenv:Header><soapenv:Body><rs:RegistryResponse xmlns:rs=\"urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0\" status=\"urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success\" /></soapenv:Body></soapenv:Envelope>";

		AxiomUtil axiom = AxiomUtil.getInstance();
		InputStream is = new ByteArrayInputStream(resource.getBytes());
		OMElement envelope = axiom.fromResources(is);
		QName qname = new QName("http://www.w3.org/2003/05/soap-envelope", "Body");
		OMElement header = envelope.getFirstChildWithName(qname );
		System.out.println(header.getFirstElement());
	}

}
