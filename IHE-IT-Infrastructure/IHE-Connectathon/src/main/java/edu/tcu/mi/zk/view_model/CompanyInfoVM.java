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


import edu.tcu.mi.ihe.utility.xml.XMLPath;
import edu.tcu.mi.zk.model.CompanyInfomation;

public class CompanyInfoVM {
	public static Logger logger = Logger.getLogger(CompanyInfoVM.class);
	private XMLPath configurations;
	private List<CompanyInfomation> companyInfomations;

	@Init
	public void init() {
		setConfigurations(new XMLPath(getClass().getClassLoader()
				.getResourceAsStream("configuration.xml")));
		setCompanyInfomations(new ArrayList<CompanyInfomation>());
		String expression = "Configuration/Connection";
		NodeList nodeList = configurations.QueryNodeList(expression);

		for (int i = 0; i < nodeList.getLength(); i++) {
			CompanyInfomation companyInfo = new CompanyInfomation();
			Node node = nodeList.item(i);
			String name = node.getAttributes().getNamedItem("name")
					.getNodeValue();
			// logger.debug(name);
			companyInfo.setName(name);
			NodeList childNodes = node.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child = childNodes.item(j);
				if (child.getNodeName().equalsIgnoreCase("PatitentId")) {
					// logger.debug("PatitentId：" + child.getTextContent());
					companyInfo.setPatitentId(child.getTextContent());
				}
				if (child.getNodeName().equalsIgnoreCase("Repository")) {
					NodeList repository = child.getChildNodes();
					for (int k = 0; k < repository.getLength(); k++) {
						Node rep = repository.item(k);
						if (rep.getNodeName().equalsIgnoreCase("Endpoint")) {
							// logger.debug("Endpoint：" + rep.getTextContent());
							companyInfo.setRepositoryEndpoint(rep
									.getTextContent());
						}
						if (rep.getNodeName().equalsIgnoreCase("UniqueId")) {
							// logger.debug("UniqueId：" + rep.getTextContent());
							companyInfo.setRepositoryUniqueId(rep
									.getTextContent());
						}
					}
				}
				if (child.getNodeName().equalsIgnoreCase("Registry")) {
					NodeList rigistry = child.getChildNodes();
					for (int k = 0; k < rigistry.getLength(); k++) {
						Node rep = rigistry.item(k);
						if (rep.getNodeName().equalsIgnoreCase("Endpoint")) {
							// logger.debug("Endpoint：" + rep.getTextContent());
							companyInfo.setRegistryEndpoint(rep
									.getTextContent());
						}
					}
				}
				if (child.getNodeName().equalsIgnoreCase("Certificate")) {
					NodeList certificate = child.getChildNodes();
					for (int k = 0; k < certificate.getLength(); k++) {
						Node cert = certificate.item(k);
						if (cert.getNodeName().equalsIgnoreCase("KeyStore")) {
							companyInfo.setKeyStore(cert.getTextContent());
						}
						if (cert.getNodeName().equalsIgnoreCase("Keypass")) {
							companyInfo.setKeyPass(cert.getTextContent());
						}
						if (cert.getNodeName().equalsIgnoreCase("TrustStore")) {
							companyInfo.setTrustStore(cert.getTextContent());
						}
						if (cert.getNodeName().equalsIgnoreCase("Trustpass")) {
							companyInfo.setTrustPass(cert.getTextContent());
						}
					}
				}
			}
			companyInfomations.add(companyInfo);
		}
		System.gc();
	}

	public XMLPath getConfigurations() {
		return this.configurations;
	}

	public void setConfigurations(XMLPath configurations) {
		this.configurations = configurations;
	}

	public List<CompanyInfomation> getCompanyInfomations() {
		return companyInfomations;
	}

	public void setCompanyInfomations(List<CompanyInfomation> companyInfomations) {
		this.companyInfomations = companyInfomations;
	}

}
