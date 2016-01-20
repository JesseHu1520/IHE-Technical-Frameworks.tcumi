package edu.tcu.mi.ihe.ws.leafclass;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.utility.AxiomUtil;
import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;

public class MetadataParser {
	public static Logger logger = Logger.getLogger(MetadataParser.class);
	private Map<String, KeyValuesImpl> mapArray = new TreeMap<String, KeyValuesImpl>();

	@SuppressWarnings({ "unchecked" })
	public MetadataParser(OMElement element) {
		OMElement firstLayer = element.getFirstElement();
		OMElement secondLayer = firstLayer.getFirstElement();
		Iterator<OMElement> list = null;
		if (firstLayer != null
				&& firstLayer.getLocalName().equals("RegistryObjectList")) {
			logger.info("firstLayer : " + firstLayer.getLocalName());
			list = firstLayer.getChildElements();
		} else if (secondLayer != null
				&& secondLayer.getLocalName().equals("RegistryObjectList")) {
			logger.info("secondLayer : " + secondLayer.getLocalName());
			list = secondLayer.getChildElements();
		}
		while (list != null && list.hasNext()) {
			OMElement e = list.next();
			String id = e.getAttributeValue(new QName("id"));
			String type = getClassifyingRegistryPackageObjects(e);
			logger.info("id : " + id);
			logger.info("type : " + type);
			if (type != null) {
				if (type.equals("DOC_ENTRY_OBJECT")) {
					KeyValuesImpl map = new KeyValuesImpl();
					mapArray.put(id, map);
					IEBXMLParser eo = new ExtrinsicObject();
					eo.execute(e, map);
				}
				if (type.equals("FOLDER_OBJECT")) {
					KeyValuesImpl map = new KeyValuesImpl();
					mapArray.put(id, map);
					IEBXMLParser rp = new RegistryPackage();
					rp.execute(e, map);
				}
				if (type.equals("SUBMISSON_SET_OBJECT")) {
					// KeyValuesImpl map = new KeyValuesImpl();
					// mapArray.put(id, map);
					// IEBXMLParser eo = new SubmissionSet();
					// eo.execute(e, map);
				}
				if (type.equals("ASSOCIATION")) {
					KeyValuesImpl map = new KeyValuesImpl();
					mapArray.put(id, map);
					IEBXMLParser asso = new Association();
					asso.execute(e, map);
				}
			}
		}
	}

	public Map<String, KeyValuesImpl> getMapArray() {
		return mapArray;
	}

	public void setMapArray(Map<String, KeyValuesImpl> array) {
		this.mapArray = array;
	}

	public String getClassifyingRegistryPackageObjects(OMElement entry) {

		String objectType = entry.getAttributeValue(new QName("objectType"));
		if (objectType != null) {
			if (objectType
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_OBJECT)) {
				return "DOC_ENTRY_OBJECT";
			}
			if (objectType
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_OBJECT)) {
				return "FOLDER_OBJECT";
			}
			if (objectType
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSON_SET_OBJECT)) {
				return "SUBMISSON_SET_OBJECT";
			}
			if (objectType
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.ASSOCIATION)) {
				return "ASSOCIATION";
			}
		}
		AxiomUtil axiom = AxiomUtil.getInstance();
		Iterator<OMElement> iterator = axiom.getChildrenWithName(entry, null,
				"Classification");
		while (iterator.hasNext()) {
			OMElement next = iterator.next();
			String classificationNode = next.getAttributeValue(new QName(
					"classificationNode"));
			if (classificationNode == null) {
				continue;
			}
			if (classificationNode
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_OBJECT)) {
				return "DOC_ENTRY_OBJECT";
			}
			if (classificationNode
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_OBJECT)) {
				return "FOLDER_OBJECT";
			}
			if (classificationNode
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSON_SET_OBJECT)) {
				return "SUBMISSON_SET_OBJECT";
			}
			if (classificationNode
					.equalsIgnoreCase(ProvideAndRegistryDocumentSet_B_UUIDs.ASSOCIATION)) {
				return "ASSOCIATION";
			}
		}
		return null;
	}

}
