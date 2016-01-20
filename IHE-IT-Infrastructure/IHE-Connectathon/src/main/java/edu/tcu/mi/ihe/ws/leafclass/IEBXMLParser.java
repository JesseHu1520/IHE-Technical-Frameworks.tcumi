package edu.tcu.mi.ihe.ws.leafclass;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;


public interface IEBXMLParser {
	void execute(OMElement element, KeyValuesImpl map) ;
}
