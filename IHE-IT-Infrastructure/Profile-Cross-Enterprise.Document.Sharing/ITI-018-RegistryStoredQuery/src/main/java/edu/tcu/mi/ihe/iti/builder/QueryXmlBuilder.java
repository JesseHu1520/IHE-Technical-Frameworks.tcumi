package edu.tcu.mi.ihe.iti.builder;

import java.util.List;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.model.QueryModel;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;

public class QueryXmlBuilder extends MessageBuilder {
	@Getter
	private QueryModel query;
	
	public QueryXmlBuilder(QueryModel query){
		this.query = query;
		this.setEndpoint(query.getEndpoint());
	}
	
	
	private OMElement createSlot(String name, Object _value) {
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement slot = axiom.createOMElement(EbXML.Slot, Namespace.RIM3);
		slot.addAttribute("name", name, null);
		OMElement valueList = axiom.createOMElement(EbXML.ValueList, Namespace.RIM3);

		slot.addChild(valueList);
		OMElement value = axiom.createOMElement(EbXML.Value, Namespace.RIM3);
		if(_value instanceof String) {
			value.setText("\'" + _value + "\'");
		} 
		if(_value instanceof Long)  {
			value.setText("" + _value);
		}
		valueList.addChild(value);
		slot.addChild(valueList);
		return slot;
	}
	
	private OMElement createSlot(String name, String ... values) {
		AxiomUtil axiom = AxiomUtil.getInstance();
		OMElement slot = axiom.createOMElement(EbXML.Slot, Namespace.RIM3);
		slot.addAttribute("name", name, null);
		OMElement valueList = axiom.createOMElement(EbXML.ValueList, Namespace.RIM3);

		for(String _value : values){
			OMElement value = axiom.createOMElement(EbXML.Value, Namespace.RIM3);
			value.setText("(\'" + _value + "\')");
			valueList.addChild(value);
		}
		slot.addChild(valueList);
		return slot;
	}
	
	@Override
	public OMElement getMessageFromXML(){
		if(!validate()) return null;
		if(query.getUuid() == null){
			logger.error("Not found message of uuid." );
			return null;
		}
		if(query.getReturnType() == null){
			logger.error("Not found message of returnType.");
			return null;
		}
		
		AxiomUtil axiom = AxiomUtil.getInstance();
		
		/* AdhocQuery */
		OMElement adhocQuery = axiom.createOMElement(EbXML.AdhocQuery, Namespace.RIM3);
		adhocQuery.addAttribute("id", query.getUuid(), null);
		
		for(String key : query.getParameter().keySet()){
			Object val = query.getParameter().get(key);
			OMElement slot = createSlot(key, val);
			adhocQuery.addChild(slot);
		}
		for(String key : query.getParameters().keySet()){
			List<String> vals = query.getParameters().get(key);
			String[] _vals = vals.toArray(new String[vals.size()]);
			OMElement slot = createSlot(key, _vals);
			adhocQuery.addChild(slot);
		}
		
		/* AdhocQueryRequest */
		OMElement adhocQueryRequest = axiom.createOMElement(EbXML.AdhocQueryRequest, Namespace.QUERY3);
		OMElement requestSlotList = axiom.createOMElement(EbXML.RequestSlotList, Namespace.RS3);
		adhocQueryRequest.addChild(requestSlotList);
		/* AdhocQueryRequest */
		OMElement responseOption = axiom.createOMElement(EbXML.ResponseOption, Namespace.QUERY3);
		responseOption.addAttribute("returnType", query.getReturnType(), null);
		
		if (responseOption != null) 
			adhocQueryRequest.addChild(responseOption);
		if (adhocQuery != null) 
			adhocQueryRequest.addChild(adhocQuery);
		return adhocQueryRequest;
	}

	@Override
	protected boolean validate() {
		return query.validate();
	}

	@Override
	public String toString(){
		return this.getMessageFromXML().toString();
	}
	
	@Deprecated
	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}
}
