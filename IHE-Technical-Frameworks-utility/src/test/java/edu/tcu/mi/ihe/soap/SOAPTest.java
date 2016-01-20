package edu.tcu.mi.ihe.soap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SOAPTest extends TestCase {
	public static Logger logger = Logger.getLogger(SOAPTest.class);

	public SOAPTest(String testName) {
		super(testName);
	}

	protected void setUp() {
	}

	public void test01() {
		sendSOAP();
	}

	public void sendSOAP() {
		try {
			String Endpoint = "http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl";
			MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
			SOAPMessage message = factory.createMessage();

			SOAPHeader header = message.getSOAPHeader();
			QName To = new QName(Endpoint, "To", "wsa");
			header.addHeaderElement(To);
			QName MessageID = new QName("urn:uuid:e475e8e5-6752-4270-a795-781e599fc2ee", "MessageID", "wsa");
			header.addHeaderElement(MessageID);
			QName Action = new QName("urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b", "Action", "wsa");
			header.addHeaderElement(Action);

			SOAPBody body = message.getSOAPBody();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder = dbFactory.newDocumentBuilder();

			ClassLoader loader = this.getClass().getClassLoader();
			InputStream is = loader.getResourceAsStream("1387554977578_iti_41_body.xml");
			Document document = builder.parse(is);
			body.addDocument(document);

			java.net.URL endpoint = new URL(Endpoint);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnectionFactory.createConnection();
			SOAPMessage response = connection.call(message, endpoint);

			connection.close();
			Iterator iterator = response.getSOAPBody().getChildElements();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
