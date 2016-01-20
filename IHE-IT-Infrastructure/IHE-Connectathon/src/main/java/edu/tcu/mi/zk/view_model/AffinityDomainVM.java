/**
 * 
 */
package edu.tcu.mi.zk.view_model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.zkoss.bind.annotation.Init;


import edu.tcu.mi.hl7.pid.AffinityDomain;
import edu.tcu.mi.ihe.utility.xml.XMLPath;

public class AffinityDomainVM {
	public static Logger logger = Logger.getLogger(AffinityDomainVM.class);
	private XMLPath configurations;
	private List<AffinityDomain> affinityDomains;

	@Init
	public void init() {
		setConfigurations(new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("PixPdqClientDomains.xml")));
		setAffinityDomains(new ArrayList<AffinityDomain>());
		String expression = "Configuration/Identifier";
		NodeList nodeList = configurations.QueryNodeList(expression);

		for (int i = 0; i < nodeList.getLength(); i++) {
			AffinityDomain affinityDomain = new AffinityDomain();
			Node node = nodeList.item(i);
			String name = node.getAttributes().getNamedItem("name")
					.getNodeValue();
			logger.debug(name);
			affinityDomain.setName(name);
			NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child = childNodes.item(j);
				if (child.getNodeName().equalsIgnoreCase("NamespaceId")) {
					affinityDomain.setNamespaceID(child.getTextContent());
				}
				if (child.getNodeName().equalsIgnoreCase("UniversalId")) {
					affinityDomain.setUniversalID(child.getTextContent());
				}
				if (child.getNodeName().equalsIgnoreCase("UniversalIdType")) {
					affinityDomain.setUniversalIDType(child.getTextContent());
				}
			}
			affinityDomains.add(affinityDomain);
		}
		System.gc();
	}

	public XMLPath getConfigurations() {
		return this.configurations;
	}

	public void setConfigurations(XMLPath configurations) {
		this.configurations = configurations;
	}

	public List<AffinityDomain> getAffinityDomains() {
		return affinityDomains;
	}

	public void setAffinityDomains(List<AffinityDomain> affinityDomains) {
		this.affinityDomains = affinityDomains;
	}

}
