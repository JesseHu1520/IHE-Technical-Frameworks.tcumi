package edu.tcu.mi.ihe.iti.builder;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.axiom.om.OMElement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import lombok.Getter;

public abstract class QueryBuilder extends MessageBuilder {
	private AxiomUtil axiom;
	@Getter
	private String uuid;
	private String returnType;

	protected Map<String, String> parameter;
	protected Map<String, List<String>> parameters;

	public QueryBuilder(String uuid){
		this.uuid = uuid;
		axiom = AxiomUtil.getInstance();
		parameter = Maps.newHashMap();
		parameters = Maps.newHashMap();
	}
	
	private OMElement createSlot(String name, String _value) {
		OMElement slot = axiom.createOMElement(EbXML.Slot, Namespace.RIM3);
		slot.addAttribute("name", name, null);
		OMElement valueList = axiom.createOMElement(EbXML.ValueList, Namespace.RIM3);

		slot.addChild(valueList);
		OMElement value = axiom.createOMElement(EbXML.Value, Namespace.RIM3);
		value.setText("\'" + _value + "\'");
		valueList.addChild(value);
		slot.addChild(valueList);
		return slot;
	}
	
	private OMElement createSlot(String name, String ... values) {
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

	public QueryBuilder andReturnObjectRef() {
		returnType = "ObjectRef";
		return this;
	}

	public QueryBuilder andReturnLeafClass() {
		returnType = "LeafClass";
		return this;
	}
	
	protected void andStatus(String key, String value){
		if(this.parameters.containsKey(key)){
			List<String> val = this.parameters.get(key);
			TreeSet<String> set = Sets.newTreeSet(val);
			set.add(value);
			this.parameters.put(key, Lists.newArrayList(set));
		} else {
			this.parameters.put(key, Lists.newArrayList(value));
		}
	}
	
	@Override
	public OMElement getMessageFromXML(){
		if(!validate()) return null;
		if(uuid == null) return null;
		
		/* AdhocQuery */
		OMElement adhocQuery = axiom.createOMElement(EbXML.AdhocQuery, Namespace.RIM3);
		adhocQuery.addAttribute("id", uuid, null);
		
		for(String key : parameter.keySet()){
			String val = this.parameter.get(key);
			OMElement slot = createSlot(key, val);
			adhocQuery.addChild(slot);
		}
		for(String key : parameters.keySet()){
			List<String> vals = this.parameters.get(key);
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
		responseOption.addAttribute("returnType", returnType, null);
		
		if (responseOption != null) 
			adhocQueryRequest.addChild(responseOption);
		if (adhocQuery != null) 
			adhocQueryRequest.addChild(adhocQuery);
		return adhocQueryRequest;
	}
}
