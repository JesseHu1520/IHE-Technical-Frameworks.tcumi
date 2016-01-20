package edu.tcu.mi.ihe.constants;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.llom.OMNamespaceImpl;
import org.apache.axiom.soap.SOAPFactory;

public enum Namespace {
	WSA("http://www.w3.org/2005/08/addressing", "wsa"),
	SOAP12("http://www.w3.org/2003/05/soap-envelope", "soapenv"),
	
	IHE("urn:ihe:iti:xds-b:2007", "ihe"), RS2(
			"urn:oasis:names:tc:ebxml-regrep:registry:xsd:2.1", ""), QUERY2(
			"urn:oasis:names:tc:ebxml-regrep:query:xsd:2.1", ""), RIM2(
			"urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.1", "")

	, RS3("urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0", "rs"), LCM3(
			"urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0", "lcm"), QUERY3(
			"urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0", "query"), RIM3(
			"urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0", "rim")

	, SUCCESS("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success", ""), FAILURE(
			"urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Failure", ""), PARTIALSUCCESS(
			"urn:oasis:names:tc:ebxml-regrep:SResponseStatusType:PartialSuccess",
			""),

	Error("urn:oasis:names:tc:ebxml-regrep:ErrorSeverityType:Error", ""), Woo(
			"W", "")

	, APPROVED("urn:oasis:names:tc:ebxml-regrep:StatusType:Approved", ""), DEPRECATED(
			"urn:oasis:names:tc:ebxml-regrep:StatusType:Deprecated", ""), SUBMITTED(
			"urn:oasis:names:tc:ebxml-regrep:StatusType:Submitted", "")

	, HASMEMBER("urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember", ""), RPLC(
			"urn:oasis:names:tc:ebxml-regrep:AssociationType:RPLC", ""), XFRM(
			"urn:oasis:names:tc:ebxml-regrep:AssociationType:XFRM", ""), APND(
			"urn:oasis:names:tc:ebxml-regrep:AssociationType:APND", ""), XFRM_RPLC(
			"urn:oasis:names:tc:ebxml-regrep:AssociationType:XFRM_RPLC", ""), SIGNS(
			"urn:oasis:names:tc:ebxml-regrep:AssociationType:signs", "")

	, ASSOCIATION(
			"urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Association",
			"");
	private String namespace;
	private String prefix;
	private OMNamespace OMNamespace;

	Namespace(String namespace, String prefix) {
		this.setNamespace(namespace);
		this.setPrefix(prefix);

		SOAPFactory factory = OMAbstractFactory.getSOAP12Factory();
		OMNamespace = factory.createOMNamespace(namespace, prefix);

	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public OMNamespace getOMNamespace() {
		return OMNamespace;
	}

	public void setOMNamespace(OMNamespaceImpl oMNamespace) {
		OMNamespace = oMNamespace;
	}

}
