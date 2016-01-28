package edu.tcu.mi.ihe.iti.builder;

import java.util.HashMap;
import java.util.UUID;

import org.apache.axiom.om.OMElement;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.utility.AxiomUtil;

public abstract class EntityXmlBuilder extends MessageBuilder {
	protected String objectType;

	protected abstract String getId();
	
	protected String generateUUID() {
		UUID uid = UUID.randomUUID();
		return "urn:uuid:" + uid.toString();
	}
	
	protected OMElement generateNameOrDescription(String text, EbXML NameOrDescription) {
		AxiomUtil axiom = AxiomUtil.getInstance();
		/* LocalizedString */
		OMElement localizedString = null;
		if (text != null && !text.equals("")) {
			localizedString = axiom.createOMElement(
					EbXML.LocalizedString, Namespace.RIM3);
			localizedString.addAttribute("value", text, null);
		}
		/* LocalizedString */

		/* Name or Description*/
		OMElement name = axiom.createOMElement(NameOrDescription,
				Namespace.RIM3);
		if (localizedString != null ) {
			name.addChild(localizedString);
		}
		/* Name or Description*/
		return name;
	}

	protected OMElement generateSlot(String name, String[] text){
		AxiomUtil axiom = AxiomUtil.getInstance();
		/* valueList */
		OMElement valueList = axiom.createOMElement(EbXML.ValueList, Namespace.RIM3);

		/* value */
		for (String t : text) {
			OMElement value = axiom.createOMElement(EbXML.Value, Namespace.RIM3);
			value.setText(t);
			valueList.addChild(value);
		}
		/* value */
		/* valueList */

		/* Slot */
		OMElement slot = axiom.createOMElement(EbXML.Slot, Namespace.RIM3);
		slot.addAttribute("name", name, null);
		slot.addChild(valueList);
		/* Slot */
		return slot;
	}
	
	protected OMElement generateClassification(String type, String value, String coding_scheme, String entryUUID, String uuid) {
		HashMap<String, String> code = extractCode(type, value);
		OMElement classification = null;
		if (code != null) {
			String localizedStringValue = code.get("Name");
			String slotValue = code.get("codeSystemName");
			String nodeRepresentation = code.get("nodeRepresentation");
			OMElement name = generateNameOrDescription(localizedStringValue, EbXML.Name);
			
			OMElement slot = generateSlot(coding_scheme, new String[] { slotValue });
			classification = generateClassification(uuid, entryUUID, nodeRepresentation, name, new OMElement[] { slot });
		}
		return classification;
	}
	
	protected OMElement generateExternalIdentifier(String uuid, String entryUUID, String value, OMElement name) {
		MetadataXmlBuilder.objectRef.add(uuid);

		AxiomUtil axiom = AxiomUtil.getInstance();
		/* ExternalIdentifier */
		OMElement externalIdentifier = axiom.createOMElement(EbXML.ExternalIdentifier, Namespace.RIM3);
		externalIdentifier.addAttribute("id", generateUUID(), null);
		externalIdentifier.addAttribute("identificationScheme", uuid, null);
		externalIdentifier.addAttribute("registryObject", entryUUID, null);
		if (value != null)
			externalIdentifier.addAttribute("value", value, null);
		if (name != null) {
			externalIdentifier.addChild(name);
		}
		/* ExternalIdentifier */
		return externalIdentifier;
	}
	

	private OMElement generateClassification(String uuid, String entryUUID, String nodeRepresentation, OMElement name, OMElement[] slot) {
		MetadataXmlBuilder.objectRef.add(uuid);
		
		AxiomUtil axiom = AxiomUtil.getInstance();
		/* classification */
		OMElement classification = axiom.createOMElement(EbXML.Classification,Namespace.RIM3);
		classification.addAttribute("id", generateUUID(), null);
		classification.addAttribute("classificationScheme", uuid, null);
		classification.addAttribute("classifiedObject", entryUUID, null);
		classification.addAttribute("nodeRepresentation", nodeRepresentation,null);
		for (OMElement s : slot) {
			classification.addChild(s);
		}
		if (name != null) {
			classification.addChild(name);
		}
		/* classification */
		return classification;
	}

	public OMElement generateClassification(){
		AxiomUtil axiom = AxiomUtil.getInstance();
		/* classification */
		OMElement classification = axiom.createOMElement(EbXML.Classification, Namespace.RIM3);
		classification.addAttribute("id", generateUUID(), null);
		classification.addAttribute("classificationNode", objectType, null);
		classification.addAttribute("classifiedObject", getId(), null);
		classification.addAttribute("nodeRepresentation", "", null);
		/* classification */
		return classification;
	}
	
	private HashMap<String, String> extractCode(String name, String code) {
		HashMap<String, String> valuelist = new HashMap<String, String>();
		String query = "Codes/CodeType[@name='" + name + "']/Code[@code='" + code + "']";
		Node node = MetadataXmlBuilder.codes.QueryNode(query);
		if(node == null){
			return null;
		}
		NamedNodeMap attrs = node.getAttributes();
		if (attrs != null) {
			valuelist.put("nodeRepresentation", attrs.getNamedItem("code").getNodeValue());
		}
		if (attrs != null) {
			valuelist.put("Name", attrs.getNamedItem("display").getNodeValue());
		}
		if (attrs != null) {
			valuelist.put("codeSystemName", attrs.getNamedItem("codingScheme").getNodeValue());
		}
		if (attrs != null) {
			valuelist.put("codeSystemVersion", "");
		}
		return valuelist;
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}
}
