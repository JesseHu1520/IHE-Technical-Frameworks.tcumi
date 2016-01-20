package edu.tcu.mi.ihe;

import java.io.InputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.tcu.mi.ihe.security.CertificateDetails;
import edu.tcu.mi.ihe.utility.xml.XMLPath;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream configuration = loader
				.getResourceAsStream("configuration.xml");
		XMLPath configurations = new XMLPath(configuration);
		String expression = "Configuration/Connection[@name='合華-Security']/Certificate";
		Node node = configurations.QueryNode(expression);
		String KeyStore = "", Keypass = "", TrustStore = "", Trustpass = "";
		NodeList childNodes = node.getChildNodes();
		for (int k = 0; k < childNodes.getLength(); k++) {
			Node cert = childNodes.item(k);
			if (cert.getNodeName().equalsIgnoreCase("KeyStore")) {
				KeyStore = cert.getTextContent();
			}
			if (cert.getNodeName().equalsIgnoreCase("Keypass")) {
				Keypass = cert.getTextContent();
			}
			if (cert.getNodeName().equalsIgnoreCase("TrustStore")) {
				TrustStore = cert.getTextContent();
			}
			if (cert.getNodeName().equalsIgnoreCase("Trustpass")) {
				Trustpass = cert.getTextContent();
			}
		}
		CertificateDetails cert = CertificateDetails.getInstance();
		cert.setCertificate(KeyStore, Keypass, TrustStore, Trustpass);
	}
}
