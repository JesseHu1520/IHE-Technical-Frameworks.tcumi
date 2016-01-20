package edu.tcu.mi.ihe.iti.rfc3881;

import java.io.InputStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import edu.tcu.mi.ihe.constants.atna.CodeType;
import edu.tcu.mi.ihe.constants.atna.RFC3881;
import edu.tcu.mi.ihe.utility.xml.XMLPath;

@XmlType(name="CodedValueType")
@XmlAccessorType (XmlAccessType.FIELD)
public class CodedValueType extends RFC3881Message {
	
	@XmlAttribute(name="csd-code", required=true)
    protected String csd_code;
	@XmlAttribute(name="displayName")
    protected String displayName;
	@XmlAttribute(name="originalText", required=true)
    protected String originalText;
    @XmlAttribute(name = "codeSystem")
    protected String codeSystem;
    @XmlAttribute(name = "codeSystemName", required=true)
    protected String codeSystemName;
    
    @XmlTransient
	protected RFC3881 tag;
    @XmlTransient
    private XMLPath xmlPath;
    
    public CodedValueType(){
    	
    }
    
    public CodedValueType(RFC3881 tag, String code){
    	this.tag = tag;
    	this.csd_code = code;  
    }
    
    public CodedValueType(RFC3881 tag, String code, CodeType codeType){
    	this.tag = tag;
    	this.csd_code =code;    	
		String query = "Codes/CodeType[@name='" + codeType.toString() + "']/Code[@code='" + code + "']";
    	Class<? extends CodedValueType> clazz = this.getClass();
    	ClassLoader loader = clazz.getClassLoader();
    	InputStream is = loader.getResourceAsStream("atnacodes.xml");
    	xmlPath = new XMLPath(is);
    	Node node = xmlPath.QueryNode(query);
    	NamedNodeMap attrs = node.getAttributes();
    	originalText = attrs.getNamedItem("display").getNodeValue();
    	codeSystemName = attrs.getNamedItem("codingScheme").getNodeValue();
    }
    
    
    @Override
	public OMElement buildRFC3881() {
    	OMNamespace namespace = null;
    	OMElement element = axiom.createOMElement(tag.getTag(), namespace);
    	if(csd_code != null)
    		element.addAttribute("csd-code", csd_code, null);
    	if(originalText != null)
    		element.addAttribute("originalText", originalText, null);
    	if(codeSystemName != null)
    		element.addAttribute("codeSystemName", codeSystemName, null);
		return element;
    }

}
