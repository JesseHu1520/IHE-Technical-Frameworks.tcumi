package edu.tcu.mi.ihe.ws.leafclass;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.zk.model.KeyValue.KeyValue;
import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;


public class Association implements IEBXMLParser {
    public static Logger logger = Logger.getLogger(Association.class);

	public void execute(OMElement element, KeyValuesImpl map) {
        String sourceObject = element.getAttributeValue(new QName("sourceObject"));
        String targetObject = element.getAttributeValue(new QName("targetObject"));
        String associationType = element.getAttributeValue(new QName("associationType"));
		map.add(new KeyValue("sourceObject", sourceObject));
		map.add(new KeyValue("targetObject", targetObject));
		map.add(new KeyValue("associationType", associationType));
	}

}
