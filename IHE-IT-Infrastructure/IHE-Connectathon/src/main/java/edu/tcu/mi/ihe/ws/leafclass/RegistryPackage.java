package edu.tcu.mi.ihe.ws.leafclass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.zk.model.KeyValue.KeyValue;
import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;


public class RegistryPackage implements IEBXMLParser {
	public static Logger logger = Logger.getLogger(RegistryPackage.class);
	private Map<String, String> schemeMap = new HashMap<String, String>();
	public final String SUBMISSON_SET_AUTHOR = "urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d";

	public RegistryPackage() {
		schemeMap.put("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500",
				"SUBMISSION_SET_CONTENT_TYPE_CODE");
		schemeMap.put("urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832",
				"SUBMISSION_SET_SOURCE_IDENTIFICATION_SCHEME");
		schemeMap.put("urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8",
				"SUBMISSION_SET_UNIQUE_IDENTIFICATION_SCHEME");
		schemeMap.put("urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446",
				"SUBMISSION_SET_PATIENT_IDENTIFICATION_SCHEME");
		schemeMap.put("urn:uuid:1ba97051-7806-41a8-a48b-8fce7af683c5",
				"FOLDER_CODE");
		schemeMap.put("urn:uuid:75df8f67-9973-4fbe-a900-df66cefecc5a",
				"FOLDER_UNIQUE_IDENTIFICATION_SCHEME");
		schemeMap.put("urn:uuid:f64ffdf0-4b97-4e06-b79f-a52b38ec2f8a",
				"FOLDER_PATIENT_IDENTIFICATION_SCHEME");
	}

	@SuppressWarnings("unchecked")
	public void execute(OMElement element, KeyValuesImpl map) {
		Iterator<OMElement> list = element.getChildElements();
		while (list.hasNext()) {
			OMElement e = list.next();
			{
				String name = e.getAttributeValue(new QName("name"));
				if (name != null) {
					String slot = e.getFirstElement().getFirstElement()
							.getText();
					map.add(new KeyValue(name, slot.trim()));
				}
			}
			{
				String classificationScheme = e.getAttributeValue(new QName(
						"classificationScheme"));
				if (classificationScheme != null) {
					if (classificationScheme.equals(SUBMISSON_SET_AUTHOR)) {
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
			{
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
					logger.trace("Name\t" + e.getFirstElement().getLocalName());
					String value = e.getFirstElement().getAttributeValue(
							new QName("value"));
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
