package edu.tcu.mi.ihe.ws.leafclass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.zk.model.KeyValue.KeyValue;
import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;

public class ExtrinsicObject implements IEBXMLParser {
	public static Logger logger = Logger.getLogger(ExtrinsicObject.class);
	private Map<String, String> schemeMap = new HashMap<String, String>();
	private final String DOC_ENTRY_AUTHOR = "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d";

	public ExtrinsicObject() {
		schemeMap.put("urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4",
				"DOC_ENTRY_EVENT_CODE");
		schemeMap.put("urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a",
				"DOC_ENTRY_CLASS_CODE");
		schemeMap.put("urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d",
				"DOC_ENTRY_FORMAT_CODE");
		schemeMap.put("urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead",
				"DOC_ENTRY_PRACTICE_SETTING_CODE");
		schemeMap.put("urn:uuid:f0306f51-975f-434e-a61c-c59651d33983",
				"DOC_ENTRY_TYPE_CODE");
		schemeMap.put("urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1",
				"DOC_ENTRY_HEALTH_CARE_FACILITY_CODE");
		schemeMap.put("urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f",
				"DOC_ENTRY_CONFIDENTIALITY_CODE");
		schemeMap.put("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab",
				"DOC_ENTRY_UNIQUE_IDENTIFICATION_SCHEME");
		schemeMap.put("urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427",
				"DOC_ENTRY_PATIENT_IDENTIFICATION_SCHEME");
	}

	@SuppressWarnings("unchecked")
	public void execute(OMElement element, KeyValuesImpl map) {
		String mimeType = element.getAttributeValue(new QName("mimeType"));
		String status = element.getAttributeValue(new QName("status"));
		map.add(new KeyValue("mimeType", mimeType));
		map.add(new KeyValue("status", status));
		Iterator<OMElement> list = element.getChildElements();
		while (list.hasNext()) {
			OMElement e = list.next();
			{
				String name = e.getAttributeValue(new QName("name"));
				if (name != null) {
					OMElement a = e.getFirstElement();
					if (name.equals("sourcePatientInfo")) {// sourcePatientInfo
						Iterator<OMElement> ValueList = a.getChildElements();
						while (ValueList.hasNext()) {
							String value = ValueList.next().getText();
							String[] v = value.split("[|]");
							map.add(new KeyValue(v[0], v[1].trim()));
						}
					} else {// Slot
						String slot = a.getFirstElement().getText();
						map.add(new KeyValue(name, slot.trim()));
					}
				}
			}
			{// Author
				String classificationScheme = e.getAttributeValue(new QName(
						"classificationScheme"));
				if (classificationScheme != null) {
					if (classificationScheme.equals(DOC_ENTRY_AUTHOR)) {
						Iterator<OMElement> authors = e.getChildElements();
						while (authors.hasNext()) {
							OMElement author = authors.next();
							String name = author.getAttributeValue(new QName(
									"name"));
							if (name != null) {
								String slot = author.getFirstElement()
										.getFirstElement().getText();
								map.add(new KeyValue(name, slot.trim()));
							}
						}
					} else {
						String display = SchemeToDisplay(classificationScheme);
						String slot = e.getFirstElement().getFirstElement()
								.getFirstElement().getText();
						map.add(new KeyValue(display, slot.trim()));
					}
				}
			}
			{// Code
				String identificationScheme = e.getAttributeValue(new QName(
						"identificationScheme"));
				if (identificationScheme != null) {
					String display = SchemeToDisplay(identificationScheme);
					String value = e.getAttributeValue(new QName("value"));
					map.add(new KeyValue(display, value.trim()));
				}
			}
			{
				String localName = e.getLocalName();
				if (localName.equalsIgnoreCase("Name")) {
					OMElement child = (e != null) ? e.getFirstElement() : null;
					String value = (child != null) ? child
							.getAttributeValue(new QName("value")) : null;
					map.add(new KeyValue("Name", value));
				}
			}
			{
				String localName = e.getLocalName();
				if (localName.equalsIgnoreCase("Description")) {
					OMElement child = (e != null) ? e.getFirstElement() : null;
					String value = (child != null) ? child
							.getAttributeValue(new QName("value")) : null;
					logger.trace("Description\t" + localName + "\t value :"
							+ value);
					map.add(new KeyValue("Description", value));
				}
			}
		}
	}

	private String SchemeToDisplay(String scheme) {
		String display = schemeMap.get(scheme);
		return display;
	}

}
